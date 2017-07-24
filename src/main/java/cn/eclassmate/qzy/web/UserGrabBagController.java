package cn.eclassmate.qzy.web;

import cn.eclassmate.qzy.domain.User;
import cn.eclassmate.qzy.global.Cst;
import cn.eclassmate.qzy.service.UserGrabBagService;
import cn.eclassmate.qzy.viewmodel.UserGrabBagModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("user/grabbag")
public class UserGrabBagController extends AbstractController
{
    private static final Logger logger = LogManager.getLogger();

    // pages
    // --------------------------------------------------------------------------------
    // 我也抢一下
    private static final String ENTER_GRAB = "user/grabbag/EnterGrab";
    private static final String ENTER_TOO_LATE = "user/grabbag/EnterTooLate";
    private static final String ENTER_TIMEOUT = "user/grabbag/EnterTimeout";

    // 答题
    private static final String ANSWERING_GRAB = "user/grabbag/AnsweringGrab";
    private static final String ANSWERING_SHUATI = "user/grabbag/AnsweringShuati";

    // 抢红包结果
    private static final String GRAB_RESULT_RIGHT_WIN = "user/grabbag/GrabResultRightWin";
    private static final String GRAB_RESULT_RIGHT_SLOW = "user/grabbag/GrabResultRightSlow";
    private static final String GRAB_RESULT_WRONG = "user/grabbag/GrabResultWrong";

    // 练一练结果
    private static final String PRACTICE_RESULT_RIGHT = "user/grabbag/PracticeResultRight";
    private static final String PRACTICE_RESULT_WRONG = "user/grabbag/PracticeResultWrong";

    // 刷题结果
    private static final String SHUATI_RESULT_RIGHT = "user/grabbag/ShuatiResultRight";
    private static final String SHUATI_RESULT_WRONG = "user/grabbag/ShuatiResultWrong";

    // 查看解析
    private static final String VIEW_ANALYISIS_GRAB = "user/grabbag/ViewAnalysisGrab";
    private static final String VIEW_ANALYISIS_SHUATI = "user/grabbag/ViewAnalysisShuati";

    // 分享"抢红包"的页面
    private static final String SHARE_SHOW = "user/share/Share";//分享公共页面
    private static final String SHARE_SHOW_FOR_QQ_SERVER = "user/qqshare/QQShare";//qq分享公共页面

    // model
    // --------------------------------------------------------------------------------
    private static final Class<UserGrabBagModel> modelClass = UserGrabBagModel.class;

    // service
    // --------------------------------------------------------------------------------
    @Autowired
    UserGrabBagService userGrabBagService;

    // handler
    // --------------------------------------------------------------------------------
    @RequestMapping("{bagId}/startgrab")
    public View startGrab(HttpServletRequest request, @PathVariable("bagId") int bagId)
    {
        if (isInvalidClient(request))
        {
            UserGrabBagModel ugbModel = getCurrentModel(request, modelClass);
            userGrabBagService.initUserGrabBagModel(ugbModel, bagId);
            userGrabBagService.initShareInfo(ugbModel);
            setCurrentModel(request, ugbModel);

            return getJspView(SHARE_SHOW_FOR_QQ_SERVER);
        }

        User user = getCurrentUser(request);
        if (user != null)
        {
            // 获取Model，包括bag，里面的queList，有没有机会等信息
            UserGrabBagModel ugbModel = getCurrentModel(request, modelClass);
            userGrabBagService.initUserGrabBagModel(ugbModel, bagId);
            setCurrentModel(request, ugbModel);

            if (ugbModel.getBag().getBagCategory() == Cst.BagCategory.HONGBAO)
            {
                String justCreatedShareType = (String) request.getSession().getAttribute("just_created_share_type");
                if ("grabbag".equals(justCreatedShareType))
                {
                    request.getSession().setAttribute("just_created_share_type", "");

                    userGrabBagService.initShareInfo(ugbModel);
                    setCurrentModel(request, ugbModel);

                    return getJspView(SHARE_SHOW);
                }

                request.getSession().setAttribute("zhuanFaBagId", ugbModel.getBag().getBagId());
                switch (ugbModel.getChanceType())
                {
                case Cst.UserBagChanceType.SUBMITTED:
                    return getGrabResultView(ugbModel);

                case Cst.UserBagChanceType.TIMEOUT:
                    return getJspView(ENTER_TIMEOUT);

                case Cst.UserBagChanceType.HAS_CHANCE:
                    return getJspView(ENTER_GRAB);

                case Cst.UserBagChanceType.TOO_LATE:
                    return getJspView(ENTER_TOO_LATE);
                }
            }
            else if (ugbModel.getBag().getBagCategory() == Cst.BagCategory.SHUATI)
            {
                switch (ugbModel.getChanceType())
                {
                case Cst.UserBagChanceType.SUBMITTED:
                    return getGrabResultView(ugbModel);

                default:
                    return getJspView(ANSWERING_SHUATI);
                }
            }
            return null; // impossible
        }
        else
        {
            String callback = Cst.DOMAIN_NAME + "/migu/user/grabbag/" + bagId + "/startgrab";
            return authSimpleAndCallback(request, callback);
        }
    }

