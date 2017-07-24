package cn.eclassmate.qzy.web;

import cn.eclassmate.qzy.domain.User;
import cn.eclassmate.qzy.global.Cst;
import cn.eclassmate.qzy.service.UserAccountService;
import cn.eclassmate.qzy.viewmodel.UserAccountModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("user/account")
public class UserAccountController extends AbstractController
{
    // page
    // --------------------------------------------------------------------------------
    private static final String BILL_RECORD = "user/account/BillRecord";

    private static final String PRIZE_LIST_MY = "user/account/PrizeListMy";
    private static final String PRIZE_LIST_GIFT_SEND = "user/account/PrizeListGiftSend";
    private static final String PRIZE_LIST_GIFT_RECEIVE = "user/account/PrizeListGiftReceive";
    private static final String PRIZE_LIST_GIFT_ALL = "user/account/PrizeListGiftAll";

    private static final String SHARE_SHOW = "user/share/Share";//分享公共页面
    private static final String SHARE_SHOW_FOR_QQ_SERVER = "user/qqshare/QQShare";//qq分享公共页面

    private static final String RECEIVE_GIFT = "user/account/ReceiveGift";//收礼物
    private static final String RECEIVE_GIFT_ALREADY = "user/account/ReceiveGiftAlready";//收礼物

    // model
    // --------------------------------------------------------------------------------
    private static final Class<UserAccountModel> modelClass = UserAccountModel.class;

    // service
    // --------------------------------------------------------------------------------
    @Autowired
    UserAccountService userAccountService;

    // handler
    // --------------------------------------------------------------------------------
    // 收支明细
    @RequestMapping("billrecord")
    public View billRecord(HttpServletRequest request) throws Exception
    {
        User user = getCurrentUser(request);
        if (user != null)
        {
            UserAccountModel upModel = getCurrentModel(request, modelClass);

            userAccountService.initUserBillRecord(upModel);

            setCurrentModel(request, upModel);
            return getJspView(BILL_RECORD);
        }
        else
        {
            return authEnsureFullAndCallback(request);
        }

    }

    // 我的奖品
    @RequestMapping("prizelist")
    public View prizeList(HttpServletRequest request) throws Exception
    {
        User user = getCurrentUser(request);
        if (user != null)
        {
            UserAccountModel upModel = getCurrentModel(request, modelClass);

            userAccountService.initUserPrizeList(upModel);

            setCurrentModel(request, upModel);
            return getJspView(PRIZE_LIST_MY);
        }
        else
        {
            return authEnsureFullAndCallback(request);
        }
    }

    //所以得礼物
    @RequestMapping("giftlist")
    public View giftList(HttpServletRequest request) throws Exception
    {
        User user = getCurrentUser(request);
        if (user != null)
        {
            UserAccountModel upModel = getCurrentModel(request, modelClass);
            userAccountService.initGiftList(upModel);
            setCurrentModel(request, upModel);
            return getJspView(PRIZE_LIST_GIFT_ALL);
        }
        else
        {
            return authEnsureFullAndCallback(request);
        }
    }

    // 收到的礼物
    @RequestMapping("giftreceive")
    public View giftReceive(HttpServletRequest request) throws Exception
    {
        User user = getCurrentUser(request);
        if (user != null)
        {
            UserAccountModel upModel = getCurrentModel(request, modelClass);
            userAccountService.initGiftReceiveList(upModel);
            setCurrentModel(request, upModel);
            return getJspView(PRIZE_LIST_GIFT_RECEIVE);
        }
        else
        {
            return authEnsureFullAndCallback(request);
        }
    }

    // 送出的礼物
    @RequestMapping("giftsend")
    public View giftSend(HttpServletRequest request) throws Exception
    {
        User user = getCurrentUser(request);
        if (user != null)
        {
            UserAccountModel upModel = getCurrentModel(request, modelClass);
            userAccountService.initGiftSendList(upModel);
            setCurrentModel(request, upModel);
            return getJspView(PRIZE_LIST_GIFT_SEND);
        }
        else
        {
            return authEnsureFullAndCallback(request);
        }
    }

    // 送礼
    @RequestMapping("sendgift")
    public View sendGift(HttpServletRequest request, int prizeId) throws Exception
    {

        User user = getCurrentUser(request);
        if (user != null)
        {
            UserAccountModel upModel = getCurrentModel(request, modelClass);

            userAccountService.sendGift(upModel, prizeId);
            setCurrentModel(request, upModel);

            request.getSession().setAttribute("just_created_share_type", "sendgift");
            return getRedirectView(Cst.DOMAIN_NAME + "/migu/user/account/" + upModel.getGift().getGiftId()
                    + "/receivegift");
        }
        else
        {
            return authEnsureFullAndCallback(request);
        }
    }

