package cn.eclassmate.qzy.viewmodel.subview;

import java.io.Serializable;

import cn.eclassmate.qzy.domain.QueChoice;

/**
 * Created by stao on 2015/5/8.
 */
public class TitledQueChoice implements Serializable
{
    private static final long serialVersionUID = 9059471908757364660L;

    private String title;               // 在显示解析的时候，显示ABCD
    private QueChoice queChoice;
    private boolean isUserSelected;     // 在显示解析的时候，显示是否选中
    private boolean isUserRight;        // 在显示解析的时候，显示红色还是绿色

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

    public boolean getIsUserSelected()
    {
        return isUserSelected;
    }

    public void setIsUserSelected(boolean isUserSelected)
    {
        this.isUserSelected = isUserSelected;
    }

    public boolean getIsUserRight()
    {
        return isUserRight;
    }

    public void setIsUserRight(boolean isUserRight)
    {
        this.isUserRight = isUserRight;
    }

}
