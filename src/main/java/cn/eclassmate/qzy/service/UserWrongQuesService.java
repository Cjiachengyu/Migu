package cn.eclassmate.qzy.service;

import cn.eclassmate.qzy.domain.Que;
import cn.eclassmate.qzy.domain.QueChoice;
import cn.eclassmate.qzy.domain.QueFeedBack;
import cn.eclassmate.qzy.domain.UserAnswer;
import cn.eclassmate.qzy.viewmodel.UserWrongQuesModel;
import cn.eclassmate.qzy.viewmodel.subview.TitledQueChoice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserWrongQuesService extends AbstractService
{
    private static final long serialVersionUID = -2971683733562233221L;
    private static final Logger logger = LogManager.getLogger();


    public void initUserWrongQuesModel(UserWrongQuesModel viewModel)
    {

        List<UserAnswer> userAnswerList = bagMapper.getWrongQueList(viewModel.getUser().getUserId());
        viewModel.setUserAnswerList(userAnswerList);

        // 设置题号相关的Map：queNumTqcListMap，queNumQueIdMap，queNumUserAnswerMap
        Map<Integer, Que> queNumQueMap = new HashMap<Integer, Que>();
        Map<Integer, List<TitledQueChoice>> queNumTqcListMap = new HashMap<Integer, List<TitledQueChoice>>();
        Map<Integer, UserAnswer> queNumUserAnswerMap = new HashMap<Integer, UserAnswer>();
        initQueNumMap(viewModel, queNumQueMap, queNumTqcListMap, queNumUserAnswerMap);
        viewModel.setQueNumQueMap(queNumQueMap);
        viewModel.setQueNumTqcListMap(queNumTqcListMap);
        viewModel.setQueNumUserAnswerMap(queNumUserAnswerMap);
    }

    private void initQueNumMap(UserWrongQuesModel viewModel, Map<Integer, Que> queNumQueMap,
            Map<Integer, List<TitledQueChoice>> queNumTqcListMap, Map<Integer, UserAnswer> queNumUserAnswerMap)
    {
        for (int i = 0; i < viewModel.getUserAnswerList().size(); i++)
        {
            // 设置题号和题目的对应
            queNumQueMap.put(i + 1, viewModel.getUserAnswerList().get(i).getQue());

            // 设置题号与选项列表的对应关系
            List<QueChoice> qcList = viewModel.getUserAnswerList().get(i).getQue().getQueChoiceList();
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
            UserAnswer userAnswer = viewModel.getUserAnswerList().get(i);
            if (userAnswer != null)
            {
                queNumUserAnswerMap.put(i + 1, userAnswer);

                updateTitledQueChoiceList(tqcList, userAnswer.getUserChoiceId());
            }
        }
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


    @Transactional
    public void queFeedBack(UserWrongQuesModel ugbModel, int queId, String feedBackMsg)
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

}
