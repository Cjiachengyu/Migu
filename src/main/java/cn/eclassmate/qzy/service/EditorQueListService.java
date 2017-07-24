package cn.eclassmate.qzy.service;

import cn.eclassmate.qzy.domain.Editor;
import cn.eclassmate.qzy.domain.Que;
import cn.eclassmate.qzy.domain.Zsk;
import cn.eclassmate.qzy.viewmodel.EditorQueListModel;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditorQueListService extends AbstractService
{
    private static final long serialVersionUID = -6364070193718803516L;

    public void initQueList(EditorQueListModel eqlModel, boolean refreshSelection)
    {
        if (eqlModel.getEditor().getIsChecker())
        {
            List<Editor> editorList = userMapper.getEditorList();
            for (int i = 0; i < editorList.size(); i++)
            {
                if (editorList.get(i).getEditorId() == eqlModel.getEditor().getEditorId() && i != 0)
                {
                    Editor tempEditor = editorList.get(i);
                    editorList.remove(i);
                    editorList.add(0, tempEditor);
                }
            }

            eqlModel.setEditorList(editorList);
        }

        //初始化知识库集合
        List<Zsk> zskList = queMapper.getAllZskWithZsd();
        eqlModel.setZskList(zskList);

        if (refreshSelection)
        {
            eqlModel.getPageModule().gotoPage(1);
            if (eqlModel.getEditor().getIsChecker())
            {
                eqlModel.setCreatorId(0);
            }
            else
            {
                eqlModel.setCreatorId(eqlModel.getEditor().getEditorId());
            }
            eqlModel.setQueStatus(-1);

            eqlModel.setZskId(0);
            eqlModel.setZsd1Id(0);
            eqlModel.setZsd2Id(0);
        }
        else
        {
            eqlModel.setZskId(eqlModel.getZskId());
            eqlModel.setZsd1Id(eqlModel.getZsd1Id());
            eqlModel.setZsd2Id(eqlModel.getZsd2Id());
        }


        refreshQueList(eqlModel);
    }

    public void changeFromType(EditorQueListModel eqlModel, int fromType)
    {
        eqlModel.setQueStatus(-1);
        if (fromType == EditorQueListModel.FROM_TYPE_SYSTEM)
        {
            eqlModel.setCreatorId(0);
        }
        else if (fromType == EditorQueListModel.FROM_TYPE_MY)
        {
            eqlModel.setCreatorId(eqlModel.getEditor().getEditorId());
        }

        eqlModel.getPageModule().gotoPage(1);
        refreshQueList(eqlModel);
    }

    public void changeFromEditor(EditorQueListModel eqlModel, int editorId)
    {
        eqlModel.setCreatorId(editorId);

        eqlModel.getPageModule().gotoPage(1);
        refreshQueList(eqlModel);
    }

    public void changeFromStatus(EditorQueListModel eqlModel, int queStatus)
    {
        eqlModel.setQueStatus(queStatus);

        eqlModel.getPageModule().gotoPage(1);
        refreshQueList(eqlModel);
    }

    public void changeFromZsk(EditorQueListModel eqlModel, int zskId)
    {
        eqlModel.setZskId(zskId);
        eqlModel.setZsd1Id(0);
        eqlModel.setZsd2Id(0);

        eqlModel.getPageModule().gotoPage(1);
        refreshQueList(eqlModel);
    }

    public void changeFromZsd1(EditorQueListModel eqlModel, int zsd1Id)
    {
        eqlModel.setZsd1Id(zsd1Id);
        eqlModel.setZsd2Id(0);

        eqlModel.getPageModule().gotoPage(1);
        refreshQueList(eqlModel);
    }

    public void changeFromZsd2(EditorQueListModel eqlModel, int zsd2Id)
    {
        eqlModel.setZsd2Id(zsd2Id);

        eqlModel.getPageModule().gotoPage(1);
        refreshQueList(eqlModel);
    }

    public void gotoPage(EditorQueListModel eqlModel, int pageNum)
    {
        eqlModel.getPageModule().gotoPage(pageNum);
        refreshQueList(eqlModel);
    }

    public void deleteQue(EditorQueListModel eqlModel, int queId)
    {
        queMapper.deleteQue(queId);

        eqlModel.getPageModule().gotoPage(eqlModel.getPageModule().getCurrentPage());
        refreshQueList(eqlModel);
    }

    public void checkQue(EditorQueListModel eqlModel, int queId)
    {
        for (Que que : eqlModel.getQueList())
        {
            if (que.getQueId() == queId)
            {
                if (que.getQueStatus() == 0)
                {
                    que.setQueStatus(2);
                }
                else if (que.getQueStatus() == 2)
                {
                    que.setQueStatus(0);
                }

                queMapper.updateQue(que);
            }
        }

        eqlModel.getPageModule().gotoPage(eqlModel.getPageModule().getCurrentPage());
        refreshQueList(eqlModel);
    }

    public Editor getEditor(int editorId)
    {
        Editor editor = userMapper.getEditorByEditorId(editorId);

        return editor;

    }

    // private
    // --------------------------------------------------------------------------------
    private void refreshQueList(EditorQueListModel eqlModel)
    {
        List<Que> queList = queMapper.getQueListForEditor(
                eqlModel.getCreatorId(),
                eqlModel.getQueStatus(),
                eqlModel.getZskId(),
                eqlModel.getZsd1Id(),
                eqlModel.getZsd2Id(),
                eqlModel.getPageModule().getLimitBegin(),
                eqlModel.getPageModule().getPageSize());

        int queListItemsCount = queMapper.getQueListForEditorCount(
                eqlModel.getCreatorId(),
                eqlModel.getQueStatus(),
                eqlModel.getZskId(),
                eqlModel.getZsd1Id(),
                eqlModel.getZsd2Id());

        eqlModel.getPageModule().changeItemsCount(queListItemsCount);

        eqlModel.setQueList(queList);
    }

}
