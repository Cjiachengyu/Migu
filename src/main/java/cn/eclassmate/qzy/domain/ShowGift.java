package cn.eclassmate.qzy.domain;

import java.io.Serializable;

public class ShowGift extends Show implements Serializable
{
    private static final long serialVersionUID = 4185389025124708253L;

    // fields
    // --------------------------------------------------------------------------------
    private int giftId;
    private int showGiftType;

    // 关联对象
    // --------------------------------------------------------------------------------
    private Gift gift;

    // getter, setter
    // --------------------------------------------------------------------------------
    public int getGiftId()
    {
        return giftId;
    }

    public void setGiftId(int giftId)
    {
        this.giftId = giftId;
    }

    public int getShowGiftType()
    {
        return showGiftType;
    }

    public void setShowGiftType(int showGiftType)
    {
        this.showGiftType = showGiftType;
    }

    public Gift getGift()
    {
        return gift;
    }

    public void setGift(Gift gift)
    {
        this.gift = gift;
    }

}
