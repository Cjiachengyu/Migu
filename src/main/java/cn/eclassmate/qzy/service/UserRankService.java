package cn.eclassmate.qzy.service;

import cn.eclassmate.qzy.domain.User;
import cn.eclassmate.qzy.viewmodel.UserRankModel;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRankService extends AbstractService
{
    private static final long serialVersionUID = -4055813186717370244L;
    private static final int rankCount = 10;

    public void refreshRankListRightAnswer(UserRankModel ubrModel)
    {
        int userId = ubrModel.getUser().getUserId();
        List<User> userRightAnswerRankList = bagMapper.getUserRightAnswerCountRankList(rankCount);

        // 先试着从列表里得到我的排名
        int myRightAnswerRank = getMyRank(ubrModel, userRightAnswerRankList);

        // 如果没有得到我的排名，从数据库里查
        if (myRightAnswerRank == 0)
        {
            myRightAnswerRank = bagMapper.getUserRightAnswerCountRank(userId);

            // 如果查到的排名在名单里，说明我与最后几名排名并列，但为了不给用户错觉，规定其排名在最后一名之后
            if (myRightAnswerRank <= userRightAnswerRankList.size())
            {
                myRightAnswerRank = userRightAnswerRankList.size() + 1;
            }
        }

        ubrModel.getUser().setRightAnswerCount(bagMapper.getUserRightAnswerCount(userId));
        ubrModel.setUserRightAnswerRankList(userRightAnswerRankList);
        ubrModel.setMyRightAnswerRank(myRightAnswerRank);
    }

    public void refreshRankListMoney(UserRankModel ubrModel)
    {
        refreshUserMoney(ubrModel);

        List<User> userMoneyRankList = bagMapper.getUserMoneyRankList(rankCount);

        // 先试着从列表里得到我的排名
        int myMoneyRank = getMyRank(ubrModel, userMoneyRankList);

        // 如果没有得到我的排名，从数据库里查
        if (myMoneyRank == 0)
        {
            myMoneyRank = bagMapper.getMyMoneyRank(ubrModel.getUser().getUserId());

            // 如果查到的排名在名单里，说明我与最后几名排名并列，但为了不给用户错觉，规定其排名在最后一名之后
            if (myMoneyRank <= userMoneyRankList.size())
            {
                myMoneyRank = userMoneyRankList.size() + 1;
            }
        }

        ubrModel.setUserMoneyRankList(userMoneyRankList);
        ubrModel.setMyMoneyRank(myMoneyRank);
    }

    public void refreshRankListSentMoney(UserRankModel ubrModel)
    {
        int userId = ubrModel.getUser().getUserId();
        List<User> userSentRankList = bagMapper.getUserSentMoneyRankList(rankCount);
        // 先试着从列表里得到我的排名
        int mySentMoneyRank = getMyRank(ubrModel, userSentRankList);

        // 如果没有得到我的排名，从数据库里查
        if (mySentMoneyRank == 0)
        {
            mySentMoneyRank = bagMapper.getUserSentMoneyRank(userId);

            // 如果查到的排名在名单里，说明我与最后几名排名并列，但为了不给用户错觉，规定其排名在最后一名之后
            if (mySentMoneyRank <= userSentRankList.size())
            {
                mySentMoneyRank = userSentRankList.size() + 1;
            }
        }

        ubrModel.getUser().setSentMoney(bagMapper.getUserSentMoney(userId));
        ubrModel.setUserSentMoneyRankList(userSentRankList);
        ubrModel.setMySentMoneyRank(mySentMoneyRank);
    }

    public void refreshRankListReturnedMoney(UserRankModel ubrModel)
    {
        int userId = ubrModel.getUser().getUserId();
        List<User> userReturnedMoneyRankList = bagMapper.getUserReturnedMoneyRankList(rankCount);
        // 先试着从列表里得到我的排名
        int myReturnedMoneyRank = getMyRank(ubrModel, userReturnedMoneyRankList);

        // 如果没有得到我的排名，从数据库里查
        if (myReturnedMoneyRank == 0)
        {
            myReturnedMoneyRank = bagMapper.getUserReturnedMoneyRank(userId);

            // 如果查到的排名在名单里，说明我与最后几名排名并列，但为了不给用户错觉，规定其排名在最后一名之后
            if (myReturnedMoneyRank <= userReturnedMoneyRankList.size())
            {
                myReturnedMoneyRank = userReturnedMoneyRankList.size() + 1;
            }
        }

        ubrModel.getUser().setReturnedMoney(bagMapper.getUserReturnedMoney(userId));
        ubrModel.setUserReturnedMoneyRankList(userReturnedMoneyRankList);
        ubrModel.setMyReturnedMoneyRank(myReturnedMoneyRank);
    }

    public void refreshRankListSpentMoney(UserRankModel ubrModel)//================
    {
        int userId = ubrModel.getUser().getUserId();
        List<User> userSpentMoneyRankList = bagMapper.getUserSpentMoneyRankList(rankCount);
        // 先试着从列表里得到我的排名
        int mySpentMoneyRank = getMyRank(ubrModel, userSpentMoneyRankList);

        // 如果没有得到我的排名，从数据库里查
        if (mySpentMoneyRank == 0)
        {
            mySpentMoneyRank = bagMapper.getUserSpentMoneyRank(userId);

            // 如果查到的排名在名单里，说明我与最后几名排名并列，但为了不给用户错觉，规定其排名在最后一名之后
            if (mySpentMoneyRank <= userSpentMoneyRankList.size())
            {
                mySpentMoneyRank = userSpentMoneyRankList.size() + 1;
            }
        }

        ubrModel.getUser().setSpentMoney(bagMapper.getUserSpentMoney(userId));
        ubrModel.setUserSpentMoneyRankList(userSpentMoneyRankList);
        ubrModel.setMySpentMoneyRank(mySpentMoneyRank);
    }


    private int getMyRank(UserRankModel ubrModel, List<User> userRankList)
    {
        int myRank = 0;
        for (int i = 0; i < userRankList.size(); i++)
        {
            if (userRankList.get(i).getUserId() == ubrModel.getUser().getUserId())
            {
                myRank = i + 1;
                break;
            }
        }
        return myRank;
    }

}
