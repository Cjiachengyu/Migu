package cn.eclassmate.qzy.service.mobile;

import cn.eclassmate.qzy.domain.Bill;
import cn.eclassmate.qzy.domain.User;
import cn.eclassmate.qzy.global.Cst;
import cn.eclassmate.qzy.global.Utl;
import cn.eclassmate.qzy.service.AbstractService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService extends AbstractService
{
    private static final Logger logger = LogManager.getLogger();

    public User refreshUserWeixinInfo(String unionId, String name, int gender, String portrait, String country,
            String province, String city) throws Exception
    {
        // 先尝试在数据库获取用户
        User user = userMapper.getUserByUnionId(unionId);

        // 拉取用户信息
        if (user == null)
        {
            user = new User();
            user.setUnionId(unionId);
            user.setMoney(Cst.Money.USER_RIGISTER_MONEY);
            user.setRegisterTime(Utl.currentSeconds());

            setUserInfo(user, name, gender, portrait, country, province, city);

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
            setUserInfo(user, name, gender, portrait, country, province, city);

            userMapper.updateUser(user);
        }

        return user;
    }

    public User refreshUserQqInfo(String qqOpenId, String name, int gender, String portrait,
            String province, String city) throws Exception
    {
        // 先尝试在数据库获取用户
        User user = userMapper.getUserByQQOpenId(qqOpenId);

        // 拉取用户信息
        if (user == null)
        {
            user = new User();
            user.setQqOpenId(qqOpenId);
            user.setMoney(Cst.Money.USER_RIGISTER_MONEY);
            user.setRegisterTime(Utl.currentSeconds());

            setUserInfo(user, name, gender, portrait, "", province, city);

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
            setUserInfo(user, name, gender, portrait, "", province, city);

            userMapper.updateUser(user);
        }

        return user;
    }

    // private
    // --------------------------------------------------------------------------------
    private void setUserInfo(User user, String name, int gender, String portrait, String country, String province,
            String city)
    {
        user.setUserName(Utl.validateUtf8(name));
        user.setGender(gender);
        user.setCity(city);
        user.setProvince(province);
        user.setCountry(country);
        user.setPortrait(portrait);

        user.setUpdateWeixinTime(Utl.currentSeconds());
        user.setLastLoginTime(Utl.currentSeconds());
    }

}
