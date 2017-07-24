package cn.eclassmate.qzy.web;

import cn.eclassmate.qzy.domain.User;
import cn.eclassmate.qzy.viewmodel.UserBagListModel;
import cn.eclassmate.qzy.service.UserBagListService;

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
@RequestMapping("user/baglist")
public class UserBagListController extends AbstractController
{
    private static final Logger logger = LogManager.getLogger();

    // page
    // --------------------------------------------------------------------------------
    private static final String LIST_BAG_MY_SENT = "user/baglist/ListMySent";
    private static final String LIST_BAG_MY_GOT = "user/baglist/ListMyGot";

    // model
    // --------------------------------------------------------------------------------
    private static final Class<UserBagListModel> modelClass = UserBagListModel.class;

    // service
    // --------------------------------------------------------------------------------
    @Autowired
    UserBagListService userBagListService;

    // handler
    // --------------------------------------------------------------------------------
    @RequestMapping("listmysent")
    public View listMySent(HttpServletRequest request) throws Exception
    {
        User user = getCurrentUser(request);
        if (user != null)
        {
            UserBagListModel ublModel = getCurrentModel(request, modelClass);

            userBagListService.refreshMySentBagList(ublModel);

            setCurrentModel(request, ublModel);
            return getJspView(LIST_BAG_MY_SENT);
        }
        else
        {
            return authEnsureFullAndCallback(request);
        }
    }

    @RequestMapping("listmygot")
    public View listMyGot(HttpServletRequest request) throws Exception
    {
        User user = getCurrentUser(request);
        if (user != null)
        {
            UserBagListModel ublModel = getCurrentModel(request, modelClass);

            userBagListService.refreshMyGotBagList(ublModel);

            setCurrentModel(request, ublModel);
            return getJspView(LIST_BAG_MY_GOT);
        }
        else
        {
            return authEnsureFullAndCallback(request);
        }
    }

}

