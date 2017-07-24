package cn.eclassmate.qzy.domain;

import java.io.Serializable;

public class UserFriend implements Serializable
{
    private static final long serialVersionUID = 9010426533072104000L;

    // user_friend
    // --------------------------------------------------------------------------------
    private int userId;
    private int friendId;
    private int intimacy;

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

    public int getFriendId()
    {
        return friendId;
    }

    public void setFriendId(int friendId)
    {
        this.friendId = friendId;
    }

    public int getIntimacy()
    {
        return intimacy;
    }

    public void setIntimacy(int intimacy)
    {
        this.intimacy = intimacy;
    }
}
