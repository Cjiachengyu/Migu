package cn.eclassmate.qzy.service;

import cn.eclassmate.qzy.domain.Bill;
import cn.eclassmate.qzy.domain.User;
import cn.eclassmate.qzy.exception.WeixinAuthException;
import cn.eclassmate.qzy.global.Cst;
import cn.eclassmate.qzy.global.Utl;
import cn.eclassmate.qzy.viewmodel.weixin.ShareSignature;
import cn.eclassmate.qzy.viewmodel.weixin.TextMessage;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import java.io.Writer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.Formatter;
import java.util.Map;
import java.util.UUID;

@Service
public class WeixinService extends AbstractService
{
    private static final long serialVersionUID = 2001305895332895165L;
    private static final Logger logger = LogManager.getLogger();

    // 公众号常量
    public static final String CONSTANT_TOKEN = "31C7817B6926";
    public static final String CONSTANT_APPID = "wx8b1a17e1d205fc6a";
    public static final String CONSTANT_APP_SECRET = "8b54b2854e68435f71a460e6f8296a54";

    // 重试次数
    private static final int MAX_TRY_COUNT = 5;
    private static final int SLEEP_MILL_SEC = 500;

    // 返回常量
    private static String CONSTANT_SUBSCRIBE = "Hi~终于等到你！本应用由时尚时尚最时尚的“抢作业”提供。题海茫茫，你主沉浮。别人刷题你赢币，学霸给你当苦力！猛戳下方菜单，刷题赢币动起来！"
            + "\n\n恭喜您获得公众号大大大红包，参与答题即可领取($_$)~~"
            + "\n\n→→<a href='http://www.qzuoye.com/migu/user/tuiguang/startgrab'>点我领取</a>←←";
    private static String CONSTANT_EXCEPTION = "敬请期待！";

    // 抢作业
    // --------------------------------------------------------------------------------
    public User pullUserInfoForceFull(String code) throws Exception
    {
        AuthInfo authInfo = getAuthInfoFromCode(code);
        User user = getUserFromDb(authInfo.openId, authInfo.unionId);

        // 拉取用户信息
        JSONObject userInfoJO = getAuthUserInfo(authInfo.authToken, authInfo.openId, MAX_TRY_COUNT, SLEEP_MILL_SEC);
        logger.debug("got user info");

        if (user == null)
        {
            user = new User();
            user.setOpenId(authInfo.openId);
            user.setUnionId(authInfo.unionId);
            user.setMoney(Cst.Money.USER_RIGISTER_MONEY);

            user.setRegisterTime(Utl.currentSeconds());
            user.setLastLoginTime(Utl.currentSeconds());
            user.setUpdateWeixinTime(Utl.currentSeconds());

            setUserInfoFields(user, userInfoJO);
            userMapper.insertUser(user);
            insertRegisterBill(user);
        }
        else
        {
            user.setLastLoginTime(Utl.currentSeconds());
            user.setUpdateWeixinTime(Utl.currentSeconds());

            setUserInfoFields(user, userInfoJO);
            userMapper.updateUser(user);
        }

        return user;
    }

    public User pullUserInfoEnsureFull(String code) throws Exception
    {
        AuthInfo authInfo = getAuthInfoFromCode(code);
        User user = getUserFromDb(authInfo.openId, authInfo.unionId);

        if (user == null || !user.getIsUserInfoFull())
        {
            return null;
        }
        else
        {
            user.setLastLoginTime(Utl.currentSeconds());
            userMapper.updateUser(user);
            return user;
        }
    }

    public User pullUserInfoSimple(String code) throws Exception
    {
        AuthInfo authInfo = getAuthInfoFromCode(code);
        User user = getUserFromDb(authInfo.openId, authInfo.unionId);

        if (user == null)
        {
            user = new User();
            user.setOpenId(authInfo.openId);
            user.setUnionId(authInfo.unionId);
            user.setMoney(Cst.Money.USER_RIGISTER_MONEY);

            user.setRegisterTime(Utl.currentSeconds());
            user.setLastLoginTime(Utl.currentSeconds());
            user.setUpdateWeixinTime(0);

            userMapper.insertUser(user);
            insertRegisterBill(user);
        }
        else
        {
            user.setLastLoginTime(Utl.currentSeconds());
            userMapper.updateUser(user);
        }
        return user;
    }

