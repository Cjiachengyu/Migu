package cn.eclassmate.qzy.web;

import cn.eclassmate.qzy.domain.User;
import cn.eclassmate.qzy.global.Cst;
import cn.eclassmate.qzy.global.Utl;
import cn.eclassmate.qzy.service.UserCreateBagService;
import cn.eclassmate.qzy.viewmodel.UserCreateBagModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("user/createbag")
public class UserCreateBagController extends AbstractController
{
    private static final Logger logger = LogManager.getLogger();

    // pages
    // --------------------------------------------------------------------------------
    private static final String CREATE_BAG_STEP1 = "user/createbag/Step1";
    private static final String CREATE_BAG_STEP2 = "user/createbag/Step2";
    private static final String CREATE_BAG_SHUATI = "user/createbag/Shuati";

    private static final String COUNT_USE_UP = "user/createbag/CountUseUp";

    // model
    // --------------------------------------------------------------------------------
    private static final Class<UserCreateBagModel> modelClass = UserCreateBagModel.class;

    // service
    // --------------------------------------------------------------------------------
    @Autowired
    UserCreateBagService userCreateBagService;

    // handler
    // --------------------------------------------------------------------------------
    @RequestMapping("step1")
    public View step1(HttpServletRequest request) throws Exception
    {
        User user = getCurrentUser(request);

        if (user != null)
        {
            int createBagCount = user.getTodayCreatedBagCount();
            if (createBagCount >= 3) //红包限制次数
            {
                return getJspView(COUNT_USE_UP);
            }

            UserCreateBagModel ucbModel = getCurrentModel(request, modelClass);
            userCreateBagService.initUcbModel(ucbModel);

            setCurrentModel(request, ucbModel);
            return getJspView(CREATE_BAG_STEP1);
        }
        else
        {
            logger.info("user is null, call weixin auth to get user info. ");
            return authEnsureFullAndCallback(request);
        }
    }

    @RequestMapping("step2")
    public View step2(HttpServletRequest request, int zsk, int zsd1, int zsd2, int difficulty) throws Exception
    {
        UserCreateBagModel ucbModel = getCurrentModel(request, modelClass);
        userCreateBagService.selectQueListForBag(ucbModel, zsk, zsd1, zsd2, 3);

        setCurrentModel(request, ucbModel);
        return getJspView(CREATE_BAG_STEP2);
    }

    @RequestMapping("zhuanfabag")
    public View zhuanFaBag(HttpServletRequest request) throws Exception
    {
        UserCreateBagModel ucbModel = getCurrentModel(request, modelClass);
        int bagId = (Integer) request.getSession().getAttribute("zhuanFaBagId");
        userCreateBagService.setZhuanFaBag(ucbModel, bagId);

        setCurrentModel(request, ucbModel);
        return getJspView(CREATE_BAG_STEP2);
    }

    @RequestMapping("zhuanfabag2")
    public View zhuanFaBag2(HttpServletRequest request, int bagId) throws Exception
    {
        UserCreateBagModel ucbModel = getCurrentModel(request, modelClass);

        userCreateBagService.setZhuanFaBag2(ucbModel, bagId);
        setCurrentModel(request, ucbModel);

        request.getSession().setAttribute("just_created_share_type", "grabbag");
        return getRedirectView(Cst.DOMAIN_NAME + "/migu/user/grabbag/" + ucbModel.getBag().getBagId() + "/startgrab");
    }

    @RequestMapping("step3")
    public View step3(HttpServletRequest request, int bagCount, int bagMoney, String bagMsg) throws Exception
    {
        UserCreateBagModel ucbModel = getCurrentModel(request, modelClass);

        int createBagCount = ucbModel.getUser().getTodayCreatedBagCount();
        if (createBagCount >= 3) //红包限制次数
        {
            return getJspView(COUNT_USE_UP);
        }

        userCreateBagService.doCreateBag(
                ucbModel, bagCount, bagMoney, bagMsg, Cst.BagType.PINSHOUQI, Cst.BagCategory.HONGBAO);

        setCurrentModel(request, ucbModel);
        request.getSession().setAttribute("just_created_share_type", "grabbag");
        return getRedirectView(Cst.DOMAIN_NAME + "/migu/user/grabbag/" + ucbModel.getBag().getBagId() + "/startgrab");
        // return getJspView(CREATE_BAG_STEP3); // 这个页面移到grabbag里去
    }

    @RequestMapping("createshuati")
    public View createShuati(HttpServletRequest request) throws Exception
    {
        User user = getCurrentUser(request);

        if (user != null)
        {
            UserCreateBagModel ucbModel = getCurrentModel(request, modelClass);
            userCreateBagService.initUcbModel(ucbModel);

            setCurrentModel(request, ucbModel);
            return getJspView(CREATE_BAG_SHUATI);
        }
        else
        {
            logger.info("user is null, call weixin auth to get user info. ");
            return authEnsureFullAndCallback(request);
        }
    }

    @RequestMapping("gotoshuati")
    public View gotoShuati(HttpServletRequest request, int zsk, int zsd1, int zsd2, int difficulty) throws Exception
    {
        String defaultName = Utl.getTimeString_MM_dd_HH_mm(Utl.currentSeconds()) + "刷题";

        UserCreateBagModel ucbModel = getCurrentModel(request, modelClass);
        userCreateBagService.selectQueListForBag(ucbModel, zsk, zsd1, zsd2, 5);
        userCreateBagService.doCreateBag(ucbModel, 0, 0, defaultName, 0, Cst.BagCategory.SHUATI);

        return getRedirectView(Cst.DOMAIN_NAME + "/migu/user/grabbag/" + ucbModel.getBag().getBagId() + "/startgrab");
    }

    @RequestMapping("recordcreatebagzskid")
    public View recordCreateBagZskId(HttpServletRequest request, Integer zskId) throws Exception
    {
        User user = getCurrentUser(request);
        if (user != null)
        {
            userCreateBagService.recordCreateBagZskId(user, zskId);

            return getTextPlainView("ok");
        }
        else
        {
            return authEnsureFullAndCallback(request);
        }
    }

}
