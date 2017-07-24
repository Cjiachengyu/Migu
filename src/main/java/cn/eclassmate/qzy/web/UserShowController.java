package cn.eclassmate.qzy.web;

import cn.eclassmate.qzy.domain.User;
import cn.eclassmate.qzy.global.Cst;
import cn.eclassmate.qzy.service.UserShowService;
import cn.eclassmate.qzy.viewmodel.UserShowModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;

/**
 * 抢作业
 * 包括：抢作业，做题，显示解析的逻辑
 *
 * @author stao
 */
@Controller
@RequestMapping("user/show")
public class UserShowController extends AbstractController
{
    private static final Logger logger = LogManager.getLogger();

    // pages
    // --------------------------------------------------------------------------------
    private static final String JSP_SHOW_GRAB_BAG_MY = "user/show/ShowGrabBagMy";
    private static final String JSP_SHOW_GRAB_BAG_LAUGH = "user/show/ShowGrabBagLaugh";
    private static final String JSP_SHOW_GRAB_BAG_PRAISE = "user/show/ShowGrabBagPraise";
    private static final String JSP_SHOW_PRIZE = "user/show/ShowPrize";
    private static final String JSP_SHOW_GIFT_SEND = "user/show/ShowGiftSend";
    private static final String JSP_SHOW_GIFT_RECEIVE = "user/show/ShowGiftReceive";

    // share
    private static final String SHARE_SHOW = "user/share/Share";//分享公共页面
    private static final String SHARE_SHOW_FOR_QQ_SERVER = "user/qqshare/QQShare";//qq分享公共页面

    // model
    // --------------------------------------------------------------------------------
    private static final Class<UserShowModel> modelClass = UserShowModel.class;

    // service
    // --------------------------------------------------------------------------------
    @Autowired
    UserShowService userShowService;

    // handler
    // --------------------------------------------------------------------------------
    @RequestMapping("{showId}/showmy")
    public View showMy(HttpServletRequest request, @PathVariable("showId") int showId)
    {
        if (isInvalidClient(request))
        {
            return getQQShareView(request, showId);
        }

        User user = getCurrentUser(request);
        if (user != null)
        {
            UserShowModel viewModel = getCurrentModel(request, modelClass);
            userShowService.initUserShowModel(viewModel, showId);

            if (isJustShare(request))
            {
                return getShareView(request, viewModel);
            }
            else
            {
                userShowService.setPraiseOrlaughCount(viewModel);
                setCurrentModel(request, viewModel);
                return getJspView(JSP_SHOW_GRAB_BAG_MY);
            }
        }
        else
        {
            String callback = Cst.DOMAIN_NAME + "/migu/user/show/" + showId + "/showmy";
            return authEnsureFullAndCallback(request, callback);
        }
    }

    @RequestMapping("{showId}/praiseuser")
    public View praiseUser(HttpServletRequest request, @PathVariable("showId") int showId)
    {
        if (isInvalidClient(request))
        {
            return getQQShareView(request, showId);
        }

        User user = getCurrentUser(request);
        if (user != null)
        {
            UserShowModel viewModel = getCurrentModel(request, modelClass);
            userShowService.initUserShowModel(viewModel, showId);

            if (isJustShare(request))
            {
                return getShareView(request, viewModel);
            }
            else
            {
                userShowService.setPraiseOrlaughCount(viewModel);
                setCurrentModel(request, viewModel);
                return getJspView(JSP_SHOW_GRAB_BAG_PRAISE);
            }
        }
        else
        {
            String callback = Cst.DOMAIN_NAME + "/migu/user/show/" + showId + "/praiseuser";
            return authEnsureFullAndCallback(request, callback);
        }
    }

    @RequestMapping("{showId}/laughuser")
    public View laughUser(HttpServletRequest request, @PathVariable("showId") int showId)
    {
        if (isInvalidClient(request))
        {
            return getQQShareView(request, showId);
        }

        User user = getCurrentUser(request);
        if (user != null)
        {
            UserShowModel viewModel = getCurrentModel(request, modelClass);
            userShowService.initUserShowModel(viewModel, showId);

            if (isJustShare(request))
            {
                return getShareView(request, viewModel);
            }
            else
            {
                userShowService.setPraiseOrlaughCount(viewModel);
                setCurrentModel(request, viewModel);
                return getJspView(JSP_SHOW_GRAB_BAG_LAUGH);
            }
        }
        else
        {
            String callback = Cst.DOMAIN_NAME + "/migu/user/show/" + showId + "/laughuser";
            return authEnsureFullAndCallback(request, callback);
        }
    }