    public ShareSignature getShareSignature(String url) throws Exception
    {
        String ticket = getJsApiTicket(MAX_TRY_COUNT, SLEEP_MILL_SEC);
        String nonce_str = UUID.randomUUID().toString();
        String timestamp = Long.toString(System.currentTimeMillis() / 1000);

        String string1 = "jsapi_ticket=" + ticket
                + "&noncestr=" + nonce_str
                + "&timestamp=" + timestamp
                + "&url=" + url;

        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(string1.getBytes("UTF-8"));
        Formatter formatter = new Formatter();
        byte[] bytes = crypt.digest();
        for (byte b : bytes)
        {
            formatter.format("%02x", b);
        }
        String signature = formatter.toString();
        formatter.close();

        ShareSignature ss = new ShareSignature();
        ss.setAppId(CONSTANT_APPID);
        ss.setNonceStr(nonce_str);
        ss.setTimestamp(timestamp);
        ss.setSignature(signature);
        return ss;
    }

    // 公众平台
    // --------------------------------------------------------------------------------
    public boolean checkSignature(String signature, String timestamp, String nonce)
    {
        String[] arr = new String[] { CONSTANT_TOKEN, timestamp, nonce };
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++)
        {
            content.append(arr[i]);
        }

        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(content.toString().getBytes());

            String tempStr = byteToStr(digest);
            return tempStr.equalsIgnoreCase(signature);
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

