package cn.eclassmate.qzy.domain;

import java.io.Serializable;

public class QueZsd implements Serializable
{
    private static final long serialVersionUID = -5667808349120535116L;

    private int queId;
    private int zskId;
    private int zsd1Id;
    private int zsd2Id;

    // getter, setter
    // --------------------------------------------------------------------------------
    public int getQueId()
    {
        return queId;
    }

    public void setQueId(int queId)
    {
        this.queId = queId;
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


}
