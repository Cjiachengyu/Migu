package cn.eclassmate.qzy.service;

import cn.eclassmate.qzy.domain.Editor;
import cn.eclassmate.qzy.viewmodel.AdminListModel;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminListService extends AbstractService
{
    private static final long serialVersionUID = -7416505185916253593L;

    public void initEditorListInModel(AdminListModel viewModel)
    {
        List<Editor> editorList = userMapper.getEditorList();

        viewModel.setEditorList(editorList);
    }


    public void changeEditorPrivilege(AdminListModel viewModel, String privilegeData)
    {
        String[] privilegeDataArr = privilegeData.substring(1, privilegeData.length() - 1).replaceAll("\"", "")
                .split(",");

        List<Editor> editorList = viewModel.getEditorList();

        for (int i = 0; i < editorList.size(); i++)
        {
            //判断权限是否改变
            if (editorList.get(i).getPrivilegeMask() != Integer.parseInt(privilegeDataArr[i], 2))
            {
                editorList.get(i).setPrivilegeMask(Integer.parseInt(privilegeDataArr[i], 2));
                userMapper.updateEditor(editorList.get(i));
            }

        }

    }
}