        return false;
    }

    public String handleWeixinEvent(HttpServletRequest request, Map<String, String> requestParams) throws Exception
    {
        String returnString = "";

        String msgType = requestParams.get("MsgType");

        // 处理不同类型的消息
        if (msgType.equals(Cst.Weixin.REQ_MESSAGE_TYPE_EVENT))
        {
            returnString = handleEvent(request, requestParams);
        }
        else if (msgType.equals(Cst.Weixin.REQ_MESSAGE_TYPE_TEXT))
        {
            returnString = "你好，我是抢作业小二。感谢你找到我，有什么问题请尽情调戏我吧！\n"
                    + "猛戳下方菜单，有惊喜哦！\n"
                    + "↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓";
        }
        else
        {
            // 目前没有其它的类型
        }

        return returnString;
    }

    // private
    // --------------------------------------------------------------------------------
    // token缓存
    private static String access_token = null;
    private static long access_token_expire_time = 0;
    private static String jsapi_ticket = null;
    private static long jsapi_ticket_expire_time = 0;

    private String handleEvent(HttpServletRequest request, Map<String, String> requestMap) throws Exception
    {
        String fromUserName = requestMap.get("FromUserName");
        String toUserName = requestMap.get("ToUserName");
        String eventType = requestMap.get("Event");
        String respContent = CONSTANT_EXCEPTION;

        if (eventType.equalsIgnoreCase(Cst.Weixin.EVENT_TYPE_SUBSCRIBE))
        {
            respContent = CONSTANT_SUBSCRIBE;
        }
        else if (eventType.equalsIgnoreCase(Cst.Weixin.EVENT_TYPE_UNSUBSCRIBE))
        {
            respContent = CONSTANT_SUBSCRIBE;
        }
        else if (eventType.equalsIgnoreCase(Cst.Weixin.EVENT_TYPE_CLICK))
        {
            String eventKey = requestMap.get("EventKey");

            if (eventKey.equals("key_gywm"))
            {
                respContent = "南京微课云 - 抢作业！";
            }
        }

        TextMessage textMessage = new TextMessage();
        textMessage.setToUserName(fromUserName);
        textMessage.setFromUserName(toUserName);
        textMessage.setContent(respContent);
        textMessage.setCreateTime(new Date().getTime());
        textMessage.setMsgType(Cst.Weixin.RESP_MESSAGE_TYPE_TEXT);
        textMessage.setFuncFlag(0);

        return textMessageToXml(textMessage);
    }

    private String textMessageToXml(TextMessage textMessage)
    {
        xstream.alias("xml", textMessage.getClass());
        return xstream.toXML(textMessage);
    }

    private String byteToStr(byte[] byteArray)
    {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++)
        {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    private String byteToHexStr(byte mByte)
    {
        char[] Digit =
        { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0x0F];
        tempArr[1] = Digit[mByte & 0x0F];

        String s = new String(tempArr);
        return s;
    }

    private static XStream xstream = new XStream(new XppDriver()
    {
        public HierarchicalStreamWriter createWriter(Writer out)
        {
            return new PrettyPrintWriter(out)
            {
                boolean cdata = true;

                public void startNode(String name, Class clazz)
                {
                    super.startNode(name, clazz);
                }

                protected void writeText(QuickWriter writer, String text)
                {
                    if (cdata)
                    {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    }
                    else
                    {
                        writer.write(text);
                    }
                }
            };
        }
    });

    // 全局token
    // --------------------------------------------------------------------------------
    private String getAccessToken(int maxTryCount, int sleepMillSec) throws Exception
    {
        int triedCount = 0;
        while (true)
        {
            try
            {
                logger.debug("trying: " + (triedCount + 1));
                return getAccessToken();
            }
            catch (WeixinAuthException e)
            {
                Thread.sleep(sleepMillSec);
                triedCount++;
                if (triedCount > maxTryCount)
                {
                    logger.error("FAILED AFTER TRIED " + maxTryCount + " TIMES, ERRORMSG: " + e.getMessage());
                    logger.error(e);
                    throw e;
                }
            }
        }
    }

    private String getAccessToken() throws Exception
    {
        synchronized (WeixinService.class)
        {
            if (Utl.stringNotEmpty(access_token) && access_token_expire_time > System.currentTimeMillis())
            {
                return access_token;
            }

            String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
                    + CONSTANT_APPID + "&secret=" + CONSTANT_APP_SECRET;
            String ret = Utl.https(url);

            JSONObject obj = new JSONObject(ret);
            int errCode = obj.optInt("errcode");
            if (errCode != 0)
            {
                throw new WeixinAuthException("get access_token failed, errorCode: " + errCode);
            }

            access_token = obj.optString("access_token");
            access_token_expire_time = System.currentTimeMillis() + obj.optInt("expires_in") * 1000 - 1000 * 600;

            return access_token;
        }
    }

    private String getJsApiTicket(int maxTryCount, int sleepMillSec) throws Exception
    {
        int triedCount = 0;
        while (true)
        {
            try
            {
                logger.debug("trying: " + (triedCount + 1));
                return getJsApiTicket();
            }
            catch (WeixinAuthException e)
            {
                Thread.sleep(sleepMillSec);
                triedCount++;
                if (triedCount > maxTryCount)
                {
                    logger.error("FAILED AFTER TRIED " + maxTryCount + " TIMES, ERRORMSG: " + e.getMessage());
                    logger.error(e);
                    throw e;
                }
            }
        }
    }

    private String getJsApiTicket() throws Exception
    {
        synchronized (WeixinService.class)
        {
            if (Utl.stringNotEmpty(jsapi_ticket) && jsapi_ticket_expire_time > System.currentTimeMillis())
            {
                return jsapi_ticket;
            }

            String token = getAccessToken(MAX_TRY_COUNT, SLEEP_MILL_SEC);
            String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + token + "&type=jsapi";
            String ret = Utl.https(url);

            JSONObject obj = new JSONObject(ret);
            int errCode = obj.optInt("errcode");
            if (errCode != 0)
            {
                throw new WeixinAuthException("get jsapi_token failed, errorCode: " + errCode);
            }

            jsapi_ticket = obj.optString("ticket");
            jsapi_ticket_expire_time = System.currentTimeMillis() + obj.optInt("expires_in") * 1000 - 1000 * 600;

            return jsapi_ticket;
        }
    }

    // 获取用户的token
    // --------------------------------------------------------------------------------
    private JSONObject getAuthAccessToken(String code, int maxTryCount, int sleepMillSec) throws Exception
    {
        int triedCount = 0;
        while (true)
        {
            try
            {
                logger.debug("trying: " + (triedCount + 1));
                return getAuthAccessToken(code);
            }
            catch (WeixinAuthException e)
            {
                Thread.sleep(sleepMillSec);
                triedCount++;
                if (triedCount > maxTryCount)
                {
                    logger.error("FAILED AFTER TRIED " + maxTryCount + " TIMES, ERRORMSG: " + e.getMessage());
                    logger.error(e);
                    throw e;
                }
            }
        }
    }

    private JSONObject getAuthAccessToken(String code) throws Exception
    {
        logger.debug("requesting auth_token");
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + CONSTANT_APPID
                + "&secret=" + CONSTANT_APP_SECRET + "&code=" + code + "&grant_type=authorization_code";
        String authAccessToken = Utl.https(url);
        logger.info("request auth_token result: " + authAccessToken);

        JSONObject obj = new JSONObject(authAccessToken);
        int errCode = obj.optInt("errcode");
        if (errCode != 0)
        {
            throw new WeixinAuthException("get auth_access_token failed, errorCode: " + errCode);
        }
        return obj;
    }

    private JSONObject getAuthUserInfo(String authAccessToken, String openId, int maxTryCount, int sleepMillSec)
            throws Exception
    {
        int triedCount = 0;
        while (true)
        {
            try
            {
                logger.debug("trying: " + (triedCount + 1));
                return getAuthUserInfo(authAccessToken, openId);
            }
            catch (WeixinAuthException e)
            {
                Thread.sleep(sleepMillSec);
                triedCount++;
                if (triedCount > maxTryCount)
                {
                    logger.error("FAILED AFTER TRIED " + maxTryCount + " TIMES, ERRORMSG: " + e.getMessage());
                    logger.error(e);
                    throw e;
                }
            }
        }
    }

    private JSONObject getAuthUserInfo(String authAccessToken, String openId) throws Exception
    {
        logger.info("requesting user_info, auth_access_token: " + authAccessToken + ", openId: " + openId);
        String url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + authAccessToken
                + "&openid=" + openId + "&lang=zh_CN";
        String ret2 = Utl.https(url);
        logger.info("request user_info result: " + ret2);

        JSONObject obj = new JSONObject(ret2);
        int errCode = obj.optInt("errcode");
        if (errCode != 0)
        {
            throw new WeixinAuthException("get user_info failed, errorCode: " + errCode);
        }
        return obj;
    }

    // 拉取用户
    // --------------------------------------------------------------------------------
    private AuthInfo getAuthInfoFromCode(String code) throws Exception
    {
        // 通过code换取网页授权access_token和openid
        JSONObject accessTokenJO = getAuthAccessToken(code, MAX_TRY_COUNT, SLEEP_MILL_SEC);
        String authToken = accessTokenJO.optString("access_token");
        String openId = accessTokenJO.optString("openid");
        String unionId = accessTokenJO.optString("unionid");
        logger.debug("authToken = " + authToken + ", openId = " + openId + ", unionId = " + unionId);

        AuthInfo authInfo = new AuthInfo();
        authInfo.authToken = authToken;
        authInfo.openId = openId;
        authInfo.unionId = unionId;
        return authInfo;
    }

    private User getUserFromDb(String openId, String unionId)
    {
        // 先尝试在数据库获取用户
        User user = userMapper.getUserByOpenId(openId);
        if (user == null && Utl.stringNotEmpty(unionId))
        {
            user = userMapper.getUserByUnionId(unionId);
        }
        else if (user != null && Utl.stringNotEmpty(unionId))
        {
            User dupUser = userMapper.getUserByUnionId(unionId);
            if (dupUser != null && dupUser.getUserId() != user.getUserId())
            {
                dupUser.setUnionId(null);
                dupUser.setOpenId(null);
                dupUser.setQqOpenId(null);
                userMapper.updateUser(dupUser);
            }
        }

        if (user != null)
        {
            user.setOpenId(openId);
            if (Utl.stringEmpty(unionId))
            {
                unionId = null;
            }
            user.setUnionId(unionId);
        }

        return user;
    }

    private void setUserInfoFields(User user, JSONObject userInfoJO)
    {
        String name = userInfoJO.optString("nickname");
        int gender = userInfoJO.optInt("sex");
        String portrait = userInfoJO.optString("headimgurl");
        String country = userInfoJO.optString("country");
        String province = userInfoJO.optString("province");
        String city = userInfoJO.optString("city");

        name = Utl.validateUtf8(name);
        if (Utl.stringEmpty(name))
        {
            name = "无名氏";
        }

        user.setUserName(name);
        user.setGender(gender);
        user.setCity(city);
        user.setProvince(province);
        user.setCountry(country);
        user.setPortrait(portrait);
    }

    private void insertRegisterBill(User user)
    {
        Bill bill = new Bill();
        bill.setBillType(Cst.BillType.SYSTEM_GIVE);
        bill.setUserId(user.getUserId());
        bill.setMoney(Cst.Money.USER_RIGISTER_MONEY);
        bill.setCreateTime(Utl.currentSeconds());
        bill.setDescription("欢迎加入抢作业，系统赠送" + bill.getMoney() + "作业币！");
        bagMapper.insertBill(bill);
    }

    private static class AuthInfo
    {
        String authToken;
        String unionId;
        String openId;
    }

}
