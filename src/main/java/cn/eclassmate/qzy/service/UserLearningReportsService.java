package cn.eclassmate.qzy.service;

import cn.eclassmate.qzy.domain.ScoreDefeat;
import cn.eclassmate.qzy.domain.UserAnswer;
import cn.eclassmate.qzy.domain.Zsd1;
import cn.eclassmate.qzy.domain.Zsd2;
import cn.eclassmate.qzy.domain.ZsdCatalog;
import cn.eclassmate.qzy.domain.Zsk;
import cn.eclassmate.qzy.global.Utl;
import cn.eclassmate.qzy.viewmodel.UserLearningReportsModel;
import cn.eclassmate.qzy.viewmodel.subview.Zsd1Report;
import cn.eclassmate.qzy.viewmodel.subview.Zsd2Report;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Service
public class UserLearningReportsService extends AbstractService
{
    private static final long serialVersionUID = 2346178968606382116L;
    private static final Logger logger = LogManager.getLogger();


    public void initLearningReportsModel(UserLearningReportsModel viewModel, int dateType)
    {

        int endTime = Utl.currentSeconds();
        int beginTime = Utl.getBeginTimeOfThisWeek();

        if (dateType == 2)
        {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            int zeroTimeToday = (int) (c.getTime().getTime() / 1000L);

            int secondsOfADay = 3600 * 24;
            int secondsOf29Days = secondsOfADay * 29;
            beginTime = zeroTimeToday - secondsOf29Days;

        }
        else if (dateType == 3)
        {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            int zeroTimeToday = (int) (c.getTime().getTime() / 1000L);

            int secondsOfADay = 3600 * 24;
            int secondsOf92Days = secondsOfADay * 92;
            beginTime = zeroTimeToday - secondsOf92Days;
        }
        else if (dateType == 4)
        {
            beginTime = 0;
        }

        viewModel.setDateType(dateType);
        viewModel.setBeginTime(beginTime);
        viewModel.setEndTime(endTime);

        Map queCountMap = bagMapper.getAnswerMostQueUserId(viewModel.getBeginTime());
        Map queTimeMap = bagMapper.getAnswerMostTimeUserId(viewModel.getBeginTime());
        if (queCountMap == null || queTimeMap == null)
        {
            viewModel.setScore(0);
            viewModel.setScoreDefeat(0);
        }
        else
        {
            int queCountMax = Integer.parseInt(queCountMap.get("queCountMax").toString());
            int consumeTimeSumMax = Integer.parseInt(queTimeMap.get("consumeTimeSumMax").toString());

            Map myMap = bagMapper.getUserAnswerByUserId(viewModel.getUser().getUserId(), viewModel.getBeginTime());
            if (myMap == null)
            {
                viewModel.setScore(0);
                viewModel.setScoreDefeat(0);
            }
            else
            {
                int queCount = Integer.parseInt(myMap.get("queCount").toString());
                int queRightCount = Integer.parseInt(myMap.get("queRightCount").toString());
                int consumeTimeSum = Integer.parseInt(myMap.get("consumeTimeSum").toString());

                int score = (queCount * 40) / queCountMax + (queRightCount * 40) / queCount + (consumeTimeSum * 20)
                        / consumeTimeSumMax;

                viewModel.setScore(score);

                Calendar c = Calendar.getInstance();
                c.set(Calendar.HOUR_OF_DAY, 0);
                c.set(Calendar.MINUTE, 0);
                c.set(Calendar.SECOND, 0);
                ScoreDefeat scoreDefeat = bagMapper.getScoreDefeat(score);

                if (dateType == 1)
                {
                    viewModel.setScoreDefeat(Double.parseDouble(scoreDefeat.getDefeatWeek()));
                }
                else if (dateType == 2)
                {
                    viewModel.setScoreDefeat(Double.parseDouble(scoreDefeat.getDefeatOneMonth()));
                }
                else if (dateType == 3)
                {
                    viewModel.setScoreDefeat(Double.parseDouble(scoreDefeat.getDefeatThreeMonth()));
                }
                else if (dateType == 4)
                {
                    viewModel.setScoreDefeat(Double.parseDouble(scoreDefeat.getDefeatAll()));
                }

            }
        }


        refreshModel(viewModel);
    }

    public void refreshModel(UserLearningReportsModel viewModel)
    {
        List<Zsd1> zsd1List = bagMapper.getAllTwoLevelZsdsForStudyReport();

        List<Zsd1Report> zsd1ReportList = initZsd1ReportList(zsd1List);

        List<UserAnswer> userAnswerList = bagMapper.getUserAnswersForLearningReport(viewModel.getUser().getUserId(),
                viewModel.getBeginTime());

        fillZsd1ReportList(viewModel, userAnswerList, zsd1ReportList);
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


    private void fillZsd1ReportList(UserLearningReportsModel viewModel, List<UserAnswer> userAnswerList,
            List<Zsd1Report> zsd1ReportList)
    {

        int rightCount = 0;
        int consumeTime = 0;

        for (UserAnswer userAnswer : userAnswerList)
        {
            if (userAnswer.getIsUserRight())
            {
                rightCount += 1;
            }
            consumeTime += userAnswer.getConsumeTime();

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

                                //zsd1Report.getAllUserAnswerList().add(userAnswer);//一级知识点总答题数

                                if (userAnswer.getIsUserRight())
                                {
                                    zsd2Report.getRightUserAnswerList().add(userAnswer);

                                    //zsd1Report.getRightUserAnswerList().add(userAnswer);//一级知识点正确答题数
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

        viewModel.setConsumeTime(consumeTime);
        viewModel.setRightPercent(Utl.getPercent(rightCount, userAnswerList.size(), 1));
        viewModel.setAnswerCount(userAnswerList.size());


        List<Zsk> zskList = queMapper.getAllZsk();
        // 计算
        for (Zsd1Report zsd1Report : zsd1ReportList)
        {
            List<UserAnswer> allUserAnswerGroupByZsd1List = zsd1Report.getAllUserAnswerList();
            List<UserAnswer> rightUserAnswerGroupByZsd1List = zsd1Report.getRightUserAnswerList();

            zsd1Report.setRightPercent(Utl.getPercent(rightUserAnswerGroupByZsd1List.size(),
                    allUserAnswerGroupByZsd1List.size(), 1));//设置一级知识点正确率

            for (Zsd2Report zsd2Report : zsd1Report.getZsd2ReportList())
            {
                List<UserAnswer> allUserAnswerList = zsd2Report.getAllUserAnswerList();
                List<UserAnswer> rightUserAnswerList = zsd2Report.getRightUserAnswerList();
                zsd2Report.setRightPercent(Utl.getPercent(rightUserAnswerList.size(), allUserAnswerList.size(), 1));//设置二级知识点正确率
            }

            for (Zsk zsk : zskList)
            {
                if (zsk.getZskId() == zsd1Report.getZsd1().getZskId())
                {
                    zsk.getZsd1ReportList().add(zsd1Report);
                }
            }

        }

        for (Zsk zsk : zskList)
        {
            int isDisplay = 0;
            for (Zsd1Report zsd1Report : zsk.getZsd1ReportList())
            {
                if (zsd1Report.getAllUserAnswerList().size() > 0)
                {
                    isDisplay = 1;
                }

                for (Zsd2Report zsd2Report : zsd1Report.getZsd2ReportList())
                {
                    if (zsd2Report.getAllUserAnswerList().size() > 0)
                    {
                        isDisplay = 1;
                    }
                }
            }
            zsk.setIsDisplay(isDisplay);
        }

        viewModel.setZskList(zskList);

    }
}
