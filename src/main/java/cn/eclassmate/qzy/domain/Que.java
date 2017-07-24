package cn.eclassmate.qzy.domain;

import cn.eclassmate.qzy.global.Cst;

import java.io.Serializable;
import java.util.List;

public class Que implements Serializable
{
    private static final long serialVersionUID = 5842580907674859374L;

    // que
    // --------------------------------------------------------------------------------
    private int queId;
    private int queType;
    private String source;
    private String kaodian;
    private int difficulty;

    private int creatorId;
    private int createTime;
    private int updaterId;
    private int updateTime;

    private String contentHtml;
    private String analysisHtml;
    private String answerHtml;

    private int queStatus;

    // 关联对象
    // --------------------------------------------------------------------------------
    private List<QueChoice> queChoiceList;
    private List<ZsdCatalog> zsdCatalogList;
    private List<QueFeedBack> queFeedBackList;
    private Editor creator;


    // 附加存取器
    // --------------------------------------------------------------------------------
    public String getQuestionTypeString()
    {
        return Cst.QueType.StrArr[queType];
    }

    public String getJoinedFeedback()
    {
        String ret = "";
        if (queFeedBackList != null)
        {
            for (QueFeedBack queFeedBack : queFeedBackList)
            {
                ret = ret + " ● " + queFeedBack.getFeedbackMsg();
            }
        }
        return ret;
    }

    // getter, setter
    // --------------------------------------------------------------------------------
    public int getQueId()
    {
        return queId;
    }

    public int getUpdaterId()
    {
        return updaterId;
    }

    public void setUpdaterId(int updaterId)
    {
        this.updaterId = updaterId;
    }

    public void setQueId(int queId)
    {
        this.queId = queId;
    }

    public int getCreatorId()
    {
        return creatorId;
    }

    public void setCreatorId(int creatorId)
    {
        this.creatorId = creatorId;
    }

    public String getContentHtml()
    {
        return contentHtml;
    }

    public void setContentHtml(String contentHtml)
    {
        this.contentHtml = contentHtml;
    }

    public String getSource()
    {
        return source;
    }

    public void setSource(String source)
    {
        this.source = source;
    }

    public String getKaodian()
    {
        return kaodian;
    }

    public void setKaodian(String kaodian)
    {
        this.kaodian = kaodian;
    }

    public int getDifficulty()
    {
        return difficulty;
    }

    public void setDifficulty(int difficulty)
    {
        this.difficulty = difficulty;
    }

    public String getAnalysisHtml()
    {
        return analysisHtml;
    }

    public void setAnalysisHtml(String analysisHtml)
    {
        this.analysisHtml = analysisHtml;
    }

    public String getAnswerHtml()
    {
        return answerHtml;
    }

    public void setAnswerHtml(String answerHtml)
    {
        this.answerHtml = answerHtml;
    }

    public int getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(int createTime)
    {
        this.createTime = createTime;
    }

    public int getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(int updateTime)
    {
        this.updateTime = updateTime;
    }

    public int getQueType()
    {
        return queType;
    }

    public void setQueType(int queType)
    {
        this.queType = queType;
    }

    public List<QueChoice> getQueChoiceList()
    {
        return queChoiceList;
    }

    public void setQueChoiceList(List<QueChoice> queChoiceList)
    {
        this.queChoiceList = queChoiceList;
    }

    public List<ZsdCatalog> getZsdCatalogList()
    {
        return zsdCatalogList;
    }

    public void setZsdCatalogList(List<ZsdCatalog> zsdCatalogList)
    {
        this.zsdCatalogList = zsdCatalogList;
    }

    public Editor getCreator()
    {
        return creator;
    }

    public void setCreator(Editor creator)
    {
        this.creator = creator;
    }

    public int getQueStatus()
    {
        return queStatus;
    }

    public void setQueStatus(int queStatus)
    {
        this.queStatus = queStatus;
    }


}
