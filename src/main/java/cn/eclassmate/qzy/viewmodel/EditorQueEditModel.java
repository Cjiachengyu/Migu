package cn.eclassmate.qzy.viewmodel;

import cn.eclassmate.qzy.domain.Que;
import cn.eclassmate.qzy.domain.QueZsd;
import cn.eclassmate.qzy.domain.Zsk;
import cn.eclassmate.qzy.viewmodel.subview.PageModule;

import java.util.List;

/**
 * Created by stao on 2015/5/8.
 */
public class EditorQueEditModel extends AbstractModel
{
    private static final long serialVersionUID = 4483750922490542228L;

    public static final int OPER_TYPE_CREATE = 1;
    public static final int OPER_TYPE_MODIFY = 2;
    public static final int OPER_TYPE_SAVE_AS = 3;

    private int operationType;
    private List<Zsk> zskList;
    private Que que;
    private List<QueZsd> queZsdList;
    private Que lastQue;    // 上一题的预览

    // getter, setter
    // --------------------------------------------------------------------------------
    public Que getQue()
    {
        return que;
    }

    public void setQue(Que que)
    {
        this.que = que;
    }

    public int getOperationType()
    {
        return operationType;
    }

    public void setOperationType(int operationType)
    {
        this.operationType = operationType;
    }

    public List<Zsk> getZskList()
    {
        return zskList;
    }

    public void setZskList(List<Zsk> zskList)
    {
        this.zskList = zskList;
    }

    public List<QueZsd> getQueZsdList()
    {
        return queZsdList;
    }

    public void setQueZsdList(List<QueZsd> queZsdList)
    {
        this.queZsdList = queZsdList;
    }

    public Que getLastQue()
    {
        return lastQue;
    }

    public void setLastQue(Que lastQue)
    {
        this.lastQue = lastQue;
    }

}
