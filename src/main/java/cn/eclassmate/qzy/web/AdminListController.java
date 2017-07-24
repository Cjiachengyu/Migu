package cn.eclassmate.qzy.web;

import cn.eclassmate.qzy.service.AdminListService;
import cn.eclassmate.qzy.viewmodel.AdminListModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("admin/list")
public class AdminListController extends AbstractController
{

    // page
    // --------------------------------------------------------------------------------
    private static final String ADMIN_PRIZE_LIST = "admin/AdminEditorList";

    // model
    // --------------------------------------------------------------------------------
    private static final Class<AdminListModel> modelClass = AdminListModel.class;

    // service
    // --------------------------------------------------------------------------------
    @Autowired
    AdminListService adminListService;

    // handler
    // --------------------------------------------------------------------------------
    @RequestMapping("editorlist")
    public View main(HttpServletRequest request) throws Exception
    {

        if (getCurrentAdmin(request) == null)
        {
            return getAdminLogoutView();
        }

        AdminListModel viewModel = getCurrentModel(request, modelClass);
        adminListService.initEditorListInModel(viewModel);
        setCurrentModel(request, viewModel);
        return getJspView(ADMIN_PRIZE_LIST);
    }

    @RequestMapping("changeeditorprivilege")
    public View changeEditorPrivilege(HttpServletRequest request, String privilegeData) throws Exception
    {

        if (getCurrentAdmin(request) == null)
        {
            return getAdminTimoutView();
        }

        AdminListModel viewModel = getCurrentModel(request, modelClass);
        adminListService.changeEditorPrivilege(viewModel, privilegeData);
        setCurrentModel(request, viewModel);

        return getJspView(ADMIN_PRIZE_LIST);
    }

}
