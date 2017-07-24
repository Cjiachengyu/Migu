package cn.eclassmate.qzy.service;

import cn.eclassmate.qzy.domain.Bill;
import cn.eclassmate.qzy.domain.User;
import cn.eclassmate.qzy.domain.UserFriend;
import cn.eclassmate.qzy.global.Cst;
import cn.eclassmate.qzy.global.Utl;
import cn.eclassmate.qzy.persistence.*;
import cn.eclassmate.qzy.viewmodel.AbstractModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractService implements Serializable
{
    private static final long serialVersionUID = 7531894485694494921L;

    @Autowired
    protected QueMapper queMapper;

    @Autowired
    protected UserMapper userMapper;

    @Autowired
    protected AdminMapper adminMapper;


    @Autowired
    protected BagMapper bagMapper;

    @Autowired
    protected ShowMapper showMapper;

    @Autowired
    protected SystemMapper systemMapper;

    // 事务
    // --------------------------------------------------------------------------------
    @Transactional
    public void updateFriendship(int user1, int user2)
    {
        if (user1 == user2)
        {
            // 自已抢自己的红包，不算好友
            return;
        }
        else
        {
            // 如果已经是好友，则亲密度加1；如果不是好友，则生成好友关系，亲密度初始化为1；

            List<UserFriend> friends = userMapper.getUserFriend(user1, user2);
            if (friends == null || friends.size() == 0)
            {
                insertUserFriend(user1, user2);
            }
            else if (friends.size() == 2)
            {
                updateUserFriend(friends);
            }
            else if (friends.size() == 1)
            {
                // 正常情况下不会出现，但是要考虑，防止数据源出错
                completeUserFriend(friends.get(0));
            }
        }
    }

    // private
    // --------------------------------------------------------------------------------
    private void completeUserFriend(UserFriend userFriend)
    {
        UserFriend oppositeUserFriend = new UserFriend();
        oppositeUserFriend.setUserId(userFriend.getFriendId());
        oppositeUserFriend.setFriendId(userFriend.getUserId());
        oppositeUserFriend.setIntimacy(userFriend.getIntimacy() + 1);
        userMapper.insertUserFriend(oppositeUserFriend);

        userFriend.setIntimacy(userFriend.getIntimacy() + 1);
        userMapper.updateUserFriend(userFriend);
    }

    private void updateUserFriend(List<UserFriend> userFriends)
    {
        for (UserFriend userFriend : userFriends)
        {
            userFriend.setIntimacy(userFriend.getIntimacy() + 1);
            userMapper.updateUserFriend(userFriend);
        }
    }

    private void insertUserFriend(int creatorId, int currentUserId)
    {
        UserFriend userFriend1 = new UserFriend();
        userFriend1.setUserId(creatorId);
        userFriend1.setFriendId(currentUserId);
        userFriend1.setIntimacy(1);
        userMapper.insertUserFriend(userFriend1);

        UserFriend userFriend2 = new UserFriend();
        userFriend2.setUserId(currentUserId);
        userFriend2.setFriendId(creatorId);
        userFriend2.setIntimacy(1);
        userMapper.insertUserFriend(userFriend2);
    }

    protected void refreshUserMoney(AbstractModel viewModel)
    {
        int userId = viewModel.getUser().getUserId();
        User user = userMapper.getUser(userId);
        viewModel.getUser().setMoney(user.getMoney());
    }

    public int getCreateBagCount(int userId)
    {
        return bagMapper.getCreateBagCount(userId, Utl.getZeroTimeToday());
    }

    protected void changeUserMoney(AbstractModel viewModel, int userId, int billType, int deltaMoney, String billMsg)
    {
        changeUserMoney(viewModel, userId, billType, deltaMoney, billMsg, Utl.currentSeconds());
    }

    protected void changeUserMoney(AbstractModel viewModel, int userId, int billType, int deltaMoney, String billMsg,
            int currentSeconds)
    {
        if (deltaMoney > 0)
        {
            User user = userMapper.getUser(userId);
            if (billType < Cst.BillType.SPEND_OR_GET_SEP)
            {
                user.setMoney(user.getMoney() - deltaMoney);
            }
            else
            {
                user.setMoney(user.getMoney() + deltaMoney);
            }
            userMapper.updateUser(user);

            Bill bill = new Bill();
            bill.setBillType(billType);
            bill.setUserId(userId);
            bill.setMoney(deltaMoney);
            bill.setCreateTime(currentSeconds);
            bill.setDescription(billMsg + bill.getMoney() + "作业币！帐户余额变为：" + user.getMoney());
            bagMapper.insertBill(bill);

            // 更新统计数据
            if (billType == Cst.BillType.SPEND)
            {
                bagMapper.updateUserSpentMoney(userId);
                bagMapper.updateWeekUserSpentMoney(userId, Utl.getBeginTimeOfThisWeek());
            }
            else if (billType == Cst.BillType.BAG_RETURN)
            {
                bagMapper.updateBagCreatorMoney(userId);
                bagMapper.updateWeekBagCreatorMoney(userId, Utl.getBeginTimeOfThisWeek());
            }

            // 如果是当前用户，刷新当前用户的钱
            if (viewModel != null && viewModel.getUser().getUserId() == user.getUserId())
            {
                viewModel.getUser().setMoney(user.getMoney());
            }
        }
    }

}
