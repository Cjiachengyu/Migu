package cn.eclassmate.qzy.domain;

import cn.eclassmate.qzy.global.Cst;

import java.io.Serializable;

public class Editor implements Serializable
{
    private static final long serialVersionUID = 7778233938677118934L;

    // editor
    // --------------------------------------------------------------------------------
    private int editorId;
    private String editorName;
    private String loginName;
    private String password;
    private int privilegeMask;
    private boolean isChecker;

    // 附加存取器
    // --------------------------------------------------------------------------------
    public boolean getCanOperateQue()
    {
        return (privilegeMask & Cst.EditorPrivilege.CAN_OPERATE_QUE) > 0;
    }

    public boolean getCanOperatePrize()
    {
        return (privilegeMask & Cst.EditorPrivilege.CAN_OPERATE_PRIZE) > 0;
    }


    // getter, setter
    // --------------------------------------------------------------------------------
    public int getPrivilegeMask()
    {
        return privilegeMask;
    }

    public void setPrivilegeMask(int privilegeMask)
    {
        this.privilegeMask = privilegeMask;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getLoginName()
    {
        return loginName;
    }

    public void setLoginName(String loginName)
    {
        this.loginName = loginName;
    }

    public String getEditorName()
    {
        return editorName;
    }

    public void setEditorName(String editorName)
    {
        this.editorName = editorName;
    }

    public int getEditorId()
    {
        return editorId;
    }

    public void setEditorId(int editorId)
    {
        this.editorId = editorId;
    }

    public boolean getIsChecker()
    {
        return isChecker;
    }

    public void setIsChecker(boolean isChecker)
    {
        this.isChecker = isChecker;
    }

}
