package cn.eclassmate.qzy.viewmodel.weixin;

import java.io.Serializable;

public class ShareSignature implements Serializable
{
    private static final long serialVersionUID = -2398222236639473343L;

    private String appId;
    private String nonceStr;
    private String timestamp;
    private String signature;

    public String getAppId()
    {
        return appId;
    }

    public void setAppId(String appId)
    {
        this.appId = appId;
    }

    public String getNonceStr()
    {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr)
    {
        this.nonceStr = nonceStr;
    }

    public String getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(String timestamp)
    {
        this.timestamp = timestamp;
    }

    public String getSignature()
    {
        return signature;
    }

    public void setSignature(String signature)
    {
        this.signature = signature;
    }

}
