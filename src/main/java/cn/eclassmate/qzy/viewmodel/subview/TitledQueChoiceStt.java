package cn.eclassmate.qzy.viewmodel.subview;

import cn.eclassmate.qzy.domain.QueChoice;
import cn.eclassmate.qzy.domain.UserBag;

import java.io.Serializable;
import java.util.List;

/**
 * 选项统计信息(Stt=Statistics)
 * Created by stao on 2015/5/8.
 */
public class TitledQueChoiceStt implements Serializable
{
    private String title;               // 在显示解析的时候，显示ABCD
    private QueChoice queChoice;
    private List<UserBag> checkedUsers;    // 统计有哪些学生选了这个选项
    private double rightPercent;        // 选项正确率，精确到小数点后1位

    // getter, setter
    // --------------------------------------------------------------------------------
    public QueChoice getQueChoice()
    {
        return queChoice;
    }

    public void setQueChoice(QueChoice queChoice)
    {
        this.queChoice = queChoice;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public double getRightPercent()
    {
        return rightPercent;
    }

    public void setRightPercent(double rightPercent)
    {
        this.rightPercent = rightPercent;
    }

    public List<UserBag> getCheckedUsers()
    {
        return checkedUsers;
    }

    public void setCheckedUsers(List<UserBag> checkedUsers)
    {
        this.checkedUsers = checkedUsers;
    }

}
