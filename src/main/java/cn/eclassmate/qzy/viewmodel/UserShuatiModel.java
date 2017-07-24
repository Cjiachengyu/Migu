package cn.eclassmate.qzy.viewmodel;

import cn.eclassmate.qzy.domain.UserAnswer;
import cn.eclassmate.qzy.domain.Zsl;
import cn.eclassmate.qzy.viewmodel.subview.Zsd1Report;

import java.util.List;
import java.util.Map;

public class UserShuatiModel extends AbstractModel
{
    private static final long serialVersionUID = 1886085397027390750L;

    // fields
    // --------------------------------------------------------------------------------
    private List<Zsl> zslList;
    private List<Zsd1Report> zsd1ReportList;    // 知识点报告

    // getter, setter
    // --------------------------------------------------------------------------------
    public List<Zsd1Report> getZsd1ReportList()
    {
        return zsd1ReportList;
    }

    public void setZsd1ReportList(List<Zsd1Report> zsd1ReportList)
    {
        this.zsd1ReportList = zsd1ReportList;
    }

    public List<Zsl> getZslList()
    {
        return zslList;
    }

    public void setZslList(List<Zsl> zslList)
    {
        this.zslList = zslList;
    }

}
