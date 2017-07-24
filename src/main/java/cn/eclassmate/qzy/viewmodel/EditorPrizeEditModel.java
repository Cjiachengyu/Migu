package cn.eclassmate.qzy.viewmodel;

import cn.eclassmate.qzy.domain.Prize;

public class EditorPrizeEditModel extends AbstractModel
{

    private static final long serialVersionUID = 4483750922490542228L;

    public static final int OPER_TYPE_CREATE = 1;
    public static final int OPER_TYPE_MODIFY = 2;

    private int operationType;

    Prize prize;

    // model getter, setter
    // --------------------------------------------------------------------------------
    public int getOperationType()
    {
        return operationType;
    }

    public void setOperationType(int operationType)
    {
        this.operationType = operationType;
    }

    public Prize getPrize()
    {
        return prize;
    }

    public void setPrize(Prize prize)
    {
        this.prize = prize;
    }
}
