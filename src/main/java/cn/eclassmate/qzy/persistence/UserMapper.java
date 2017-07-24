package cn.eclassmate.qzy.persistence;

import cn.eclassmate.qzy.domain.Admin;
import cn.eclassmate.qzy.domain.Editor;
import cn.eclassmate.qzy.domain.User;
import cn.eclassmate.qzy.domain.UserFriend;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper
{
    User getUser(@Param("userId") int userId);

    User getUserByQQOpenId(@Param("qqOpenId") String qqOpenId);

    User getUserByOpenId(@Param("openId") String openId);

    User getUserByUnionId(@Param("unionId") String unionId);

    Editor getEditorByLoginName(@Param("loginName") String loginName);

    Admin getAdminByLoginName(@Param("loginName") String loginName);

    int insertUser(User user);

    int updateUser(User user);

    int updateEditor(Editor editor);

    int updateAdmin(Admin admin);

    List<UserFriend> getUserFriend(@Param("userId") int userId, @Param("friendId") int friendId);

    void insertUserFriend(UserFriend userFriend);

    void updateUserFriend(UserFriend userFriend);

    List<Editor> getEditorList();

    Editor getEditorByEditorId(int editorId);

    int getUserCount();
}
