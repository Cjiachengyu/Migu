package cn.eclassmate.qzy.web;

import cn.eclassmate.qzy.domain.User;
import cn.eclassmate.qzy.domain.UserBag;
import cn.eclassmate.qzy.global.Utl;
import cn.eclassmate.qzy.service.UserBagSttService;
import cn.eclassmate.qzy.viewmodel.UserBagSttModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

/**
 * 单份作业统计
 * 1. 发作业者可查看本份作业的统计：答题名单和答对题数
 * 2. 统计可包括成绩分布、每题正确率、答题时间分布以及每题每选项的统计
 *
 * @author stao
 */
@Controller
@RequestMapping("user/bagstt")
public class UserBagSttController extends AbstractController
{
    private static final Logger logger = LogManager.getLogger();

    // page
    // --------------------------------------------------------------------------------
    private static final String BAG_INFO = "user/UserBagStt";

    // model
    // --------------------------------------------------------------------------------
    private static final Class<UserBagSttModel> modelClass = UserBagSttModel.class;

    // service
    // --------------------------------------------------------------------------------
    @Autowired
    UserBagSttService userBagSttService;

    // handler
    // --------------------------------------------------------------------------------
    @RequestMapping("{bagId}/showbag")
    public View showBag(HttpServletRequest request, @PathVariable("bagId") int bagId) throws Exception
    {
        User user = getCurrentUser(request);
        if (user != null)
        {
            UserBagSttModel ubsModel = getCurrentModel(request, modelClass);
            userBagSttService.initUbsModel(ubsModel, bagId);

            setCurrentModel(request, ubsModel);
            return getJspView(BAG_INFO);
        }
        else
        {
            return authEnsureFullAndCallback(request);
        }
    }

    @RequestMapping("{bagId}/pullsttdata")
    public View pullSttData(HttpServletRequest request, @PathVariable("bagId") int bagId) throws Exception
    {
        UserBagSttModel ubsModel = getCurrentModel(request, modelClass);

        JSONObject jo = new JSONObject();
        jo.put("zsdStr", ubsModel.getBag().getZsdCatalog().getZsdString());
        jo.put("displayData", getDisplayData(ubsModel));

        return getJsonView(jo.toString());
    }

    // private
    // --------------------------------------------------------------------------------
    private JSONArray getDisplayData(UserBagSttModel ubsModel)
    {
        JSONArray ja = new JSONArray();

        // 倒序排(答对3题，答对2题，...，答对0题)
        for (int i = ubsModel.getBag().getQueList().size(); i >= 0; i--)
        {
            int rightCount = i;
            List<UserBag> userBagList = ubsModel.getRightCountUserCountMap().get(rightCount);
            if (userBagList != null && userBagList.size() > 0)
            {
                double percent = Utl.getPercent(userBagList.size(), ubsModel.getUserBagList().size(), 1);

                JSONArray innerJa = new JSONArray();
                innerJa.put(0, "答对" + rightCount + "题：" + userBagList.size() + "人");
                innerJa.put(1, percent);

                ja.put(innerJa);
            }
        }

        return ja;
    }

}
