package cn.eclassmate.qzy.service;

import cn.eclassmate.qzy.domain.Bill;
import cn.eclassmate.qzy.domain.UserAnswer;
import cn.eclassmate.qzy.domain.UserMoon;
import cn.eclassmate.qzy.domain.UserStar;
import cn.eclassmate.qzy.domain.Zsd1;
import cn.eclassmate.qzy.domain.Zsd2;
import cn.eclassmate.qzy.domain.ZsdCatalog;
import cn.eclassmate.qzy.domain.Zsl;
import cn.eclassmate.qzy.global.Cst;
import cn.eclassmate.qzy.global.Utl;
import cn.eclassmate.qzy.viewmodel.UserStudyReportModel;
import cn.eclassmate.qzy.viewmodel.subview.Zsd1Report;
import cn.eclassmate.qzy.viewmodel.subview.Zsd2Report;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserStudyReportService extends AbstractService
{
    private static final long serialVersionUID = 2346178968606382116L;
    private static final Logger logger = LogManager.getLogger();

    public void initTrend(UserStudyReportModel usrModel)
    {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        int zeroTimeToday = (int) (c.getTime().getTime() / 1000L);

        int secondsOfADay = 3600 * 24;
        int secondsOf29Days = secondsOfADay * 29;
        int zeroTimeOf29DaysAgo = zeroTimeToday - secondsOf29Days;

        List<UserAnswer> allUserAnswerList = bagMapper.getUserAnswersByTimespan(
                usrModel.getUser().getUserId(), zeroTimeOf29DaysAgo, Utl.currentSeconds());
        logger.debug("got all user answers, size: " + allUserAnswerList.size());

        // init index-date and blank lists
        Map<Integer, String> indexDateMap = new HashMap<Integer, String>();
        Map<String, List<UserAnswer>> dateAllUserAnswerListMap = new HashMap<String, List<UserAnswer>>();
        Map<String, List<UserAnswer>> dateRightUserAnswerListMap = new HashMap<String, List<UserAnswer>>();
        for (int i = 0; i < 30; i++)
        {
            String dateString = Utl.getTimeString("MM-dd", zeroTimeOf29DaysAgo + i * secondsOfADay);
            indexDateMap.put(i + 1, dateString);
            dateAllUserAnswerListMap.put(dateString, new ArrayList<UserAnswer>());
            dateRightUserAnswerListMap.put(dateString, new ArrayList<UserAnswer>());
        }

        // fill lists
        for (UserAnswer ua : allUserAnswerList)
        {
            String date = Utl.getTimeString("MM-dd", ua.getBeginAnswerTime());
            dateAllUserAnswerListMap.get(date).add(ua);
            if (ua.getIsUserRight())
            {
                dateRightUserAnswerListMap.get(date).add(ua);
            }
        }

        // compute percent
        Map<String, Double> dateRightPercentMap = new HashMap<String, Double>();
        for (int i = 0; i < 30; i++)
        {
            String dateString = Utl.getTimeString("MM-dd", zeroTimeOf29DaysAgo + i * secondsOfADay);
            int allCount = dateAllUserAnswerListMap.get(dateString).size();
            if (allCount == 0)
            {
                dateRightPercentMap.put(dateString, 0d);
            }
            else
            {
                int rightCount = dateRightUserAnswerListMap.get(dateString).size();
                double rightPercent = Utl.getPercent(rightCount, allCount, 1);
                dateRightPercentMap.put(dateString, rightPercent);
            }
        }

        usrModel.setIndexDateMap(indexDateMap);
        usrModel.setDateAllUserAnswerListMap(dateAllUserAnswerListMap);
        usrModel.setDateRightUserAnswerListMap(dateRightUserAnswerListMap);
        usrModel.setDateRightPercentMap(dateRightPercentMap);
    }

}
