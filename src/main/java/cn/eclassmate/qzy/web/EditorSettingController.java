package cn.eclassmate.qzy.web;

import cn.eclassmate.qzy.domain.Editor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("editor/setting")
public class EditorSettingController extends AbstractController
{
    private static final Logger logger = LogManager.getLogger();

    // page
    // --------------------------------------------------------------------------------
    private static final String EDITOR_SETTING = "editor/EditorSetting";

    // handler
    // --------------------------------------------------------------------------------
    @RequestMapping("changepwd")
    public View changePwd(HttpServletRequest request) throws Exception
    {
        if (getCurrentEditor(request) == null)
        {
            return getEditorLogoutView();
        }

        return getJspView(EDITOR_SETTING);
    }

    @RequestMapping("dochangepwd")
    public View doChangePwd(HttpServletRequest request, String oldPwd, String newPwd) throws Exception
    {
        if (getCurrentEditor(request) == null)
        {
            return getEditorTimoutView();
        }

        Editor editor = getCurrentEditor(request);
        if (!editor.getPassword().equals(oldPwd))
        {
            return getTextPlainView("notequal");
        }
        else if (newPwd == null || newPwd.length() < 6)
        {
            return getTextPlainView("tooshort");
        }
        else
        {
            editor.setPassword(newPwd);
            basicService.updateEditor(editor);

            return getTextPlainView("ok");
        }
    }
}
