package cn.eclassmate.qzy.web;

import cn.eclassmate.qzy.domain.User;
import cn.eclassmate.qzy.service.UserFriendService;
import cn.eclassmate.qzy.viewmodel.UserFriendModel;

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
@RequestMapping("user/friend")
public class UserFriendController extends AbstractController
{
    private static final Logger logger = LogManager.getLogger();

    // page
    // --------------------------------------------------------------------------------
    private static final String RANKLIST_RIGHT_ANSWER = "user/friend/RankListRightAnswer";
    private static final String RANKLIST_MONEY = "user/friend/RankListMoney";
    private static final String RANKLIST_SENT_MONEY = "user/friend/RankListSentMoney";
    private static final String RANKLIST_RETURNED_MONEY = "user/friend/RankListReturnedMoney";
    private static final String RANKLIST_SPENT_MONEY = "user/friend/RankListSpentMoney";

    // model
    // --------------------------------------------------------------------------------
    private static final Class<UserFriendModel> modelClass = UserFriendModel.class;

    // service
    // --------------------------------------------------------------------------------
    @Autowired
    UserFriendService userFriendService;

    // handler
    // --------------------------------------------------------------------------------
    @RequestMapping("rankrightanswer")
    public View rankRightAnswer(HttpServletRequest request) throws Exception
    {
        User user = getCurrentUser(request);
        if (user != null)
        {
            UserFriendModel ufbrModel = getCurrentModel(request, modelClass);
            userFriendService.refreshRankListRightAnswer(ufbrModel);

            setCurrentModel(request, ufbrModel);
            return getJspView(RANKLIST_RIGHT_ANSWER);
        }
        else
        {
            return authEnsureFullAndCallback(request);
        }
    }

    @RequestMapping("rankmoney")
    public View rankMoney(HttpServletRequest request) throws Exception
    {
        User user = getCurrentUser(request);
        if (user != null)
        {
            UserFriendModel ufbrModel = getCurrentModel(request, modelClass);
            userFriendService.refreshRankListMoney(ufbrModel);

            setCurrentModel(request, ufbrModel);
            return getJspView(RANKLIST_MONEY);
        }
        else
        {
            return authEnsureFullAndCallback(request);
        }

    }

    @RequestMapping("ranksentmoney")
    public View rankSentMoney(HttpServletRequest request) throws Exception
    {
        User user = getCurrentUser(request);
        if (user != null)
        {
            UserFriendModel ufbrModel = getCurrentModel(request, modelClass);
            userFriendService.refreshRankListSentMoney(ufbrModel);

            setCurrentModel(request, ufbrModel);
            return getJspView(RANKLIST_SENT_MONEY);
        }
        else
        {
            return authEnsureFullAndCallback(request);
        }
    }

    @RequestMapping("rankreturnedmoney")
    public View rankReturnedMoney(HttpServletRequest request) throws Exception
    {
        User user = getCurrentUser(request);
        if (user != null)
        {
            UserFriendModel ufbrModel = getCurrentModel(request, modelClass);
            userFriendService.refreshRankListReturnedMoney(ufbrModel);

            setCurrentModel(request, ufbrModel);
            return getJspView(RANKLIST_RETURNED_MONEY);
        }
        else
        {
            return authEnsureFullAndCallback(request);
        }
    }

    @RequestMapping("rankspentmoney")
    public View rankSpentMoney(HttpServletRequest request) throws Exception
    {
        User user = getCurrentUser(request);
        if (user != null)
        {
            UserFriendModel ubrModel = getCurrentModel(request, modelClass);
            userFriendService.refreshRankListSpentMoney(ubrModel);

            setCurrentModel(request, ubrModel);
            return getJspView(RANKLIST_SPENT_MONEY);
        }
        else
        {
            return authEnsureFullAndCallback(request);
        }
    }

}
