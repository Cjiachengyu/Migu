package cn.eclassmate.qzy.domain;

import java.io.Serializable;
import java.util.List;

public class Zsl implements Serializable
{
    private static final long serialVersionUID = 6243886517396921647L;

    // zsl
    // --------------------------------------------------------------------------------
    private int zslId;
    private String zslName;

    private List<Zsk> zskList;

    // getter, setter
    // --------------------------------------------------------------------------------
    public int getZslId()
    {
        return zslId;
    }

    public void setZslId(int zslId)
    {
        this.zslId = zslId;
    }

    public String getZslName()
    {
        return zslName;
    }

    public void setZslName(String zslName)
    {
        this.zslName = zslName;
    }

    public List<Zsk> getZskList()
    {
        return zskList;
    }

    public void setZskList(List<Zsk> zskList)
    {
        this.zskList = zskList;
    }


}
