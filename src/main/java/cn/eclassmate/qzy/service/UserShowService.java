package cn.eclassmate.qzy.service;

import cn.eclassmate.qzy.domain.Show;
import cn.eclassmate.qzy.domain.ShowAction;
import cn.eclassmate.qzy.domain.ShowGift;
import cn.eclassmate.qzy.domain.ShowGrabBag;
import cn.eclassmate.qzy.domain.ShowPrize;
import cn.eclassmate.qzy.domain.User;
import cn.eclassmate.qzy.global.Cst;
import cn.eclassmate.qzy.global.Utl;
import cn.eclassmate.qzy.viewmodel.UserShowModel;
import cn.eclassmate.qzy.viewmodel.subview.ShareInfo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserShowService extends AbstractService
{
    private static final long serialVersionUID = -1882603606653618110L;

    public void initUserShowModel(UserShowModel viewModel, int showId)
    {
        Show show = showMapper.getShow(showId);
        viewModel.setShowType(show.getShowType());

        if (show.getShowType() == Cst.ShowType.SHOW_GRAB_BAG)
        {
            ShowGrabBag showGrabBag = showMapper.getShowGrabBag(showId);
            showGrabBag.setShower(userMapper.getUser(showGrabBag.getShowerId()));
            showGrabBag.setUserBag(bagMapper.getUserBagForShow(showGrabBag.getUserId(), showGrabBag.getBagId()));
            viewModel.setShowGrabBag(showGrabBag);
        }
        else if (show.getShowType() == Cst.ShowType.SHOW_PRIZE)
        {
            ShowPrize showPrize = showMapper.getShowPrize(showId);
            viewModel.setShowPrize(showPrize);
        }
        else if (show.getShowType() == Cst.ShowType.SHOW_GIFT)
        {
            ShowGift showGift = showMapper.getShowGift(showId);
            viewModel.setShowGift(showGift);
        }
    }

    public void setShowAction(UserShowModel viewModel, int showId)
    {
        ShowAction myShowAction = showMapper.getShowAction(viewModel.getUser().getUserId(), showId);
        if (myShowAction == null)
        {
            myShowAction = new ShowAction();
            myShowAction.setShowId(showId);
            myShowAction.setUserId(viewModel.getUser().getUserId());
            myShowAction.setActionType(0);

            showMapper.insertShowAction(myShowAction);
        }

        List<ShowAction> showActionList = showMapper.getShowActionList(showId);

        int opposite = 0;//反对人数
        int support = 0;//支持人数

        for (ShowAction showAction : showActionList)
        {
            if (showAction.getActionType() == 1)
            {
                support = support + 1;
            }
            else if (showAction.getActionType() == 2)
            {
                opposite = opposite + 1;
            }

        }

        viewModel.setShowActionList(showActionList);
        viewModel.setSupport(support);
        viewModel.setOpposite(opposite);
        viewModel.setMyActionType(myShowAction.getActionType());
    }

    public void showAction(UserShowModel viewModel, int showId, int actionType)
    {
        ShowAction myShowAction = showMapper.getShowAction(viewModel.getUser().getUserId(), showId);
        if (actionType == 1)
        {
            if (myShowAction.getActionType() == 1)
            {
                myShowAction.setActionType(0);
            }
            else
            {
                myShowAction.setActionType(1);
            }
        }
        else if (actionType == 2)
        {
            if (myShowAction.getActionType() == 2)
            {
                myShowAction.setActionType(0);
            }
            else
            {
                myShowAction.setActionType(2);
            }
        }

        showMapper.updateShowAction(myShowAction);
    }

    public void setPraiseOrlaughCount(UserShowModel viewModel)
    {
        int bagId = viewModel.getShowGrabBag().getBagId();
        int userId = viewModel.getShowGrabBag().getUserId();
        int showGrabBagType = viewModel.getShowGrabBag().getShowGrabBagType();

        int praiseOrlaughCount = showMapper.getPraiseOrlaughCount(bagId, userId, showGrabBagType);
        viewModel.setPraiseOrlaughCount(praiseOrlaughCount);
    }

    @Transactional
    public void showGrabBag(UserShowModel viewModel, int showGrabBagType)
    {
        ShowGrabBag showGrabBag = new ShowGrabBag();
        showGrabBag.setShowType(Cst.ShowType.SHOW_GRAB_BAG);
        showGrabBag.setShowTime(Utl.currentSeconds());
        showGrabBag.setShowerId(viewModel.getUser().getUserId());    // 发起show的人
        showMapper.insertShow(showGrabBag);

        showGrabBag.setShowGrabBagType(showGrabBagType);
        showGrabBag.setBagId(viewModel.getShowGrabBag().getBagId());
        showGrabBag.setUserId(viewModel.getShowGrabBag().getUserId());                              // 被show的人
        showMapper.insertShowGrabBag(showGrabBag);

        viewModel.setShowGrabBag(showGrabBag);

        User shower = viewModel.getUser();

        User showedUser = userMapper.getUser(viewModel.getShowGrabBag().getUserId());           // 被show的人

        if (showGrabBagType == Cst.ShowGrabBagType.SHOW_GRAB_BAG_LAUGH)
        {
            changeUserMoney(viewModel, shower.getUserId(), Cst.BillType.SPEND,
                    10, "嘲笑了" + showedUser.getUserName() + "一下，花去");
        }
        else if (showGrabBagType == Cst.ShowGrabBagType.SHOW_GRAB_BAG_PRAISE)
        {
            changeUserMoney(viewModel, shower.getUserId(), Cst.BillType.SPEND,
                    10, "赞美了" + showedUser.getUserName() + "一下，花去");
        }
    }

    public void initShareInfo(UserShowModel viewModel)
    {
        ShareInfo shareInfo = new ShareInfo();
        if (viewModel.getShowType() == Cst.ShowType.SHOW_GRAB_BAG)
        {
            if (viewModel.getShowGrabBag().getShowGrabBagType() == Cst.ShowGrabBagType.SHOW_GRAB_BAG_MY)
            {
                initShareShowMyBag(viewModel, shareInfo);
            }
            else if (viewModel.getShowGrabBag().getShowGrabBagType() == Cst.ShowGrabBagType.SHOW_GRAB_BAG_LAUGH)
            {
                initShareShowLaugh(viewModel, shareInfo);
            }
            else if (viewModel.getShowGrabBag().getShowGrabBagType() == Cst.ShowGrabBagType.SHOW_GRAB_BAG_PRAISE)
            {
                initShareShowPraise(viewModel, shareInfo);
            }
        }
        else if (viewModel.getShowType() == Cst.ShowType.SHOW_PRIZE)
        {
            initShareShowPrize(viewModel, shareInfo);
        }
        else if (viewModel.getShowType() == Cst.ShowType.SHOW_GIFT)
        {
            if (viewModel.getShowGift().getShowGiftType() == Cst.ShowGiftType.SHOW_GIFT_SEND)
            {
                initShareShowSendGift(viewModel, shareInfo);
            }
            else if (viewModel.getShowGift().getShowGiftType() == Cst.ShowGiftType.SHOW_GIFT_RECEIVE)
            {
                initShareShowReceiveGift(viewModel, shareInfo);
            }
        }

        viewModel.setShareInfo(shareInfo);
    }

    // private
    // --------------------------------------------------------------------------------
    private void initShareShowMyBag(UserShowModel viewModel, ShareInfo shareInfo)
    {
        shareInfo.setShareTitle(viewModel.getUser().getUserName() + "抢了个大红包！");
        shareInfo.setShareMsg(viewModel.getUser().getUserName() + "抢了个大红包，得到"
                + viewModel.getShowGrabBag().getUserBag().getGotMoney() + "作业币！");
        shareInfo.setShareLink("/migu/user/show/" + viewModel.getShowGrabBag().getShowId() + "/showmy");
        shareInfo.setShareImage("/migu/resources/image/user/grabbag/show_my_logo.png");

        shareInfo.setSucessTip("显摆成功！");
        shareInfo.setSucessImage(Cst.DOMAIN_NAME + "/migu/resources/image/user/grabbag/show_my_share.png");
        shareInfo.setSuccessCallback("/migu/user/grabbag/" + viewModel.getShowGrabBag().getBagId() + "/startgrab");
    }

    private void initShareShowPraise(UserShowModel viewModel, ShareInfo shareInfo)
    {
        shareInfo.setShareTitle("赞美你！");
        shareInfo.setShareMsg(viewModel.getUser().getUserName() + "赞美了"
                + viewModel.getShowGrabBag().getUserBag().getUser().getUserName() + "！");
        shareInfo.setShareLink("/migu/user/show/" + viewModel.getShowGrabBag().getShowId() + "/praiseuser");
        shareInfo.setShareImage("/migu/resources/image/user/grabbag/show_praise_logo.png");

        shareInfo.setSucessTip("赞美成功！");
        shareInfo.setSucessImage(Cst.DOMAIN_NAME + "/migu/resources/image/user/grabbag/show_praise_share.png");
        shareInfo.setSuccessCallback("/migu/user/grabbag/" + viewModel.getShowGrabBag().getBagId() + "/startgrab");
    }

    private void initShareShowLaugh(UserShowModel viewModel, ShareInfo shareInfo)
    {
        shareInfo.setShareTitle("嘲笑你！");
        shareInfo.setShareMsg(viewModel.getUser().getUserName() + "嘲笑了"
                + viewModel.getShowGrabBag().getUserBag().getUser().getUserName() + "一下！");
        shareInfo.setShareLink("/migu/user/show/" + viewModel.getShowGrabBag().getShowId() + "/laughuser");
        shareInfo.setShareImage("/migu/resources/image/user/grabbag/show_laugh_logo.png");

        shareInfo.setSucessTip("嘲笑成功！");
        shareInfo.setSucessImage(Cst.DOMAIN_NAME + "/migu/resources/image/user/grabbag/show_laugh_share.png");
        shareInfo.setSuccessCallback("/migu/user/grabbag/" + viewModel.getShowGrabBag().getBagId() + "/startgrab");
    }

    private void initShareShowPrize(UserShowModel viewModel, ShareInfo shareInfo)
    {
        shareInfo.setShareTitle("我收藏了一个" + viewModel.getShowPrize().getPrize().getPrizeName());
        shareInfo.setShareMsg("我收藏了一个" + viewModel.getShowPrize().getPrize().getPrizeName() + "！来看看吧！");
        shareInfo.setShareLink("/migu/user/show/" + viewModel.getShowPrize().getShowId() + "/showprize");
        shareInfo.setShareImage("/migu/resources/image/user/prize/share_show_prize_logo.png");

        shareInfo.setSucessTip("显摆成功！");
        shareInfo.setSucessImage(Cst.DOMAIN_NAME + "/migu/resources/image/user/prize/share_show_prize.png");
        shareInfo.setSuccessCallback("/migu/user/account/prizelist");
    }

    private void initShareShowSendGift(UserShowModel viewModel, ShareInfo shareInfo)
    {
        shareInfo.setShareTitle("我送出了礼物");
        shareInfo.setShareMsg("我送出了1个" + viewModel.getShowGift().getGift().getPrize().getPrizeName() + "！来看看吧！");
        shareInfo.setShareLink("/migu/user/show/" + viewModel.getShowGift().getShowId() + "/showsendgift");
        shareInfo.setShareImage("/migu/resources/image/user/prize/share_show_gift_logo.png");

        shareInfo.setSucessTip("显摆成功！");
        shareInfo.setSucessImage(Cst.DOMAIN_NAME + "/migu/resources/image/user/prize/share_show_gift.png");
        shareInfo.setSuccessCallback("/migu/user/account/giftsend");
    }

    private void initShareShowReceiveGift(UserShowModel viewModel, ShareInfo shareInfo)
    {
        shareInfo.setShareTitle("我收到了礼物");
        shareInfo.setShareMsg("我收到了1个" + viewModel.getShowGift().getGift().getPrize().getPrizeName() + "！来看看吧！");
        shareInfo.setShareLink("/migu/user/show/" + viewModel.getShowGift().getShowId() + "/showreceivegift");
        shareInfo.setShareImage("/migu/resources/image/user/prize/share_show_gift_logo.png");

        shareInfo.setSucessTip("显摆成功！");
        shareInfo.setSucessImage(Cst.DOMAIN_NAME + "/migu/resources/image/user/prize/share_show_gift.png");
        shareInfo.setSuccessCallback("/migu/user/account/giftreceive");
    }

}
