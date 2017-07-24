package cn.eclassmate.qzy.domain;

import java.io.Serializable;

public class ScoreDefeat implements Serializable
{
    private static final long serialVersionUID = -4013573473020500518L;

    // fields
    // --------------------------------------------------------------------------------
    private int score;
    private String defeatWeek;
    private String defeatOneMonth;
    private String defeatThreeMonth;
    private String defeatAll;

    // getter, setter
    // --------------------------------------------------------------------------------
    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public String getDefeatWeek()
    {
        return defeatWeek;
    }

    public void setDefeatWeek(String defeatWeek)
    {
        this.defeatWeek = defeatWeek;
    }

    public String getDefeatOneMonth()
    {
        return defeatOneMonth;
    }

    public void setDefeatOneMonth(String defeatOneMonth)
    {
        this.defeatOneMonth = defeatOneMonth;
    }

    public String getDefeatThreeMonth()
    {
        return defeatThreeMonth;
    }

    public void setDefeatThreeMonth(String defeatThreeMonth)
    {
        this.defeatThreeMonth = defeatThreeMonth;
    }

    public String getDefeatAll()
    {
        return defeatAll;
    }

    public void setDefeatAll(String defeatAll)
    {
        this.defeatAll = defeatAll;
    }

}
