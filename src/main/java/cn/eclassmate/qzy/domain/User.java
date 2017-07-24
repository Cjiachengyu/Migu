package cn.eclassmate.qzy.domain;

import cn.eclassmate.qzy.global.Utl;

import java.io.Serializable;

public class User implements Serializable
{
    private static final long serialVersionUID = -1233622778451379089L;

    // user
    // --------------------------------------------------------------------------------
    private int userId;
    private String userName;
    private String openId;  // 微信openId
    private String unionId; // 微信unionId
    private String qqOpenId;// QQ的openId

    private int registerTime;
    private int updateWeixinTime;
    private int lastLoginTime;

    private int gender;     // 0 -> unknown; 1 -> male; 2 -> female;
    private String portrait = "";// 头像url
    private String city = "";
    private String province = "";
    private String country = "";

    private int money;
    private int shuatiZskId;
    private int createBagZskId;

    private int answerCount;
    private int rightAnswerCount;
    private int sentMoney;
    private int returnedMoney;
    private int spentMoney;

    private int weekAnswerCount;
    private int weekRightAnswerCount;
    private int weekSentMoney;
    private int weekReturnedMoney;
    private int weekSpentMoney;

    private int tuiGuangStatus;

    // 附加字段
    // --------------------------------------------------------------------------------
    private int todayCreatedBagCount = -1;

    // 附加字段
    // --------------------------------------------------------------------------------
    public double getRightPercent()
    {
        return Utl.getPercent(rightAnswerCount, answerCount, 1);
    }

    public double getWeekRightPercent()
    {
        return Utl.getPercent(weekRightAnswerCount, weekAnswerCount, 1);
    }

    public boolean getIsUserInfoFull()
    {
        return updateWeixinTime != 0;
    }

    // getter, setter
    // --------------------------------------------------------------------------------
    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public int getGender()
    {
        return gender;
    }

    public void setGender(int gender)
    {
        this.gender = gender;
    }

    public int getRegisterTime()
    {
        return registerTime;
    }

    public void setRegisterTime(int registerTime)
    {
        this.registerTime = registerTime;
    }

    public String getOpenId()
    {
        return openId;
    }

    public void setOpenId(String openId)
    {
        this.openId = openId;
    }

    public String getUnionId()
    {
        return unionId;
    }

    public void setUnionId(String unionId)
    {
        this.unionId = unionId;
    }

    public int getMoney()
    {
        return money;
    }

    public void setMoney(int money)
    {
        this.money = money;
    }

    public String getPortrait()
    {
        return portrait;
    }

    public void setPortrait(String portrait)
    {
        this.portrait = portrait;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getProvince()
    {
        return province;
    }

    public void setProvince(String province)
    {
        this.province = province;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public int getRightAnswerCount()
    {
        return rightAnswerCount;
    }

    public void setRightAnswerCount(int rightAnswerCount)
    {
        this.rightAnswerCount = rightAnswerCount;
    }

    public int getSentMoney()
    {
        return sentMoney;
    }

    public void setSentMoney(int sentMoney)
    {
        this.sentMoney = sentMoney;
    }

    public int getLastLoginTime()
    {
        return lastLoginTime;
    }

    public void setLastLoginTime(int lastLoginTime)
    {
        this.lastLoginTime = lastLoginTime;
    }

    public int getUpdateWeixinTime()
    {
        return updateWeixinTime;
    }

    public void setUpdateWeixinTime(int updateWeixinTime)
    {
        this.updateWeixinTime = updateWeixinTime;
    }

    public int getReturnedMoney()
    {
        return returnedMoney;
    }

    public void setReturnedMoney(int returnedMoney)
    {
        this.returnedMoney = returnedMoney;
    }

    public String getQqOpenId()
    {
        return qqOpenId;
    }

    public void setQqOpenId(String qqOpenId)
    {
        this.qqOpenId = qqOpenId;
    }

    public int getSpentMoney()
    {
        return spentMoney;
    }

    public void setSpentMoney(int spentMoney)
    {
        this.spentMoney = spentMoney;
    }

    public int getShuatiZskId()
    {
        return shuatiZskId;
    }

    public void setShuatiZskId(int shuatiZskId)
    {
        this.shuatiZskId = shuatiZskId;
    }

    public int getAnswerCount()
    {
        return answerCount;
    }

    public void setAnswerCount(int answerCount)
    {
        this.answerCount = answerCount;
    }

    public int getWeekAnswerCount()
    {
        return weekAnswerCount;
    }

    public void setWeekAnswerCount(int weekAnswerCount)
    {
        this.weekAnswerCount = weekAnswerCount;
    }

    public int getWeekRightAnswerCount()
    {
        return weekRightAnswerCount;
    }

    public void setWeekRightAnswerCount(int weekRightAnswerCount)
    {
        this.weekRightAnswerCount = weekRightAnswerCount;
    }

    public int getWeekSentMoney()
    {
        return weekSentMoney;
    }

    public void setWeekSentMoney(int weekSentMoney)
    {
        this.weekSentMoney = weekSentMoney;
    }

    public int getWeekReturnedMoney()
    {
        return weekReturnedMoney;
    }

    public void setWeekReturnedMoney(int weekReturnedMoney)
    {
        this.weekReturnedMoney = weekReturnedMoney;
    }

    public int getWeekSpentMoney()
    {
        return weekSpentMoney;
    }

    public void setWeekSpentMoney(int weekSpentMoney)
    {
        this.weekSpentMoney = weekSpentMoney;
    }

    public int getTodayCreatedBagCount()
    {
        if (userId == 10001 || userId == 10004 || userId == 10005 || userId == 10017)
        {
            return 0;
        }

        return todayCreatedBagCount;
    }

    public void setTodayCreatedBagCount(int todayCreatedBagCount)
    {
        this.todayCreatedBagCount = todayCreatedBagCount;
    }

    public int getCreateBagZskId()
    {
        return createBagZskId;
    }

    public void setCreateBagZskId(int createBagZskId)
    {
        this.createBagZskId = createBagZskId;
    }

    public int getTuiGuangStatus()
    {
        return tuiGuangStatus;
    }

    public void setTuiGuangStatus(int tuiGuangStatus)
    {
        this.tuiGuangStatus = tuiGuangStatus;
    }


}
