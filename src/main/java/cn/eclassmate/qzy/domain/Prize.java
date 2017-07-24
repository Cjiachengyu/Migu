package cn.eclassmate.qzy.domain;

import cn.eclassmate.qzy.global.Cst;
import cn.eclassmate.qzy.global.Utl;

import java.io.Serializable;

public class Prize implements Serializable
{
    private static final long serialVersionUID = -3394255857805001462L;

    // prize
    // --------------------------------------------------------------------------------
    private int prizeId;
    private String prizeName;
    private String prizeImage;  // 相对于Cst.BASE_PATH的路径，比如"prize/image/123.png"
    private int prizeMoney;

    // 关联对象
    // --------------------------------------------------------------------------------

    // 附加字段
    // --------------------------------------------------------------------------------

    // 附加存取器
    // --------------------------------------------------------------------------------
    public String getPrizeImageUrl()
    {
        if (prizeImage != null && Utl.fileExist(Cst.BASE_PATH + prizeImage))
        {
            return Cst.BASE_URL + prizeImage;
        }

        return "";
    }

    // getter, setter
    // --------------------------------------------------------------------------------
    public int getPrizeMoney()
    {
        return prizeMoney;
    }

    public void setPrizeMoney(int prizeMoney)
    {
        this.prizeMoney = prizeMoney;
    }

    public String getPrizeName()
    {
        return prizeName;
    }

    public void setPrizeName(String prizeName)
    {
        this.prizeName = prizeName;
    }

    public int getPrizeId()
    {
        return prizeId;
    }

    public void setPrizeId(int prizeId)
    {
        this.prizeId = prizeId;
    }

    public String getPrizeImage()
    {
        return prizeImage;
    }

    public void setPrizeImage(String prizeImage)
    {
        this.prizeImage = prizeImage;
    }

}

