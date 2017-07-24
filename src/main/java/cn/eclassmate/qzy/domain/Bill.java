package cn.eclassmate.qzy.domain;

import cn.eclassmate.qzy.global.Utl;

import java.io.Serializable;

public class Bill implements Serializable
{
    private static final long serialVersionUID = -4591681944922719628L;

    // bag
    // --------------------------------------------------------------------------------
    private int billId;
    private int billType;
    private int userId;
    private int money;
    private int createTime;
    private String description;


    // 附加存取器
    // --------------------------------------------------------------------------------
    public String getCreateTimeString()
    {
        return Utl.getTimeString_MM_dd_HH_mm(createTime);
    }


    // getter, setter
    // --------------------------------------------------------------------------------
    public int getBillId()
    {
        return billId;
    }


    public void setBillId(int billId)
    {
        this.billId = billId;
    }


    public int getBillType()
    {
        return billType;
    }


    public void setBillType(int billType)
    {
        this.billType = billType;
    }


    public int getUserId()
    {
        return userId;
    }


    public void setUserId(int userId)
    {
        this.userId = userId;
    }


    public int getMoney()
    {
        return money;
    }


    public void setMoney(int money)
    {
        this.money = money;
    }


    public int getCreateTime()
    {
        return createTime;
    }


    public void setCreateTime(int createTime)
    {
        this.createTime = createTime;
    }


    public String getDescription()
    {
        return description;
    }


    public void setDescription(String description)
    {
        this.description = description;
    }


}
