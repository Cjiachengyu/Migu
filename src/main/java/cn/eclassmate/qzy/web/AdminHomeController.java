package cn.eclassmate.qzy.web;

import cn.eclassmate.qzy.domain.Admin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("admin/home")
public class  AdminHomeController extends AbstractController
{

    // page
    // --------------------------------------------------------------------------------
    private static final String HOME_INDEX = "admin/AdminHomeIndex";

    // handler
    // --------------------------------------------------------------------------------
    @RequestMapping("index")
    public View index(HttpServletRequest request) throws Exception
    {
        return getJspView(HOME_INDEX);
    }

    @RequestMapping("login")
    public View login(HttpServletRequest request, String account, String password) throws Exception
    {
        Admin admin = basicService.managerLogin(account, password);
        if (admin != null)
        {
            request.getSession().setAttribute("admin", admin);

            if ("123456".equals(admin.getPassword()))
            {
                return getTextPlainView("simplepwd");
            }
            else
            {
                return getTextPlainView("ok");
            }
        }

        return getTextPlainView("error");
    }

    @RequestMapping("logout")
    public View logout(HttpServletRequest request) throws Exception
    {
        request.getSession().invalidate();
        return getJspView(HOME_INDEX);
    }

}
