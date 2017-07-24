package cn.eclassmate.qzy.web;

import cn.eclassmate.qzy.domain.Admin;
import cn.eclassmate.qzy.domain.Editor;
import cn.eclassmate.qzy.domain.User;
import cn.eclassmate.qzy.global.Cst;
import cn.eclassmate.qzy.global.Glb;
import cn.eclassmate.qzy.global.Utl;
import cn.eclassmate.qzy.service.BasicService;
import cn.eclassmate.qzy.service.WeixinService;
import cn.eclassmate.qzy.viewmodel.AbstractModel;
import cn.eclassmate.qzy.viewmodel.mobile.BasicMobileResult;

import com.google.gson.Gson;
import com.qq.connect.QQConnectException;
import com.qq.connect.oauth.Oauth;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidParameterException;
import java.util.Map;

/**
 * Created by TAO on 2015/5/11.
 */
public class AbstractController
{
    private static final Logger logger = LogManager.getLogger();

    @Autowired
    BasicService basicService;

    // 登录
    // --------------------------------------------------------------------------------
    protected void doLoginSetUser(HttpServletRequest request, User user)
    {
        request.getSession().setAttribute("user", user);  // login by debug
        user.setTodayCreatedBagCount(basicService.getCreateBagCount(user.getUserId()));

        synchronized (Glb.userIdUserMap)
        {
            Glb.userIdUserMap.put(user.getUserId(), user);
        }
    }

    // model
    // --------------------------------------------------------------------------------
    protected <T extends AbstractModel> T getCurrentModel(HttpServletRequest request, Class<T> modelClass)
    {
        try
        {
            T model = (T) request.getSession().getAttribute(modelClass.getName());
            if (model == null)
            {
                model = modelClass.newInstance();
                request.getSession().setAttribute(model.getClass().getName(), model);
            }

            model.setUser(getCurrentUser(request));
            model.setEditor(getCurrentEditor(request));
            model.setAdmin(getCurrentAdmin(request));

            return model;
        }
        catch (InstantiationException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }

        throw new InvalidParameterException();
    }

    protected void setCurrentModel(HttpServletRequest request, AbstractModel model)
    {
        request.getSession().setAttribute(model.getClass().getName(), model);
        request.setAttribute("viewModel", model);
        request.setAttribute("currentTime", Utl.currentSeconds());
    }

    // 授权登录
    // --------------------------------------------------------------------------------
    protected View authForceFullAndCallback(HttpServletRequest request)
    {
        String callbackUrl = request.getRequestURL().toString();
        return doAuthAndCallback(request, callbackUrl, Cst.WeixinAuthType.FORCE_FULL);
    }

    protected View authForceFullAndCallback(HttpServletRequest request, String callbackUrl)
    {
        return doAuthAndCallback(request, callbackUrl, Cst.WeixinAuthType.FORCE_FULL);
    }

    protected View authEnsureFullAndCallback(HttpServletRequest request)
    {
        String callbackUrl = request.getRequestURL().toString();
        return doAuthAndCallback(request, callbackUrl, Cst.WeixinAuthType.ENSURE_FULL);
    }

    protected View authEnsureFullAndCallback(HttpServletRequest request, String callbackUrl)
    {
        return doAuthAndCallback(request, callbackUrl, Cst.WeixinAuthType.ENSURE_FULL);
    }

    protected View authSimpleAndCallback(HttpServletRequest request)
    {
        String callbackUrl = request.getRequestURL().toString();
        return doAuthAndCallback(request, callbackUrl, Cst.WeixinAuthType.SIMPLE);
    }

    protected View authSimpleAndCallback(HttpServletRequest request, String callbackUrl)
    {
        return doAuthAndCallback(request, callbackUrl, Cst.WeixinAuthType.SIMPLE);
    }

    // 常见返回页面
    // --------------------------------------------------------------------------------
    protected View getJspView(String jsp)
    {
        return new InternalResourceView("/WEB-INF/jsp/" + jsp + ".jsp");
    }

    protected View getRedirectView(String gotoUrl)
    {
        return new RedirectView(gotoUrl, false);
    }

    protected View getJsonView(final String json)
    {
        return getTextView(json, "json");
    }

    protected View getXmlView(final String xml)
    {
        return getTextView(xml, "xml");
    }

    protected View getTextPlainView(final String text)
    {
        return getTextView(text, "plain");
    }

