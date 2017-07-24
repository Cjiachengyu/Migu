package cn.eclassmate.qzy.domain;

import cn.eclassmate.qzy.global.Utl;

import java.io.Serializable;

public class Gift implements Serializable
{
    private static final long serialVersionUID = -4013573473020500518L;

    // fields
    // --------------------------------------------------------------------------------
    private int giftId;
    private int prizeId;
    private int senderId;
    private int receiverId;
    private int sendTime;
    private int receiveTime;

    //关联对象
    // --------------------------------------------------------------------------------
    Prize prize;
    User sender;
    User receiver;

    // 附加存取器
    // --------------------------------------------------------------------------------
    public String getSendTimeString()
    {
        return Utl.getTimeString_MM_dd(sendTime);
    }

    public String getReceiveTimeString()
    {
        return Utl.getTimeString_MM_dd(receiveTime);
    }

    // getter, setter
    // --------------------------------------------------------------------------------
    public int getGiftId()
    {
        return giftId;
    }

    public void setGiftId(int giftId)
    {
        this.giftId = giftId;
    }

    public int getPrizeId()
    {
        return prizeId;
    }

    public void setPrizeId(int prizeId)
    {
        this.prizeId = prizeId;
    }

    public int getSenderId()
    {
        return senderId;
    }

    public void setSenderId(int senderId)
    {
        this.senderId = senderId;
    }

    public int getReceiverId()
    {
        return receiverId;
    }

    public void setReceiverId(int receiverId)
    {
        this.receiverId = receiverId;
    }

    public int getSendTime()
    {
        return sendTime;
    }

    public void setSendTime(int sendTime)
    {
        this.sendTime = sendTime;
    }

    public int getReceiveTime()
    {
        return receiveTime;
    }

    public void setReceiveTime(int receiveTime)
    {
        this.receiveTime = receiveTime;
    }

    public Prize getPrize()
    {
        return prize;
    }

    public void setPrize(Prize prize)
    {
        this.prize = prize;
    }

    public User getSender()
    {
        return sender;
    }

    public void setSender(User sender)
    {
        this.sender = sender;
    }

    public User getReceiver()
    {
        return receiver;
    }

    public void setReceiver(User receiver)
    {
        this.receiver = receiver;
    }

}
