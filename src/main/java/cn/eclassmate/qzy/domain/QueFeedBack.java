package cn.eclassmate.qzy.domain;

import java.io.Serializable;

public class QueFeedBack implements Serializable
{
    private static final long serialVersionUID = -5667808349120535116L;

    private int queFeedbackId;
    private int queId;
    private int userId;
    private String feedbackMsg;

    public int getQueFeedbackId()
    {
        return queFeedbackId;
    }

    public void setQueFeedbackId(int queFeedbackId)
    {
        this.queFeedbackId = queFeedbackId;
    }

    public int getQueId()
    {
        return queId;
    }

    public void setQueId(int queId)
    {
        this.queId = queId;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public String getFeedbackMsg()
    {
        return feedbackMsg;
    }

    public void setFeedbackMsg(String feedbackMsg)
    {
        this.feedbackMsg = feedbackMsg;
    }

}
