package cn.eclassmate.qzy.viewmodel.subview;

import cn.eclassmate.qzy.domain.UserAnswer;
import cn.eclassmate.qzy.domain.UserMoon;
import cn.eclassmate.qzy.domain.Zsd1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by stao on 2015/5/26.
 */
public class Zsd1Report implements Serializable
{
    private static final long serialVersionUID = 2937043318606532904L;

    private Zsd1 zsd1;
    private List<Zsd2Report> zsd2ReportList;


    private List<UserAnswer> allUserAnswerList = new ArrayList<UserAnswer>();
    private List<UserAnswer> rightUserAnswerList = new ArrayList<UserAnswer>();

    private double rightPercent;
    private UserMoon userMoon;

    // getter, setter
    // --------------------------------------------------------------------------------
    public List<Zsd2Report> getZsd2ReportList()
    {
        return zsd2ReportList;
    }

    public void setZsd2ReportList(List<Zsd2Report> zsd2ReportList)
    {
        this.zsd2ReportList = zsd2ReportList;
    }

    public Zsd1 getZsd1()
    {
        return zsd1;
    }

    public void setZsd1(Zsd1 zsd1)
    {
        this.zsd1 = zsd1;
    }


    public List<UserAnswer> getAllUserAnswerList()
    {
        return allUserAnswerList;
    }

    public void setAllUserAnswerList(List<UserAnswer> allUserAnswerList)
    {
        this.allUserAnswerList = allUserAnswerList;
    }

    public List<UserAnswer> getRightUserAnswerList()
    {
        return rightUserAnswerList;
    }

    public void setRightUserAnswerList(List<UserAnswer> rightUserAnswerList)
    {
        this.rightUserAnswerList = rightUserAnswerList;
    }

    public double getRightPercent()
    {
        return rightPercent;
    }

    public void setRightPercent(double rightPercent)
    {
        this.rightPercent = rightPercent;
    }

    public UserMoon getUserMoon()
    {
        return userMoon;
    }

    public void setUserMoon(UserMoon userMoon)
    {
        this.userMoon = userMoon;
    }


}
