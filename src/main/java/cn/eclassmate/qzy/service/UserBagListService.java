package cn.eclassmate.qzy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.eclassmate.qzy.domain.Bag;
import cn.eclassmate.qzy.domain.UserBag;
import cn.eclassmate.qzy.viewmodel.UserBagListModel;

@Service
public class UserBagListService extends AbstractService
{
    private static final long serialVersionUID = -4055813186717370244L;

    public void refreshMySentBagList(UserBagListModel ublModel)
    {
        List<Bag> mySentBagList = bagMapper.getMySentBagList(ublModel.getUser().getUserId());
        int mySentBagMoney = 0;
        for (Bag bag : mySentBagList)
        {
            mySentBagMoney = mySentBagMoney + bag.getBagMoney();
        }

        ublModel.setMySentBagList(mySentBagList);
        ublModel.setMySentBagMoney(mySentBagMoney);
    }

    public void refreshMyGotBagList(UserBagListModel ublModel)
    {
        List<UserBag> myGotBagList = bagMapper.getMyGotBagList(ublModel.getUser().getUserId());
        int myGotBagMoney = 0;
        for (UserBag userBag : myGotBagList)
        {
            myGotBagMoney = myGotBagMoney + userBag.getGotMoney();
        }

        ublModel.setMyGotBagList(myGotBagList);
        ublModel.setMyGotBagMoney(myGotBagMoney);
    }
}
