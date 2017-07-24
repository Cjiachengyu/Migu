package cn.eclassmate.qzy.viewmodel;

import cn.eclassmate.qzy.domain.Bag;
import cn.eclassmate.qzy.domain.Que;
import cn.eclassmate.qzy.domain.User;
import cn.eclassmate.qzy.domain.UserBag;
import cn.eclassmate.qzy.viewmodel.subview.TitledQueChoiceStt;

import java.util.List;
import java.util.Map;

/**
 * 1. 发作业者可查看本份作业的统计：答题名单和答对题数
 * 2. 统计可包括成绩分布、每题正确率、答题时间分布以及每题每选项的统计
 * Created by stao on 2015/5/13.
 */
public class UserBagSttModel extends AbstractModel
{
    private static final long serialVersionUID = 1886085397027390750L;

    // fields
    // --------------------------------------------------------------------------------
    private Bag bag;
    private Map<Integer, Que> queNumQueMap;

    private List<UserBag> userBagList;  // 所抢过这个红包的用户(userBagStatus!=0)，注意UserBag里的rightAnswerCount
    private double allRightAndWinPercent;  // 抢红包成功率，精确到小数点后1位
    private Map<Integer, List<UserBag>> rightCountUserCountMap;  // <答对题数，哪些人>，共答对1题的人，共答对2题的人...
    private Map<Integer, Double> queNumRightPercentMap;  // <题号，正确率百分比>，第1题正确率，第2题正确率...(小数点1位)
    private Map<Integer, List<TitledQueChoiceStt>> queNumTqcsListMap; // 每题选项统计：每个选项有哪些人选了，正确率...

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

    public List<UserBag> getUserBagList()
    {
        return userBagList;
    }

    public void setUserBagList(List<UserBag> userBagList)
    {
        this.userBagList = userBagList;
    }

    public void setQueNumQueMap(Map<Integer, Que> queNumQueMap)
    {
        this.queNumQueMap = queNumQueMap;
    }

    public Map<Integer, List<TitledQueChoiceStt>> getQueNumTqcsListMap()
    {
        return queNumTqcsListMap;
    }

    public void setQueNumTqcsListMap(Map<Integer, List<TitledQueChoiceStt>> queNumTqcsListMap)
    {
        this.queNumTqcsListMap = queNumTqcsListMap;
    }

    public Map<Integer, Que> getQueNumQueMap()
    {
        return queNumQueMap;
    }

    public double getAllRightAndWinPercent()
    {
        return allRightAndWinPercent;
    }

    public void setAllRightAndWinPercent(double allRightAndWinPercent)
    {
        this.allRightAndWinPercent = allRightAndWinPercent;
    }

    public Map<Integer, Double> getQueNumRightPercentMap()
    {
        return queNumRightPercentMap;
    }

    public void setQueNumRightPercentMap(Map<Integer, Double> queNumRightPercentMap)
    {
        this.queNumRightPercentMap = queNumRightPercentMap;
    }

    public Map<Integer, List<UserBag>> getRightCountUserCountMap()
    {
        return rightCountUserCountMap;
    }

    public void setRightCountUserCountMap(Map<Integer, List<UserBag>> rightCountUserCountMap)
    {
        this.rightCountUserCountMap = rightCountUserCountMap;
    }

}
