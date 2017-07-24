package cn.eclassmate.qzy.viewmodel;

import cn.eclassmate.qzy.domain.Prize;

import java.util.List;

public class UserPrizeModel extends AbstractModel
{
    private static final long serialVersionUID = -4464423277174176553L;

    // fields
    // --------------------------------------------------------------------------------
    List<Prize> prizeList;

    // getter, setter
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
