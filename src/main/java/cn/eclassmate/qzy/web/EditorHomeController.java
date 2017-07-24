package cn.eclassmate.qzy.web;

import cn.eclassmate.qzy.domain.Editor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("editor/home")
public class EditorHomeController extends AbstractController
{
    private static final Logger logger = LogManager.getLogger();

    // page
    // --------------------------------------------------------------------------------
    private static final String HOME_INDEX = "editor/EditorHomeIndex";

    // handler
    // --------------------------------------------------------------------------------
    @RequestMapping("index")
    public View index(HttpServletRequest request) throws Exception
    {
        return getJspView(HOME_INDEX);
    }

    @RequestMapping("login")
    public View login(HttpServletRequest request, String account, String password) throws Exception
    {
        Editor editor = basicService.editorLogin(account, password);
        if (editor != null)
        {
            request.getSession().setAttribute("editor", editor);

            if ("123456".equals(editor.getPassword()))
            {
                return getTextPlainView("simplepwd");
            }
            else
            {
                if (editor.getCanOperateQue())
                {
                    return getTextPlainView("gotoque");
                }
                else if (editor.getCanOperatePrize())
                {
                    return getTextPlainView("gotoprize");
                }
                else
                {
                    return getTextPlainView("noprivilege");
                }
            }
        }

        return getTextPlainView("error");
    }

    @RequestMapping("logout")
    public View logout(HttpServletRequest request) throws Exception
    {
        request.getSession().invalidate();
        return getJspView(HOME_INDEX);
    }

}
