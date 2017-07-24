package cn.eclassmate.qzy.domain;

import java.io.Serializable;
import java.util.List;

public class Zsd1 implements Serializable
{
    private static final long serialVersionUID = 7463422536053920690L;

    // zsd_1
    // --------------------------------------------------------------------------------
    private int zsd1Id;
    private String zsd1Name;
    private int zskId;
    private List<Zsd2> zsd2List;

    // getter, setter
    // --------------------------------------------------------------------------------
    public String getZsd1Name()
    {
        return zsd1Name;
    }

    public void setZsd1Name(String zsd1Name)
    {
        this.zsd1Name = zsd1Name;
    }

    public int getZsd1Id()
    {
        return zsd1Id;
    }

    public void setZsd1Id(int zsd1Id)
    {
        this.zsd1Id = zsd1Id;
    }

    public int getZskId()
    {
        return zskId;
    }

    public void setZskId(int zskId)
    {
        this.zskId = zskId;
    }

    public List<Zsd2> getZsd2List()
    {
        return zsd2List;
    }

    public void setZsd2List(List<Zsd2> zsd2List)
    {
        this.zsd2List = zsd2List;
    }

    

}