    protected View getMobileResultView(HttpServletRequest request, BasicMobileResult result)
    {
        if ("true".equalsIgnoreCase(request.getHeader("UseGzip")))
        {
            return getGzipView(new Gson().toJson(result));
        }
        else
        {
            return getTextView(new Gson().toJson(result), "json");
        }
    }

    // 常见角色的返回页面
    // --------------------------------------------------------------------------------
    protected View getEditorLogoutView()
    {
        return new RedirectView(Cst.DOMAIN_NAME + "/migu/editor/home/logout", false);
    }

    protected View getEditorTimoutView()
    {
        return getTextPlainView("timeout");
    }

    protected View getAdminLogoutView()
    {
        return new RedirectView(Cst.DOMAIN_NAME + "/migu/admin/home/logout", false);
    }

    protected View getAdminTimoutView()
    {
        return getTextPlainView("timeout");
    }

    // 常见对象和值
    // --------------------------------------------------------------------------------
    protected User getCurrentUser(HttpServletRequest request)
    {
        return (User) request.getSession().getAttribute("user");
    }

    protected Editor getCurrentEditor(HttpServletRequest request)
    {
        return (Editor) request.getSession().getAttribute("editor");
    }

    protected Admin getCurrentAdmin(HttpServletRequest request)
    {
        return (Admin) request.getSession().getAttribute("admin");
    }

    protected boolean isFromWeixin(HttpServletRequest request)
    {
        Boolean isFromWeixin = (Boolean) request.getAttribute("isFromWeixin");
        return isFromWeixin != null && isFromWeixin;
    }

    protected boolean isFromQQ(HttpServletRequest request)
    {
        Boolean isFromQQ = (Boolean) request.getAttribute("isFromQQ");
        return isFromQQ != null && isFromQQ;
    }

    protected boolean isFromClient(HttpServletRequest request)
    {
        Boolean isFromClient = (Boolean) request.getAttribute("isFromClient");
        return isFromClient != null && isFromClient;
    }

    protected boolean isInvalidClient(HttpServletRequest request)
    {
        Boolean isInvalidClient = (Boolean) request.getAttribute("isInvalidClient");
        return isInvalidClient != null && isInvalidClient;
    }

    protected boolean isJustShare(HttpServletRequest request)
    {
        String sourceType = (String) request.getSession().getAttribute("just_created_share_type");
        boolean isJustShare = Utl.stringNotEmpty(sourceType);
        if (isJustShare)
        {
            request.getSession().setAttribute("just_created_share_type", "");
        }
        return isJustShare;
    }

    // private
    // --------------------------------------------------------------------------------
    private View getTextView(final String text, final String format)
    {
        return new View()
        {
            @Override
            public String getContentType()
            {
                return format;
            }

            @Override
            public void render(Map<String, ?> map, HttpServletRequest httpServletRequest,
                    HttpServletResponse httpServletResponse) throws Exception
            {
                httpServletResponse.setContentType("text/" + format + ";charset=utf-8");
                httpServletResponse.getOutputStream().write(text.getBytes("UTF-8"));
            }
        };
    }

    private View getGzipView(final String text)
    {
        return new View()
        {
            @Override
            public String getContentType()
            {
                return "gzip";
            }

            @Override
            public void render(Map<String, ?> map, HttpServletRequest httpServletRequest,
                    HttpServletResponse httpServletResponse) throws Exception
            {
                httpServletResponse.setContentType("application/x-gzip");
                httpServletResponse.getOutputStream().write(Utl.getGzippedBytes(text.getBytes("UTF-8")));
            }
        };
    }

