package cn.eclassmate.qzy.service;

import cn.eclassmate.qzy.domain.Bag;
import cn.eclassmate.qzy.domain.Que;
import cn.eclassmate.qzy.domain.QueChoice;
import cn.eclassmate.qzy.domain.QueFeedBack;
import cn.eclassmate.qzy.domain.ShowGrabBag;
import cn.eclassmate.qzy.domain.User;
import cn.eclassmate.qzy.domain.UserAnswer;
import cn.eclassmate.qzy.domain.UserBag;
import cn.eclassmate.qzy.global.Cst;
import cn.eclassmate.qzy.global.Utl;
import cn.eclassmate.qzy.viewmodel.UserGrabBagModel;
import cn.eclassmate.qzy.viewmodel.subview.ShareInfo;
import cn.eclassmate.qzy.viewmodel.subview.TitledQueChoice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class UserGrabBagService extends AbstractService
{
    private static final long serialVersionUID = -1882603606653618110L;
    private static final Logger logger = LogManager.getLogger();

    public void initUserGrabBagModel(UserGrabBagModel ugbModel, int bagId)
    {
        refreshUserMoney(ugbModel);

        // 获取bag
        Bag bag = bagMapper.getBagForUserGrabBag(bagId);
        ugbModel.setBag(bag);

        // 获取userBag
        UserBag userBag = initUserBag(bagId, ugbModel.getUser().getUserId());
        ugbModel.setUserBag(userBag);

        // 获取queList
        List<Que> queList = bag.getQueList();

        // 设置题号相关的Map：queNumTqcListMap，queNumQueIdMap，queNumUserAnswerMap
        Map<Integer, Que> queNumQueMap = new HashMap<Integer, Que>();
        Map<Integer, List<TitledQueChoice>> queNumTqcListMap = new HashMap<Integer, List<TitledQueChoice>>();
        Map<Integer, UserAnswer> queNumUserAnswerMap = new HashMap<Integer, UserAnswer>();
        initQueNumMap(ugbModel, queNumQueMap, queNumTqcListMap, queNumUserAnswerMap);
        ugbModel.setQueNumQueMap(queNumQueMap);
        ugbModel.setQueNumTqcListMap(queNumTqcListMap);
        ugbModel.setQueNumUserAnswerMap(queNumUserAnswerMap);

        // 设置chanceType
        int chanceType = initChanceType(bag, userBag);
        ugbModel.setChanceType(chanceType);

        // 已经抢到红包的红包信息列表
        refreshAlreadyGrabbedBagList(ugbModel);

        // 第一次抢该红包时，生成好友关系或增加好友亲密度
        if (userBag.getUserBagStatus() == Cst.UserBagStatus.BEGIN_GRAB)
        {
            updateFriendship(bag.getCreatorId(), ugbModel.getUser().getUserId());
        }
    }

    @Transactional
    public void submitBag(UserGrabBagModel ugbModel, String answerDataList)
    {
        // 如果还没有提交才更新数据
        if (ugbModel.getUserBag().getUserBagStatus() == Cst.UserBagStatus.BEGIN_GRAB)
        {
            // 保存题目数据
            String[] answerDataArr = answerDataList.split("\\|");
            for (String answerData : answerDataArr)
            {
                saveUserAnswer(ugbModel, answerData);
            }

            // 更新userbag及相关信息
            if (ugbModel.getBag().getBagCategory() == Cst.BagCategory.HONGBAO)
            {
                doUserGrabBag(ugbModel);
            }
            else if (ugbModel.getBag().getBagCategory() == Cst.BagCategory.SHUATI)
            {
                doUserShuati(ugbModel);
            }
        }

        // 已经抢到红包的红包信息列表
        refreshAlreadyGrabbedBagList(ugbModel);

        //更新answerCount和rightAnswerCount
        bagMapper.updateGrabUserAnswerCount(ugbModel.getUser().getUserId());
        bagMapper.updateWeekGrabUserAnswerCount(ugbModel.getUser().getUserId(), Utl.getBeginTimeOfThisWeek());
    }

    @Transactional
    public void cheatGrab(UserGrabBagModel ugbModel)
    {
        if (ugbModel.getBag().getBagCategory() == Cst.BagCategory.HONGBAO)
        {
            int originWrongCount = ugbModel.getWrongOrBlankCount();

            // 把错的选项改成正确的
            for (int queNum = 1; queNum <= ugbModel.getQueNumQueMap().size(); queNum++)
            {
                UserAnswer ua = ugbModel.getQueNumUserAnswerMap().get(queNum);
                Que que = ugbModel.getQueNumQueMap().get(queNum);
                List<TitledQueChoice> tqcList = ugbModel.getQueNumTqcListMap().get(queNum);
                if (!ua.getIsUserRight())
                {
                    for (QueChoice queChoice : que.getQueChoiceList())
                    {
                        if (queChoice.getIsRightAnswer())
                        {
                            int rightQueChoiceId = queChoice.getQueChoiceId();

                            ua.setUserChoiceId(rightQueChoiceId);
                            ua.setIsUserRight(true);
                            bagMapper.updateUserAnswer(ua);

                            updateTitledQueChoiceList(tqcList, rightQueChoiceId);

                            break;
                        }
                    }
                }
            }

            doUserCheatGrabBag(ugbModel);

            refreshAlreadyGrabbedBagList(ugbModel);

            //更新answerCount和rightAnswerCount
            bagMapper.updateGrabUserAnswerCount(ugbModel.getUser().getUserId());
            bagMapper.updateWeekGrabUserAnswerCount(ugbModel.getUser().getUserId(), Utl.getBeginTimeOfThisWeek());

            // +1是为了让bill有序地插入
            changeUserMoney(ugbModel, ugbModel.getUser().getUserId(), Cst.BillType.SPEND,
                    Cst.Money.CHEAT_BAG_PER_QUE * originWrongCount, "抢红包作弊了一次，花去", Utl.currentSeconds() + 1);
        }
    }

    @Transactional
    public void showGrabBag(UserGrabBagModel ugbModel, int userId, int showGrabBagType)
    {
        ShowGrabBag showGrabBag = new ShowGrabBag();
        showGrabBag.setShowType(Cst.ShowType.SHOW_GRAB_BAG);
        showGrabBag.setShowTime(Utl.currentSeconds());
        showGrabBag.setShowerId(ugbModel.getUser().getUserId());    // 发起show的人
        showMapper.insertShow(showGrabBag);

        showGrabBag.setShowGrabBagType(showGrabBagType);
        showGrabBag.setBagId(ugbModel.getUserBag().getBagId());
        showGrabBag.setUserId(userId);                              // 被show的人
        showMapper.insertShowGrabBag(showGrabBag);

        User shower = ugbModel.getUser();
        showGrabBag.setShower(shower);

        User showedUser = userMapper.getUser(userId);           // 被show的人
        UserBag userBag = new UserBag();
        userBag.setUserId(userId);
        userBag.setUser(showedUser);
        showGrabBag.setUserBag(userBag);

        ugbModel.setShowGrabBag(showGrabBag);

        if (showGrabBagType == Cst.ShowGrabBagType.SHOW_GRAB_BAG_MY)
        {
            changeUserMoney(ugbModel, shower.getUserId(), Cst.BillType.SPEND, Cst.Money.SHOW_GRAB_BAG, "抢到了红包，显摆一下，花去");
        }
        else if (showGrabBagType == Cst.ShowGrabBagType.SHOW_GRAB_BAG_LAUGH)
        {
            changeUserMoney(ugbModel, shower.getUserId(), Cst.BillType.SPEND,
                    10, "嘲笑了" + showedUser.getUserName() + "一下，花去");

        }
        else if (showGrabBagType == Cst.ShowGrabBagType.SHOW_GRAB_BAG_PRAISE)
        {
            changeUserMoney(ugbModel, shower.getUserId(), Cst.BillType.SPEND,
                    10, "赞美了" + showedUser.getUserName() + "一下，花去");
        }
    }

    @Transactional
    public void queFeedBack(UserGrabBagModel ugbModel, int queId, String feedBackMsg)
    {
        Que que = queMapper.getQue(queId);
        if (que.getQueStatus() != 3)
        {
            que.setQueStatus(3);
            queMapper.updateQue(que);
        }

        QueFeedBack queFeedBack = bagMapper.getQueFeedBack(ugbModel.getUser().getUserId(), queId);
        if (queFeedBack != null && feedBackMsg.equals(queFeedBack.getFeedbackMsg()))
        {
            return;
        }

        queFeedBack = new QueFeedBack();
        queFeedBack.setUserId(ugbModel.getUser().getUserId());
        queFeedBack.setQueId(queId);
        queFeedBack.setFeedbackMsg(feedBackMsg);
        bagMapper.insertQueFeedBack(queFeedBack);
    }

    @Transactional
    public void viewAnalysis(UserGrabBagModel ugbModel)
    {
        UserBag userBag = ugbModel.getUserBag();
        if (userBag.getHasPaidAnalysis() == false)
        {
            userBag.setHasPaidAnalysis(true);
            bagMapper.updateUserBag(userBag);

            changeUserMoney(ugbModel, ugbModel.getUser().getUserId(), Cst.BillType.SPEND,
                    Cst.Money.PAY_ANALYSIS_PER_QUE * ugbModel.getWrongOrBlankCount(), "查看解析，花费");
        }
    }

    // private
    // --------------------------------------------------------------------------------
    private UserBag initUserBag(int bagId, int userId)
    {
        UserBag userBag = bagMapper.getUserBag(userId, bagId);
        if (userBag == null)
        {
            // 用户是第一次来抢
            userBag = new UserBag();
            userBag.setUserId(userId);
            userBag.setBagId(bagId);
            userBag.setBeginBagTime(Utl.currentSeconds());
            userBag.setUserBagStatus(Cst.UserBagStatus.BEGIN_GRAB);
            bagMapper.insertUserBag(userBag);
        }
        return userBag;
    }

    private void initQueNumMap(UserGrabBagModel ugbModel, Map<Integer, Que> queNumQueMap,
            Map<Integer, List<TitledQueChoice>> queNumTqcListMap, Map<Integer, UserAnswer> queNumUserAnswerMap)
    {
        int userId = ugbModel.getUser().getUserId();
        int bagId = ugbModel.getBag().getBagId();
        List<Que> queList = ugbModel.getBag().getQueList();
        for (int i = 0; i < queList.size(); i++)
        {
            // 查找并打乱选项
            Random rand = new Random(ugbModel.getUserBag().getBeginBagTime());
            Collections.shuffle(queList.get(i).getQueChoiceList(), rand);

            // 设置题号和题目的对应
            queNumQueMap.put(i + 1, queList.get(i));

            // 设置题号与选项列表的对应关系
            List<QueChoice> qcList = queList.get(i).getQueChoiceList();
            List<TitledQueChoice> tqcList = new ArrayList<TitledQueChoice>();
            for (int j = 0; j < qcList.size(); j++)
            {
                // 对每一个QueChoice，新建一个TitledQueChoice，设置好对应的ABCD，存到tqcList中
                TitledQueChoice tqc = new TitledQueChoice();
                tqc.setQueChoice(qcList.get(j));
                tqc.setTitle("" + (char) (65 + j));
                tqcList.add(tqc);
            }
            queNumTqcListMap.put(i + 1, tqcList);

            // 设置题号与用户作答的对应关系，更新tqcList
            UserAnswer userAnswer = bagMapper.getUserAnswer(userId, bagId, queList.get(i).getQueId());
            if (userAnswer != null)
            {
                queNumUserAnswerMap.put(i + 1, userAnswer);

                updateTitledQueChoiceList(tqcList, userAnswer.getUserChoiceId());
            }
        }
    }

    private int initChanceType(Bag bag, UserBag userBag)
    {
        int chanceType;

        if (userBag.getUserBagStatus() != Cst.UserBagStatus.BEGIN_GRAB)
        {
            // 上次已经答完了
            chanceType = Cst.UserBagChanceType.SUBMITTED;
        }
        else if (bag.getBagStatus() == Cst.BagStatus.TIMEOUT)
        {
            // 红包过期
            chanceType = Cst.UserBagChanceType.TIMEOUT;
        }
        else if (bag.getBagCount() == bag.getSentCount())
        {
            // 红包被抢完了
            chanceType = Cst.UserBagChanceType.TOO_LATE;
        }
        else
        {
            // 第一次来抢
            chanceType = Cst.UserBagChanceType.HAS_CHANCE;
        }

        return chanceType;
    }

    private void saveUserAnswer(UserGrabBagModel ugbModel, String answerData)
    {
        if (Utl.stringEmpty(answerData))
        {
            return;
        }

        // answerData: queNum-userChoice-begAnswerTime-endAnswerTime-consumeTime
        String[] answerDataArr = answerData.split("\\-");
        int queNum = Integer.valueOf(answerDataArr[0]);
        int userChoice = Integer.valueOf(answerDataArr[1]);
        int begAnswerTime = Integer.valueOf(answerDataArr[2]);
        int endAnswerTime = Integer.valueOf(answerDataArr[3]);
        int consumeTime = Integer.valueOf(answerDataArr[4]);

        // 保存上一题的用户数据
        savePrevUserAnswer(ugbModel, queNum, userChoice, begAnswerTime, endAnswerTime, consumeTime);
    }

    private void savePrevUserAnswer(UserGrabBagModel ugbModel, int queNum, int userChoice, int begAnswerTime,
            int endAnswerTime, int consumeTime)
    {
        // 从queNumUserAnswerMap中找到对应的UserAnswer
        boolean needInsert = false;
        UserAnswer userAnswer = ugbModel.getQueNumUserAnswerMap().get(queNum);
        if (userAnswer == null)
        {
            // 获取UserAnswer(先从数据库获取，如果没有则插入)，并set到queNumUserAnswerMap中
            int userId = ugbModel.getUser().getUserId();
            int bagId = ugbModel.getBag().getBagId();
            int queId = ugbModel.getQueNumQueMap().get(queNum).getQueId();

            // 奇怪的问题。按道理说insert是一定会成功的，但服务器报过这样的错
            userAnswer = bagMapper.getUserAnswer(userId, bagId, queId);
            if (userAnswer == null)
            {
                userAnswer = new UserAnswer();
                userAnswer.setUserId(userId);
                userAnswer.setBagId(bagId);
                userAnswer.setQueId(queId);
                needInsert = true;
            }
        }

        // 设置currentUserAnswer的结束时间，用户选项等，更新数据库
        userAnswer.setUserChoiceId(userChoice);
        userAnswer.setBeginAnswerTime(begAnswerTime);
        userAnswer.setEndAnswerTime(endAnswerTime);
        userAnswer.setConsumeTime(consumeTime);

        // 找到上一题的选项列表，准备更新用户选项
        List<TitledQueChoice> tqcList = ugbModel.getQueNumTqcListMap().get(queNum);
        userAnswer.setIsUserRight(false);
        for (TitledQueChoice tqc : tqcList)
        {
            if (tqc.getQueChoice().getQueChoiceId() == userChoice)
            {
                if (tqc.getQueChoice().getIsRightAnswer())
                {
                    userAnswer.setIsUserRight(true);
                }
            }
        }

        if (needInsert)
        {
            bagMapper.insertUserAnswer(userAnswer);
        }
        else
        {
            bagMapper.updateUserAnswer(userAnswer);
        }
        ugbModel.getQueNumUserAnswerMap().put(queNum, userAnswer);

        // 重新设置用户选项
        updateTitledQueChoiceList(tqcList, userChoice);
    }

    private void updateTitledQueChoiceList(List<TitledQueChoice> tqcList, int userChoice)
    {
        // 先清空选项
        for (TitledQueChoice tqc : tqcList)
        {
            tqc.setIsUserSelected(false);
            tqc.setIsUserRight(false);
        }

        // 再对每个选项设置：isUserSelected和isUserRight
        for (TitledQueChoice tqc : tqcList)
        {
            if (tqc.getQueChoice().getQueChoiceId() == userChoice)
            {
                tqc.setIsUserSelected(true);
                if (tqc.getQueChoice().getIsRightAnswer())
                {
                    tqc.setIsUserRight(true);
                }
            }
        }
    }

    private int computGotMoney(Bag bag)
    {
        int leftMoney = bag.getBagMoney() - bag.getSentMoney();
        int leftCount = bag.getBagCount() - bag.getSentCount();
        if (leftCount == 1 || leftMoney <= 1)
        {
            // 只剩1份了或者已经没钱分了，全部给完
            return leftMoney;
        }
        else if (1 <= leftMoney && leftMoney <= leftCount)
        {
            // 剩下的钱最多只够一人分一个了
            return 1;
        }
        else
        {
            if (bag.getBagType() == Cst.BagType.PINSHOUQI)
            {
                // 拼手气红包
                return computeRandomGotMoney(leftMoney, leftCount);
            }
            else
            {
                // 其它类型的红包
                return 0;
            }
        }
    }

    private int computeRandomGotMoney(int leftMoney, int leftCount)
    {
        // 在一条长度为1的线段上随机切n-1刀，把这条线段分成n份
        Random rand = new Random();
        List<Double> doubleList = new ArrayList<Double>();
        for (int i = 0; i < leftCount - 1; i++)
        {
            doubleList.add(rand.nextDouble());
        }

        // 找出第一段的长度
        double min = 1;
        for (double d : doubleList)
        {
            if (d < min)
            {
                min = d;
            }
        }

        // 计算出得多少钱
        int toReturn = (int) (leftMoney * min);

        // 至少得一个币
        if (toReturn <= 1)
        {
            toReturn = 1;
        }

        // 至少给剩下的人每人留一个币
        if (toReturn >= leftMoney - (leftCount - 1))
        {
            toReturn = leftMoney - (leftCount - 1);
        }

        return toReturn;
    }

    private void refreshAlreadyGrabbedBagList(UserGrabBagModel ugbModel)
    {
        if (ugbModel.getBag().getBagCategory() == Cst.BagCategory.HONGBAO)
        {
            List<UserBag> alreadyGrabbedBagList = bagMapper.getAlreadyGrabbedUserBagList(ugbModel.getBag().getBagId());
            ugbModel.setAlreadyGrabbedBagList(alreadyGrabbedBagList);

            if (ugbModel.getBag().getSentCount() == ugbModel.getBag().getBagCount())
            {
                for (int i = alreadyGrabbedBagList.size() - 1; i >= 0; i--)
                {
                    UserBag ub = alreadyGrabbedBagList.get(i);
                    if (ub.getUserBagStatus() == Cst.UserBagStatus.FINISH_GRAB_RIGHT_WIN)
                    {
                        int bagBlankTime = ub.getEndBagTime() - ugbModel.getBag().getCreateTime();
                        ugbModel.setBagBlankTime(bagBlankTime);
                        break;
                    }
                }
            }
        }
    }

    private int roundDouble(double value)
    {
        return (int) Math.round(value);
    }

    private int computeMinGrabCount(int bagCount)
    {
        if (bagCount < 3)
        {
            return 0;
        }
        else
        {
            return bagCount;
        }
    }

    private int computeCanGetMoney(int bagCount, int bagMoney)
    {
        if (bagCount < 3)
        {
            return 0;
        }
        else
        {
            int tempData = bagMoney / bagCount;
            if (tempData > 0 && tempData <= 40)
            {
                return roundDouble(1.5 * bagMoney);
            }
            else if (tempData > 40 && tempData <= 60)
            {
                return (int) (roundDouble(((tempData - 40) / 1.25 + 40) / tempData) * 40 * bagCount * 0.5 + bagMoney);
            }
            else if (tempData > 60 && tempData <= 80)
            {
                return (int) (roundDouble(((tempData - 60) / 2 + 56) / tempData) * 40 * bagCount * 0.5 + bagMoney);
            }
            else if (tempData > 80 && tempData <= 100)
            {
                return (int) (roundDouble(((tempData - 80) / 5 + 66) / tempData) * 40 * bagCount * 0.5 + bagMoney);
            }
            else if (tempData > 100 && tempData <= 120)
            {
                return (int) (roundDouble(((tempData - 100) / 10 + 70) / tempData) * 40 * bagCount * 0.5 + bagMoney);
            }
            else if (tempData > 120 && tempData <= 140)
            {
                return bagMoney;
            }
            else if (tempData > 140 && tempData <= 160)
            {
                return bagMoney - roundDouble(((tempData - 140) / 5) / (220 - tempData)) * 20 * bagCount;
            }
            else if (tempData > 160 && tempData <= 180)
            {
                return bagMoney - roundDouble(((tempData - 160) / 2 + 4) / (220 - tempData)) * 20 * bagCount;
            }
            else if (tempData > 180 && tempData <= 200)
            {
                return bagMoney - roundDouble(((tempData - 180) / 1.25 + 14) / (220 - tempData)) * 20 * bagCount;
            }
            else
            {
                return 170 * bagCount;
            }
        }
    }

    private void submitBagSystemReturnMoney(UserGrabBagModel ugbModel)
    {
        Bag bag = ugbModel.getBag(); // 已经是最新
        int minGrabCount = computeMinGrabCount(bag.getBagCount());
        logger.debug("minGrabCount: " + minGrabCount + ", grabbedCount: " + bag.getGrabbedCount());

        if (minGrabCount > 0 && bag.getGrabbedCount() >= minGrabCount && bag.getReturnedMoney() == 0)
        {
            int canGetMoney = roundDouble(computeCanGetMoney(bag.getBagCount(), bag.getBagMoney()));

            // 更新红包
            bag.setReturnedMoney(canGetMoney);

            // 更创建者的money
            changeUserMoney(ugbModel, bag.getCreatorId(), Cst.BillType.BAG_RETURN,
                    canGetMoney, "有" + minGrabCount + "个童鞋抢了你的作业，系统返还");

            logger.info("user: " + bag.getCreatorId() + ", money returned: " + canGetMoney);
        }
    }

    private void submitBagHasChance(UserGrabBagModel ugbModel, UserBag userBag)
    {
        Bag bag = ugbModel.getBag();

        // 检查有没有全部答对
        if (ugbModel.getWrongOrBlankCount() > 0) // 没有全部答对
        {
            userBag.setGotMoney(0);
            userBag.setUserBagStatus(Cst.UserBagStatus.FINISH_GRAB_WRONG);
        }
        else
        // 全部答对
        {
            if (bag.getBagCount() > bag.getSentCount()) // 全部答对，并且红包没抢完，调用computGotMoney()计算能得多少钱
            {
                int gotMoney = computGotMoney(bag);
                userBag.setGotMoney(gotMoney);
                userBag.setUserBagStatus(Cst.UserBagStatus.FINISH_GRAB_RIGHT_WIN);

                // 更新红包信息
                bag.setSentCount(bag.getSentCount() + 1);
                bag.setSentMoney(bag.getSentMoney() + gotMoney);

                if (bag.getBagCount() == bag.getSentCount())
                {
                    bag.setBagStatus(Cst.BagStatus.ALL_GRABBED);
                }

                changeUserMoney(ugbModel, ugbModel.getUser().getUserId(), Cst.BillType.GOT_BAG,
                        userBag.getGotMoney(), "抢到一个红包，得到");
            }
            else
            // 全部答对，但是红包抢完了，奖励作业币
            {
                userBag.setGotMoney(Cst.Money.ANSWER_RIGHT_BUT_LATE);
                userBag.setUserBagStatus(Cst.UserBagStatus.FINISH_GRAB_RIGHT_SLOW);

                changeUserMoney(ugbModel, ugbModel.getUser().getUserId(), Cst.BillType.GOT_BAG,
                        userBag.getGotMoney(), "抢到红包时全部答对，系统奖励");
            }
        }

    }

    private void submitBagPractice(UserGrabBagModel ugbModel, UserBag userBag)
    {
        // 做题练一练点提交，只需要更新状态
        if (ugbModel.getWrongOrBlankCount() == 0)
        {
            userBag.setUserBagStatus(Cst.UserBagStatus.FINISH_PRACTICE_RIGHT);
        }
        else
        {
            userBag.setUserBagStatus(Cst.UserBagStatus.FINISH_PRACTICE_WRONG);
        }
    }

    // 保存答案以后做的事情
    // --------------------------------------------------------------------------------
    private void doUserShuati(UserGrabBagModel ugbModel)
    {
        // 更新UserBag时间
        UserBag userBag = ugbModel.getUserBag();
        userBag.setEndBagTime(Utl.currentSeconds());

        if (ugbModel.getWrongOrBlankCount() == 0)
        {
            userBag.setGotMoney(Cst.Money.SHUATI_ALL_RIGHT);
            userBag.setUserBagStatus(Cst.UserBagStatus.FINISH_SHUATI_RIGHT);
        }
        else if (ugbModel.getWrongOrBlankCount() == 1)
        {
            userBag.setGotMoney(Cst.Money.SHUATI_FOUR_RIGHT);
            userBag.setUserBagStatus(Cst.UserBagStatus.FINISH_SHUATI_WRONG);
        }
        else if (ugbModel.getWrongOrBlankCount() == 2)
        {
            userBag.setGotMoney(Cst.Money.SHUATI_THREE_RIGHT);
            userBag.setUserBagStatus(Cst.UserBagStatus.FINISH_SHUATI_WRONG);
        }
        else
        {
            userBag.setUserBagStatus(Cst.UserBagStatus.FINISH_SHUATI_WRONG);
        }

        if (userBag.getGotMoney() != 0)
        {
            changeUserMoney(ugbModel, ugbModel.getUser().getUserId(), Cst.BillType.SHUATI_RESULT,
                    userBag.getGotMoney(), "刷题成功，系统奖励");
        }
        bagMapper.updateUserBag(userBag);
    }

    private void doUserGrabBag(UserGrabBagModel ugbModel)
    {
        // 获取红包的最新情况
        Bag bag = bagMapper.getBagForUserGrabBag(ugbModel.getBag().getBagId());
        ugbModel.setBag(bag);
        bag.setGrabbedCount(bag.getGrabbedCount() + 1);

        // 更新UserBag时间
        UserBag userBag = ugbModel.getUserBag();
        userBag.setEndBagTime(Utl.currentSeconds());

        // 更新UserBag状态等
        if (ugbModel.getChanceType() == Cst.UserBagChanceType.HAS_CHANCE)
        {
            submitBagHasChance(ugbModel, userBag);
        }
        else
        {
            submitBagPractice(ugbModel, userBag);
        }

        // 更新创建者和bag返还的money
        submitBagSystemReturnMoney(ugbModel);

        bagMapper.updateBag(bag);
        bagMapper.updateUserBag(userBag);
    }

    private void doUserCheatGrabBag(UserGrabBagModel ugbModel)
    {
        // 获取红包的最新情况
        Bag bag = bagMapper.getBagForUserGrabBag(ugbModel.getBag().getBagId());
        ugbModel.setBag(bag);

        // 更新UserBag状态等
        UserBag userBag = ugbModel.getUserBag();
        submitBagHasChance(ugbModel, userBag);
        bagMapper.updateUserBag(userBag);

        // 更新创建者和bag返还的money
        submitBagSystemReturnMoney(ugbModel);

        // 更新bag，可能会设置sentCount之类的值
        bagMapper.updateBag(bag);
    }

    public void initShareInfo(UserGrabBagModel ugbModel)
    {
        ShareInfo shareInfo = new ShareInfo();

        String zsdString = ugbModel.getBag().getZsdCatalog().getZsdString();
        if (zsdString.contains("精品题"))
        {
            shareInfo.setShareTitle("这几道题，据说只有1%的人全对");
            shareInfo.setShareMsg("你敢来挑战吗？" + zsdString + "，" + ugbModel.getBag().getBagMoney() + "作业币");
            shareInfo.setShareImage("/migu/resources/image/user/createbag/share_logo_jing_pin.png");
        }
        else if (zsdString.contains("抗战胜利70周年"))
        {
            List<String> titleList = new ArrayList<String>();
            titleList.add("抢答，你手边的知识加油站");

            List<String> msgList = new ArrayList<String>();
            msgList.add("3道题，带你走进抗战历史：纪念抗战胜利70周年专题，答题抢" + ugbModel.getBag().getBagMoney() + "作业币");
            msgList.add("3道题，让你了解抗日战争史！纪念抗战胜利70周年专题，答题抢" + ugbModel.getBag().getBagMoney() + "作业币");
            msgList.add("你了解伟大的抗日战争么？纪念抗战胜利70周年专题，答题抢" + ugbModel.getBag().getBagMoney() + "作业币");
            msgList.add("一个中国人必须了解的抗战历史知识！纪念抗战胜利70周年专题，答题抢" + ugbModel.getBag().getBagMoney() + "作业币");
            msgList.add("3道抗战知识问答题，你能做对几道？纪念抗战胜利70周年专题，答题抢" + ugbModel.getBag().getBagMoney() + "作业币");
            msgList.add("抗战记忆——盘点两百个抗战历史知识。纪念抗战胜利70周年专题，答题抢" + ugbModel.getBag().getBagMoney() + "作业币");
            msgList.add("历史不能忘记！你该知道的抗战知识。纪念抗战胜利70周年专题，答题抢" + ugbModel.getBag().getBagMoney() + "作业币");

            List<String> shareImageList = new ArrayList<String>();
            shareImageList.add("/migu/resources/image/user/createbag/share_logo_kang_zhan_1.png");
            shareImageList.add("/migu/resources/image/user/createbag/share_logo_kang_zhan_2.png");
            shareImageList.add("/migu/resources/image/user/createbag/share_logo_kang_zhan_3.png");
            shareImageList.add("/migu/resources/image/user/createbag/share_logo_kang_zhan_4.png");

            Random random = new Random();
            shareInfo.setShareTitle(titleList.get(random.nextInt(titleList.size())));
            shareInfo.setShareMsg(msgList.get(random.nextInt(msgList.size())));
            shareInfo.setShareImage(shareImageList.get(random.nextInt(shareImageList.size())));
        }
        else
        {
            List<String> shareImageList = new ArrayList<String>();
            shareImageList.add("/migu/resources/image/user/createbag/share_logo_1.png");
            shareImageList.add("/migu/resources/image/user/createbag/share_logo_2.png");
            shareImageList.add("/migu/resources/image/user/createbag/share_logo_3.png");
            shareImageList.add("/migu/resources/image/user/createbag/share_logo_4.png");

            Random random = new Random();
            shareInfo.setShareTitle("3道越做越聪明的题!");
            shareInfo.setShareMsg("机智的你快来试一试：" + zsdString + "，" + ugbModel.getBag().getBagMoney() + "作业币");
            shareInfo.setShareImage(shareImageList.get(random.nextInt(shareImageList.size())));
        }

        shareInfo.setShareLink("/migu/user/grabbag/" + ugbModel.getBag().getBagId() + "/startgrab");
        shareInfo.setSucessTip("创建红包成功！");
        shareInfo.setSucessImage(Cst.DOMAIN_NAME + "/migu/resources/image/user/createbag/share_tip.jpg");
        shareInfo.setSuccessCallback("/migu/user/baglist/listmysent");

        ugbModel.setShareInfo(shareInfo);
    }

}
