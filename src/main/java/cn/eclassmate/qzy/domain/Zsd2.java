package cn.eclassmate.qzy.domain;

import java.io.Serializable;

public class Zsd2 implements Serializable
{
    private static final long serialVersionUID = 1808317663356037551L;

    // zsd_1
    // --------------------------------------------------------------------------------
    private int zsd2Id;
    private String zsd2Name;
    private int zsd1Id;

    // getter, setter
    // --------------------------------------------------------------------------------
    public String getZsd2Name()
    {
        return zsd2Name;
    }

    public void setZsd2Name(String zsd2Name)
    {
        this.zsd2Name = zsd2Name;
    }

    public int getZsd2Id()
    {
        return zsd2Id;
    }

    public void setZsd2Id(int zsd2Id)
    {
        this.zsd2Id = zsd2Id;
    }

    public int getZsd1Id()
    {
        return zsd1Id;
    }

    public void setZsd1Id(int zsd1Id)
    {
        this.zsd1Id = zsd1Id;
    }

}
