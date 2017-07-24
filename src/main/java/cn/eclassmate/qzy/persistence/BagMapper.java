package cn.eclassmate.qzy.persistence;

import cn.eclassmate.qzy.domain.Bag;
import cn.eclassmate.qzy.domain.BagQue;
import cn.eclassmate.qzy.domain.Bill;
import cn.eclassmate.qzy.domain.Gift;
import cn.eclassmate.qzy.domain.Prize;
import cn.eclassmate.qzy.domain.QueFeedBack;
import cn.eclassmate.qzy.domain.ScoreDefeat;
import cn.eclassmate.qzy.domain.User;
import cn.eclassmate.qzy.domain.UserAnswer;
import cn.eclassmate.qzy.domain.UserBag;
import cn.eclassmate.qzy.domain.UserMoon;
import cn.eclassmate.qzy.domain.UserPrize;
import cn.eclassmate.qzy.domain.UserStar;
import cn.eclassmate.qzy.domain.Zsd1;

import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface BagMapper
{

    // 创建作业
    // --------------------------------------------------------------------------------
    int getCreateBagCount(@Param("userId") int userId, @Param("zeroTimeToday") int zeroTimeToday);

    int insertBag(Bag bag);

    void insertBagQue(List<BagQue> list);


    // 抢作业
    // --------------------------------------------------------------------------------
    Bag getBagForUserGrabBag(@Param("bagId") int bagId);

    UserBag getUserBag(@Param("userId") int userId, @Param("bagId") int bagId);

    List<UserBag> getAlreadyGrabbedUserBagList(int bagId);

    UserAnswer getUserAnswer(@Param("userId") int userId, @Param("bagId") int bagId, @Param("queId") int queId);

    int insertUserAnswer(UserAnswer userAnswer);

    void updateUserAnswer(UserAnswer userAnswer);

    int insertUserBag(UserBag userBag);

    void updateUserBag(UserBag userBag);

    void updateBag(Bag bag);


    // 作业列表
    // --------------------------------------------------------------------------------
    List<Bag> getMySentBagList(int userId);

    List<UserBag> getMyGotBagList(int userId);


    // 排行榜
    // --------------------------------------------------------------------------------
    // 学霸榜
    List<User> getUserRightAnswerCountRankList(int limitRank);

    List<User> getUserRightAnswerCountRankListInFriend(@Param("userId") int userId, @Param("beginTime") int beginTime);

    int getUserRightAnswerCountRank(int userId);

    int getUserRightAnswerCount(int userId);

    //int getUserRightAnswerCountByTime(@Param("userId") int userId, @Param("beginTime") int beginTime);

    int getUserRightAnswerCountByTime(@Param("userId") int userId);

    // 财富榜
    List<User> getUserMoneyRankList(int limitRank);

    List<User> getUserMoneyRankListInFriend(@Param("userId") int userId, @Param("limitRank") int limitRank);

    int getMyMoneyRank(int userId);

    int getMyMoneyRankInFriends(int userId);

    // 豪气榜
    List<User> getUserSentMoneyRankList(int limitRank);

    List<User> getUserSentMoneyRankListInFriend(@Param("userId") int userId, @Param("beginTime") int beginTime);

    int getUserSentMoneyRank(int userId);

    int getUserSentMoney(int userId);

    int getUserSentMoneyByTime(@Param("userId") int userId, @Param("beginTime") int beginTime);

    // 返币榜
    List<User> getUserReturnedMoneyRankList(int limitRank);

    List<User> getUserReturnedMoneyRankListInFriend(@Param("userId") int userId, @Param("beginTime") int beginTime);

    int getUserReturnedMoneyRank(int userId);

    int getUserReturnedMoney(int userId);

    int getUserReturnedMoneyByTime(@Param("userId") int userId, @Param("beginTime") int beginTime);

    //消费榜
    List<User> getUserSpentMoneyRankList(int limitRank);

    int getUserSpentMoneyRank(int userId);

    int getUserSpentMoney(int userId);

    List<User> getUserSpentMoneyRankListInFriend(@Param("userId") int userId, @Param("beginTime") int beginTime);

    int getUserSpentMoneyByTime(@Param("userId") int userId, @Param("beginTime") int beginTime);


    // 单个作业的统计
    // --------------------------------------------------------------------------------
    List<UserBag> getUserBagForUserBagStt(int bagId);


    // 学习报告
    // --------------------------------------------------------------------------------
    List<UserAnswer> getUserAnswersByTimespan(
            @Param("userId") int userId,
            @Param("beginTime") int beginTime,
            @Param("endTime") int endTime);

    List<UserAnswer> getUserAnswersForStudyReport(@Param("userId") int userId);

    List<Zsd1> getAllTwoLevelZsdsForStudyReport();

    List<UserStar> getAllUserStarByUserId(int userId);

    List<UserMoon> getAllUserMoonByUserId(int userId);

    int insertUserStar(UserStar userStar);

    int insertUserMoon(UserMoon usermoon);

    UserStar getUserStar(
            @Param("userId") int userId,
            @Param("zskId") int zskId,
            @Param("zsd1Id") int zsd1Id,
            @Param("zsd2Id") int zsd2Id);

    UserMoon getUserMoon(
            @Param("userId") int userId,
            @Param("zskId") int zskId,
            @Param("zsd1Id") int zsd1Id);

    int updateUserStar(UserStar userStar);

    int updateUserMoon(UserMoon usermoon);


    //user learning reports
    List<UserAnswer> getUserAnswersForLearningReport(@Param("userId") int userId, @Param("beginTime") int beginTime);

    //user study statistics
    List<UserAnswer> getAllUserAnswers(@Param("beginTime") int beginTime);

    List<UserAnswer> getAllUserAnswer();

    // 奖品兑换和我的收藏
    // -----------------------------------------------------------------------------------
    List<Prize> getprizeList();

    UserPrize getUserPrize(@Param("userId") int userId, @Param("prizeId") int prizeId);

    int insertUserPrize(UserPrize userPrize);

    void updateUserPrize(UserPrize userPrize);

    List<UserPrize> getUserPrizeList(@Param("userId") int userId);

    void deletePrize(@Param("prizeId") int prizeId);

    int insertPrize(Prize prize);

    int updatePrize(Prize prize);

    Prize getPrize(@Param("prizeId") int prizeId);

    UserBag getUserBagForShow(@Param("userId") int userId, @Param("bagId") int bagId);

    //送礼
    //-----------------------------------------------------------------------------------
    int insertGift(Gift gift);

    Gift getGift(@Param("giftId") int giftId);

    int updateGift(Gift gift);

    //问题反馈
    //-----------------------------------------------------------------------------------
    QueFeedBack getQueFeedBack(@Param("userId") int userId, @Param("queId") int queId);

    int insertQueFeedBack(QueFeedBack queFeedBack);

    void updateQueFeedBack(QueFeedBack queFeedBack);

    //礼品展示
    //-----------------------------------------------------------------------------------
    List<Gift> getGiftSendList(int senderId);

    List<Gift> getGiftReceiveList(int receiverId);

    List<Gift> getGiftListAll(int userId);

    //用户中心
    List<Bill> getBillList(@Param("userId") int userId, @Param("returnAmount") int returnAmount);

    int insertBill(Bill bill);

    //红包过期
    List<Bag> getTimeoutBagList(@Param("timeoutSeconds") int timeoutSeconds);


    int updateGrabUserAnswerCount(int userId);

    int updateWeekGrabUserAnswerCount(@Param("userId") int userId, @Param("beginTime") int beginTime);

    int updateWeekAnswerCountForQuartz(@Param("beginTime") int beginTime);

    int updateBagCreatorMoney(int userId);

    int updateWeekBagCreatorMoney(@Param("userId") int userId, @Param("beginTime") int beginTime);

    int updateWeekMoneyForQuartz(@Param("beginTime") int beginTime);

    int updateUserSpentMoney(int userId);

    int updateWeekUserSpentMoney(@Param("userId") int userId, @Param("beginTime") int beginTime);

    int updateWeekUserSpentMoneyForQuartz(@Param("beginTime") int beginTime);


    //score_defeat
    Map getAnswerMostQueUserId(@Param("beginTime") int beginTime);

    Map getAnswerMostTimeUserId(@Param("beginTime") int beginTime);

    List<HashMap> getUserAnswerGroupByUser(@Param("beginTime") int beginTime);

    Map getUserAnswerByUserId(@Param("userId") int userId, @Param("beginTime") int beginTime);

    int insertScoreDefeat(ScoreDefeat scoreDefeat);

    void updateScoreDefeat(ScoreDefeat scoreDefeat);

    ScoreDefeat getScoreDefeat(@Param("score") int score);


    //错题集
    List<UserAnswer> getWrongQueList(@Param("userId") int userId);

}
