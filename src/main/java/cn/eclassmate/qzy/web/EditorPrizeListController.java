package cn.eclassmate.qzy.web;

import cn.eclassmate.qzy.service.EditorService;
import cn.eclassmate.qzy.viewmodel.EditorPrizeListModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("editor/list")
public class EditorPrizeListController extends AbstractController
{

    // page
    // --------------------------------------------------------------------------------
    private static final String EDITOR_PRIZE_LIST = "editor/EditorPrizeList";
    private static final String PRIZE_LIST_VIEW = "editor/subview/PrizeListView";

    // model
    // --------------------------------------------------------------------------------
    private static final Class<EditorPrizeListModel> modelClass = EditorPrizeListModel.class;

    // service
    // --------------------------------------------------------------------------------
    @Autowired
    EditorService editorService;

    // handler
    // --------------------------------------------------------------------------------
    @RequestMapping("prizelist")
    public View main(HttpServletRequest request) throws Exception
    {

        EditorPrizeListModel viewModel = getCurrentModel(request, modelClass);
        editorService.initEditorPrizeListModel(viewModel);
        setCurrentModel(request, viewModel);
        return getJspView(EDITOR_PRIZE_LIST);
    }


    @RequestMapping("deleteprize")
    public View deletePrize(HttpServletRequest request, Integer prizeId) throws Exception
    {
        if (getCurrentEditor(request) == null)
        {
            return getTextPlainView("timeout");
        }

        EditorPrizeListModel viewModel = getCurrentModel(request, modelClass);
        editorService.deletePrize(viewModel, prizeId);
        setCurrentModel(request, viewModel);


        return getJspView(PRIZE_LIST_VIEW);
    }
}
