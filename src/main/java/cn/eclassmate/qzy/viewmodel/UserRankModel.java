package cn.eclassmate.qzy.viewmodel;

import cn.eclassmate.qzy.domain.User;

import java.util.List;

/**
 * Created by stao on 2015/5/8.
 */
public class UserRankModel extends AbstractModel
{
    private static final long serialVersionUID = -7819385797699690129L;

    // fields
    // --------------------------------------------------------------------------------
    List<User> userRightAnswerRankList;
    int myRightAnswerRank;
    List<User> userMoneyRankList;
    int myMoneyRank;
    List<User> userSentMoneyRankList;
    int mySentMoneyRank;
    List<User> userReturnedMoneyRankList;
    int myReturnedMoneyRank;
    List<User> userSpentMoneyRankList;
    int mySpentMoneyRank;

    // getter, setter
    // --------------------------------------------------------------------------------
    public List<User> getUserMoneyRankList()
    {
        return userMoneyRankList;
    }

    public void setUserMoneyRankList(List<User> userMoneyRankList)
    {
        this.userMoneyRankList = userMoneyRankList;
    }

    public int getMyMoneyRank()
    {
        return myMoneyRank;
    }

    public void setMyMoneyRank(int myMoneyRank)
    {
        this.myMoneyRank = myMoneyRank;
    }

    public List<User> getUserRightAnswerRankList()
    {
        return userRightAnswerRankList;
    }

    public void setUserRightAnswerRankList(List<User> userRightAnswerRankList)
    {
        this.userRightAnswerRankList = userRightAnswerRankList;
    }

    public int getMyRightAnswerRank()
    {
        return myRightAnswerRank;
    }

    public void setMyRightAnswerRank(int myRightAnswerRank)
    {
        this.myRightAnswerRank = myRightAnswerRank;
    }

    public List<User> getUserSentMoneyRankList()
    {
        return userSentMoneyRankList;
    }

    public void setUserSentMoneyRankList(List<User> userSentMoneyRankList)
    {
        this.userSentMoneyRankList = userSentMoneyRankList;
    }

    public int getMySentMoneyRank()
    {
        return mySentMoneyRank;
    }

    public void setMySentMoneyRank(int mySentMoneyRank)
    {
        this.mySentMoneyRank = mySentMoneyRank;
    }

    public List<User> getUserReturnedMoneyRankList()
    {
        return userReturnedMoneyRankList;
    }

    public void setUserReturnedMoneyRankList(List<User> userReturnedMoneyRankList)
    {
        this.userReturnedMoneyRankList = userReturnedMoneyRankList;
    }

    public int getMyReturnedMoneyRank()
    {
        return myReturnedMoneyRank;
    }

    public void setMyReturnedMoneyRank(int myReturnedMoneyRank)
    {
        this.myReturnedMoneyRank = myReturnedMoneyRank;
    }

    public List<User> getUserSpentMoneyRankList()
    {
        return userSpentMoneyRankList;
    }

    public void setUserSpentMoneyRankList(List<User> userSpentMoneyRankList)
    {
        this.userSpentMoneyRankList = userSpentMoneyRankList;
    }

    public int getMySpentMoneyRank()
    {
        return mySpentMoneyRank;
    }

    public void setMySpentMoneyRank(int mySpentMoneyRank)
    {
        this.mySpentMoneyRank = mySpentMoneyRank;
    }


}
