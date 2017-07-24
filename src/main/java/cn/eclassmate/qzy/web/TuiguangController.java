package cn.eclassmate.qzy.web;

import cn.eclassmate.qzy.domain.User;
import cn.eclassmate.qzy.global.Cst;
import cn.eclassmate.qzy.service.TuiGuangService;
import cn.eclassmate.qzy.viewmodel.TuiGuangModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;

/**
 * 抢作业
 * 包括：抢作业，做题，显示解析的逻辑
 *
 * @author stao
 */
@Controller
@RequestMapping("user/tuiguang")
public class TuiguangController extends AbstractController
{
    private static final Logger logger = LogManager.getLogger();

    // model
    // --------------------------------------------------------------------------------
    private static final Class<TuiGuangModel> modelClass = TuiGuangModel.class;

    // pages
    // --------------------------------------------------------------------------------
    private static final String ALREADY_GRAB_TUIGUANG_BAG = "user/tuiguang/AlreadyGrabTuiGuangBag";
    private static final String FIRST_GRAB_TUIGUANG_BAG = "user/tuiguang/FirstGrabTuiGuangBag";
    private static final String ANSWERING_TUIGUANG_BAG = "user/tuiguang/AnsweringTuiguangBag";
    private static final String MONEY_INFO_VIEW = "user/tuiguang/subview/MoneyInfo";

    // service
    // --------------------------------------------------------------------------------
    @Autowired
    TuiGuangService tuiGuangService;

    // handler
    // --------------------------------------------------------------------------------
    @RequestMapping("startgrab")
    public View startGrab(HttpServletRequest request)
    {
        User user = getCurrentUser(request);
        if (user != null)
        {
            if (user.getTuiGuangStatus() == 0)
            {
                return getJspView(FIRST_GRAB_TUIGUANG_BAG);
            }
            else
            {
                return getJspView(ALREADY_GRAB_TUIGUANG_BAG);
            }
        }
        else
        {
            String callback = Cst.DOMAIN_NAME + "/migu/user/tuiguang/startgrab";
            return authEnsureFullAndCallback(request, callback);
        }
    }

    @RequestMapping("gotoanswering")
    public View gotoAnswering(HttpServletRequest request)
    {
        User user = getCurrentUser(request);
        if (user != null)
        {
            if (user.getTuiGuangStatus() != 0)
            {
                return getJspView(ALREADY_GRAB_TUIGUANG_BAG);
            }
            else
            {
                return getJspView(ANSWERING_TUIGUANG_BAG);
            }
        }
        else
        {
            String callback = Cst.DOMAIN_NAME + "/migu/user/tuiguang/startgrab";
            return authEnsureFullAndCallback(request, callback);
        }
    }

    @RequestMapping("submittuiguangbag")
    public View submitTuiGuangBag(HttpServletRequest request)
    {
        User user = getCurrentUser(request);
        if (user != null)
        {
            TuiGuangModel tgModel = getCurrentModel(request, modelClass);

            if (user.getTuiGuangStatus() == 1)
            {
                return getJspView(ALREADY_GRAB_TUIGUANG_BAG);
            }

            tuiGuangService.addTuiGuangMoney(tgModel);
            setCurrentModel(request, tgModel);

            return getJspView(MONEY_INFO_VIEW);
        }
        else
        {
            String callback = Cst.DOMAIN_NAME + "/migu/user/tuiguang/startgrab";
            return authEnsureFullAndCallback(request, callback);
        }
    }

}
