package cn.eclassmate.qzy.domain;

import cn.eclassmate.qzy.viewmodel.subview.Zsd1Report;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Zsk implements Serializable
{
    private static final long serialVersionUID = 6243886517396921647L;

    // zsd
    // --------------------------------------------------------------------------------
    private int zskId;
    private int zslId;
    private String zskName;
    private List<Zsd1> zsd1List;

    private int isDisplay;//用于learningReports
    private List<Zsd1Report> zsd1ReportList = new ArrayList<Zsd1Report>();//for learning reports

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

    public String getZskName()
    {
        return zskName;
    }

    public void setZskName(String zskName)
    {
        this.zskName = zskName;
    }

    public List<Zsd1> getZsd1List()
    {
        return zsd1List;
    }

    public void setZsd1List(List<Zsd1> zsd1List)
    {
        this.zsd1List = zsd1List;
    }

    public int getZslId()
    {
        return zslId;
    }

    public void setZslId(int zslId)
    {
        this.zslId = zslId;
    }

    public int getIsDisplay()
    {
        return isDisplay;
    }

    public void setIsDisplay(int isDisplay)
    {
        this.isDisplay = isDisplay;
    }

    public List<Zsd1Report> getZsd1ReportList()
    {
        return zsd1ReportList;
    }

    public void setZsd1ReportList(List<Zsd1Report> zsd1ReportList)
    {
        this.zsd1ReportList = zsd1ReportList;
    }


}
