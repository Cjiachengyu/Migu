package cn.eclassmate.qzy.service;

import cn.eclassmate.qzy.domain.Bag;
import cn.eclassmate.qzy.domain.ScoreDefeat;
import cn.eclassmate.qzy.domain.User;
import cn.eclassmate.qzy.global.Cst;
import cn.eclassmate.qzy.global.Glb;
import cn.eclassmate.qzy.global.Utl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuartzService extends AbstractService
{
    private static final Logger logger = LogManager.getLogger();

    public void execute()
    {
        logger.info("calling QuartzService execute()");

        int timeoutSeconds = Utl.currentSeconds() - 3 * 24 * 3600;

        // 查出所有三天前创建的状态为NEW_BAG的红包
        List<Bag> bagList = bagMapper.getTimeoutBagList(timeoutSeconds);
        for (Bag bag : bagList)
        {
            changeUserMoney(null, bag.getCreatorId(), Cst.BillType.TIMEOUT_RETURN_BACK,
                    bag.getBagMoney() - bag.getSentMoney(), "红包过期，系统归还");

            bag.setBagStatus(Cst.BagStatus.TIMEOUT);
            bagMapper.updateBag(bag);
        }

        synchronized (Glb.userIdUserMap)
        {
            for (User user : Glb.userIdUserMap.values())
            {
                user.setTodayCreatedBagCount(0);
            }
        }

        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        int zeroTimeToday = (int) (c.getTime().getTime() / 1000L);

        int secondsOfADay = 3600 * 24;

        int secondsOf29Days = secondsOfADay * 29;
        int monthBeginTime = zeroTimeToday - secondsOf29Days;

        int secondsOf92Days = secondsOfADay * 92;
        int threeMonthBeginTime = zeroTimeToday - secondsOf92Days;

        Map<Integer, Double> weekScoreDefeatMap = getScoreDefeat(Utl.getBeginTimeOfThisWeek());
        Map<Integer, Double> monthScoreDefeatMap = getScoreDefeat(monthBeginTime);
        Map<Integer, Double> threeMonthScoreDefeatMap = getScoreDefeat(threeMonthBeginTime);
        Map<Integer, Double> allScoreDefeatMap = getScoreDefeat(0);

        for (int i = 0; i <= 100; i++)
        {
            ScoreDefeat scoreDefeat = new ScoreDefeat();
            scoreDefeat.setScore(i);
            scoreDefeat.setDefeatWeek(weekScoreDefeatMap.get(i).toString());
            scoreDefeat.setDefeatOneMonth(monthScoreDefeatMap.get(i).toString());
            scoreDefeat.setDefeatThreeMonth(threeMonthScoreDefeatMap.get(i).toString());
            scoreDefeat.setDefeatAll(allScoreDefeatMap.get(i).toString());

            if (bagMapper.getScoreDefeat(i) == null)
            {
                bagMapper.insertScoreDefeat(scoreDefeat);
            }
            else
            {
                bagMapper.updateScoreDefeat(scoreDefeat);
            }
        }

    }

    private Map<Integer, Double> getScoreDefeat(int beginTime)
    {
        Map queCountMap = bagMapper.getAnswerMostQueUserId(beginTime);
        Map queTimeMap = bagMapper.getAnswerMostTimeUserId(beginTime);
        if (queCountMap == null || queTimeMap == null)
        {
            Map<Integer, Double> itemMap = new HashMap<Integer, Double>();
            for (int i = 0; i <= 100; i++)
            {
                itemMap.put(i, 0d);
            }
            return itemMap;

        }
        int queCountMax = Integer.parseInt(queCountMap.get("queCountMax").toString());
        int consumeTimeSumMax = Integer.parseInt(queTimeMap.get("consumeTimeSumMax").toString());

        //设置score_defeat
        Map<Integer, Integer> scoreMap = new HashMap<Integer, Integer>();
        for (int i = 0; i <= 100; i++)
        {
            scoreMap.put(i, 0);
        }

        List<HashMap> userAnswerMapList = bagMapper.getUserAnswerGroupByUser(beginTime);
        for (HashMap hashMap : userAnswerMapList)
        {
            int queCount = Integer.parseInt(hashMap.get("queCount").toString());
            int queRightCount = Integer.parseInt(hashMap.get("queRightCount").toString());
            int consumeTimeSum = Integer.parseInt(hashMap.get("consumeTimeSum").toString());

            //  答题数/最多人的答题数*40+正确率*40+答题时间/最多人的答题时间*20
            int score = (queCount * 40) / queCountMax + (queRightCount * 40) / queCount + (consumeTimeSum * 20)
                    / consumeTimeSumMax;
            scoreMap.put(score, scoreMap.get(score) + 1);//每个分数的个数
        }

        Map<Integer, Integer> scoreChangedMap = changeScoreMap(scoreMap);

        return getScoreDefeatMap(scoreChangedMap);
    }

    private Map<Integer, Double> getScoreDefeatMap(Map<Integer, Integer> scoreChangedMap)
    {
        Map<Integer, Double> scoreRankMap = new HashMap<Integer, Double>();
        for (int i = 0; i <= 100; i++)
        {
            scoreRankMap.put(i, Utl.getPercent(scoreChangedMap.get(i), scoreChangedMap.get(100), 1));
        }
        return scoreRankMap;
    }

    private Map<Integer, Integer> changeScoreMap(Map<Integer, Integer> scoreMap)
    {
        Map<Integer, Integer> retMap = new HashMap<Integer, Integer>();
        for (int i = 0; i <= 100; i++)
        {
            int score = 0;
            for (int j = 0; j <= i; j++)
            {
                score += scoreMap.get(j);
            }
            retMap.put(i, score);
        }
        return retMap;
    }
}
