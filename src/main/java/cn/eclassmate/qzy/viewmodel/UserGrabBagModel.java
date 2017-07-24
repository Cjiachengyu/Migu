package cn.eclassmate.qzy.viewmodel;

import cn.eclassmate.qzy.domain.Bag;
import cn.eclassmate.qzy.domain.Que;
import cn.eclassmate.qzy.domain.ShowGrabBag;
import cn.eclassmate.qzy.domain.UserAnswer;
import cn.eclassmate.qzy.domain.UserBag;
import cn.eclassmate.qzy.global.Utl;
import cn.eclassmate.qzy.viewmodel.subview.ShareInfo;
import cn.eclassmate.qzy.viewmodel.subview.TitledQueChoice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by stao on 2015/5/8.
 */
public class UserGrabBagModel extends AbstractModel
{
    private static final long serialVersionUID = -7819385797699690129L;

    // fields
    // --------------------------------------------------------------------------------
    // 红包信息
    private Bag bag;
    private UserBag userBag;
    private int chanceType;     // 1 -> 有机会； 2 -> 没机会； 3 -> 已经抢过了
    private Map<Integer, Que> queNumQueMap = new HashMap<Integer, Que>();   // 题号与题目的对应关系
    private Map<Integer, List<TitledQueChoice>> queNumTqcListMap = new HashMap<Integer, List<TitledQueChoice>>();   // 题号与选项列表的对应关系
    private Map<Integer, UserAnswer> queNumUserAnswerMap = new HashMap<Integer, UserAnswer>();   // 题号与用户作答的对应关系

    private List<UserBag> alreadyGrabbedBagList;
    private int bagBlankTime;

    private ShareInfo shareInfo;

    // 显摆
    private ShowGrabBag showGrabBag;

    // additional getter, setter
    // --------------------------------------------------------------------------------
    // 获取做错的或没做的题
    public int getWrongOrBlankCount()
    {
        int rightCount = 0;
        for (UserAnswer ua : queNumUserAnswerMap.values())
        {
            if (ua.getIsUserRight())
            {
                rightCount++;
            }
        }

        return bag.getQueList().size() - rightCount;
    }

    public int getUnfinishedCount()
    {
        int finishedCount = 0;
        for (UserAnswer ua : queNumUserAnswerMap.values())
        {
            if (ua.getUserChoiceId() != 0)
            {
                finishedCount++;
            }
        }

        return bag.getQueList().size() - finishedCount;
    }

    public String getBagBlankTimeString()
    {
        if (bagBlankTime < 60)
        {
            return bagBlankTime + "秒";
        }
        else if (bagBlankTime < 3600)
        {
            return Utl.roundDouble(bagBlankTime / 60.0, 1) + "分钟";
        }
        else if (bagBlankTime < 3600 * 24)
        {
            return Utl.roundDouble(bagBlankTime / 3600.0, 1) + "小时";
        }
        else
        {
            return Utl.roundDouble(bagBlankTime / 3600.0 * 24.0, 1) + "天";
        }
    }

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

    public Map<Integer, Que> getQueNumQueMap()
    {
        return queNumQueMap;
    }

    public void setQueNumQueMap(Map<Integer, Que> queNumQueMap)
    {
        this.queNumQueMap = queNumQueMap;
    }

    public Map<Integer, UserAnswer> getQueNumUserAnswerMap()
    {
        return queNumUserAnswerMap;
    }

    public void setQueNumUserAnswerMap(Map<Integer, UserAnswer> queNumUserAnswerMap)
    {
        this.queNumUserAnswerMap = queNumUserAnswerMap;
    }

    public UserBag getUserBag()
    {
        return userBag;
    }

    public void setUserBag(UserBag userBag)
    {
        this.userBag = userBag;
    }

    public Map<Integer, List<TitledQueChoice>> getQueNumTqcListMap()
    {
        return queNumTqcListMap;
    }

    public void setQueNumTqcListMap(Map<Integer, List<TitledQueChoice>> queNumTqcListMap)
    {
        this.queNumTqcListMap = queNumTqcListMap;
    }

    public int getChanceType()
    {
        return chanceType;
    }

    public void setChanceType(int chanceType)
    {
        this.chanceType = chanceType;
    }

    public List<UserBag> getAlreadyGrabbedBagList()
    {
        return alreadyGrabbedBagList;
    }

    public void setAlreadyGrabbedBagList(List<UserBag> alreadyGrabbedBagList)
    {
        this.alreadyGrabbedBagList = alreadyGrabbedBagList;
    }

    public int getBagBlankTime()
    {
        return bagBlankTime;
    }

    public void setBagBlankTime(int bagBlankTime)
    {
        this.bagBlankTime = bagBlankTime;
    }

    public ShowGrabBag getShowGrabBag()
    {
        return showGrabBag;
    }

    public void setShowGrabBag(ShowGrabBag showGrabBag)
    {
        this.showGrabBag = showGrabBag;
    }

    public ShareInfo getShareInfo()
    {
        return shareInfo;
    }

    public void setShareInfo(ShareInfo shareInfo)
    {
        this.shareInfo = shareInfo;
    }


}