    @RequestMapping("{bagId}/gotoanswering")
    public View gotoAnswering(HttpServletRequest request, @PathVariable("bagId") int bagId)
    {
        UserGrabBagModel ugbModel = getCurrentModel(request, modelClass);
        if (!viewModelValid(ugbModel, bagId)
                || ugbModel.getUserBag().getUserBagStatus() != Cst.UserBagStatus.BEGIN_GRAB)
        {
            return redirectToMain(bagId);
        }

        setCurrentModel(request, ugbModel);
        return getAnsweringView(ugbModel);
    }

    @RequestMapping("{bagId}/submitbag")
    public View submitBag(HttpServletRequest request, @PathVariable("bagId") int bagId, String answerDataList)
    {
        UserGrabBagModel ugbModel = getCurrentModel(request, modelClass);
        if (!viewModelValid(ugbModel, bagId))
        {
            return redirectToMain(bagId);
        }

        userGrabBagService.submitBag(ugbModel, answerDataList);
        setCurrentModel(request, ugbModel);

        if (ugbModel.getUser().getIsUserInfoFull())
        {
            return redirectToMain(bagId);
        }
        else
        {
            String callback = Cst.DOMAIN_NAME + "/migu/user/grabbag/" + bagId + "/startgrab";
            return authForceFullAndCallback(request, callback);
        }
    }

    @RequestMapping("{bagId}/gotoanalysis")
    public View gotoAnalysis(HttpServletRequest request, @PathVariable("bagId") int bagId)
    {
        UserGrabBagModel ugbModel = getCurrentModel(request, modelClass);
        if (!viewModelValid(ugbModel, bagId)
                || ugbModel.getUserBag().getUserBagStatus() == Cst.UserBagStatus.BEGIN_GRAB)
        {
            return redirectToMain(bagId);
        }

        userGrabBagService.viewAnalysis(ugbModel);
        setCurrentModel(request, ugbModel);

        return getAnalysisView(ugbModel);
    }

    @RequestMapping("{bagId}/getpayanalysisinfo")
    public View getPayAnalysisInfo(HttpServletRequest request, @PathVariable("bagId") int bagId)
    {
        UserGrabBagModel ugbModel = getCurrentModel(request, modelClass);

        boolean hasPaid = ugbModel.getUserBag().getHasPaidAnalysis();
        int wrongCount = ugbModel.getWrongOrBlankCount();

        JSONObject jo = new JSONObject();
        jo.put("hasPaid", hasPaid);
        jo.put("wrongCount", wrongCount);
        return getJsonView(jo.toString());
    }

    @RequestMapping("{bagId}/showmy")
    public View showMy(HttpServletRequest request, @PathVariable("bagId") int bagId)
    {
        UserGrabBagModel ugbModel = getCurrentModel(request, modelClass);
        if (!viewModelValid(ugbModel, bagId))
        {
            return redirectToMain(bagId);
        }

        int userId = getCurrentUser(request).getUserId();
        userGrabBagService.showGrabBag(ugbModel, userId, Cst.ShowGrabBagType.SHOW_GRAB_BAG_MY);
        setCurrentModel(request, ugbModel);

        request.getSession().setAttribute("just_created_share_type", "showmy");
        return getRedirectView(Cst.DOMAIN_NAME + "/migu/user/show/" + ugbModel.getShowGrabBag().getShowId()
                + "/showmy");
    }

    @RequestMapping("{bagId}/praiseuser")
    public View praiseUser(HttpServletRequest request, @PathVariable("bagId") int bagId, int userId)
    {
        UserGrabBagModel ugbModel = getCurrentModel(request, modelClass);
        if (!viewModelValid(ugbModel, bagId))
        {
            return redirectToMain(bagId);
        }

        userGrabBagService.showGrabBag(ugbModel, userId, Cst.ShowGrabBagType.SHOW_GRAB_BAG_PRAISE);
        setCurrentModel(request, ugbModel);

        request.getSession().setAttribute("just_created_share_type", "praiseuser");
        return getRedirectView(Cst.DOMAIN_NAME + "/migu/user/show/" + ugbModel.getShowGrabBag().getShowId()
                + "/praiseuser");
    }

