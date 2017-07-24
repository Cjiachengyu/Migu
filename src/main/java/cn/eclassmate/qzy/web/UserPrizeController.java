package cn.eclassmate.qzy.web;

import cn.eclassmate.qzy.domain.User;
import cn.eclassmate.qzy.service.UserPrizeService;
import cn.eclassmate.qzy.viewmodel.UserPrizeModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("user/prize")
public class UserPrizeController extends AbstractController
{
    private static final Logger logger = LogManager.getLogger();

    // page
    // --------------------------------------------------------------------------------
    private static final String PRIZE_EXCHANGE = "user/prize/PrizeExchange";
    private static final String LOTTERY = "user/prize/Lottery";

    // model
    // --------------------------------------------------------------------------------
    private static final Class<UserPrizeModel> modelClass = UserPrizeModel.class;

    // service
    // --------------------------------------------------------------------------------
    @Autowired
    UserPrizeService userPrizeService;

    // handler
    // --------------------------------------------------------------------------------
    //奖品兑换页面，所有奖品
    @RequestMapping("prizeexchange")
    public View prizeExchange(HttpServletRequest request) throws Exception
    {
        User user = getCurrentUser(request);
        if (user != null)
        {
            UserPrizeModel upModel = getCurrentModel(request, modelClass);

            upModel = userPrizeService.initPrizeList(upModel);

            setCurrentModel(request, upModel);
            return getJspView(PRIZE_EXCHANGE);
        }
        else
        {
            return authEnsureFullAndCallback(request);
        }
    }

    //奖品兑换页面,兑换奖品
    @RequestMapping("doexchange")
    public View doExchange(HttpServletRequest request, Integer prizeId, Integer prizeMoney) throws Exception
    {
        User user = getCurrentUser(request);
        if (user != null)
        {
            UserPrizeModel upModel = getCurrentModel(request, modelClass);

            userPrizeService.prizeExchange(upModel, prizeId, prizeMoney);

            JSONObject jo = new JSONObject();
            jo.put("isSuccess", "yes");
            return getJsonView(jo.toString());
        }
        else
        {
            return authEnsureFullAndCallback(request);
        }
    }

    @RequestMapping("lottery")
    public View lottery(HttpServletRequest request) throws Exception
    {
        User user = getCurrentUser(request);
        if (user != null)
        {
            return getJspView(LOTTERY);
        }
        else
        {
            return authEnsureFullAndCallback(request);
        }
    }

    //奖品兑换页面,兑换奖品
    @RequestMapping("joinlottery")
    public View joinLottery(HttpServletRequest request, Integer lotteryItem) throws Exception
    {
        User user = getCurrentUser(request);
        if (user != null)
        {
            UserPrizeModel upModel = getCurrentModel(request, modelClass);
            userPrizeService.joinLottery(upModel, lotteryItem);
            return getTextPlainView("ok");
        }
        else
        {
            return authEnsureFullAndCallback(request);
        }
    }


}
