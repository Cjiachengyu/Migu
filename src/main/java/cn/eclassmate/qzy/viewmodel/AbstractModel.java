package cn.eclassmate.qzy.viewmodel;

import cn.eclassmate.qzy.domain.Admin;
import cn.eclassmate.qzy.domain.Editor;
import cn.eclassmate.qzy.domain.User;

import java.io.Serializable;

/**
 * Created by stao on 2015/5/8.
 */
public class AbstractModel implements Serializable
{
    private static final long serialVersionUID = -7819385797699690129L;

    // fields
    // --------------------------------------------------------------------------------
    User user;
    Editor editor;
    Admin admin;

    // getter, setter
    // --------------------------------------------------------------------------------
    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Editor getEditor()
    {
        return editor;
    }

    public void setEditor(Editor editor)
    {
        this.editor = editor;
    }

    public Admin getAdmin()
    {
        return admin;
    }

    public void setAdmin(Admin admin)
    {
        this.admin = admin;
    }


}
