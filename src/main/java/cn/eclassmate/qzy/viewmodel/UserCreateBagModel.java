package cn.eclassmate.qzy.viewmodel;

import cn.eclassmate.qzy.domain.Bag;
import cn.eclassmate.qzy.domain.Que;
import cn.eclassmate.qzy.domain.Zsl;

import java.util.List;

/**
 * Created by stao on 2015/5/8.
 */
public class UserCreateBagModel extends AbstractModel
{
    private static final long serialVersionUID = 4751700788744991426L;

    // fields
    // --------------------------------------------------------------------------------
    private List<Zsl> zslList;
    private Bag bag;
    private List<Que> queList;

    // getter, setter
    // --------------------------------------------------------------------------------
    public Bag getBag()
    {
        return bag;
    }

    public void setBag(Bag bag)
    {
        this.bag = bag;
    }

    public List<Que> getQueList()
    {
        return queList;
    }

    public void setQueList(List<Que> queList)
    {
        this.queList = queList;
    }

    public List<Zsl> getZslList()
    {
        return zslList;
    }

    public void setZslList(List<Zsl> zslList)
    {
        this.zslList = zslList;
    }

}
