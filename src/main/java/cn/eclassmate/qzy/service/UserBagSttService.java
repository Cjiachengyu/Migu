package cn.eclassmate.qzy.service;

import cn.eclassmate.qzy.domain.Bag;
import cn.eclassmate.qzy.domain.Que;
import cn.eclassmate.qzy.domain.QueChoice;
import cn.eclassmate.qzy.domain.UserAnswer;
import cn.eclassmate.qzy.domain.UserBag;
import cn.eclassmate.qzy.global.Cst;
import cn.eclassmate.qzy.global.Utl;
import cn.eclassmate.qzy.viewmodel.UserBagSttModel;
import cn.eclassmate.qzy.viewmodel.subview.TitledQueChoiceStt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserBagSttService extends AbstractService
{
    private static final long serialVersionUID = 2346178968606382116L;
    private static final Logger logger = LogManager.getLogger();

    public void initUbsModel(UserBagSttModel ubiModel, int bagId)
    {
        // 获取bag，包括关联属性
        Bag bag = bagMapper.getBagForUserGrabBag(bagId);
        ubiModel.setBag(bag);
        logger.debug("got bag and que list and que choice list");

        // 所抢过这个红包的用户(userBagStatus!=0)，注意UserBag里的rightAnswerCount
        List<UserBag> userBagList = bagMapper.getUserBagForUserBagStt(bag.getBagId());
        ubiModel.setUserBagList(userBagList);
        logger.debug("got user bag list");

        // 抢红包成功率，精确到小数点后1位
        ubiModel.setAllRightAndWinPercent(getAllRightAndWinPercent(userBagList));

        Map<Integer, Que> queNumQueMap = new HashMap<Integer, Que>();
        Map<Integer, List<UserBag>> rightCountUserCountMap = new HashMap<Integer, List<UserBag>>();
        Map<Integer, Double> queNumRightPercentMap = new HashMap<Integer, Double>();
        Map<Integer, List<TitledQueChoiceStt>> queNumTqcsListMap = new HashMap<Integer, List<TitledQueChoiceStt>>();

        initQueNumMap(ubiModel, queNumQueMap, rightCountUserCountMap, queNumRightPercentMap, queNumTqcsListMap);

        ubiModel.setQueNumQueMap(queNumQueMap);
        ubiModel.setRightCountUserCountMap(rightCountUserCountMap);
        ubiModel.setQueNumRightPercentMap(queNumRightPercentMap);
        ubiModel.setQueNumTqcsListMap(queNumTqcsListMap);
    }

    // private
    // --------------------------------------------------------------------------------
    private void initQueNumMap(UserBagSttModel ubiModel, Map<Integer, Que> queNumQueMap,
            Map<Integer, List<UserBag>> rightCountUserCountMap, Map<Integer, Double> queNumRightPercentMap,
            Map<Integer, List<TitledQueChoiceStt>> queNumTqcsListMap)
    {
        int bagId = ubiModel.getBag().getBagId();
        List<Que> queList = ubiModel.getBag().getQueList();

        for (int i = 0; i < queList.size(); i++)
        {
            Que que = queList.get(i);
            queNumQueMap.put(i + 1, que);

            // <题号，正确率百分比>，第1题正确率，第2题正确率...(小数点1位)
            queNumRightPercentMap.put(i + 1, getRightPercent(ubiModel, que));

            // 每题选项统计：每个选项有哪些人选了，正确率...
            queNumTqcsListMap.put(i + 1, getQueNumTqcsList(ubiModel, que));
        }

        for (int count = 0; count <= queList.size(); count++)
        {
            // <答对题数，哪些人>，共答对0题的人，共答对1题的人...
            rightCountUserCountMap.put(count, getRightCountUserCountList(ubiModel, count));
        }

    }

    // 某一红包答对0题、1题的人
    private List<UserBag> getRightCountUserCountList(UserBagSttModel ubiModel, int rightCount)
    {
        List<UserBag> list = new ArrayList<UserBag>();
        for (UserBag ub : ubiModel.getUserBagList())
        {
            if (ub.getRightAnswerCount() == rightCount)
            {
                list.add(ub);
            }
        }

        return list;
    }

    private List<TitledQueChoiceStt> getQueNumTqcsList(UserBagSttModel ubiModel, Que que)
    {
        int queId = que.getQueId();
        List<QueChoice> qcList = que.getQueChoiceList();

        List<TitledQueChoiceStt> tqcsList = new ArrayList<TitledQueChoiceStt>();
        for (int i = 0; i < qcList.size(); i++)
        {
            // 对每一个QueChoice，新建一个TitledQueChoiceStt，设置好对应的ABCD，存到tqcList中
            TitledQueChoiceStt tqcs = new TitledQueChoiceStt();

            tqcs.setTitle("" + (char) (65 + i));
            tqcs.setQueChoice(qcList.get(i));

            List<UserBag> checkedUsers = new ArrayList<UserBag>();
            for (UserBag ub : ubiModel.getUserBagList())
            {
                UserAnswer ua = findUserAnswer(ub, queId);
                if (ua != null && ua.getUserChoiceId() == qcList.get(i).getQueChoiceId())
                {
                    checkedUsers.add(ub);
                }
            }

            tqcs.setCheckedUsers(checkedUsers);
            tqcs.setRightPercent(Utl.getPercent(checkedUsers.size(), ubiModel.getUserBagList().size(), 1));

            tqcsList.add(tqcs);
        }

        return tqcsList;
    }

    private UserAnswer findUserAnswer(UserBag ub, int queId)
    {
        for (UserAnswer ua : ub.getUserAnswerList())
        {
            if (ua.getQueId() == queId)
            {
                return ua;
            }
        }

        return null;
    }

    private double getRightPercent(UserBagSttModel ubiModel, Que que)
    {
        int rightCount = 0;
        for (UserBag ub : ubiModel.getUserBagList())
        {
            UserAnswer uaa = findUserAnswer(ub, que.getQueId());
            if (uaa != null && uaa.getIsUserRight())
            {
                rightCount++;
            }
        }
        return Utl.getPercent(rightCount, ubiModel.getUserBagList().size(), 1);
    }

    private double getAllRightAndWinPercent(List<UserBag> userBagList)
    {
        int winCount = 0;
        for (UserBag ub : userBagList)
        {
            // 成功抢到红包的用户(userBagStatus=1)
            if (ub.getUserBagStatus() == Cst.UserBagStatus.FINISH_GRAB_RIGHT_WIN)
            {
                winCount++;
            }
        }

        return Utl.getPercent(winCount, userBagList.size(), 1);
    }

}
