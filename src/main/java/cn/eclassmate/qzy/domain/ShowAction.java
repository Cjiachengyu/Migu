package cn.eclassmate.qzy.domain;

import java.io.Serializable;

public class ShowAction implements Serializable
{
    private static final long serialVersionUID = -5667808349120535116L;

    private int showId;
    private int userId;
    private int actionType;

    // getter, setter
    // --------------------------------------------------------------------------------
    public int getShowId()
    {
        return showId;
    }

    public void setShowId(int showId)
    {
        this.showId = showId;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public int getActionType()
    {
        return actionType;
    }

    public void setActionType(int actionType)
    {
        this.actionType = actionType;
    }


}
