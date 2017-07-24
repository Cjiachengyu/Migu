package cn.eclassmate.qzy.domain;

import java.io.Serializable;

public class QueChoice implements Serializable
{
    private static final long serialVersionUID = 2773917502944305310L;

    // que
    // --------------------------------------------------------------------------------
    private int queChoiceId;
    private int queId;
    private String queChoiceHtml;
    private boolean isRightAnswer;

    // 附加字段
    // --------------------------------------------------------------------------------

    // 附加存取器
    // --------------------------------------------------------------------------------

    // getter, setter
    // --------------------------------------------------------------------------------
    public int getQueChoiceId()
    {
        return queChoiceId;
    }

    public void setQueChoiceId(int queChoiceId)
    {
        this.queChoiceId = queChoiceId;
    }

    public int getQueId()
    {
        return queId;
    }

    public void setQueId(int queId)
    {
        this.queId = queId;
    }

    public String getQueChoiceHtml()
    {
        return queChoiceHtml;
    }

    public void setQueChoiceHtml(String queChoiceHtml)
    {
        this.queChoiceHtml = queChoiceHtml;
    }

    public boolean getIsRightAnswer()
    {
        return isRightAnswer;
    }

    public void setIsRightAnswer(boolean isRightAnswer)
    {
        this.isRightAnswer = isRightAnswer;
    }


}
