package cn.eclassmate.qzy.web;

import cn.eclassmate.qzy.domain.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("admin/setting")
public class AdminSettingController extends AbstractController
{
    // page
    // --------------------------------------------------------------------------------
    private static final String ADMIN_SETTING = "admin/AdminSetting";

    // handler
    // --------------------------------------------------------------------------------
    @RequestMapping("changepwd")
    public View changePwd(HttpServletRequest request) throws Exception
    {
        if (getCurrentAdmin(request) == null)
        {
            return getAdminLogoutView();
        }

        return getJspView(ADMIN_SETTING);
    }

    @RequestMapping("dochangepwd")
    public View doChangePwd(HttpServletRequest request, String oldPwd, String newPwd) throws Exception
    {
        if (getCurrentAdmin(request) == null)
        {
            return getAdminTimoutView();
        }

        Admin admin = getCurrentAdmin(request);
        if (!admin.getPassword().equals(oldPwd))
        {
            return getTextPlainView("notequal");
        }
        else if (newPwd == null || newPwd.length() < 6)
        {
            return getTextPlainView("tooshort");
        }
        else
        {
            admin.setPassword(newPwd);
            basicService.updateAdmin(admin);

            return getTextPlainView("ok");
        }
    }
}
