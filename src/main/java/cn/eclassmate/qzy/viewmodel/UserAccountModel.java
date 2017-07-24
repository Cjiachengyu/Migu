package cn.eclassmate.qzy.viewmodel;

import cn.eclassmate.qzy.domain.Bill;
import cn.eclassmate.qzy.domain.Gift;
import cn.eclassmate.qzy.domain.ShowGift;
import cn.eclassmate.qzy.domain.ShowPrize;
import cn.eclassmate.qzy.domain.UserPrize;
import cn.eclassmate.qzy.viewmodel.subview.ShareInfo;

import java.util.List;

public class UserAccountModel extends AbstractModel
{
    private static final long serialVersionUID = -7819385797699690129L;

    // fields
    // --------------------------------------------------------------------------------
    List<Bill> billList;

    List<UserPrize> userPrizeList;
    private ShowPrize showPrize;// 显摆
    private Gift gift; //送礼
    List<Gift> giftSendList;
    List<Gift> giftReceiveList;
    private ShowGift showGift;// 显摆
    List<Gift> giftList;

    private ShareInfo shareInfo;

    // getter, setter
    // --------------------------------------------------------------------------------
    public List<Bill> getBillList()
    {
        return billList;
    }

    public void setBillList(List<Bill> billList)
    {
        this.billList = billList;
    }

    public List<UserPrize> getUserPrizeList()
    {
        return userPrizeList;
    }

    public void setUserPrizeList(List<UserPrize> userPrizeList)
    {
        this.userPrizeList = userPrizeList;
    }

    public ShowPrize getShowPrize()
    {
        return showPrize;
    }

    public void setShowPrize(ShowPrize showPrize)
    {
        this.showPrize = showPrize;
    }

    public Gift getGift()
    {
        return gift;
    }

    public void setGift(Gift gift)
    {
        this.gift = gift;
    }

    public List<Gift> getGiftSendList()
    {
        return giftSendList;
    }

    public void setGiftSendList(List<Gift> giftSendList)
    {
        this.giftSendList = giftSendList;
    }

    public List<Gift> getGiftReceiveList()
    {
        return giftReceiveList;
    }

    public void setGiftReceiveList(List<Gift> giftReceiveList)
    {
        this.giftReceiveList = giftReceiveList;
    }

    public ShowGift getShowGift()
    {
        return showGift;
    }

    public void setShowGift(ShowGift showGift)
    {
        this.showGift = showGift;
    }

    public List<Gift> getGiftList()
    {
        return giftList;
    }

    public void setGiftList(List<Gift> giftList)
    {
        this.giftList = giftList;
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
