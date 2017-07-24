package cn.eclassmate.qzy.viewmodel.subview;

import java.io.Serializable;

/**
 * Created by stao on 2015/5/26.
 */
public class ShareInfo implements Serializable
{
    private static final long serialVersionUID = 9059471908757364660L;

    private String shareMsg;    // 一定有
    private String shareTitle;  // 可有可无，分享给微信好友时会用到
    private String shareImage;
    private String shareLink;

    private String sucessTip;
    private String sucessImage;
    private String successCallback;

    public String getShareMsg()
    {
        return shareMsg;
    }

    public void setShareMsg(String shareMsg)
    {
        this.shareMsg = shareMsg;
    }

    public String getShareTitle()
    {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle)
    {
        this.shareTitle = shareTitle;
    }

    public String getShareImage()
    {
        return shareImage;
    }

    public void setShareImage(String shareImage)
    {
        this.shareImage = shareImage;
    }

    public String getShareLink()
    {
        return shareLink;
    }

    public void setShareLink(String shareLink)
    {
        this.shareLink = shareLink;
    }

    public String getSucessTip()
    {
        return sucessTip;
    }

    public void setSucessTip(String sucessTip)
    {
        this.sucessTip = sucessTip;
    }

    public String getSucessImage()
    {
        return sucessImage;
    }

    public void setSucessImage(String sucessImage)
    {
        this.sucessImage = sucessImage;
    }

    public String getSuccessCallback()
    {
        return successCallback;
    }

    public void setSuccessCallback(String successCallback)
    {
        this.successCallback = successCallback;
    }


}
