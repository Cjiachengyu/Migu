package cn.eclassmate.qzy.web;

import cn.eclassmate.qzy.domain.User;
import cn.eclassmate.qzy.service.UserLearningReportsService;
import cn.eclassmate.qzy.viewmodel.UserLearningReportsModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("user/learningreports")
public class UserLearningReportsController extends AbstractController
{
    private static final Logger logger = LogManager.getLogger();

    // page
    // --------------------------------------------------------------------------------
    private static final String USER_LEARNING_REPORTS = "user/learningreports/LearningReports";
    private static final String LEARNING_REPORTS_DETAIL = "user/learningreports/subview/LearningReportsDetail";


    // model
    // --------------------------------------------------------------------------------
    private static final Class<UserLearningReportsModel> modelClass = UserLearningReportsModel.class;

    // service
    // --------------------------------------------------------------------------------
    @Autowired
    UserLearningReportsService userLearningReportsService;

    // handler
    // --------------------------------------------------------------------------------
    @RequestMapping("myreports")
    public View myreports(HttpServletRequest request) throws Exception
    {
        User user = getCurrentUser(request);
        if (user != null)
        {
            UserLearningReportsModel viewModel = getCurrentModel(request, modelClass);
            setCurrentModel(request, viewModel);
            return getJspView(USER_LEARNING_REPORTS);
        }
        else
        {
            return authEnsureFullAndCallback(request);
        }
    }


    @RequestMapping("detail")
    public View detail(HttpServletRequest request, Integer dateType) throws Exception
    {
        User user = getCurrentUser(request);
        if (user != null)
        {
            UserLearningReportsModel viewModel = getCurrentModel(request, modelClass);
            userLearningReportsService.initLearningReportsModel(viewModel, dateType);
            setCurrentModel(request, viewModel);
            return getJspView(LEARNING_REPORTS_DETAIL);
        }
        else
        {
            return authEnsureFullAndCallback(request);
        }
    }


}
