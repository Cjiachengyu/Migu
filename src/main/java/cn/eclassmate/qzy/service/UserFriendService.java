package cn.eclassmate.qzy.service;

import cn.eclassmate.qzy.domain.User;
import cn.eclassmate.qzy.global.Utl;
import cn.eclassmate.qzy.viewmodel.UserFriendModel;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserFriendService extends AbstractService
{
    private static final long serialVersionUID = -4055813186717370244L;
    private static final int rankCount = 10;

    public void refreshRankListRightAnswer(UserFriendModel ufbrModel)
    {
        int userId = ufbrModel.getUser().getUserId();
        int beginTime = Utl.getBeginTimeOfThisWeek();

        // TODO
        // 这边是把所有好友的这个星期答对统计都查出来，然后从这里面获取当前用户在好友中的排名，
        // 再把list截取前rankCount个放到新的list中，传给ufbrModel
        // 暂时这样做是因为没能实现一条sql查询当前用户这个星期在好友中的排名，后面有时间想想有没有更好的办法实现
        List<User> userRightAnswerRankList = bagMapper.getUserRightAnswerCountRankListInFriend(userId, beginTime);

        // 从列表里得到我的排名
        int myRightAnswerRank = getMyRank(ufbrModel, userRightAnswerRankList);

        List<User> userRightAnswerRankList2 = new ArrayList<User>();
        for (int userListIndex = 0; userListIndex < rankCount; userListIndex++)
        {
            if (userListIndex < userRightAnswerRankList.size())
            {
                userRightAnswerRankList2.add(userRightAnswerRankList.get(userListIndex));
            }
        }

        ufbrModel.getUser().setRightAnswerCount(bagMapper.getUserRightAnswerCountByTime(userId));
        ufbrModel.setUserRightAnswerRankList(userRightAnswerRankList2);
        ufbrModel.setMyRightAnswerRank(myRightAnswerRank);
    }

    public void refreshRankListMoney(UserFriendModel ufbrModel)
    {
        refreshUserMoney(ufbrModel);

        int userId = ufbrModel.getUser().getUserId();
        List<User> userMoneyRankList = bagMapper.getUserMoneyRankListInFriend(userId, rankCount);

        // 先试着从列表里得到我的排名
        int myMoneyRank = getMyRank(ufbrModel, userMoneyRankList);

        // 如果没有得到我的排名，从数据库里查
        if (myMoneyRank == 0)
        {
            myMoneyRank = bagMapper.getMyMoneyRankInFriends(userId);

            // 如果查到的排名在名单里，说明我与最后几名排名并列，但为了不给用户错觉，规定其排名在最后一名之后
            if (myMoneyRank <= userMoneyRankList.size())
            {
                myMoneyRank = userMoneyRankList.size() + 1;
            }
        }

        ufbrModel.setUserMoneyRankList(userMoneyRankList);
        ufbrModel.setMyMoneyRank(myMoneyRank);
    }

    public void refreshRankListSentMoney(UserFriendModel ufbrModel)
    {
        int userId = ufbrModel.getUser().getUserId();
        int beginTime = Utl.getBeginTimeOfThisWeek();

        List<User> userSentRankList = bagMapper.getUserSentMoneyRankListInFriend(userId, beginTime);

        // 从列表里得到我的排名
        int mySentMoneyRank = getMyRank(ufbrModel, userSentRankList);

        List<User> userSentRankList2 = new ArrayList<User>();
        for (int userListIndex = 0; userListIndex < rankCount; userListIndex++)
        {
            if (userListIndex < userSentRankList.size())
            {
                userSentRankList2.add(userSentRankList.get(userListIndex));
            }
        }

        ufbrModel.getUser().setSentMoney(bagMapper.getUserSentMoneyByTime(userId, beginTime));
        ufbrModel.setUserSentMoneyRankList(userSentRankList2);
        ufbrModel.setMySentMoneyRank(mySentMoneyRank);
    }

    public void refreshRankListReturnedMoney(UserFriendModel ufbrModel)
    {
        int userId = ufbrModel.getUser().getUserId();
        int beginTime = Utl.getBeginTimeOfThisWeek();

        List<User> userReturnedMoneyRankList = bagMapper.getUserReturnedMoneyRankListInFriend(userId, beginTime);

        // 从列表里得到我的排名
        int myReturnedMoneyRank = getMyRank(ufbrModel, userReturnedMoneyRankList);

        List<User> userReturnedMoneyRankList2 = new ArrayList<User>();
        for (int userListIndex = 0; userListIndex < rankCount; userListIndex++)
        {
            if (userListIndex < userReturnedMoneyRankList.size())
            {
                userReturnedMoneyRankList2.add(userReturnedMoneyRankList.get(userListIndex));
            }
        }

        ufbrModel.getUser().setReturnedMoney(bagMapper.getUserReturnedMoneyByTime(userId, beginTime));
        ufbrModel.setMyReturnedMoneyRank(myReturnedMoneyRank);
        ufbrModel.setUserReturnedMoneyRankList(userReturnedMoneyRankList2);
    }


    public void refreshRankListSpentMoney(UserFriendModel ufbrModel)
    {
        int userId = ufbrModel.getUser().getUserId();
        int beginTime = Utl.getBeginTimeOfThisWeek();

        List<User> userSpentRankList = bagMapper.getUserSpentMoneyRankListInFriend(userId, beginTime);

        // 从列表里得到我的排名
        int mySpentMoneyRank = getMyRank(ufbrModel, userSpentRankList);

        List<User> userSpentRankList2 = new ArrayList<User>();
        for (int userListIndex = 0; userListIndex < rankCount; userListIndex++)
        {
            if (userListIndex < userSpentRankList.size())
            {
                userSpentRankList2.add(userSpentRankList.get(userListIndex));
            }
        }

        ufbrModel.getUser().setSpentMoney(bagMapper.getUserSpentMoneyByTime(userId, beginTime));
        ufbrModel.setUserSpentMoneyRankList(userSpentRankList2);
        ufbrModel.setMySpentMoneyRank(mySpentMoneyRank);
    }


    private int getMyRank(UserFriendModel ufbrModel, List<User> userRankList)
    {
        int myRank = 0;
        for (int i = 0; i < userRankList.size(); i++)
        {
            if (userRankList.get(i).getUserId() == ufbrModel.getUser().getUserId())
            {
                myRank = i + 1;
                break;
            }
        }
        return myRank;
    }

}
