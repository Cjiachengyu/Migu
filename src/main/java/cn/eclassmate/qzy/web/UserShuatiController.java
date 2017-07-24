package cn.eclassmate.qzy.web;

import cn.eclassmate.qzy.domain.User;
import cn.eclassmate.qzy.service.UserShuatiService;
import cn.eclassmate.qzy.viewmodel.UserShuatiModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("user/shuati")
public class UserShuatiController extends AbstractController
{
    private static final Logger logger = LogManager.getLogger();

    // page
    // --------------------------------------------------------------------------------
    private static final String SELECT_ZSD = "user/shuati/SelectZsd";
    private static final String ZSD_REPORT_DETAIL = "user/shuati/subview/ZsdReportDetail";


    // model
    // --------------------------------------------------------------------------------
    private static final Class<UserShuatiModel> modelClass = UserShuatiModel.class;

    // service
    // --------------------------------------------------------------------------------
    @Autowired
    UserShuatiService userShuatiService;

    // handler
    // --------------------------------------------------------------------------------
    @RequestMapping("selectzsd")
    public View showZsd(HttpServletRequest request) throws Exception
    {
        User user = getCurrentUser(request);
        if (user != null)
        {
            UserShuatiModel usrModel = getCurrentModel(request, modelClass);
            userShuatiService.initZsdCatalog(usrModel);

            setCurrentModel(request, usrModel);
            return getJspView(SELECT_ZSD);
        }
        else
        {
            return authEnsureFullAndCallback(request);
        }
    }

    @RequestMapping("initzsdreportdetail")
    public View initZsdReportDetail(HttpServletRequest request) throws Exception
    {
        User user = getCurrentUser(request);
        if (user != null)
        {
            UserShuatiModel usrModel = getCurrentModel(request, modelClass);
            userShuatiService.initZsdReportDetail(usrModel);

            setCurrentModel(request, usrModel);
            return getJspView(ZSD_REPORT_DETAIL);
        }
        else
        {
            return authEnsureFullAndCallback(request);
        }
    }


    @RequestMapping("changestarstatus")
    public View changeStarStatus(HttpServletRequest request,
            Integer zskId, Integer zsd1Id, Integer zsd2Id, Integer starNum) throws Exception
    {
        User user = getCurrentUser(request);
        if (user != null)
        {
            UserShuatiModel usrModel = getCurrentModel(request, modelClass);
            userShuatiService.changeStarStatus(usrModel, zskId, zsd1Id, zsd2Id, starNum);

            JSONObject jo = new JSONObject();
            jo.put("isSuccess", "yes");
            return getJsonView(jo.toString());
        }
        else
        {
            return authEnsureFullAndCallback(request);
        }
    }

    @RequestMapping("changemoonstatus")
    public View changeMoonStatus(HttpServletRequest request,
            Integer zskId, Integer zsd1Id, Integer moonNum) throws Exception
    {
        User user = getCurrentUser(request);
        if (user != null)
        {
            UserShuatiModel usrModel = getCurrentModel(request, modelClass);
            userShuatiService.changeMoonStatus(usrModel, zskId, zsd1Id, moonNum);

            JSONObject jo = new JSONObject();
            jo.put("isSuccess", "yes");
            return getJsonView(jo.toString());
        }
        else
        {
            return authEnsureFullAndCallback(request);
        }
    }

    @RequestMapping("recordshuatizskid")
    public View recordShuatiZskId(HttpServletRequest request, Integer zskId) throws Exception
    {
        User user = getCurrentUser(request);
        if (user != null)
        {
            userShuatiService.recordShuatiZskId(user, zskId);

            return getTextPlainView("ok");
        }
        else
        {
            return authEnsureFullAndCallback(request);
        }
    }

}
