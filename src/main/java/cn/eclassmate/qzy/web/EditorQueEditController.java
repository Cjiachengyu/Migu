package cn.eclassmate.qzy.web;

import cn.eclassmate.qzy.service.EditorQueEditService;
import cn.eclassmate.qzy.viewmodel.EditorQueEditModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("editor/queedit")
public class EditorQueEditController extends AbstractController
{
    private static final Logger logger = LogManager.getLogger();

    // page
    // --------------------------------------------------------------------------------
    private static final String QUE_EDIT = "editor/EditorQueEdit";
    private static final String LAST_QUE_PREVIEW = "editor/subview/LastQuePreview";

    // model
    // --------------------------------------------------------------------------------
    private static final Class<EditorQueEditModel> modelClass = EditorQueEditModel.class;

    // service
    // --------------------------------------------------------------------------------
    @Autowired
    EditorQueEditService editorQueEditService;

    // handler
    // --------------------------------------------------------------------------------
    @RequestMapping("create")
    public View create(HttpServletRequest request) throws Exception
    {
        if (getCurrentEditor(request) == null)
        {
            return getEditorLogoutView();
        }

        EditorQueEditModel viewModel = getCurrentModel(request, modelClass);
        editorQueEditService.initQueEditCreate(viewModel);
        setCurrentModel(request, viewModel);

        return getJspView(QUE_EDIT);
    }

    @RequestMapping("{queId}/modify")
    public View modify(HttpServletRequest request, @PathVariable Integer queId) throws Exception
    {
        if (getCurrentEditor(request) == null)
        {
            return getEditorLogoutView();
        }

        EditorQueEditModel viewModel = getCurrentModel(request, modelClass);
        editorQueEditService.initQueEditModify(viewModel, queId);
        setCurrentModel(request, viewModel);

        return getJspView(QUE_EDIT);
    }

    @RequestMapping("{queId}/saveas")
    public View saveAs(HttpServletRequest request, @PathVariable Integer queId) throws Exception
    {
        if (getCurrentEditor(request) == null)
        {
            return getEditorLogoutView();
        }

        EditorQueEditModel viewModel = getCurrentModel(request, modelClass);
        editorQueEditService.initQueEditSaveAs(viewModel, queId);
        setCurrentModel(request, viewModel);

        return getJspView(QUE_EDIT);
    }

    @RequestMapping("docreatequestion")
    public View doCreateQuestion(HttpServletRequest request, String source, String kaodian, String zsdValue,
            String contentHtml, String analysisHtml, String answerHtml, int answerNum, int choiceCount,
            String imageSrcList) throws Exception
    {
        if (getCurrentEditor(request) == null)
        {
            return getEditorTimoutView();
        }

        // 获取选项列表
        Map<Integer, String> queChoiceHtmlMap = new HashMap<Integer, String>();
        for (int i = 1; i <= choiceCount; i++)
        {
            queChoiceHtmlMap.put(i, request.getParameter("queChoiceHtml" + i));
        }

        EditorQueEditModel viewModel = getCurrentModel(request, modelClass);
        editorQueEditService.doCreateQue(viewModel, source, kaodian, zsdValue, contentHtml, analysisHtml, answerHtml,
                queChoiceHtmlMap, answerNum, imageSrcList);
        setCurrentModel(request, viewModel);

        return getJspView(LAST_QUE_PREVIEW);
    }


    @RequestMapping("resetoperationType")
    public View resetOperationType(HttpServletRequest request) throws Exception
    {
        if (getCurrentEditor(request) == null)
        {
            return getEditorTimoutView();
        }

        EditorQueEditModel viewModel = getCurrentModel(request, modelClass);
        editorQueEditService.resetoperationType(viewModel);
        setCurrentModel(request, viewModel);

        return getTextPlainView("ok");
    }

}
