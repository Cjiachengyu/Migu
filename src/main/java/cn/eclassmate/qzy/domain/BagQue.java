package cn.eclassmate.qzy.domain;

import java.io.Serializable;

public class BagQue implements Serializable
{
    private static final long serialVersionUID = 4948332885509689058L;

    // bag_que
    // --------------------------------------------------------------------------------
    private int bagId;
    private int queId;

    // 附加字段
    // --------------------------------------------------------------------------------

    // 附加存取器
    // --------------------------------------------------------------------------------

    // getter, setter
    // --------------------------------------------------------------------------------
    public int getBagId()
    {
        return bagId;
    }

    public void setBagId(int bagId)
    {
        this.bagId = bagId;
    }

    public int getQueId()
    {
        return queId;
    }

    public void setQueId(int queId)
    {
        this.queId = queId;
    }

}
