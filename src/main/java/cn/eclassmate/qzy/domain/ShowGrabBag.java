package cn.eclassmate.qzy.domain;

import java.io.Serializable;

public class ShowGrabBag extends Show implements Serializable
{
    private static final long serialVersionUID = 4185389025124708253L;

    // fields
    // --------------------------------------------------------------------------------
    private int showGrabBagType;
    private int userId;
    private int bagId;

    // 关联对象
    // --------------------------------------------------------------------------------
    private UserBag userBag;

    // getter, setter
    // --------------------------------------------------------------------------------
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

    public int getShowGrabBagType()
    {
        return showGrabBagType;
    }

    public void setShowGrabBagType(int showGrabBagType)
    {
        this.showGrabBagType = showGrabBagType;
    }

    public UserBag getUserBag()
    {
        return userBag;
    }

    public void setUserBag(UserBag userBag)
    {
        this.userBag = userBag;
    }

}
