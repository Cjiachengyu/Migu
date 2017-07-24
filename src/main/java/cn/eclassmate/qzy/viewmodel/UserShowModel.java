package cn.eclassmate.qzy.viewmodel;

import cn.eclassmate.qzy.domain.ShowAction;
import cn.eclassmate.qzy.domain.ShowGift;
import cn.eclassmate.qzy.domain.ShowGrabBag;
import cn.eclassmate.qzy.domain.ShowPrize;
import cn.eclassmate.qzy.viewmodel.subview.ShareInfo;

import java.util.List;

/**
 * Created by stao on 2015/5/8.
 */
public class UserShowModel extends AbstractModel
{
    private static final long serialVersionUID = -7819385797699690129L;

    // fields
    // --------------------------------------------------------------------------------
    private int showType;
    private ShowGrabBag showGrabBag;
    private ShowPrize showPrize;
    private ShowGift showGift;

    List<ShowAction> showActionList;
    int opposite;//反对人数
    int support;//支持人数
    int myActionType;//我的action


    int praiseOrlaughCount;

    private ShareInfo shareInfo;


    // getter, setter
    // --------------------------------------------------------------------------------
    public ShowPrize getShowPrize()
    {
        return showPrize;
    }

    public void setShowPrize(ShowPrize showPrize)
    {
        this.showPrize = showPrize;
    }

    public ShowGrabBag getShowGrabBag()
    {
        return showGrabBag;
    }

    public void setShowGrabBag(ShowGrabBag showGrabBag)
    {
        this.showGrabBag = showGrabBag;
    }

    public int getShowType()
    {
        return showType;
    }

    public void setShowType(int showType)
    {
        this.showType = showType;
    }

    public ShowGift getShowGift()
    {
        return showGift;
    }

    public void setShowGift(ShowGift showGift)
    {
        this.showGift = showGift;
    }

    public List<ShowAction> getShowActionList()
    {
        return showActionList;
    }

    public void setShowActionList(List<ShowAction> showActionList)
    {
        this.showActionList = showActionList;
    }

    public int getOpposite()
    {
        return opposite;
    }

    public void setOpposite(int opposite)
    {
        this.opposite = opposite;
    }

    public int getSupport()
    {
        return support;
    }

    public void setSupport(int support)
    {
        this.support = support;
    }

    public int getMyActionType()
    {
        return myActionType;
    }

    public void setMyActionType(int myActionType)
    {
        this.myActionType = myActionType;
    }

    public int getPraiseOrlaughCount()
    {
        return praiseOrlaughCount;
    }

    public void setPraiseOrlaughCount(int praiseOrlaughCount)
    {
        this.praiseOrlaughCount = praiseOrlaughCount;
    }

    public ShareInfo getShareInfo()
    {
        return shareInfo;
    }

    public void setShareInfo(ShareInfo shareInfo)
    {
        this.shareInfo = shareInfo;
    }


}
