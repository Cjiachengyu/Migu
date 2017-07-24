package cn.eclassmate.qzy.service;

import cn.eclassmate.qzy.global.Utl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class QuartzWeekService extends AbstractService
{
    private static final Logger logger = LogManager.getLogger();

    public void execute()
    {
        logger.info("calling QuartzWeekService execute()");

        int beginTime = Utl.getBeginTimeOfThisWeek();
        bagMapper.updateWeekAnswerCountForQuartz(beginTime);
        bagMapper.updateWeekMoneyForQuartz(beginTime);
        bagMapper.updateWeekUserSpentMoneyForQuartz(beginTime);
    }
}
