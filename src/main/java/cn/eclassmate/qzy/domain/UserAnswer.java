package cn.eclassmate.qzy.domain;

import cn.eclassmate.qzy.global.Utl;

import java.io.Serializable;

public class UserAnswer implements Serializable
{
    private static final long serialVersionUID = -805059473073684642L;

    // bag
    // --------------------------------------------------------------------------------
    private int userId;
    private int bagId;
    private int queId;
    private int beginAnswerTime;
    private int endAnswerTime;
    private int consumeTime;
    private int userChoiceId;
    private boolean isUserRight;

    // 关联对象
    // --------------------------------------------------------------------------------
    private User user;
    private Que que;

    // 附加字段
    // --------------------------------------------------------------------------------

    // 附加存取器
    // --------------------------------------------------------------------------------

    // getter, setter
    // --------------------------------------------------------------------------------
    public int getQueId()
    {
        return queId;
    }

    public void setQueId(int queId)
    {
        this.queId = queId;
    }

    public int getBeginAnswerTime()
    {
        return beginAnswerTime;
    }

    public void setBeginAnswerTime(int beginAnswerTime)
    {
        this.beginAnswerTime = beginAnswerTime;
    }

    public int getEndAnswerTime()
    {
        return endAnswerTime;
    }

    public void setEndAnswerTime(int endAnswerTime)
    {
        this.endAnswerTime = endAnswerTime;
    }

    public int getUserChoiceId()
    {
        return userChoiceId;
    }

    public void setUserChoiceId(int userChoiceId)
    {
        this.userChoiceId = userChoiceId;
    }

    public boolean getIsUserRight()
    {
        return isUserRight;
    }

    public void setIsUserRight(boolean isUserRight)
    {
        this.isUserRight = isUserRight;
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

    public int getConsumeTime()
    {
        return consumeTime;
    }

    public void setConsumeTime(int consumeTime)
    {
        this.consumeTime = consumeTime;
    }

    public Que getQue()
    {
        return que;
    }

    public void setQue(Que que)
    {
        this.que = que;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }


    public String getEndAnswerTimeString()
    {
        if (endAnswerTime != 0)
        {
            return Utl.getTimeString("yy_MM_dd HH:mm", endAnswerTime);
        }
        return null;
    }
}
