package cn.eclassmate.qzy.service;

import cn.eclassmate.qzy.domain.UserAnswer;
import cn.eclassmate.qzy.domain.ZsdCatalog;
import cn.eclassmate.qzy.domain.Zsk;
import cn.eclassmate.qzy.global.Utl;
import cn.eclassmate.qzy.viewmodel.UserStudyStatisticsModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserStudyStatisticsService extends AbstractService
{
    private static final long serialVersionUID = 2346178968606382116L;
    private static final Logger logger = LogManager.getLogger();

    public void initTrend(UserStudyStatisticsModel viewModel)
    {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        int zeroTimeToday = (int) (c.getTime().getTime() / 1000L);

        int secondsOfADay = 3600 * 24;
        int secondsOf29Days = secondsOfADay * 29;
        int zeroTimeOf29DaysAgo = zeroTimeToday - secondsOf29Days;

        List<UserAnswer> allUserAnswerList = bagMapper.getAllUserAnswers(zeroTimeOf29DaysAgo);

        logger.debug("got all user answers, size: " + allUserAnswerList.size());

        // init index-date and blank lists
        Map<Integer, String> indexDateMap = new HashMap<Integer, String>();

        Map<String, Map<Integer, Integer>> userCountByDateMap = new HashMap<String, Map<Integer, Integer>>();//date
        Map<Integer, Integer> userCountByZskMap = new HashMap<Integer, Integer>();

        Map<String, Integer> queCountByDateMap = new HashMap<String, Integer>();//date
        Map<Integer, Integer> queCountByZskMap = new HashMap<Integer, Integer>();

        Map<String, Integer> queRightCountByDateMap = new HashMap<String, Integer>();//date
        Map<Integer, Integer> queRightCountByZskMap = new HashMap<Integer, Integer>();

        Map<String, Integer> consumeTimeByDateMap = new HashMap<String, Integer>();//date
        Map<Integer, Integer> consumeTimeByZskMap = new HashMap<Integer, Integer>();

        for (int i = 0; i < 30; i++)
        {
            String dateString = Utl.getTimeString("MM-dd", zeroTimeOf29DaysAgo + i * secondsOfADay);
            indexDateMap.put(i + 1, dateString);
            userCountByDateMap.put(dateString, new HashMap<Integer, Integer>());
            queCountByDateMap.put(dateString, 0);
            queRightCountByDateMap.put(dateString, 0);
            consumeTimeByDateMap.put(dateString, 0);
        }

        List<Zsk> zskList = queMapper.getAllZsk();
        Map<Integer, String> zskMap = new HashMap<Integer, String>();
        for (Zsk zsk : zskList)
        {
            userCountByZskMap.put(zsk.getZskId(), 0);
            queCountByZskMap.put(zsk.getZskId(), 0);
            queRightCountByZskMap.put(zsk.getZskId(), 0);
            consumeTimeByZskMap.put(zsk.getZskId(), 0);
            zskMap.put(zsk.getZskId(), zsk.getZskName());
        }

        // fill lists
        for (UserAnswer ua : allUserAnswerList)
        {
            String date = Utl.getTimeString("MM-dd", ua.getBeginAnswerTime());
            userCountByDateMap.get(date).put(ua.getUserId(), ua.getUserId());
            queCountByDateMap.put(date, queCountByDateMap.get(date) + 1);
            if (ua.getIsUserRight())
            {
                queRightCountByDateMap.put(date, queRightCountByDateMap.get(date) + 1);
            }
            consumeTimeByDateMap.put(date, consumeTimeByDateMap.get(date) + ua.getConsumeTime());

            for (ZsdCatalog zsdCatalog : ua.getQue().getZsdCatalogList())
            {
                userCountByZskMap.put(zsdCatalog.getZskId(), userCountByZskMap.get(zsdCatalog.getZskId()) + 1);
                queCountByZskMap.put(zsdCatalog.getZskId(), queCountByZskMap.get(zsdCatalog.getZskId()) + 1);
                if (ua.getIsUserRight())
                {
                    queRightCountByZskMap.put(zsdCatalog.getZskId(),
                            queRightCountByZskMap.get(zsdCatalog.getZskId()) + 1);
                }
                consumeTimeByZskMap.put(zsdCatalog.getZskId(),
                        consumeTimeByZskMap.get(zsdCatalog.getZskId()) + ua.getConsumeTime());
            }
        }


        // compute percent
        Map<String, Double> queRightPercentByDateMap = new HashMap<String, Double>();
        for (int i = 0; i < 30; i++)
        {
            String dateString = Utl.getTimeString("MM-dd", zeroTimeOf29DaysAgo + i * secondsOfADay);
            int allCount = queCountByDateMap.get(dateString);
            if (allCount == 0)
            {
                queRightPercentByDateMap.put(dateString, 0d);
            }
            else
            {
                int rightCount = queRightCountByDateMap.get(dateString);
                double rightPercent = Utl.getPercent(rightCount, allCount, 1);
                queRightPercentByDateMap.put(dateString, rightPercent);
            }
        }

        Map<Integer, Double> userPercentByZskMap = getPercentGroupByZskId(userCountByZskMap);
        Map<Integer, Double> quesPercentByZskMap = getPercentGroupByZskId(queCountByZskMap);
        Map<Integer, Double> rightQuesPercentByZskMap = getPercentGroupByZskId(queRightCountByZskMap);
        Map<Integer, Double> consumeTimePercentByZskMap = getPercentGroupByZskId(consumeTimeByZskMap);

        List<UserAnswer> userAnswerList = bagMapper.getAllUserAnswer();
        int rightAnswerCount = 0;
        int allConsumeTime = 0;
        for (UserAnswer userAnswer : userAnswerList)
        {
            if (userAnswer.getIsUserRight())
            {
                rightAnswerCount = rightAnswerCount + 1;
            }

            allConsumeTime = allConsumeTime + userAnswer.getConsumeTime();
        }

        viewModel.setIndexDateMap(indexDateMap);
        viewModel.setUserCountByDateMap(userCountByDateMap);
        viewModel.setQueCountByDateMap(queCountByDateMap);
        viewModel.setQueRightPercentByDateMap(queRightPercentByDateMap);
        viewModel.setConsumeTimeByDateMap(consumeTimeByDateMap);
        viewModel.setUserPercentByZskMap(userPercentByZskMap);
        viewModel.setQuesPercentByZskMap(quesPercentByZskMap);
        viewModel.setRightQuesPercentByZskMap(rightQuesPercentByZskMap);
        viewModel.setConsumeTimePercentByZskMap(consumeTimePercentByZskMap);
        viewModel.setZskMap(zskMap);
        viewModel.setUserCount(userMapper.getUserCount());
        viewModel.setQueCount(userAnswerList.size());
        viewModel.setRightPercent(Utl.getPercent(rightAnswerCount, userAnswerList.size(), 1));
        viewModel.setAllConsumeTime(allConsumeTime);
    }

    private Map<Integer, Double> getPercentGroupByZskId(Map<Integer, Integer> map)
    {
        int sum = 0;
        for (int val : map.values())
        {
            sum += val;
        }

        Map<Integer, Double> retMap = new HashMap<Integer, Double>();
        if (sum == 0)
        {
            for (int key : map.keySet())
            {
                retMap.put(key, 0d);
            }
        }
        else
        {
            for (int key : map.keySet())
            {
                int subCount = map.get(key);
                double rightPercent = Utl.getPercent(subCount, sum, 1);
                retMap.put(key, rightPercent);
            }
        }

        return retMap;
    }


}
