package cn.eclassmate.qzy.domain;

import java.io.Serializable;

public class Show implements Serializable
{
    private static final long serialVersionUID = 4185389025124708253L;

    // fields
    // --------------------------------------------------------------------------------
    private int showId;
    private int showType;
    private int showTime;
    private int showerId;

    // 关联属性
    // --------------------------------------------------------------------------------
    private User shower;

    // getter, setter
    // --------------------------------------------------------------------------------
    public int getShowerId()
    {
        return showerId;
    }

    public void setShowerId(int showerId)
    {
        this.showerId = showerId;
    }

    public int getShowTime()
    {
        return showTime;
    }

    public void setShowTime(int showTime)
    {
        this.showTime = showTime;
    }

    public int getShowType()
    {
        return showType;
    }

    public void setShowType(int showType)
    {
        this.showType = showType;
    }

    public int getShowId()
    {
        return showId;
    }

    public void setShowId(int showId)
    {
        this.showId = showId;
    }

    public User getShower()
    {
        return shower;
    }

    public void setShower(User shower)
    {
        this.shower = shower;
    }

}
