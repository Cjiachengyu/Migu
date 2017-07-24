package cn.eclassmate.qzy.viewmodel;

import cn.eclassmate.qzy.domain.UserAnswer;
import cn.eclassmate.qzy.domain.Zsl;
import cn.eclassmate.qzy.viewmodel.subview.Zsd1Report;

import java.util.List;
import java.util.Map;

public class UserStudyReportModel extends AbstractModel
{
    private static final long serialVersionUID = 1886085397027390750L;

    // fields
    // --------------------------------------------------------------------------------
    private Map<Integer, String> indexDateMap;          // 日期序号-日期
    private Map<String, List<UserAnswer>> dateAllUserAnswerListMap;   // 每日答题总数
    private Map<String, List<UserAnswer>> dateRightUserAnswerListMap; // 每日答对题数
    private Map<String, Double> dateRightPercentMap;    // 每日正确率

    // getter, setter
    // --------------------------------------------------------------------------------
    public Map<Integer, String> getIndexDateMap()
    {
        return indexDateMap;
    }

    public void setIndexDateMap(Map<Integer, String> indexDateMap)
    {
        this.indexDateMap = indexDateMap;
    }

    public Map<String, List<UserAnswer>> getDateAllUserAnswerListMap()
    {
        return dateAllUserAnswerListMap;
    }

    public void setDateAllUserAnswerListMap(Map<String, List<UserAnswer>> dateAllUserAnswerListMap)
    {
        this.dateAllUserAnswerListMap = dateAllUserAnswerListMap;
    }

    public Map<String, List<UserAnswer>> getDateRightUserAnswerListMap()
    {
        return dateRightUserAnswerListMap;
    }

    public void setDateRightUserAnswerListMap(Map<String, List<UserAnswer>> dateRightUserAnswerListMap)
    {
        this.dateRightUserAnswerListMap = dateRightUserAnswerListMap;
    }

    public Map<String, Double> getDateRightPercentMap()
    {
        return dateRightPercentMap;
    }

    public void setDateRightPercentMap(Map<String, Double> dateRightPercentMap)
    {
        this.dateRightPercentMap = dateRightPercentMap;
    }
}
