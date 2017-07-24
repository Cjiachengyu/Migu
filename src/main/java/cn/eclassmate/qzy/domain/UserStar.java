package cn.eclassmate.qzy.domain;

import java.io.Serializable;

public class UserStar implements Serializable
{
    private static final long serialVersionUID = 3034800697631818686L;

    private int userId;
    private int zskId;
    private int zsd1Id;
    private int zsd2Id;
    private int star1Status = 1;
    private int star2Status = 1;
    private int star3Status = 1;

    // getter, setter
    // --------------------------------------------------------------------------------
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

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public int getStar3Status()
    {
        return star3Status;
    }

    public void setStar3Status(int star3Status)
    {
        this.star3Status = star3Status;
    }

    public int getStar2Status()
    {
        return star2Status;
    }

    public void setStar2Status(int star2Status)
    {
        this.star2Status = star2Status;
    }

    public int getStar1Status()
    {
        return star1Status;
    }

    public void setStar1Status(int star1Status)
    {
        this.star1Status = star1Status;
    }

}
