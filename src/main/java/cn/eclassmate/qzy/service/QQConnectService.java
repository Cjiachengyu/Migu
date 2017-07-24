package cn.eclassmate.qzy.service;

import cn.eclassmate.qzy.domain.Bill;
import cn.eclassmate.qzy.domain.User;
import cn.eclassmate.qzy.global.Cst;
import cn.eclassmate.qzy.global.Utl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class QQConnectService extends AbstractService
{
    private static final long serialVersionUID = 6344435866735052076L;
    private static final Logger logger = LogManager.getLogger();

    // 抢作业
    // --------------------------------------------------------------------------------
    public User updateUser(String qqOpenId, String userName, String gender, String protrait)
    {
        User user = userMapper.getUserByQQOpenId(qqOpenId);
        if (user == null)
        {
            user = new User();
            updateUserFields(user, userName, gender, protrait);

            user.setQqOpenId(qqOpenId);
            user.setMoney(Cst.Money.USER_RIGISTER_MONEY);
            user.setRegisterTime(Utl.currentSeconds());
            user.setLastLoginTime(Utl.currentSeconds());
            userMapper.insertUser(user);

            Bill bill = new Bill();
            bill.setBillType(Cst.BillType.SYSTEM_GIVE);
            bill.setUserId(user.getUserId());
            bill.setMoney(Cst.Money.USER_RIGISTER_MONEY);
            bill.setCreateTime(Utl.currentSeconds());
            bill.setDescription("欢迎加入抢作业，系统赠送" + bill.getMoney() + "作业币！");
            bagMapper.insertBill(bill);
        }
        else
        {
            updateUserFields(user, userName, gender, protrait);
            user.setLastLoginTime(Utl.currentSeconds());
            userMapper.updateUser(user);
        }

        return user;
    }

    private void updateUserFields(User user, String userName, String gender, String protrait)
    {
        user.setUserName(userName);
        if (gender.contains("男"))
        {
            user.setGender(1);
        }
        else if (gender.contains("女"))
        {
            user.setGender(2);
        }
        else
        {
            user.setGender(0);
        }
        user.setPortrait(protrait);
    }

}
