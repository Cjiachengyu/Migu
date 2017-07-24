package cn.eclassmate.qzy.web;

import cn.eclassmate.qzy.domain.User;
import cn.eclassmate.qzy.service.UserStudyStatisticsService;
import cn.eclassmate.qzy.viewmodel.UserStudyStatisticsModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;

import java.text.NumberFormat;
import java.util.Map;


@Controller
@RequestMapping("user/studystatistics")
public class UserStudyStatisticsController extends AbstractController
{
    private static final Logger logger = LogManager.getLogger();

    // page
    // --------------------------------------------------------------------------------
    private static final String DISPLAY_REPORT = "user/studystatistics/DisplayReport";

    // model
    // --------------------------------------------------------------------------------
    private static final Class<UserStudyStatisticsModel> modelClass = UserStudyStatisticsModel.class;

    // service
    // --------------------------------------------------------------------------------
    @Autowired
    UserStudyStatisticsService userStudyStatisticsService;

    // handler
    // --------------------------------------------------------------------------------
    @RequestMapping("displayreport")
    public View displayReport(HttpServletRequest request) throws Exception
    {
        User user = getCurrentUser(request);
        if (user != null)
        {
            UserStudyStatisticsModel usrModel = getCurrentModel(request, modelClass);
            userStudyStatisticsService.initTrend(usrModel);

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

        UserStudyStatisticsModel viewModel = getCurrentModel(request, modelClass);

        //获取数据
        Map<Integer, String> indexDateMap = viewModel.getIndexDateMap();
        Map<String, Map<Integer, Integer>> userCountByDateMap = viewModel.getUserCountByDateMap();
        Map<String, Integer> queCountByDateMap = viewModel.getQueCountByDateMap();
        Map<String, Double> queRightPercentByDateMap = viewModel.getQueRightPercentByDateMap();
        Map<String, Integer> consumeTimeByDateMap = viewModel.getConsumeTimeByDateMap();

        //init JSONArray
        JSONArray indexDateJA = new JSONArray();
        JSONArray userCountByDateJA = new JSONArray();
        JSONArray queCountByDateJA = new JSONArray();
        JSONArray queRightPercentByDateJA = new JSONArray();
        JSONArray consumeTimeByDateJA = new JSONArray();

        //fill JSONArray
        for (int i = 1; i <= indexDateMap.entrySet().size(); i++)
        {
            indexDateJA.put(indexDateMap.get(i));
            userCountByDateJA.put(userCountByDateMap.get(indexDateMap.get(i)).keySet().size());
            queCountByDateJA.put(queCountByDateMap.get(indexDateMap.get(i)));
            queRightPercentByDateJA.put(queRightPercentByDateMap.get(indexDateMap.get(i)));
            consumeTimeByDateJA.put(getConsumeTimeString(consumeTimeByDateMap.get(indexDateMap.get(i))));
        }

        Map<Integer, Double> userPercentByZskMap = viewModel.getUserPercentByZskMap();
        Map<Integer, Double> quesPercentByZskMap = viewModel.getQuesPercentByZskMap();
        Map<Integer, Double> rightQuesPercentByZskMap = viewModel.getRightQuesPercentByZskMap();
        Map<Integer, Double> consumeTimePercentByZskMap = viewModel.getConsumeTimePercentByZskMap();
        Map<Integer, String> zskMap = viewModel.getZskMap();

        JSONArray userPercentByZskJA = getDataGroupByZsk(zskMap, userPercentByZskMap);
        JSONArray quesPercentByZskJA = getDataGroupByZsk(zskMap, quesPercentByZskMap);
        JSONArray rightQuesPercentByZskJA = getDataGroupByZsk(zskMap, rightQuesPercentByZskMap);
        JSONArray consumeTimePercentByZskJA = getDataGroupByZsk(zskMap, consumeTimePercentByZskMap);


        jo.put("indexDate", indexDateJA);
        jo.put("userCountByDate", userCountByDateJA);
        jo.put("queCountByDate", queCountByDateJA);
        jo.put("queRightPercentByDate", queRightPercentByDateJA);
        jo.put("consumeTimeByDate", consumeTimeByDateJA);

        jo.put("userPercentByZsk", userPercentByZskJA);
        jo.put("quesPercentByZsk", quesPercentByZskJA);
        jo.put("rightQuesPercentByZsk", rightQuesPercentByZskJA);
        jo.put("consumeTimePercentByZsk", consumeTimePercentByZskJA);


        return getJsonView(jo.toString());
    }


    private JSONArray getDataGroupByZsk(Map<Integer, String> zskMap, Map<Integer, Double> map)
    {
        JSONArray ja = new JSONArray();
        for (int key : map.keySet())
        {
            JSONArray itemJA = new JSONArray();
            itemJA.put(0, zskMap.get(key));
            itemJA.put(1, map.get(key));
            ja.put(itemJA);
        }
        return ja;
    }


    public double getConsumeTimeString(int consumeTime)
    {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        double rightPercent = consumeTime / (double) 3600;
        double retNum = Double.parseDouble(nf.format(rightPercent));
        return retNum;
    }
}
