package cn.eclassmate.qzy.domain;

import java.io.Serializable;

public class RequestRecord implements Serializable
{
    private static final long serialVersionUID = -1233622778451379089L;

    // requestRecord
    // --------------------------------------------------------------------------------
    private int requestId;
    private long enterTime;
    private long leaveTime;
    private long consumeTime;
    private int userId;
    private String fromIp;
    private String requestUrl;
    private String requestParam;
    private String requestHeader;


    // getter, setter
    // --------------------------------------------------------------------------------
    public int getRequestId()
    {
        return requestId;
    }

    public void setRequestId(int requestId)
    {
        this.requestId = requestId;
    }

    public long getEnterTime()
    {
        return enterTime;
    }

    public void setEnterTime(long enterTime)
    {
        this.enterTime = enterTime;
    }

    public long getLeaveTime()
    {
        return leaveTime;
    }

    public void setLeaveTime(long leaveTime)
    {
        this.leaveTime = leaveTime;
    }

    public long getConsumeTime()
    {
        return consumeTime;
    }

    public void setConsumeTime(long consumeTime)
    {
        this.consumeTime = consumeTime;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public String getFromIp()
    {
        return fromIp;
    }

    public void setFromIp(String fromIp)
    {
        this.fromIp = fromIp;
    }

    public String getRequestUrl()
    {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl)
    {
        this.requestUrl = requestUrl;
    }

    public String getRequestParam()
    {
        return requestParam;
    }

    public void setRequestParam(String requestParam)
    {
        this.requestParam = requestParam;
    }

    public String getRequestHeader()
    {
        return requestHeader;
    }

    public void setRequestHeader(String requestHeader)
    {
        this.requestHeader = requestHeader;
    }


}