    // 送礼，重送
    @RequestMapping("resendgift")
    public View reSendGift(HttpServletRequest request, int giftId) throws Exception
    {

        User user = getCurrentUser(request);
        if (user != null)
        {
            UserAccountModel upModel = getCurrentModel(request, modelClass);

            userAccountService.reSendGift(upModel, giftId);
            setCurrentModel(request, upModel);

            request.getSession().setAttribute("just_created_share_type", "sendgift");
            return getRedirectView(Cst.DOMAIN_NAME + "/migu/user/account/" + upModel.getGift().getGiftId()
                    + "/receivegift");
        }
        else
        {
            return authEnsureFullAndCallback(request);
        }
    }

    // 收礼
    @RequestMapping("{giftId}/receivegift")
    public View receiveGift(HttpServletRequest request, @PathVariable("giftId") int giftId)
    {
        if (isInvalidClient(request))
        {
            UserAccountModel ugbModel = getCurrentModel(request, modelClass);
            userAccountService.initShareInfo(ugbModel);
            setCurrentModel(request, ugbModel);
            return getJspView(SHARE_SHOW_FOR_QQ_SERVER);
        }

        User user = getCurrentUser(request);

        if (user != null)
        {
            UserAccountModel ugbModel = getCurrentModel(request, modelClass);
            setCurrentModel(request, ugbModel);

            String sourceType = (String) request.getSession().getAttribute("just_created_share_type");
            if (sourceType != null && sourceType.equals("sendgift"))
            {
                request.getSession().setAttribute("just_created_share_type", "");
                userAccountService.initShareInfo(ugbModel);
                setCurrentModel(request, ugbModel);

                return getJspView(SHARE_SHOW);
            }

            userAccountService.userReceiveGift(ugbModel, giftId);
            if (ugbModel.getGift().getReceiverId() != user.getUserId())
            {
                return getJspView(RECEIVE_GIFT_ALREADY);
            }
            else
            {
                return getJspView(RECEIVE_GIFT);
            }
        }
        else
        {
            String callback = Cst.DOMAIN_NAME + "/migu/user/account/" + giftId + "/receivegift";
            return authEnsureFullAndCallback(request, callback);
        }
    }

    // 显摆奖品
    @RequestMapping("showprize")
    public View showPrize(HttpServletRequest request, int prizeId) throws Exception
    {

        User user = getCurrentUser(request);
        if (user != null)
        {
            UserAccountModel upModel = getCurrentModel(request, modelClass);

            userAccountService.showPrize(upModel, prizeId);

            setCurrentModel(request, upModel);
            //return getJspView(SHOW_PRIZE);

            request.getSession().setAttribute("just_created_share_type", "showprize");

            return getRedirectView(Cst.DOMAIN_NAME + "/migu/user/show/" + upModel.getShowPrize().getShowId()
                    + "/showprize");
        }
        else
        {
            return authEnsureFullAndCallback(request);
        }
    }

    // 显摆收到的礼物
    @RequestMapping("showreceivegift")
    public View showReceiveGift(HttpServletRequest request, int giftId) throws Exception
    {
        User user = getCurrentUser(request);
        if (user != null)
        {
            UserAccountModel upModel = getCurrentModel(request, modelClass);
            userAccountService.showGiftReceive(upModel, giftId);
            setCurrentModel(request, upModel);

            request.getSession().setAttribute("just_created_share_type", "showreceivegift");
            return getRedirectView(Cst.DOMAIN_NAME + "/migu/user/show/" + upModel.getShowGift().getShowId()
                    + "/showreceivegift");
        }
        else
        {
            return authEnsureFullAndCallback(request);
        }

    }

    // 显摆送出的礼物
    @RequestMapping("showsendgift")
    public View showSendGift(HttpServletRequest request, int giftId) throws Exception
    {
        User user = getCurrentUser(request);
        if (user != null)
        {
            UserAccountModel upModel = getCurrentModel(request, modelClass);
            userAccountService.showGiftSend(upModel, giftId);
            setCurrentModel(request, upModel);
            //return getJspView(SHOW_SEND_GIFT);

            request.getSession().setAttribute("just_created_share_type", "showsendgift");

            return getRedirectView(Cst.DOMAIN_NAME + "/migu/user/show/" + upModel.getShowGift().getShowId()
                    + "/showsendgift");

        }
        else
        {
            return authEnsureFullAndCallback(request);
        }

    }

    // 获取最新的信息帐户信息
    @RequestMapping("refreshweixinaccount")
    public View refreshWeixinAccount(HttpServletRequest request) throws Exception
    {
        String callback = Cst.DOMAIN_NAME + "/migu/user/account/billrecord";
        return authForceFullAndCallback(request, callback);
    }

}
