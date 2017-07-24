package cn.eclassmate.qzy.viewmodel;

import cn.eclassmate.qzy.domain.Editor;
import cn.eclassmate.qzy.domain.Que;
import cn.eclassmate.qzy.domain.Zsk;
import cn.eclassmate.qzy.viewmodel.subview.PageModule;

import java.util.List;

/**
 * Created by stao on 2015/5/8.
 */
public class EditorQueListModel extends AbstractModel
{
    private static final long serialVersionUID = -5499764300022065386L;

    public static final int ITEMS_ONE_PAGE = 12;
    public static final int FROM_TYPE_MY = 1;
    public static final int FROM_TYPE_SYSTEM = 2;

    // fields
    // --------------------------------------------------------------------------------
    List<Que> queList;
    // 查询条件
    int creatorId = 0;
    int zskId = 0;
    int zsd1Id = 0;
    int zsd2Id = 0;
    PageModule pageModule = new PageModule(ITEMS_ONE_PAGE);

    List<Zsk> zskList;

    int queStatus = -1;
    List<Editor> editorList;

    // getter, setter
    // --------------------------------------------------------------------------------
    public List<Que> getQueList()
    {
        return queList;
    }

    public void setQueList(List<Que> queList)
    {
        this.queList = queList;
    }

    public PageModule getPageModule()
    {
        return pageModule;
    }

    public void setPageModule(PageModule pageModule)
    {
        this.pageModule = pageModule;
    }

    public int getCreatorId()
    {
        return creatorId;
    }

    public void setCreatorId(int creatorId)
    {
        this.creatorId = creatorId;
    }

    public int getZsd2Id()
    {
        return zsd2Id;
    }

    public void setZsd2Id(int zsd2Id)
    {
        this.zsd2Id = zsd2Id;
    }

    public int getZsd1Id()
    {
        return zsd1Id;
    }

    public void setZsd1Id(int zsd1Id)
    {
        this.zsd1Id = zsd1Id;
    }

    public int getZskId()
    {
        return zskId;
    }

    public void setZskId(int zskId)
    {
        this.zskId = zskId;
    }

    public int getQueStatus()
    {
        return queStatus;
    }

    public void setQueStatus(int queStatus)
    {
        this.queStatus = queStatus;
    }

    public List<Editor> getEditorList()
    {
        return editorList;
    }

    public void setEditorList(List<Editor> editorList)
    {
        this.editorList = editorList;
    }

    public List<Zsk> getZskList()
    {
        return zskList;
    }

    public void setZskList(List<Zsk> zskList)
    {
        this.zskList = zskList;
    }


}