    @RequestMapping("{bagId}/laughuser")
    public View laughUser(HttpServletRequest request, @PathVariable("bagId") int bagId, int userId)
    {
        UserGrabBagModel ugbModel = getCurrentModel(request, modelClass);
        if (!viewModelValid(ugbModel, bagId))
        {
            return redirectToMain(bagId);
        }

        userGrabBagService.showGrabBag(ugbModel, userId, Cst.ShowGrabBagType.SHOW_GRAB_BAG_LAUGH);
        setCurrentModel(request, ugbModel);

        request.getSession().setAttribute("just_created_share_type", "laughuser");
        return getRedirectView(Cst.DOMAIN_NAME + "/migu/user/show/" + ugbModel.getShowGrabBag().getShowId()
                + "/laughuser");
    }

    @RequestMapping("{bagId}/cheatgrab")
    public View cheatGrab(HttpServletRequest request, @PathVariable("bagId") int bagId)
    {
        UserGrabBagModel ugbModel = getCurrentModel(request, modelClass);
        if (!viewModelValid(ugbModel, bagId)
                || ugbModel.getUserBag().getUserBagStatus() != Cst.UserBagStatus.FINISH_GRAB_WRONG)
        {
            return redirectToMain(bagId);
        }

        userGrabBagService.cheatGrab(ugbModel);
        setCurrentModel(request, ugbModel);

        return redirectToMain(bagId);
    }

    @RequestMapping("{bagId}/quefeedback")
    public View queFeedBack(HttpServletRequest request, @PathVariable("bagId") int bagId, int queId,
            String feedBackMsg)
    {
        UserGrabBagModel ugbModel = getCurrentModel(request, modelClass);
        if (!viewModelValid(ugbModel, bagId))
        {
            return redirectToMain(bagId);
        }

        userGrabBagService.queFeedBack(ugbModel, queId, feedBackMsg);
        return getTextPlainView("ignore");
    }

    // private
    // --------------------------------------------------------------------------------
    private boolean viewModelValid(UserGrabBagModel ugbModel, int bagId)
    {
        boolean invalid = (ugbModel == null || ugbModel.getBag() == null || ugbModel.getBag().getBagId() != bagId
                || ugbModel.getUser() == null);

        if (invalid)
        {
            logger.warn("model is null, or bagId inconsist");
        }

        return !invalid;
    }

    private View redirectToMain(int bagId)
    {
        String redirect = Cst.DOMAIN_NAME + "/migu/user/grabbag/" + bagId + "/startgrab";
        return getRedirectView(redirect);
    }

    private View getGrabResultView(UserGrabBagModel ugbModel)
    {
        switch (ugbModel.getUserBag().getUserBagStatus())
        {
        case Cst.UserBagStatus.FINISH_GRAB_RIGHT_WIN:
            return getJspView(GRAB_RESULT_RIGHT_WIN);

        case Cst.UserBagStatus.FINISH_GRAB_RIGHT_SLOW:
            return getJspView(GRAB_RESULT_RIGHT_SLOW);

        case Cst.UserBagStatus.FINISH_GRAB_WRONG:
            return getJspView(GRAB_RESULT_WRONG);


        case Cst.UserBagStatus.FINISH_PRACTICE_RIGHT:
            return getJspView(PRACTICE_RESULT_RIGHT);

        case Cst.UserBagStatus.FINISH_PRACTICE_WRONG:
            return getJspView(PRACTICE_RESULT_WRONG);


        case Cst.UserBagStatus.FINISH_SHUATI_RIGHT:
            return getJspView(SHUATI_RESULT_RIGHT);

        case Cst.UserBagStatus.FINISH_SHUATI_WRONG:
            return getJspView(SHUATI_RESULT_WRONG);


        default:
            return null; // impossible
        }
    }

    private View getAnsweringView(UserGrabBagModel ugbModel)
    {
        if (ugbModel.getBag().getBagCategory() == Cst.BagCategory.HONGBAO)
        {
            return getJspView(ANSWERING_GRAB);
        }
        else if (ugbModel.getBag().getBagCategory() == Cst.BagCategory.SHUATI)
        {
            return getJspView(ANSWERING_SHUATI);
        }
        else
        {
            return null;
        }
    }

    private View getAnalysisView(UserGrabBagModel ugbModel)
    {
        if (ugbModel.getBag().getBagCategory() == Cst.BagCategory.HONGBAO)
        {
            return getJspView(VIEW_ANALYISIS_GRAB);
        }
        else if (ugbModel.getBag().getBagCategory() == Cst.BagCategory.SHUATI)
        {
            return getJspView(VIEW_ANALYISIS_SHUATI);
        }
        else
        {
            return null;
        }
    }

}
