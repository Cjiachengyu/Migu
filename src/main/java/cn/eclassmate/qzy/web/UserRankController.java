package cn.eclassmate.qzy.web;

import cn.eclassmate.qzy.domain.User;
import cn.eclassmate.qzy.service.UserRankService;
import cn.eclassmate.qzy.viewmodel.UserRankModel;

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
@RequestMapping("user/rank")
public class UserRankController extends AbstractController
{
    private static final Logger logger = LogManager.getLogger();

    // page
    // --------------------------------------------------------------------------------
    private static final String RANKLIST_RIGHT_ANSWER = "user/rank/ListRightAnswer";
    private static final String RANKLIST_MONEY = "user/rank/ListMoney";
    private static final String RANKLIST_SENT_MONEY = "user/rank/ListSentMoney";
    private static final String RANKLIST_RETURNED_MONEY = "user/rank/ListReturnedMoney";
    private static final String RANKLIST_SPENT_MONEY = "user/rank/ListSpentMoney";

    // model
    // --------------------------------------------------------------------------------
    private static final Class<UserRankModel> modelClass = UserRankModel.class;

    // service
    // --------------------------------------------------------------------------------
    @Autowired
    UserRankService userRankService;

    // handler
    // --------------------------------------------------------------------------------
    @RequestMapping("rankrightanswer")
    public View rankRightAnswer(HttpServletRequest request) throws Exception
    {
        User user = getCurrentUser(request);
        if (user != null)
        {
            UserRankModel ubrModel = getCurrentModel(request, modelClass);
            userRankService.refreshRankListRightAnswer(ubrModel);

            setCurrentModel(request, ubrModel);
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
            UserRankModel ubrModel = getCurrentModel(request, modelClass);
            userRankService.refreshRankListMoney(ubrModel);

            setCurrentModel(request, ubrModel);
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
            UserRankModel ubrModel = getCurrentModel(request, modelClass);
            userRankService.refreshRankListSentMoney(ubrModel);

            setCurrentModel(request, ubrModel);
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
            UserRankModel ubrModel = getCurrentModel(request, modelClass);
            userRankService.refreshRankListReturnedMoney(ubrModel);

            setCurrentModel(request, ubrModel);
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
            UserRankModel ubrModel = getCurrentModel(request, modelClass);
            userRankService.refreshRankListSpentMoney(ubrModel);

            setCurrentModel(request, ubrModel);
            return getJspView(RANKLIST_SPENT_MONEY);
        }
        else
        {
            return authEnsureFullAndCallback(request);
        }
    }

}
