package cn.eclassmate.qzy.viewmodel.subview;

import cn.eclassmate.qzy.domain.UserAnswer;
import cn.eclassmate.qzy.domain.UserStar;
import cn.eclassmate.qzy.domain.Zsd2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by stao on 2015/5/26.
 */
public class Zsd2Report implements Serializable
{
    private static final long serialVersionUID = -80288156610348295L;

    private Zsd2 zsd2;
    private List<UserAnswer> allUserAnswerList = new ArrayList<UserAnswer>();
    private List<UserAnswer> rightUserAnswerList = new ArrayList<UserAnswer>();
    private double rightPercent;
    private UserStar userStar;

    // getter, setter
    // --------------------------------------------------------------------------------
    public Zsd2 getZsd2()
    {
        return zsd2;
    }

    public void setZsd2(Zsd2 zsd2)
    {
        this.zsd2 = zsd2;
    }

    public List<UserAnswer> getRightUserAnswerList()
    {
        return rightUserAnswerList;
    }

    public void setRightUserAnswerList(List<UserAnswer> rightUserAnswerList)
    {
        this.rightUserAnswerList = rightUserAnswerList;
    }

    public List<UserAnswer> getAllUserAnswerList()
    {
        return allUserAnswerList;
    }

    public void setAllUserAnswerList(List<UserAnswer> allUserAnswerList)
    {
        this.allUserAnswerList = allUserAnswerList;
    }

    public double getRightPercent()
    {
        return rightPercent;
    }

    public void setRightPercent(double rightPercent)
    {
        this.rightPercent = rightPercent;
    }

    public UserStar getUserStar()
    {
        return userStar;
    }

    public void setUserStar(UserStar userStar)
    {
        this.userStar = userStar;
    }

}
