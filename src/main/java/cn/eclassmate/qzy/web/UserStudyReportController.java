package cn.eclassmate.qzy.web;

import cn.eclassmate.qzy.domain.User;
import cn.eclassmate.qzy.domain.UserAnswer;
import cn.eclassmate.qzy.service.UserStudyReportService;
import cn.eclassmate.qzy.viewmodel.UserStudyReportModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("user/studyreport")
public class UserStudyReportController extends AbstractController
{
    private static final Logger logger = LogManager.getLogger();

    // page
    // --------------------------------------------------------------------------------
    private static final String DISPLAY_REPORT = "user/studyreport/DisplayReport";

    // model
    // --------------------------------------------------------------------------------
    private static final Class<UserStudyReportModel> modelClass = UserStudyReportModel.class;

    // service
    // --------------------------------------------------------------------------------
    @Autowired
    UserStudyReportService userStudyReportService;

    // handler
    // --------------------------------------------------------------------------------
    @RequestMapping("displayreport")
    public View displayReport(HttpServletRequest request) throws Exception
    {
        User user = getCurrentUser(request);
        if (user != null)
        {
            UserStudyReportModel usrModel = getCurrentModel(request, modelClass);
            userStudyReportService.initTrend(usrModel);

            setCurrentModel(request, usrModel);
            return getJspView(DISPLAY_REPORT);
        }
        else
        {
            return authEnsureFullAndCallback(request);
        }
    }

    @RequestMapping("getdisplaydata")
    public View getDisplayData(HttpServletRequest request) throws Exception
    {
        JSONObject jo = new JSONObject();

        UserStudyReportModel usrModel = getCurrentModel(request, modelClass);
        Map<Integer, String> indexDateMap = usrModel.getIndexDateMap();
        Map<String, List<UserAnswer>> dateAllUserAnswerListMap = usrModel.getDateAllUserAnswerListMap();
        Map<String, List<UserAnswer>> dateRightUserAnswerListMap = usrModel.getDateRightUserAnswerListMap();
        Map<String, Double> dateRightPercentMap = usrModel.getDateRightPercentMap();

        JSONArray indexDateJA = new JSONArray();
        JSONArray dateAllQueListJA = new JSONArray();
        JSONArray dateRightQueListJA = new JSONArray();
        JSONArray dateRightPercentJA = new JSONArray();

        for (int i = 1; i <= indexDateMap.entrySet().size(); i++)
        {
            indexDateJA.put(indexDateMap.get(i));

            dateAllQueListJA.put(dateAllUserAnswerListMap.get(indexDateMap.get(i)).size());
            dateRightQueListJA.put(dateRightUserAnswerListMap.get(indexDateMap.get(i)).size());
            dateRightPercentJA.put(dateRightPercentMap.get(indexDateMap.get(i)));
        }
        jo.put("indexDate", indexDateJA);
        jo.put("dateAllQueList", dateAllQueListJA);
        jo.put("dateRightQueList", dateRightQueListJA);
        jo.put("dateRightPercent", dateRightPercentJA);

        return getJsonView(jo.toString());
    }

}
