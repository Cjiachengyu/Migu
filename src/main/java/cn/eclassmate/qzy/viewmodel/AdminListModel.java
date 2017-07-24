package cn.eclassmate.qzy.viewmodel;

import cn.eclassmate.qzy.domain.Editor;

import java.util.List;

public class AdminListModel extends AbstractModel
{

    private static final long serialVersionUID = 4483750922490542228L;

    List<Editor> editorList;

    public List<Editor> getEditorList()
    {
        return editorList;
    }

    public void setEditorList(List<Editor> editorList)
    {
        this.editorList = editorList;
    }


}
