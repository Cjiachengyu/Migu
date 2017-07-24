package cn.eclassmate.qzy.web.mobile;

import cn.eclassmate.qzy.global.Cst;
import cn.eclassmate.qzy.service.mobile.UserLoginService;
import cn.eclassmate.qzy.viewmodel.mobile.BasicMobileResult;
import cn.eclassmate.qzy.viewmodel.mobile.UserLoginResult;
import cn.eclassmate.qzy.web.AbstractController;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;

/**
 * @author stao
 */
@Controller
@RequestMapping("mobile/user/login")
public class UserLoginController extends AbstractController
{
    private static final Logger logger = LogManager.getLogger();

    // service
    // --------------------------------------------------------------------------------
    @Autowired
    UserLoginService userLoginService;

    // handler
    // --------------------------------------------------------------------------------
    @RequestMapping("refreshuserweixininfo")
    public View refreshUserWeixinInfo(HttpServletRequest request, String unionId,
            String name, Integer gender, String portrait, String country, String province, String city)
    {
        try
        {
            userLoginService.refreshUserWeixinInfo(unionId, name, gender, portrait, country, province, city);
            return getMobileResultView(request, new UserLoginResult(Cst.MobileErrorCode.NoError));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return getMobileResultView(request, new BasicMobileResult(Cst.MobileErrorCode.ServerException));
        }
    }

    @RequestMapping("refreshuserqqinfo")
    public View refreshUserQQInfo(HttpServletRequest request, String openId,
            String name, Integer gender, String portrait, String province, String city)
    {
        try
        {
            userLoginService.refreshUserQqInfo(openId, name, gender, portrait, province, city);
            return getMobileResultView(request, new UserLoginResult(Cst.MobileErrorCode.NoError));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return getMobileResultView(request, new BasicMobileResult(Cst.MobileErrorCode.ServerException));
        }
    }

}

