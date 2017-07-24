package cn.eclassmate.qzy.web;

import cn.eclassmate.qzy.domain.User;
import cn.eclassmate.qzy.global.Utl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("user/home")
public class UserHomeController extends AbstractController
{
    // page
    // --------------------------------------------------------------------------------
    private static final String USER_LOGIN = "user/home/Login";
    private static final String USER_INDEX = "user/home/Index";

    // handler
    // --------------------------------------------------------------------------------
    @RequestMapping("login")
    public View login(HttpServletRequest request) throws Exception
    {
        return getJspView(USER_LOGIN);
    }

    @RequestMapping("index")
    public View index(HttpServletRequest request) throws Exception
    {
        User user = getCurrentUser(request);

        // 客户端直接登录
        if (isFromClient(request) || "1".equals(request.getParameter("fromMobile")))
        {
            String loginType = request.getParameter("loginType");
            request.getSession().setAttribute("loginType", loginType);
            request.getSession().setAttribute("fromMobile", "1");

            if ("weixin".equals(loginType))
            {
                String unionId = request.getParameter("unionId");
                if (Utl.stringNotEmpty(unionId))
                {
                    if (user == null || !unionId.equals(user.getUnionId()))
                    {
                        User newUser = basicService.getUserByUnionId(unionId);
                        if (user == null || newUser.getUserId() != user.getUserId())
                        {
                            doLoginSetUser(request, newUser);   // client login from weixin
                        }
                    }
                }
            }
            else if ("qq".equals(loginType))
            {
                String qqOpenId = request.getParameter("openId");
                if (Utl.stringNotEmpty(qqOpenId))
                {
                    if (user == null || !qqOpenId.equals(user.getQqOpenId()))
                    {
                        User newUser = basicService.getUserByQQOpenId(qqOpenId);
                        if (user == null || newUser.getUserId() != user.getUserId())
                        {
                            doLoginSetUser(request, newUser);   // client login from qq
                        }
                    }
                }
            }
        }

        // 从公众平台登录
        if (user == null)
        {
            if (isFromWeixin(request))
            {
                return authEnsureFullAndCallback(request);
            }
        }

        return getJspView(USER_INDEX);
    }

}
