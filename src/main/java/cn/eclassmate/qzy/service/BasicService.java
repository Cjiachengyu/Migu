package cn.eclassmate.qzy.service;

import cn.eclassmate.qzy.domain.Admin;
import cn.eclassmate.qzy.domain.Editor;
import cn.eclassmate.qzy.domain.Manager;
import cn.eclassmate.qzy.domain.User;

import org.springframework.stereotype.Service;

@Service
public class BasicService extends AbstractService
{
    private static final long serialVersionUID = -3671055858376986565L;

    // user
    // --------------------------------------------------------------------------------
    public Editor editorLogin(String account, String password)
    {
        Editor editor = userMapper.getEditorByLoginName(account);
        if (editor != null && editor.getPassword().equals(password))
        {
            return editor;
        }
        else
        {
            return null;
        }
    }

    public Manager managerLogin(String account, String password)
    {
        Manager manager = managerMapper.getManagerByName(account);

        if (manager != null && manager.getPassword().equals(password))
        {
            return manager;
        }
        else
        {
            return null;
        }
    }

    public User getUser(int userId)
    {
        return userMapper.getUser(userId);
    }

    public User getUserByUnionId(String unionId)
    {
        return userMapper.getUserByUnionId(unionId);
    }

    public User getUserByQQOpenId(String qqOpenId)
    {
        return userMapper.getUserByQQOpenId(qqOpenId);
    }

    public void updateUser(User user)
    {
        userMapper.updateUser(user);
    }

    public void updateEditor(Editor editor)
    {
        userMapper.updateEditor(editor);
    }

    public void updateAdmin(Admin admin)
    {
        userMapper.updateAdmin(admin);
    }
}
