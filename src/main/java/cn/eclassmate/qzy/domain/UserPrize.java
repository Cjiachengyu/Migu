package cn.eclassmate.qzy.domain;

import java.io.Serializable;

public class UserPrize implements Serializable
{
    private static final long serialVersionUID = -4212828308397529653L;

    // user_prize
    // --------------------------------------------------------------------------------
    private int userId;
    private int prizeId;
    private int prizeCount;

    // 关联对象
    // --------------------------------------------------------------------------------
    private Prize prize;

    // 附加字段
    // --------------------------------------------------------------------------------

    // 附加存取器
    // --------------------------------------------------------------------------------

    // getter, setter
    // --------------------------------------------------------------------------------
    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public int getPrizeId()
    {
        return prizeId;
    }

    public void setPrizeId(int prizeId)
    {
        this.prizeId = prizeId;
    }

    public int getPrizeCount()
    {
        return prizeCount;
    }

    public void setPrizeCount(int prizeCount)
    {
        this.prizeCount = prizeCount;
    }

    public Prize getPrize()
    {
        return prize;
    }

    public void setPrize(Prize prize)
    {
        this.prize = prize;
    }


}
