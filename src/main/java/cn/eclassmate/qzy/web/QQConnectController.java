package cn.eclassmate.qzy.web;

import cn.eclassmate.qzy.domain.User;
import cn.eclassmate.qzy.global.Cst;
import cn.eclassmate.qzy.global.Utl;
import cn.eclassmate.qzy.service.QQConnectService;

import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("qqconnect")
public class QQConnectController extends AbstractController
{
    private static final Logger logger = LogManager.getLogger();

    // service
    // --------------------------------------------------------------------------------
    @Autowired
    QQConnectService qqConnectService;

    // handler
    // --------------------------------------------------------------------------------
    @RequestMapping("auth")
    public void auth(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        logger.info("enter qqconnect/auth");

        response.setContentType("text/html;charset=utf-8");

        // 删除cookie
        // Cookie openIDCookie = new Cookie("user_qq_open_id", null);
        // openIDCookie.setMaxAge(0);
        // response.addCookie(openIDCookie);

        String gotoUrl = new Oauth().getAuthorizeURL(request);
        response.sendRedirect(gotoUrl);

        logger.info("leave qqconnect/auth, gotoUrl = " + gotoUrl);
    }

    @RequestMapping("authcallback")
    public View authCallback(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        logger.info("enter qqconnect/authcallback");
        AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);
        if (Utl.stringEmpty(accessTokenObj.getAccessToken()))
        {
            logger.error("cannot get qq access_token, redo auth");
            return getRedirectView(Cst.DOMAIN_NAME + "/migu/qqconnect/auth");
        }

        String accessToken = accessTokenObj.getAccessToken();
        long tokenExpireIn = accessTokenObj.getExpireIn();
        logger.info("got access_token: " + accessToken);

        request.getSession().setAttribute("demo_access_token", accessToken);
        request.getSession().setAttribute("demo_token_expirein", String.valueOf(tokenExpireIn));

        // 利用获取到的accessToken 去获取当前用的openid
        OpenID openIDObj = new OpenID(accessToken);
        String openID = openIDObj.getUserOpenID();

        // 利用获取到的accessToken, openid去获取用户在Qzone的昵称等信息
        UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
        UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
        if (userInfoBean.getRet() != 0)
        {
            logger.error("cannot get user info, redo auth");
            return getRedirectView(Cst.DOMAIN_NAME + "/migu/qqconnect/auth");
        }

        String userName = userInfoBean.getNickname();
        String gender = userInfoBean.getGender();
        String protrait = userInfoBean.getAvatar().getAvatarURL100();
        logger.info("openId: " + openID + ", name: " + userName + ", gender: " + gender + ", protrait: " + protrait);
        User user = qqConnectService.updateUser(openID, userName, gender, protrait);
        doLoginSetUser(request, user);  // qq login from qq_connection_auth

        // 添加Cookie
        Cookie openIDCookie = new Cookie("user_qq_open_id", openID);
        openIDCookie.setMaxAge(365 * 24 * 3600); // 一年
        openIDCookie.setPath("/");
        openIDCookie.setDomain("qzuoye.com");
        response.addCookie(openIDCookie);
        logger.info("cookieName:" + openIDCookie.getName() + "cookieValue:" + openIDCookie.getValue());

        String gotoUrl = (String) request.getSession().getAttribute("qqconnectauthcallbackgotourl");
        request.getSession().removeAttribute("qqconnectauthcallbackgotourl");
        if (Utl.stringEmpty(gotoUrl))
        {
            gotoUrl = Cst.DOMAIN_NAME + "/migu/user/home/index";
        }

        return getRedirectView(gotoUrl);
    }
}
