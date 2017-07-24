package cn.eclassmate.qzy.viewmodel;

import cn.eclassmate.qzy.domain.Prize;

import java.util.List;

public class EditorPrizeListModel extends AbstractModel
{
    private static final long serialVersionUID = 4483750922490542228L;

    List<Prize> prizeList;

    // model getter, setter
    // --------------------------------------------------------------------------------

    public List<Prize> getPrizeList()
    {
        return prizeList;
    }

    public void setPrizeList(List<Prize> prizeList)
    {
        this.prizeList = prizeList;
    }

}
