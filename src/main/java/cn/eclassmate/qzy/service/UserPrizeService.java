package cn.eclassmate.qzy.service;

import cn.eclassmate.qzy.domain.Prize;
import cn.eclassmate.qzy.domain.User;
import cn.eclassmate.qzy.domain.UserPrize;
import cn.eclassmate.qzy.global.Cst;
import cn.eclassmate.qzy.viewmodel.UserPrizeModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserPrizeService extends AbstractService
{
    private static final long serialVersionUID = -2971683733562233221L;
    private static final Logger logger = LogManager.getLogger();

    public UserPrizeModel initPrizeList(UserPrizeModel upModel)
    {

        List<Prize> prizeList = bagMapper.getprizeList();

        upModel.setPrizeList(prizeList);

        return upModel;
    }

    @Transactional
    public void prizeExchange(UserPrizeModel upModel, int prizeId, int prizeMoney)
    {
        refreshUserMoney(upModel);

        User user = upModel.getUser();

        UserPrize userPrize = bagMapper.getUserPrize(user.getUserId(), prizeId);
        if (userPrize == null)
        {
            userPrize = new UserPrize();
            userPrize.setUserId(user.getUserId());
            userPrize.setPrizeId(prizeId);
            userPrize.setPrizeCount(1);

            bagMapper.insertUserPrize(userPrize);
        }
        else
        {
            userPrize.setPrizeCount(userPrize.getPrizeCount() + 1);

            bagMapper.updateUserPrize(userPrize);
        }

        changeUserMoney(upModel, upModel.getUser().getUserId(), Cst.BillType.SPEND,
                prizeMoney, "兑换了一个奖品，花去");

    }

    public void joinLottery(UserPrizeModel upModel, int lotteryItem)
    {
        if (lotteryItem == 4)
        {
            changeUserMoney(upModel, upModel.getUser().getUserId(), Cst.BillType.LOTTERY_RESULT,
                    20000, "抽奖，获得");
        }
        else if (lotteryItem == 5)
        {
            changeUserMoney(upModel, upModel.getUser().getUserId(), Cst.BillType.LOTTERY_RESULT,
                    10000, "抽奖，获得");
        }

        //抽奖花去10000作业币
        changeUserMoney(upModel, upModel.getUser().getUserId(), Cst.BillType.LOTTERY,
                Cst.Money.LOTTERY, "抽奖，花去");
    }

}
