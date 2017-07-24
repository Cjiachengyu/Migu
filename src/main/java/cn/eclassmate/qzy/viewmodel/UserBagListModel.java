package cn.eclassmate.qzy.viewmodel;

import cn.eclassmate.qzy.domain.Bag;
import cn.eclassmate.qzy.domain.User;
import cn.eclassmate.qzy.domain.UserBag;
import cn.eclassmate.qzy.global.Utl;

import java.util.List;

/**
 * Created by stao on 2015/5/8.
 */
public class UserBagListModel extends AbstractModel
{
    private static final long serialVersionUID = -7819385797699690129L;

    // fields
    // --------------------------------------------------------------------------------
    List<Bag> mySentBagList;    // bag.creatorId==userId
    int mySentBagMoney;

    List<UserBag> myGotBagList; // user_bag.userId==userId && user_bag.userBagStatus==FINISH_ALL_RIGHT_AND_WIN
    int myGotBagMoney;

    // getter, setter
    // --------------------------------------------------------------------------------
    public List<Bag> getMySentBagList()
    {
        return mySentBagList;
    }

    public void setMySentBagList(List<Bag> mySentBagList)
    {
        this.mySentBagList = mySentBagList;
    }

    public List<UserBag> getMyGotBagList()
    {
        return myGotBagList;
    }

    public void setMyGotBagList(List<UserBag> myGotBagList)
    {
        this.myGotBagList = myGotBagList;
    }

    public int getMySentBagMoney()
    {
        return mySentBagMoney;
    }

    public void setMySentBagMoney(int mySentBagMoney)
    {
        this.mySentBagMoney = mySentBagMoney;
    }

    public int getMyGotBagMoney()
    {
        return myGotBagMoney;
    }

    public void setMyGotBagMoney(int myGotBagMoney)
    {
        this.myGotBagMoney = myGotBagMoney;
    }

}
