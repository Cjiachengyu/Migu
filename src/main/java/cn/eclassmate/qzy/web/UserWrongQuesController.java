package cn.eclassmate.qzy.web;

import cn.eclassmate.qzy.domain.User;
import cn.eclassmate.qzy.service.UserWrongQuesService;
import cn.eclassmate.qzy.viewmodel.UserWrongQuesModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("user/wrong")
public class UserWrongQuesController extends AbstractController
{
    private static final Logger logger = LogManager.getLogger();

    // page
    // --------------------------------------------------------------------------------
    private static final String WRONG_QUES = "user/wrongques/WrongQues";

    // model
    // --------------------------------------------------------------------------------
    private static final Class<UserWrongQuesModel> modelClass = UserWrongQuesModel.class;

    // service
    // --------------------------------------------------------------------------------
    @Autowired
    UserWrongQuesService userWrongQuesService;

    // handler
    // --------------------------------------------------------------------------------
    //奖品兑换页面，所有奖品
    @RequestMapping("quelist")
    public View queList(HttpServletRequest request) throws Exception
    {
        User user = getCurrentUser(request);
        if (user != null)
        {
            UserWrongQuesModel viewModel = getCurrentModel(request, modelClass);

            userWrongQuesService.initUserWrongQuesModel(viewModel);

            setCurrentModel(request, viewModel);
            return getJspView(WRONG_QUES);
        }
        else
        {
            return authEnsureFullAndCallback(request);
        }
    }

    @RequestMapping("quefeedback")
    public View queFeedBack(HttpServletRequest request, int queId,
            String feedBackMsg)
    {
        UserWrongQuesModel ugbModel = getCurrentModel(request, modelClass);
        userWrongQuesService.queFeedBack(ugbModel, queId, feedBackMsg);
        return getTextPlainView("ignore");
    }


}
