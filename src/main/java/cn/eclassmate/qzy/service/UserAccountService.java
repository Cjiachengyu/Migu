package cn.eclassmate.qzy.service;

import cn.eclassmate.qzy.domain.Bill;
import cn.eclassmate.qzy.domain.Gift;
import cn.eclassmate.qzy.domain.ShowGift;
import cn.eclassmate.qzy.domain.ShowPrize;
import cn.eclassmate.qzy.domain.UserPrize;
import cn.eclassmate.qzy.global.Cst;
import cn.eclassmate.qzy.global.Utl;
import cn.eclassmate.qzy.viewmodel.UserAccountModel;
import cn.eclassmate.qzy.viewmodel.subview.ShareInfo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAccountService extends AbstractService
{
    private static final long serialVersionUID = -2971683733562233221L;
    private static final Logger logger = LogManager.getLogger();

    public void initUserBillRecord(UserAccountModel viewModel)
    {
        refreshUserMoney(viewModel);

        List<Bill> billList = bagMapper.getBillList(viewModel.getUser().getUserId(), 100);

        viewModel.setBillList(billList);
    }

    public void initUserPrizeList(UserAccountModel upModel)
    {
        List<UserPrize> userPrizeList = bagMapper.getUserPrizeList(upModel.getUser().getUserId());
        upModel.setUserPrizeList(userPrizeList);
    }

    public void initGiftList(UserAccountModel upModel)
    {
        upModel.setGiftList(bagMapper.getGiftListAll(upModel.getUser().getUserId()));
    }

    public void initGiftReceiveList(UserAccountModel upModel)
    {
        List<Gift> giftReceiveList = bagMapper.getGiftReceiveList(upModel.getUser().getUserId());
        upModel.setGiftReceiveList(giftReceiveList);
    }

    public void initGiftSendList(UserAccountModel upModel)
    {
        List<Gift> giftSendList = bagMapper.getGiftSendList(upModel.getUser().getUserId());
        upModel.setGiftSendList(giftSendList);
    }

    public void sendGift(UserAccountModel upModel, int prizeId)
    {
        //生成礼物并存入数据库
        Gift gift = new Gift();
        gift.setPrizeId(prizeId);
        gift.setSenderId(upModel.getUser().getUserId());
        gift.setSendTime(Utl.currentSeconds());

        bagMapper.insertGift(gift);

        //减少用户的奖品收藏
        for (UserPrize userPrize : upModel.getUserPrizeList())
        {
            if (userPrize.getPrizeId() == prizeId)
            {
                userPrize.setPrizeCount(userPrize.getPrizeCount() - 1);
                bagMapper.updateUserPrize(userPrize);

                gift.setPrize(userPrize.getPrize());
            }

        }

        upModel.setGift(gift);

    }

    public void reSendGift(UserAccountModel upModel, int giftId)
    {
        //从数据库查出礼物
        Gift gift = bagMapper.getGift(giftId);

        upModel.setGift(gift);
    }

    public void userReceiveGift(UserAccountModel ugbModel, int giftId)
    {
        Gift gift = bagMapper.getGift(giftId);
        if (gift.getReceiverId() != 0)
        {
            // 已经被领取了
            ugbModel.setGift(gift);
            return;
        }

        gift.setReceiverId(ugbModel.getUser().getUserId());
        gift.setReceiveTime(Utl.currentSeconds());
        bagMapper.updateGift(gift);

        UserPrize userPrize = bagMapper.getUserPrize(ugbModel.getUser().getUserId(), gift.getPrize().getPrizeId());
        if (userPrize == null)
        {
            // 本来没有奖品
            userPrize = new UserPrize();
            userPrize.setUserId(ugbModel.getUser().getUserId());
            userPrize.setPrizeId(gift.getPrize().getPrizeId());
            userPrize.setPrizeCount(1);

            bagMapper.insertUserPrize(userPrize);
        }
        else
        {
            // 已经有奖品了，加1
            userPrize.setPrizeCount(userPrize.getPrizeCount() + 1);
            bagMapper.updateUserPrize(userPrize);
        }

        ugbModel.setGift(gift);
    }

    public void showPrize(UserAccountModel upModel, int prizeId)
    {
        ShowPrize showPrize = new ShowPrize();
        showPrize.setShowType(Cst.ShowType.SHOW_PRIZE);
        showPrize.setShowTime(Utl.currentSeconds());
        showPrize.setShowerId(upModel.getUser().getUserId());
        showMapper.insertShow(showPrize);

        showPrize.setPrizeId(prizeId);
        showPrize.setPrize(bagMapper.getPrize(prizeId));
        showMapper.insertShowPrize(showPrize);

        upModel.setShowPrize(showPrize);

        changeUserMoney(upModel, upModel.getUser().getUserId(), Cst.BillType.SPEND,
                Cst.Money.SHOW_PRIZE, "显摆了一个奖品，花去");
    }

    public void showGiftSend(UserAccountModel upModel, int giftId)
    {
        ShowGift showGift = new ShowGift();
        showGift.setShowType(Cst.ShowType.SHOW_GIFT);
        showGift.setShowTime(Utl.currentSeconds());
        showGift.setShowerId(upModel.getUser().getUserId());
        showMapper.insertShow(showGift);

        showGift.setShowGiftType(Cst.ShowGiftType.SHOW_GIFT_SEND);
        showGift.setGiftId(giftId);
        showMapper.insertShowGift(showGift);

        upModel.setShowGift(showGift);
        changeUserMoney(upModel, upModel.getUser().getUserId(), Cst.BillType.SPEND,
                Cst.Money.SHOW_GIFT_SEND, "送出了礼物，显摆一下，花去");
    }

    public void showGiftReceive(UserAccountModel upModel, int giftId)
    {
        ShowGift showGift = new ShowGift();
        showGift.setShowType(Cst.ShowType.SHOW_GIFT);
        showGift.setShowTime(Utl.currentSeconds());
        showGift.setShowerId(upModel.getUser().getUserId());
        showMapper.insertShow(showGift);

        showGift.setShowGiftType(Cst.ShowGiftType.SHOW_GIFT_RECEIVE);
        showGift.setGiftId(giftId);
        showMapper.insertShowGift(showGift);

        upModel.setShowGift(showGift);
        changeUserMoney(upModel, upModel.getUser().getUserId(), Cst.BillType.SPEND,
                Cst.Money.SHOW_GIFT_RECEIVE, "收到了礼物，显摆一下，花去");
    }

    public void initShareInfo(UserAccountModel ugbModel)
    {
        ShareInfo shareInfo = new ShareInfo();

        shareInfo.setShareTitle("快来收礼物！");
        shareInfo.setShareMsg(ugbModel.getUser().getUserName() + "送出1个"
                + ugbModel.getGift().getPrize().getPrizeName() + "！");
        shareInfo.setShareLink("/migu/user/account/" + ugbModel.getGift().getGiftId() + "/receivegift");
        shareInfo.setShareImage(Cst.DOMAIN_NAME + "/migu/resources/image/user/prize/share_send_gift_logo.png");

        shareInfo.setSucessTip("送礼成功！");
        shareInfo.setSucessImage(Cst.DOMAIN_NAME + "/migu/resources/image/user/prize/share_send_gift.png");
        shareInfo.setSuccessCallback("/migu/user/account/prizelist");

        ugbModel.setShareInfo(shareInfo);
    }
}
