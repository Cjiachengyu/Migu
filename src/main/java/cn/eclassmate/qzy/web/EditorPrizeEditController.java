package cn.eclassmate.qzy.web;

import cn.eclassmate.qzy.global.Cst;
import cn.eclassmate.qzy.global.Utl;
import cn.eclassmate.qzy.service.EditorService;
import cn.eclassmate.qzy.viewmodel.EditorPrizeEditModel;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.InputStream;


@Controller
@RequestMapping("editor/prizeedit")
public class EditorPrizeEditController extends AbstractController
{

    // model
    // --------------------------------------------------------------------------------
    private static final Class<EditorPrizeEditModel> modelClass = EditorPrizeEditModel.class;

    // service
    // --------------------------------------------------------------------------------
    @Autowired
    EditorService editorService;

    // page
    // --------------------------------------------------------------------------------
    private static final String EDIT_PRIZE = "editor/EditorPrizeEdit";

    // handler
    // --------------------------------------------------------------------------------
    @RequestMapping("{prizeId}/modify")
    public View modify(HttpServletRequest request, @PathVariable Integer prizeId) throws Exception
    {
        if (getCurrentEditor(request) == null)
        {
            return getEditorLogoutView();
        }

        EditorPrizeEditModel viewModel = getCurrentModel(request, modelClass);
        editorService.modifyPrize(viewModel, prizeId);
        setCurrentModel(request, viewModel);

        return getJspView(EDIT_PRIZE);
    }

    @RequestMapping("editprize")
    public View createPrize(HttpServletRequest request) throws Exception
    {

        if (getCurrentEditor(request) == null)
        {
            return getEditorLogoutView();
        }

        EditorPrizeEditModel viewModel = getCurrentModel(request, modelClass);
        viewModel.setOperationType(EditorPrizeEditModel.OPER_TYPE_CREATE);
        setCurrentModel(request, viewModel);

        return getJspView(EDIT_PRIZE);
    }

    @RequestMapping("uploadprizeimage")
    public View doUpload(HttpServletRequest request, MultipartFile prizeImage) throws IOException
    {
        InputStream is = prizeImage.getInputStream();
        String originalName = prizeImage.getOriginalFilename();

        String imageFileDir = Cst.BASE_PATH + "prize/image/";
        String saveFileName = Utl.getNextLongId() + "." + Utl.getFileSuffix(originalName);
        Utl.makeSureDirExists(imageFileDir);

        Utl.writeFile(imageFileDir + saveFileName, is);

        JSONObject jo = new JSONObject();
        jo.put("imageSrc", Cst.BASE_URL + "prize/image/" + saveFileName);
        return getJsonView(jo.toString());
    }

    @RequestMapping("docreateprize")
    public View doCreatePrize(HttpServletRequest request, String prizeName, String prizeMoney, String prizeImage)
            throws IOException
    {
        EditorPrizeEditModel viewModel = getCurrentModel(request, modelClass);
        editorService.savePrice(viewModel, prizeName, prizeMoney, prizeImage);
        setCurrentModel(request, viewModel);

        JSONObject jo = new JSONObject();
        jo.put("ok", "yes");
        return getJsonView(jo.toString());
    }
}
