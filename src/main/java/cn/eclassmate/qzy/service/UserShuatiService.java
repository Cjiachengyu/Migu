package cn.eclassmate.qzy.service;

import cn.eclassmate.qzy.domain.Bill;
import cn.eclassmate.qzy.domain.User;
import cn.eclassmate.qzy.domain.UserAnswer;
import cn.eclassmate.qzy.domain.UserMoon;
import cn.eclassmate.qzy.domain.UserStar;
import cn.eclassmate.qzy.domain.Zsd1;
import cn.eclassmate.qzy.domain.Zsd2;
import cn.eclassmate.qzy.domain.ZsdCatalog;
import cn.eclassmate.qzy.domain.Zsl;
import cn.eclassmate.qzy.global.Cst;
import cn.eclassmate.qzy.global.Utl;
import cn.eclassmate.qzy.viewmodel.UserShuatiModel;
import cn.eclassmate.qzy.viewmodel.subview.Zsd1Report;
import cn.eclassmate.qzy.viewmodel.subview.Zsd2Report;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserShuatiService extends AbstractService
{
    private static final long serialVersionUID = 2346178968606382116L;
    private static final Logger logger = LogManager.getLogger();

    public void initZsdCatalog(UserShuatiModel usrModel)
    {
        List<Zsl> zslList = queMapper.getAllZslWithZsd();
        usrModel.setZslList(zslList);
    }

    public void initZsdReportDetail(UserShuatiModel usrModel)
    {
        List<Zsd1> zsd1List = bagMapper.getAllTwoLevelZsdsForStudyReport();

        List<Zsd1Report> zsd1ReportList = initZsd1ReportList(zsd1List);

        List<UserAnswer> userAnswerList = bagMapper.getUserAnswersForStudyReport(usrModel.getUser().getUserId());

        fillZsd1ReportList(usrModel, userAnswerList, zsd1ReportList);

        usrModel.setZsd1ReportList(zsd1ReportList);
    }

    @Transactional
    public void changeStarStatus(UserShuatiModel usrModel, int zskId, int zsd1Id, int zsd2Id, int starNum)
    {
        int userId = usrModel.getUser().getUserId();
        UserStar userStar = bagMapper.getUserStar(userId, zskId, zsd1Id, zsd2Id);
        if (userStar == null)
        {
            userStar = new UserStar();
            userStar.setUserId(userId);
            userStar.setZskId(zskId);
            userStar.setZsd1Id(zsd1Id);
            userStar.setZsd2Id(zsd2Id);

            doUpdateStarStatus(starNum, userStar);
            bagMapper.insertUserStar(userStar);
        }
        else
        {
            doUpdateStarStatus(starNum, userStar);
            bagMapper.updateUserStar(userStar);
        }

        Bill bill = new Bill();
        if (starNum == 1)
        {
            changeUserMoney(usrModel, usrModel.getUser().getUserId(), Cst.BillType.GOT_STAR,
                    Cst.Money.GOT_STAR_ONE_MONEY, "领取一颗星星，得到");
        }
        else if (starNum == 2)
        {
            changeUserMoney(usrModel, usrModel.getUser().getUserId(), Cst.BillType.GOT_STAR,
                    Cst.Money.GOT_STAR_TWO_MONEY, "领取一颗星星，得到");
        }
        else if (starNum == 3)
        {
            changeUserMoney(usrModel, usrModel.getUser().getUserId(), Cst.BillType.GOT_STAR,
                    Cst.Money.GOT_STAR_THREE_MONEY, "领取一颗星星，得到");
        }

    }

    @Transactional
    public void changeMoonStatus(UserShuatiModel usrModel, int zskId, int zsd1Id, int moonNum)
    {
        int userId = usrModel.getUser().getUserId();
        UserMoon userMoon = bagMapper.getUserMoon(userId, zskId, zsd1Id);
        if (userMoon == null)
        {
            userMoon = new UserMoon();
            userMoon.setUserId(userId);
            userMoon.setZskId(zskId);
            userMoon.setZsd1Id(zsd1Id);

            doUpdateMoonStatus(moonNum, userMoon);
            bagMapper.insertUserMoon(userMoon);
        }
        else
        {
            doUpdateMoonStatus(moonNum, userMoon);
            bagMapper.updateUserMoon(userMoon);
        }

        Bill bill = new Bill();

        if (moonNum == 1)
        {
            changeUserMoney(usrModel, usrModel.getUser().getUserId(), Cst.BillType.GOT_MOON,
                    Cst.Money.GOT_MOON_ONE_MONEY, "领取一个月亮，得到");
        }
        else if (moonNum == 2)
        {
            changeUserMoney(usrModel, usrModel.getUser().getUserId(), Cst.BillType.GOT_MOON,
                    Cst.Money.GOT_MOON_TWO_MONEY, "领取一个月亮，得到");
        }
        else if (moonNum == 3)
        {
            changeUserMoney(usrModel, usrModel.getUser().getUserId(), Cst.BillType.GOT_MOON,
                    Cst.Money.GOT_MOON_THREE_MONEY, "领取一个月亮，得到");
        }

    }

    // private
    // --------------------------------------------------------------------------------
    private List<Zsd1Report> initZsd1ReportList(List<Zsd1> zsd1List)
    {
        List<Zsd1Report> zsd1ReportList = new ArrayList<Zsd1Report>();

        for (Zsd1 zsd1 : zsd1List)
        {
            Zsd1Report zsd1Report = new Zsd1Report();
            zsd1Report.setZsd1(zsd1);

            List<Zsd2Report> zsd2ReportList = new ArrayList<Zsd2Report>();
            for (Zsd2 zsd2 : zsd1.getZsd2List())
            {
                Zsd2Report zsd2Report = new Zsd2Report();
                zsd2Report.setZsd2(zsd2);
                zsd2ReportList.add(zsd2Report);
            }
            zsd1Report.setZsd2ReportList(zsd2ReportList);

            zsd1ReportList.add(zsd1Report);
        }

        return zsd1ReportList;
    }

    private void fillZsd1ReportList(UserShuatiModel usrModel, List<UserAnswer> userAnswerList,
            List<Zsd1Report> zsd1ReportList)
    {
        for (UserAnswer userAnswer : userAnswerList)
        {
            for (ZsdCatalog zsdCatalog : userAnswer.getQue().getZsdCatalogList())
            {
                // 查找所属的ZsdReport
                for (Zsd1Report zsd1Report : zsd1ReportList)
                {
                    if (zsdCatalog.getZsd1Id() == zsd1Report.getZsd1().getZsd1Id())
                    {

                        zsd1Report.getAllUserAnswerList().add(userAnswer);//一级知识点总答题数
                        if (userAnswer.getIsUserRight())
                        {
                            zsd1Report.getRightUserAnswerList().add(userAnswer);//一级知识点正确答题数
                        }

                        for (Zsd2Report zsd2Report : zsd1Report.getZsd2ReportList())
                        {
                            if (zsdCatalog.getZsd2Id() == zsd2Report.getZsd2().getZsd2Id())
                            {
                                // 找到ZsdReport
                                zsd2Report.getAllUserAnswerList().add(userAnswer);

                                if (userAnswer.getIsUserRight())
                                {
                                    zsd2Report.getRightUserAnswerList().add(userAnswer);
                                }

                                // 已经找到Zsd2Report，无需继续搜索
                                break;
                            }
                        }

                        // 已经找到Zsd1Report，无需继续搜索
                        break;
                    }
                }
            }
        }

        List<UserStar> allUserStar = bagMapper.getAllUserStarByUserId(usrModel.getUser().getUserId());

        List<UserMoon> allUserMoon = bagMapper.getAllUserMoonByUserId(usrModel.getUser().getUserId());

        // 计算
        for (Zsd1Report zsd1Report : zsd1ReportList)
        {
            List<UserAnswer> allUserAnswerGroupByZsd1List = zsd1Report.getAllUserAnswerList();
            List<UserAnswer> rightUserAnswerGroupByZsd1List = zsd1Report.getRightUserAnswerList();

            zsd1Report.setRightPercent(Utl.getPercent(rightUserAnswerGroupByZsd1List.size(),
                    allUserAnswerGroupByZsd1List.size(), 1));//设置一级知识点正确率


            UserMoon userMoon = findUserMoon(allUserMoon,
                    zsd1Report.getZsd1().getZskId(),
                    zsd1Report.getZsd1().getZsd1Id());
            if (userMoon == null)
            {
                userMoon = new UserMoon();
            }

            if (allUserAnswerGroupByZsd1List.size() >= 30 && userMoon.getMoon1Status() == 1)
            {
                userMoon.setMoon1Status(Cst.UserStarStatus.CAN_GET);
            }

            if (rightUserAnswerGroupByZsd1List.size() >= 30 && userMoon.getMoon2Status() == 1)
            {
                userMoon.setMoon2Status(Cst.UserStarStatus.CAN_GET);
            }

            int continuousAnswerRightCountGroupByZsd1 = getContinuousAnswerRightCount(allUserAnswerGroupByZsd1List);
            if (continuousAnswerRightCountGroupByZsd1 >= 30 && userMoon.getMoon3Status() == 1)
            {
                userMoon.setMoon3Status(Cst.UserStarStatus.CAN_GET);
            }

            zsd1Report.setUserMoon(userMoon);


            for (Zsd2Report zsd2Report : zsd1Report.getZsd2ReportList())
            {
                List<UserAnswer> allUserAnswerList = zsd2Report.getAllUserAnswerList();
                List<UserAnswer> rightUserAnswerList = zsd2Report.getRightUserAnswerList();
                zsd2Report.setRightPercent(Utl.getPercent(rightUserAnswerList.size(), allUserAnswerList.size(), 1));

                UserStar userStar = findUserStar(allUserStar,
                        zsd1Report.getZsd1().getZskId(),
                        zsd1Report.getZsd1().getZsd1Id(),
                        zsd2Report.getZsd2().getZsd2Id());
                if (userStar == null)
                {
                    userStar = new UserStar();
                }

                if (allUserAnswerList.size() >= 10 && userStar.getStar1Status() == 1)
                {
                    userStar.setStar1Status(Cst.UserStarStatus.CAN_GET);
                    //userStar.setStar1Status(3);
                }

                if (rightUserAnswerList.size() >= 15 && userStar.getStar2Status() == 1)
                {
                    userStar.setStar2Status(Cst.UserStarStatus.CAN_GET);
                    //userStar.setStar2Status(3);
                }

                int continuousAnswerRightCount = getContinuousAnswerRightCount(allUserAnswerList);
                if (continuousAnswerRightCount >= 20 && userStar.getStar3Status() == 1)
                {
                    userStar.setStar3Status(Cst.UserStarStatus.CAN_GET);
                    //userStar.setStar3Status(3);
                }

                zsd2Report.setUserStar(userStar);
            }
        }
    }

    private int getContinuousAnswerRightCount(List<UserAnswer> allUserAnswerList)
    {
        int result = 0;
        for (int i = 0; i < allUserAnswerList.size(); i++)
        {
            if (allUserAnswerList.get(i).getIsUserRight())
            {
                int count = 1;
                for (int j = i + 1; j < allUserAnswerList.size(); j++)
                {
                    if (allUserAnswerList.get(j).getIsUserRight())
                    {
                        count++;
                    }
                    else
                    {
                        break;
                    }
                }
                if (count > result)
                {
                    result = count;
                }
            }
        }

        return result;
    }

    private UserStar findUserStar(List<UserStar> allUserStar, int zskId, int zsd1Id, int zsd2Id)
    {
        for (UserStar us : allUserStar)
        {
            if (us.getZskId() == zskId && us.getZsd1Id() == zsd1Id && us.getZsd2Id() == zsd2Id)
            {
                return us;
            }
        }

        return null;
    }

    private UserMoon findUserMoon(List<UserMoon> allUserMoon, int zskId, int zsd1Id)
    {
        for (UserMoon um : allUserMoon)
        {
            if (um.getZskId() == zskId && um.getZsd1Id() == zsd1Id)
            {
                return um;
            }
        }

        return null;
    }

    private void doUpdateStarStatus(int starNum, UserStar userStar)
    {
        if (starNum == 1)
        {
            userStar.setStar1Status(3);
        }
        else if (starNum == 2)
        {
            userStar.setStar2Status(3);
        }
        else
        {
            userStar.setStar3Status(3);
        }
    }

    private void doUpdateMoonStatus(int moonNum, UserMoon userMoon)
    {
        if (moonNum == 1)
        {
            userMoon.setMoon1Status(3);
        }
        else if (moonNum == 2)
        {
            userMoon.setMoon2Status(3);
            ;
        }
        else
        {
            userMoon.setMoon3Status(3);
            ;
        }
    }

    public void recordShuatiZskId(User user, int zskId)
    {
        user.setShuatiZskId(zskId);
        userMapper.updateUser(user);
    }

}
