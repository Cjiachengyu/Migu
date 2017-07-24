package cn.eclassmate.qzy.web;

import cn.eclassmate.qzy.domain.User;
import cn.eclassmate.qzy.exception.WeixinAuthException;
import cn.eclassmate.qzy.global.Utl;
import cn.eclassmate.qzy.service.WeixinService;
import cn.eclassmate.qzy.viewmodel.weixin.ShareSignature;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WeixinController extends AbstractController
{
    private static final Logger logger = LogManager.getLogger();

    // service
    // --------------------------------------------------------------------------------
    @Autowired
    WeixinService weixinService;

    // 公众号
    // --------------------------------------------------------------------------------
    @RequestMapping(value = "weixin.action", method = RequestMethod.GET)
    public View weixinMainGet(String signature, String timestamp, String nonce, String echostr)
    {
        if (weixinService.checkSignature(signature, timestamp, nonce))
        {
            return getTextPlainView(echostr);
        }
        else
        {
            logger.error("weixin main validate failed");
            return getTextPlainView("");
        }
    }

    @RequestMapping(value = "weixin.action", method = RequestMethod.POST)
    public View weixinMainPost(HttpServletRequest request) throws Exception
    {
        // 获取xml文档对象
        request.setCharacterEncoding("UTF-8");
        InputStream inputStream = request.getInputStream();
        Document document = (new SAXReader()).read(inputStream);

        // 遍历获取参数
        Map<String, String> requestParams = new HashMap<String, String>();
        Element root = document.getRootElement();
        List<Element> elementlist = root.elements();
        for (Element e : elementlist)
        {
            requestParams.put(e.getName(), e.getText());
        }
        inputStream.close();

        String returnString = weixinService.handleWeixinEvent(request, requestParams);
        return getXmlView(returnString);
    }

    // 授权登录
    // --------------------------------------------------------------------------------
    @RequestMapping("weixin/auth/forcefull")
    public View authForceFull(HttpServletRequest request, String code, String state) throws Exception
    {
        String callbackUrl = checkRequestAndGetCallbackUrl(code, state);

        User user = weixinService.pullUserInfoForceFull(code);

        doLoginSetUser(request, user);  // weixin login from base_user_info
        return getRedirectView(callbackUrl);
    }

    @RequestMapping("weixin/auth/ensurefull")
    public View authEnsureFull(HttpServletRequest request, String code, String state) throws Exception
    {
        String callbackUrl = checkRequestAndGetCallbackUrl(code, state);

        User user = weixinService.pullUserInfoEnsureFull(code);
        if (user == null)
        {
            request.setAttribute("isFromWeixin", true);
            return authForceFullAndCallback(request, callbackUrl);
        }

        doLoginSetUser(request, user);  // weixin login from full_user_info
        return getRedirectView(callbackUrl);
    }

    @RequestMapping("weixin/auth/simple")
    public View authSimple(HttpServletRequest request, String code, String state) throws Exception
    {
        String callbackUrl = checkRequestAndGetCallbackUrl(code, state);

        User user = weixinService.pullUserInfoSimple(code);

        doLoginSetUser(request, user);  // weixin login from base_user_info
        return getRedirectView(callbackUrl);
    }

    // 微信分享，获取签名
    // --------------------------------------------------------------------------------
    @RequestMapping("weixin/getsignature")
    public View getSignature(HttpServletRequest request, String url) throws Exception
    {
        ShareSignature shareSignature = weixinService.getShareSignature(url);

        Map<String, String> map = new HashMap<String, String>();
        map.put("appId", shareSignature.getAppId());
        map.put("timestamp", shareSignature.getTimestamp());
        map.put("nonceStr", shareSignature.getNonceStr());
        map.put("signature", shareSignature.getSignature());
        JSONObject obj = new JSONObject(map);

        return getJsonView(obj.toString());
    }

    // private
    // --------------------------------------------------------------------------------
    private String checkRequestAndGetCallbackUrl(String code, String state) throws WeixinAuthException
    {
        if (Utl.stringEmpty(code))
        {
            throw new WeixinAuthException("code invalid, return");
        }

        if (Utl.stringEmpty(state))
        {
            throw new WeixinAuthException("state invalid, return");
        }

        try
        {
            return URLDecoder.decode(state, "utf-8");
        }
        catch (UnsupportedEncodingException e)
        {
            throw new WeixinAuthException("encode invalid, return");
        }
    }

}
