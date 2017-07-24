package cn.eclassmate.qzy.viewmodel;

import cn.eclassmate.qzy.domain.Zsk;
import cn.eclassmate.qzy.global.Utl;

import java.util.List;

public class UserLearningReportsModel extends AbstractModel
{
    private static final long serialVersionUID = 1886085397027390750L;

    // fields
    // --------------------------------------------------------------------------------
    int dateType = 1;
    private int beginTime;
    private int endTime;
    private int score;
    private double scoreRank;
    private int answerCount;//答题数
    private double rightPercent;//正确率
    private int consumeTime;//答题时间
    private List<Zsk> zskList;    // 知识点报告

    // getter, setter
    // --------------------------------------------------------------------------------
    public int getDateType()
    {
        return dateType;
    }

    public void setDateType(int dateType)
    {
        this.dateType = dateType;
    }

    public int getBeginTime()
    {
        return beginTime;
    }

    public void setBeginTime(int beginTime)
    {
        this.beginTime = beginTime;
    }

    public int getEndTime()
    {
        return endTime;
    }

    public void setEndTime(int endTime)
    {
        this.endTime = endTime;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public double getScoreDefeat()
    {
        return scoreRank;
    }

    public void setScoreDefeat(double scoreRank)
    {
        this.scoreRank = scoreRank;
    }

    public int getAnswerCount()
    {
        return answerCount;
    }

    public void setAnswerCount(int answerCount)
    {
        this.answerCount = answerCount;
    }

    public double getRightPercent()
    {
        return rightPercent;
    }

    public void setRightPercent(double rightPercent)
    {
        this.rightPercent = rightPercent;
    }

    public int getConsumeTime()
    {
        return consumeTime;
    }

    public void setConsumeTime(int consumeTime)
    {
        this.consumeTime = consumeTime;
    }

    public List<Zsk> getZskList()
    {
        return zskList;
    }

    public void setZskList(List<Zsk> zskList)
    {
        this.zskList = zskList;
    }


    public String getBeginTimeString()
    {
        return Utl.getTimeString_MM_dd(beginTime);
    }

    public String getEndTimeString()
    {
        return Utl.getTimeString_MM_dd(endTime);
    }

    public String getConsumeTimeString()
    {
        if (consumeTime < 60)
        {
            return consumeTime + "秒";
        }
        else if (consumeTime >= 60 && consumeTime < 3600)
        {
            if (consumeTime % 60 == 0)
            {
                return consumeTime / 60 + "分";
            }
            else
            {
                return consumeTime / 60 + "分" + consumeTime % 60 + "秒";
            }
        }
        else if (consumeTime >= 3600)
        {
            if (consumeTime % 3600 == 0)
            {
                return consumeTime / 3600 + "小时";
            }
            else if (consumeTime % 60 == 0)
            {
                return consumeTime / 3600 + "小时" + (consumeTime % 3600) / 60 + "分";
            }
            else if (consumeTime % 3600 < 60)
            {
                return consumeTime / 3600 + "小时" + consumeTime % 60 + "秒";
            }
            else
            {
                return consumeTime / 3600 + "小时" + (consumeTime % 3600) / 60 + "分" + consumeTime % 60 + "秒";
            }
        }
        return null;
    }


}
