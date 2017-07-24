package cn.eclassmate.qzy.web;

import cn.eclassmate.qzy.domain.Editor;
import cn.eclassmate.qzy.service.EditorQueListService;
import cn.eclassmate.qzy.viewmodel.EditorQueListModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("editor/quelist")
public class EditorQueListController extends AbstractController
{
    private static final Logger logger = LogManager.getLogger();

    // page
    // --------------------------------------------------------------------------------
    private static final String QUE_LIST = "editor/EditorQueList";
    private static final String QUE_LIST_VIEW = "editor/subview/QueListView";

    // model
    // --------------------------------------------------------------------------------
    private static final Class<EditorQueListModel> modelClass = EditorQueListModel.class;

    // service
    // --------------------------------------------------------------------------------
    @Autowired
    EditorQueListService editorQueListService;

    // handler
    // --------------------------------------------------------------------------------
    @RequestMapping("main")
    public View main(HttpServletRequest request) throws Exception
    {
        if (getCurrentEditor(request) == null)
        {
            return getEditorLogoutView();
        }

        EditorQueListModel viewModel = getCurrentModel(request, modelClass);
        editorQueListService.initQueList(viewModel, true);
        setCurrentModel(request, viewModel);

        return getJspView(QUE_LIST);
    }

    @RequestMapping("backtolist")
    public View backToList(HttpServletRequest request) throws Exception
    {
        if (getCurrentEditor(request) == null)
        {
            return getEditorLogoutView();
        }

        EditorQueListModel viewModel = getCurrentModel(request, modelClass);
        editorQueListService.initQueList(viewModel, false);
        setCurrentModel(request, viewModel);

        return getJspView(QUE_LIST);
    }

    @RequestMapping("gotopage")
    public View gotoPage(HttpServletRequest request, Integer pageNum) throws Exception
    {
        if (getCurrentEditor(request) == null)
        {
            return getEditorTimoutView();
        }

        EditorQueListModel viewModel = getCurrentModel(request, modelClass);
        editorQueListService.gotoPage(viewModel, pageNum);
        setCurrentModel(request, viewModel);

        return getJspView(QUE_LIST_VIEW);
    }

    @RequestMapping("changefromtype")
    public View changeFromType(HttpServletRequest request, Integer fromType) throws Exception
    {
        if (getCurrentEditor(request) == null)
        {
            return getEditorTimoutView();
        }

        EditorQueListModel viewModel = getCurrentModel(request, modelClass);
        editorQueListService.changeFromType(viewModel, fromType);
        setCurrentModel(request, viewModel);

        return getJspView(QUE_LIST_VIEW);
    }

    @RequestMapping("changefromeditor")
    public View changeFromEditor(HttpServletRequest request, Integer editorId) throws Exception
    {
        if (getCurrentEditor(request) == null)
        {
            return getEditorTimoutView();
        }

        EditorQueListModel viewModel = getCurrentModel(request, modelClass);
        editorQueListService.changeFromEditor(viewModel, editorId);
        setCurrentModel(request, viewModel);

        return getJspView(QUE_LIST_VIEW);
    }

    @RequestMapping("changefromstatus")
    public View changeFromStatus(HttpServletRequest request, Integer queStatus) throws Exception
    {
        if (getCurrentEditor(request) == null)
        {
            return getEditorTimoutView();
        }

        EditorQueListModel viewModel = getCurrentModel(request, modelClass);
        editorQueListService.changeFromStatus(viewModel, queStatus);
        setCurrentModel(request, viewModel);

        return getJspView(QUE_LIST_VIEW);
    }

    @RequestMapping("changefromzsk")
    public View changeFromZsk(HttpServletRequest request, Integer zskId) throws Exception
    {
        if (getCurrentEditor(request) == null)
        {
            return getEditorTimoutView();
        }

        EditorQueListModel viewModel = getCurrentModel(request, modelClass);
        editorQueListService.changeFromZsk(viewModel, zskId);
        setCurrentModel(request, viewModel);

        return getJspView(QUE_LIST_VIEW);
    }

    @RequestMapping("changefromzsd1")
    public View changeFromZsd1(HttpServletRequest request, Integer zsd1Id) throws Exception
    {
        if (getCurrentEditor(request) == null)
        {
            return getEditorTimoutView();
        }

        EditorQueListModel viewModel = getCurrentModel(request, modelClass);
        editorQueListService.changeFromZsd1(viewModel, zsd1Id);
        setCurrentModel(request, viewModel);

        return getJspView(QUE_LIST_VIEW);
    }

    @RequestMapping("changefromzsd2")
    public View changeFromZsd2(HttpServletRequest request, Integer zsd2Id) throws Exception
    {
        if (getCurrentEditor(request) == null)
        {
            return getEditorTimoutView();
        }

        EditorQueListModel viewModel = getCurrentModel(request, modelClass);
        editorQueListService.changeFromZsd2(viewModel, zsd2Id);
        setCurrentModel(request, viewModel);

        return getJspView(QUE_LIST_VIEW);
    }

    @RequestMapping("deleteque")
    public View deleteQue(HttpServletRequest request, Integer queId) throws Exception
    {
        if (getCurrentEditor(request) == null)
        {
            return getEditorTimoutView();
        }

        EditorQueListModel viewModel = getCurrentModel(request, modelClass);
        editorQueListService.deleteQue(viewModel, queId);
        setCurrentModel(request, viewModel);

        return getJspView(QUE_LIST_VIEW);
    }

    @RequestMapping("checkque")
    public View checkQue(HttpServletRequest request, Integer queId) throws Exception
    {
        if (getCurrentEditor(request) == null)
        {
            return getEditorTimoutView();
        }

        EditorQueListModel viewModel = getCurrentModel(request, modelClass);
        editorQueListService.checkQue(viewModel, queId);
        setCurrentModel(request, viewModel);

        return getJspView(QUE_LIST_VIEW);
    }

    @RequestMapping("{editorId}/adminlogin")
    public View adminLogin(HttpServletRequest request, @PathVariable Integer editorId) throws Exception
    {
        if (getCurrentAdmin(request) == null)
        {
            return getAdminLogoutView();
        }

        Editor editor = editorQueListService.getEditor(editorId);
        request.getSession().setAttribute("editor", editor);

        return getRedirectView("/migu/editor/quelist/main");
    }

}
