package cn.eclassmate.qzy.domain;

import cn.eclassmate.qzy.global.Cst;

import java.io.Serializable;

public class Admin implements Serializable
{
    private static final long serialVersionUID = 2817319975432247327L;

    // editor
    // --------------------------------------------------------------------------------
    private int adminId;
    private String adminName;
    private String loginName;
    private String password;

    // getter setter
    // --------------------------------------------------------------------------------
    public int getAdminId()
    {
        return adminId;
    }

    public void setAdminId(int adminId)
    {
        this.adminId = adminId;
    }

    public String getAdminName()
    {
        return adminName;
    }

    public void setAdminName(String adminName)
    {
        this.adminName = adminName;
    }

    public String getLoginName()
    {
        return loginName;
    }

    public void setLoginName(String loginName)
    {
        this.loginName = loginName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

}
