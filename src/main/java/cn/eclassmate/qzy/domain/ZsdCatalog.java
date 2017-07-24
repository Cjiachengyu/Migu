package cn.eclassmate.qzy.domain;

import java.io.Serializable;

public class ZsdCatalog implements Serializable
{
    private static final long serialVersionUID = 2609369521698363231L;

    private int zskId;
    private int zsd1Id;
    private int zsd2Id;
    private String zskName;
    private String zsd1Name;
    private String zsd2Name;

    //附加存取器
    //--------------------------------------------------------------------------------
    public String getZsdString()
    {
        if (zsd2Id != 0)
        {
            return zskName + " - " + zsd2Name;
        }
        else if (zsd1Id != 0)
        {
            return zskName + " - " + zsd1Name;
        }
        else if (zskId != 0)
        {
            return zskName;
        }
        else
        {
            return "随机知识点";
        }
    }

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

    public String getZskName()
    {
        return zskName;
    }

    public void setZskName(String zskName)
    {
        this.zskName = zskName;
    }

    public String getZsd1Name()
    {
        return zsd1Name;
    }

    public void setZsd1Name(String zsd1Name)
    {
        this.zsd1Name = zsd1Name;
    }

    public String getZsd2Name()
    {
        return zsd2Name;
    }

    public void setZsd2Name(String zsd2Name)
    {
        this.zsd2Name = zsd2Name;
    }


}
