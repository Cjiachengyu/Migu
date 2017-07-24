package cn.eclassmate.qzy.domain;

import cn.eclassmate.qzy.global.Utl;

import java.io.Serializable;
import java.util.List;

public class UserBag implements Serializable
{
    private static final long serialVersionUID = -7311283136802846693L;

    // user_bag
    // --------------------------------------------------------------------------------
    private int userId;
    private int bagId;
    private int beginBagTime;
    private int endBagTime;
    private int userBagStatus;
    private int gotMoney;
    private boolean hasPaidAnalysis;


    // 关联对象
    // --------------------------------------------------------------------------------
    private User user;
    private Bag bag;
    private List<UserAnswer> userAnswerList;

    // 附加字段
    // --------------------------------------------------------------------------------
    private int rightAnswerCount;   // 本次作业里答对几题
    private int praiseOrLaughCount;

    // 附加存取器
    // --------------------------------------------------------------------------------
    public String getEndBagTimeString()
    {
        return Utl.getTimeString_MM_dd_HH_mm(endBagTime);
    }

    public String getConsumeTimeString()
    {
        String retStr;
        int time = endBagTime - beginBagTime;
        if (time < 60)
        {
            retStr = time + "''";
        }
        else if (time > 3600)
        {
            retStr = "> 60'";
        }
        else
        {
            if (time % 60 == 0)
            {
                retStr = time / 60 + "'";
            }
            else
            {
                retStr = time / 60 + "'" + time % 60 + "''";
            }
        }

        return retStr;
    }

    // getter, setter
    // --------------------------------------------------------------------------------
    public int getBeginBagTime()
    {
        return beginBagTime;
    }

    public void setBeginBagTime(int beginBagTime)
    {
        this.beginBagTime = beginBagTime;
    }

    public int getEndBagTime()
    {
        return endBagTime;
    }

    public void setEndBagTime(int endBagTime)
    {
        this.endBagTime = endBagTime;
    }

    public int getGotMoney()
    {
        return gotMoney;
    }

    public void setGotMoney(int gotMoney)
    {
        this.gotMoney = gotMoney;
    }

    public int getBagId()
    {
        return bagId;
    }

    public void setBagId(int bagId)
    {
        this.bagId = bagId;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public int getUserBagStatus()
    {
        return userBagStatus;
    }

    public void setUserBagStatus(int userBagStatus)
    {
        this.userBagStatus = userBagStatus;
    }

    public int getRightAnswerCount()
    {
        return rightAnswerCount;
    }

    public void setRightAnswerCount(int rightAnswerCount)
    {
        this.rightAnswerCount = rightAnswerCount;
    }

    public List<UserAnswer> getUserAnswerList()
    {
        return userAnswerList;
    }

    public void setUserAnswerList(List<UserAnswer> userAnswerList)
    {
        this.userAnswerList = userAnswerList;
    }

    public Bag getBag()
    {
        return bag;
    }

    public void setBag(Bag bag)
    {
        this.bag = bag;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public boolean getHasPaidAnalysis()
    {
        return hasPaidAnalysis;
    }

    public void setHasPaidAnalysis(boolean hasPaidAnalysis)
    {
        this.hasPaidAnalysis = hasPaidAnalysis;
    }

    public int getPraiseOrLaughCount()
    {
        return praiseOrLaughCount;
    }

    public void setPraiseOrLaughCount(int praiseOrLaughCount)
    {
        this.praiseOrLaughCount = praiseOrLaughCount;
    }


}
