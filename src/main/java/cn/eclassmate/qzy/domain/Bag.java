package cn.eclassmate.qzy.domain;

import cn.eclassmate.qzy.global.Cst;
import cn.eclassmate.qzy.global.Utl;

import java.io.Serializable;
import java.util.List;

public class Bag implements Serializable
{
    private static final long serialVersionUID = -4591681944922719628L;

    // bag
    // --------------------------------------------------------------------------------
    private int bagId;
    private int bagCategory;
    private int bagStatus;
    private int bagCount;
    private int bagMoney;
    private String bagMsg;
    private int bagType;
    private int creatorId;
    private int createTime;
    private int zskId;
    private int zsd1Id;
    private int zsd2Id;
    private int grabbedCount = 0;   // 多少人来抢了，不管有没有抢到
    private int sentCount = 0;      // 多少人抢到了
    private int sentMoney = 0;      // 被抢了多少钱
    private int returnedMoney = 0;  // 系统返还了多少钱

    // 关联字段
    // --------------------------------------------------------------------------------
    private List<Que> queList;
    private ZsdCatalog zsdCatalog;
    private User creator;

    // 附加存取器
    // --------------------------------------------------------------------------------
    public String getCreateTimeString()
    {
        return Utl.getTimeString_MM_dd_HH_mm(createTime);
    }

    public String getBagTypeString()
    {
        if (bagType == Cst.BagType.PINSHOUQI)
        {
            return "拼手气红包";
        }
        else
        {
            return "";
        }
    }

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

    public int getBagMoney()
    {
        return bagMoney;
    }

    public void setBagMoney(int bagMoney)
    {
        this.bagMoney = bagMoney;
    }

    public int getBagType()
    {
        return bagType;
    }

    public void setBagType(int bagType)
    {
        this.bagType = bagType;
    }

    public String getBagMsg()
    {
        return bagMsg;
    }

    public void setBagMsg(String bagMsg)
    {
        this.bagMsg = bagMsg;
    }

    public int getBagCount()
    {
        return bagCount;
    }

    public void setBagCount(int bagCount)
    {
        this.bagCount = bagCount;
    }

    public int getCreatorId()
    {
        return creatorId;
    }

    public void setCreatorId(int creatorId)
    {
        this.creatorId = creatorId;
    }

    public int getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(int createTime)
    {
        this.createTime = createTime;
    }

    public int getSentCount()
    {
        return sentCount;
    }

    public void setSentCount(int sentCount)
    {
        this.sentCount = sentCount;
    }

    public int getSentMoney()
    {
        return sentMoney;
    }

    public void setSentMoney(int sentMoney)
    {
        this.sentMoney = sentMoney;
    }

    public int getZskId()
    {
        return zskId;
    }

    public void setZskId(int zskId)
    {
        this.zskId = zskId;
    }

    public int getZsd1Id()
    {
        return zsd1Id;
    }

    public void setZsd1Id(int zsd1Id)
    {
        this.zsd1Id = zsd1Id;
    }

    public int getZsd2Id()
    {
        return zsd2Id;
    }

    public void setZsd2Id(int zsd2Id)
    {
        this.zsd2Id = zsd2Id;
    }

    public List<Que> getQueList()
    {
        return queList;
    }

    public void setQueList(List<Que> queList)
    {
        this.queList = queList;
    }

    public User getCreator()
    {
        return creator;
    }

    public void setCreator(User creator)
    {
        this.creator = creator;
    }

    public ZsdCatalog getZsdCatalog()
    {
        return zsdCatalog;
    }

    public void setZsdCatalog(ZsdCatalog zsdCatalog)
    {
        this.zsdCatalog = zsdCatalog;
    }

    public int getBagCategory()
    {
        return bagCategory;
    }

    public void setBagCategory(int bagCategory)
    {
        this.bagCategory = bagCategory;
    }

    public int getGrabbedCount()
    {
        return grabbedCount;
    }

    public void setGrabbedCount(int grabbedCount)
    {
        this.grabbedCount = grabbedCount;
    }

    public int getReturnedMoney()
    {
        return returnedMoney;
    }

    public void setReturnedMoney(int returnedMoney)
    {
        this.returnedMoney = returnedMoney;
    }

    public int getBagStatus()
    {
        return bagStatus;
    }

    public void setBagStatus(int bagStatus)
    {
        this.bagStatus = bagStatus;
    }


}
