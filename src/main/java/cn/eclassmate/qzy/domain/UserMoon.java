package cn.eclassmate.qzy.domain;

import java.io.Serializable;

public class UserMoon implements Serializable
{
    private static final long serialVersionUID = 3034800697631818686L;

    private int userId;
    private int zskId;
    private int zsd1Id;
    private int moon1Status = 1;
    private int moon2Status = 1;
    private int moon3Status = 1;

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

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public int getMoon1Status()
    {
        return moon1Status;
    }

    public void setMoon1Status(int moon1Status)
    {
        this.moon1Status = moon1Status;
    }

    public int getMoon2Status()
    {
        return moon2Status;
    }

    public void setMoon2Status(int moon2Status)
    {
        this.moon2Status = moon2Status;
    }

    public int getMoon3Status()
    {
        return moon3Status;
    }

    public void setMoon3Status(int moon3Status)
    {
        this.moon3Status = moon3Status;
    }

}