    @RequestMapping("{showId}/showprize")
    public View showPrize(HttpServletRequest request, @PathVariable("showId") int showId)
    {
        if (isInvalidClient(request))
        {
            return getQQShareView(request, showId);
        }

        User user = getCurrentUser(request);

        if (user != null)
        {
            UserShowModel viewModel = getCurrentModel(request, modelClass);
            userShowService.initUserShowModel(viewModel, showId);

            if (isJustShare(request))
            {
                return getShareView(request, viewModel);
            }
            else
            {
                setCurrentModel(request, viewModel);
                return getJspView(JSP_SHOW_PRIZE);
            }
        }
        else
        {
            String callback = Cst.DOMAIN_NAME + "/migu/user/show/" + showId + "/showprize";
            return authEnsureFullAndCallback(request, callback);
        }
    }

    @RequestMapping("{showId}/showreceivegift")
    public View showReceiveGift(HttpServletRequest request, @PathVariable("showId") int showId)
    {
        if (isInvalidClient(request))
        {
            return getQQShareView(request, showId);
        }

        User user = getCurrentUser(request);
        if (user != null)
        {
            UserShowModel viewModel = getCurrentModel(request, modelClass);
            userShowService.initUserShowModel(viewModel, showId);

            if (isJustShare(request))
            {
                return getShareView(request, viewModel);
            }
            else
            {
                setCurrentModel(request, viewModel);
                return getJspView(JSP_SHOW_GIFT_RECEIVE);
            }
        }
        else
        {
            String callback = Cst.DOMAIN_NAME + "/migu/user/show/" + showId + "/showreceivegift";
            return authEnsureFullAndCallback(request, callback);
        }
    }

    @RequestMapping("{showId}/showsendgift")
    public View showSendGift(HttpServletRequest request, @PathVariable("showId") int showId)
    {
        if (isInvalidClient(request))
        {
            return getQQShareView(request, showId);
        }

        User user = getCurrentUser(request);
        if (user != null)
        {
            UserShowModel viewModel = getCurrentModel(request, modelClass);
            userShowService.initUserShowModel(viewModel, showId);

            if (isJustShare(request))
            {
                return getShareView(request, viewModel);
            }
            else
            {
                setCurrentModel(request, viewModel);
                return getJspView(JSP_SHOW_GIFT_SEND);
            }
        }
        else
        {
            String callback = Cst.DOMAIN_NAME + "/migu/user/show/" + showId + "/showsendgift";
            return authEnsureFullAndCallback(request, callback);
        }
    }


    @RequestMapping("{showId}/support")
    public View support(HttpServletRequest request, @PathVariable("showId") int showId)
    {
        UserShowModel viewModel = getCurrentModel(request, modelClass);
        userShowService.showAction(viewModel, showId, 1);
        setCurrentModel(request, viewModel);

        JSONObject jo = new JSONObject();
        jo.put("isSuccess", "yes");
        return getJsonView(jo.toString());
    }

    @RequestMapping("{showId}/opposite")
    public View opposite(HttpServletRequest request, @PathVariable("showId") int showId)
    {
        UserShowModel viewModel = getCurrentModel(request, modelClass);
        userShowService.showAction(viewModel, showId, 2);
        setCurrentModel(request, viewModel);

        JSONObject jo = new JSONObject();
        jo.put("isSuccess", "yes");
        return getJsonView(jo.toString());
    }


    @RequestMapping("praiseuser")
    public View praiseUser(HttpServletRequest request)
    {
        UserShowModel viewModel = getCurrentModel(request, modelClass);
        userShowService.showGrabBag(viewModel, Cst.ShowGrabBagType.SHOW_GRAB_BAG_PRAISE);
        setCurrentModel(request, viewModel);

        request.getSession().setAttribute("just_created_share_type", "praiseuser");

        return getRedirectView(Cst.DOMAIN_NAME + "/migu/user/show/" + viewModel.getShowGrabBag().getShowId()
                + "/praiseuser");
    }

    @RequestMapping("laughuser")
    public View laughuser(HttpServletRequest request)
    {
        UserShowModel viewModel = getCurrentModel(request, modelClass);
        userShowService.showGrabBag(viewModel, Cst.ShowGrabBagType.SHOW_GRAB_BAG_LAUGH);
        setCurrentModel(request, viewModel);

        request.getSession().setAttribute("just_created_share_type", "laughuser");

        return getRedirectView(Cst.DOMAIN_NAME + "/migu/user/show/" + viewModel.getShowGrabBag().getShowId()
                + "/laughuser");
    }

    // --------------------------------------------------------------------------------
    private View getQQShareView(HttpServletRequest request, int showId)
    {
        UserShowModel viewModel = getCurrentModel(request, modelClass);
        userShowService.initUserShowModel(viewModel, showId);
        userShowService.initShareInfo(viewModel);

        setCurrentModel(request, viewModel);
        return getJspView(SHARE_SHOW_FOR_QQ_SERVER);
    }

    private View getShareView(HttpServletRequest request, UserShowModel viewModel)
    {
        userShowService.initShareInfo(viewModel);
        setCurrentModel(request, viewModel);
        return getJspView(SHARE_SHOW);
    }

}
