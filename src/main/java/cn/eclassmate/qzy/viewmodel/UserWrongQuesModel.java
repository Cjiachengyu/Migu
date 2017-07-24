package cn.eclassmate.qzy.viewmodel;

import cn.eclassmate.qzy.domain.Que;
import cn.eclassmate.qzy.domain.UserAnswer;
import cn.eclassmate.qzy.viewmodel.subview.TitledQueChoice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserWrongQuesModel extends AbstractModel
{
    private static final long serialVersionUID = -4464423277174176553L;

    // fields
    // --------------------------------------------------------------------------------
    List<UserAnswer> userAnswerList;
    private Map<Integer, Que> queNumQueMap = new HashMap<Integer, Que>();   // 题号与题目的对应关系
    private Map<Integer, List<TitledQueChoice>> queNumTqcListMap = new HashMap<Integer, List<TitledQueChoice>>();   // 题号与选项列表的对应关系
    private Map<Integer, UserAnswer> queNumUserAnswerMap = new HashMap<Integer, UserAnswer>();   // 题号与用户作答的对应关系


    // getter, setter
    // --------------------------------------------------------------------------------
    public List<UserAnswer> getUserAnswerList()
    {
        return userAnswerList;
    }

    public void setUserAnswerList(List<UserAnswer> userAnswerList)
    {
        this.userAnswerList = userAnswerList;
    }

    public Map<Integer, Que> getQueNumQueMap()
    {
        return queNumQueMap;
    }

    public void setQueNumQueMap(Map<Integer, Que> queNumQueMap)
    {
        this.queNumQueMap = queNumQueMap;
    }

    public Map<Integer, List<TitledQueChoice>> getQueNumTqcListMap()
    {
        return queNumTqcListMap;
    }

    public void setQueNumTqcListMap(Map<Integer, List<TitledQueChoice>> queNumTqcListMap)
    {
        this.queNumTqcListMap = queNumTqcListMap;
    }

    public Map<Integer, UserAnswer> getQueNumUserAnswerMap()
    {
        return queNumUserAnswerMap;
    }

    public void setQueNumUserAnswerMap(Map<Integer, UserAnswer> queNumUserAnswerMap)
    {
        this.queNumUserAnswerMap = queNumUserAnswerMap;
    }

}