    private static String getWeixinAuthBaseUrl(String callbackUrl, String authCallbackUrl)
    {
        String weixinRedirectUrl = null;
        try
        {
            weixinRedirectUrl = "https://open.weixin.qq.com/connect/oauth2/authorize"
                    + "?appid=" + WeixinService.CONSTANT_APPID
                    + "&redirect_uri=" + URLEncoder.encode(authCallbackUrl, "utf-8")
                    + "&response_type=code"
                    + "&scope=snsapi_base"
                    + "&state=" + URLEncoder.encode(callbackUrl, "utf-8")
                    + "#wechat_redirect";
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        // 当前地址 --重定向--> weixinRedirectUrl --重定向--> authCallbackUrl --重定向--> callbackUrl
        logger.info("sending redirect to: " + weixinRedirectUrl + " to redirect to authCallbackUrl");
        logger.info("weixin will callback: " + authCallbackUrl + " to do auth");
        logger.info("after do auth, callback: " + callbackUrl);

        return weixinRedirectUrl;
    }

    private static String getWeixinAuthFullUrl(String callbackUrl, String authCallbackUrl)
    {
        String weixinRedirectUrl = null;
        try
        {
            weixinRedirectUrl = "https://open.weixin.qq.com/connect/oauth2/authorize"
                    + "?appid=" + WeixinService.CONSTANT_APPID
                    + "&redirect_uri=" + URLEncoder.encode(authCallbackUrl, "utf-8")
                    + "&response_type=code"
                    + "&scope=snsapi_userinfo"
                    + "&state=" + URLEncoder.encode(callbackUrl, "utf-8")
                    + "#wechat_redirect";
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        // 当前地址 --重定向--> weixinRedirectUrl --重定向--> authCallbackUrl --重定向--> callbackUrl
        logger.debug("sending redirect to: " + weixinRedirectUrl + " to redirect to authCallbackUrl");
        logger.debug("weixin will callback: " + authCallbackUrl + " to do auth");
        logger.debug("after do auth, callback: " + callbackUrl);

        return weixinRedirectUrl;
    }

    private View doAuthAndCallback(HttpServletRequest request, String callbackUrl, int weixinAuthType)
    {
        if (Cst.DEBUG_MODE)
        {
            User user = basicService.getUser(10002);
            doLoginSetUser(request, user);  // debug login

            return new RedirectView(callbackUrl, false);
        }

        if (isFromWeixin(request))
        {
            logger.info("do auth by weixin");
            return doAuthByWeixin(callbackUrl, weixinAuthType);
        }
        else if (isFromQQ(request))
        {
            logger.info("do auth by qq");
            return doAuthByQQ(request, callbackUrl);
        }
        else
        {
            logger.info("is from invalid client, do auth by login");
            // throw new InvalidParameterException("invalid client");
            return new RedirectView(Cst.DOMAIN_NAME + "/migu/user/home/login", false);
        }
    }

    private View doAuthByWeixin(String callbackUrl, int weixinAuthType)
    {
        if (weixinAuthType == Cst.WeixinAuthType.FORCE_FULL)
        {
            String authCallbackUrl = Cst.DOMAIN_NAME + "/migu/weixin/auth/forcefull";
            return getRedirectView(getWeixinAuthFullUrl(callbackUrl, authCallbackUrl));
        }
        else if (weixinAuthType == Cst.WeixinAuthType.ENSURE_FULL)
        {
            String authCallbackUrl = Cst.DOMAIN_NAME + "/migu/weixin/auth/ensurefull";
            return getRedirectView(getWeixinAuthBaseUrl(callbackUrl, authCallbackUrl));
        }
        else
        // simple
        {
            String authCallbackUrl = Cst.DOMAIN_NAME + "/migu/weixin/auth/simple";
            return getRedirectView(getWeixinAuthBaseUrl(callbackUrl, authCallbackUrl));
        }
    }

    private View doAuthByQQ(HttpServletRequest request, String callbackUrl)
    {
        // 先尝试从Cookie里获取用户信息
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0)
        {
            for (int i = 0; i < cookies.length; i++)
            {
                Cookie cookie = cookies[i];
                if ("user_qq_open_id".equals(cookie.getName()))
                {
                    logger.info("do auth by qq from cache");
                    User user = basicService.getUserByQQOpenId(cookie.getValue());
                    logger.info("CookieName" + cookie.getName() + "CookieValue" + cookie.getValue());
                    doLoginSetUser(request, user);  // qq login from cookie
                    return new RedirectView(callbackUrl, false);
                }
            }
        }

        // 把回调地址存入session，等用户信息获取之后重定向到这个地址
        request.getSession().setAttribute("qqconnectauthcallbackgotourl", callbackUrl);

        try
        {
            // 先尝试直接跳转
            String authorizeURL = new Oauth().getAuthorizeURL(request);
            logger.info("goto authorizeURL: " + authorizeURL);
            return new RedirectView(authorizeURL, false);
        }
        catch (QQConnectException e)
        {
            // 如果出错，再重定向到标准的授权界面，一般不会用到
            logger.info("QQConnectException: " + e.toString());
            e.printStackTrace();
            return new RedirectView(Cst.DOMAIN_NAME + "/migu/qqconnect/auth", false);
        }
    }

}
