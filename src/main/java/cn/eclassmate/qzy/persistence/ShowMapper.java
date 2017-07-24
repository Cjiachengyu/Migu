package cn.eclassmate.qzy.persistence;

import cn.eclassmate.qzy.domain.Show;
import cn.eclassmate.qzy.domain.ShowAction;
import cn.eclassmate.qzy.domain.ShowGift;
import cn.eclassmate.qzy.domain.ShowGrabBag;
import cn.eclassmate.qzy.domain.ShowPrize;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShowMapper
{
    int insertShow(Show show);

    int insertShowGrabBag(ShowGrabBag showGrabBag);

    int insertShowPrize(ShowPrize showPrize);

    int insertShowGift(ShowGift showGift);

    Show getShow(int showId);

    ShowGrabBag getShowGrabBag(int showId);

    ShowPrize getShowPrize(int showId);

    ShowGift getShowGift(int showId);

    ShowAction getShowAction(@Param("userId") int userId, @Param("showId") int showId);

    int updateShowAction(ShowAction showAction);

    int insertShowAction(ShowAction showAction);

    List<ShowAction> getShowActionList(int showId);

    int getPraiseOrlaughCount(@Param("bagId") int bagId, @Param("userId") int userId,
            @Param("showGrabBagType") int showGrabBagType);
}
