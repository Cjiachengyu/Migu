package cn.eclassmate.qzy.domain;

import java.io.Serializable;

public class ShowPrize extends Show implements Serializable
{
    private static final long serialVersionUID = 4185389025124708253L;

    // fields
    // --------------------------------------------------------------------------------
    private int prizeId;

    // 关联对象
    // --------------------------------------------------------------------------------
    private Prize prize;

    // getter, setter
    // --------------------------------------------------------------------------------
    public Prize getPrize()
    {
        return prize;
    }

    public void setPrize(Prize prize)
    {
        this.prize = prize;
    }

    public int getPrizeId()
    {
        return prizeId;
    }

    public void setPrizeId(int prizeId)
    {
        this.prizeId = prizeId;
    }

}
