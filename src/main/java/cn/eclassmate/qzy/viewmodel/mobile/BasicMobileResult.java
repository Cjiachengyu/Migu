package cn.eclassmate.qzy.viewmodel.mobile;

import java.io.Serializable;

public class BasicMobileResult implements Serializable
{
    private static final long serialVersionUID = -8186273773015253889L;

    public BasicMobileResult()
    {

    }

    public BasicMobileResult(int errorCode)
    {
        this.errorCode = errorCode;
    }

    // fields
    // --------------------------------------------------------------------------------
    int errorCode;

    // getter, setter
    // --------------------------------------------------------------------------------
    public int getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(int errorCode)
    {
        this.errorCode = errorCode;
    }
}
