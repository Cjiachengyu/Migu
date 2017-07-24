package cn.eclassmate.qzy.viewmodel;

import java.util.Map;

public class UserStudyStatisticsModel extends AbstractModel
{
    private static final long serialVersionUID = 1886085397027390750L;

    // fields
    // --------------------------------------------------------------------------------
    private Map<Integer, String> indexDateMap;          // 日期序号-日期
    Map<String, Map<Integer, Integer>> userCountByDateMap;
    Map<String, Integer> queCountByDateMap;
    Map<String, Double> queRightPercentByDateMap;
    Map<String, Integer> consumeTimeByDateMap;
    Map<Integer, Double> userPercentByZskMap;
    Map<Integer, Double> quesPercentByZskMap;
    Map<Integer, Double> rightQuesPercentByZskMap;
    Map<Integer, Double> consumeTimePercentByZskMap;

    int userCount;
    int queCount;
    double rightPercent;
    int allConsumeTime;

    Map<Integer, String> zskMap;

    // getter, setter
    // --------------------------------------------------------------------------------
    public Map<Integer, String> getIndexDateMap()
    {
        return indexDateMap;
    }

    public void setIndexDateMap(Map<Integer, String> indexDateMap)
    {
        this.indexDateMap = indexDateMap;
    }

    public Map<String, Map<Integer, Integer>> getUserCountByDateMap()
    {
        return userCountByDateMap;
    }

    public void setUserCountByDateMap(Map<String, Map<Integer, Integer>> userCountByDateMap)
    {
        this.userCountByDateMap = userCountByDateMap;
    }

    public Map<String, Integer> getQueCountByDateMap()
    {
        return queCountByDateMap;
    }

    public void setQueCountByDateMap(Map<String, Integer> queCountByDateMap)
    {
        this.queCountByDateMap = queCountByDateMap;
    }

    public Map<String, Double> getQueRightPercentByDateMap()
    {
        return queRightPercentByDateMap;
    }

    public void setQueRightPercentByDateMap(Map<String, Double> queRightPercentByDateMap)
    {
        this.queRightPercentByDateMap = queRightPercentByDateMap;
    }

    public Map<String, Integer> getConsumeTimeByDateMap()
    {
        return consumeTimeByDateMap;
    }

    public void setConsumeTimeByDateMap(Map<String, Integer> consumeTimeByDateMap)
    {
        this.consumeTimeByDateMap = consumeTimeByDateMap;
    }

    public Map<Integer, Double> getUserPercentByZskMap()
    {
        return userPercentByZskMap;
    }

    public void setUserPercentByZskMap(Map<Integer, Double> userPercentByZskMap)
    {
        this.userPercentByZskMap = userPercentByZskMap;
    }

    public Map<Integer, Double> getQuesPercentByZskMap()
    {
        return quesPercentByZskMap;
    }

    public void setQuesPercentByZskMap(Map<Integer, Double> quesPercentByZskMap)
    {
        this.quesPercentByZskMap = quesPercentByZskMap;
    }

    public Map<Integer, Double> getRightQuesPercentByZskMap()
    {
        return rightQuesPercentByZskMap;
    }

    public void setRightQuesPercentByZskMap(Map<Integer, Double> rightQuesPercentByZskMap)
    {
        this.rightQuesPercentByZskMap = rightQuesPercentByZskMap;
    }

    public Map<Integer, Double> getConsumeTimePercentByZskMap()
    {
        return consumeTimePercentByZskMap;
    }

    public void setConsumeTimePercentByZskMap(Map<Integer, Double> consumeTimePercentByZskMap)
    {
        this.consumeTimePercentByZskMap = consumeTimePercentByZskMap;
    }

    public Map<Integer, String> getZskMap()
    {
        return zskMap;
    }

    public void setZskMap(Map<Integer, String> zskMap)
    {
        this.zskMap = zskMap;
    }

    public int getUserCount()
    {
        return userCount;
    }

    public void setUserCount(int userCount)
    {
        this.userCount = userCount;
    }

    public int getQueCount()
    {
        return queCount;
    }

    public void setQueCount(int queCount)
    {
        this.queCount = queCount;
    }

    public double getRightPercent()
    {
        return rightPercent;
    }

    public void setRightPercent(double rightPercent)
    {
        this.rightPercent = rightPercent;
    }

    public int getAllConsumeTime()
    {
        return allConsumeTime;
    }

    public void setAllConsumeTime(int allConsumeTime)
    {
        this.allConsumeTime = allConsumeTime;
    }

    public String getConsumeTimeString()
    {
        if (allConsumeTime < 60)
        {
            return allConsumeTime + "秒";
        }
        else if (allConsumeTime >= 60 && allConsumeTime < 3600)
        {
            if (allConsumeTime % 60 == 0)
            {
                return allConsumeTime / 60 + "分";
            }
            else
            {
                return allConsumeTime / 60 + "分" + allConsumeTime % 60 + "秒";
            }
        }
        else if (allConsumeTime >= 3600)
        {
            if (allConsumeTime % 3600 == 0)
            {
                return allConsumeTime / 3600 + "小时";
            }
            else if (allConsumeTime % 60 == 0)
            {
                return allConsumeTime / 3600 + "小时" + (allConsumeTime % 3600) / 60 + "分";
            }
            else if (allConsumeTime % 3600 < 60)
            {
                return allConsumeTime / 3600 + "小时" + allConsumeTime % 60 + "秒";
            }
            else
            {
                return allConsumeTime / 3600 + "小时" + (allConsumeTime % 3600) / 60 + "分" + allConsumeTime % 60 + "秒";
            }
        }
        return null;
    }


}
