package cn.eclassmate.qzy.service;

import cn.eclassmate.qzy.domain.RequestRecord;

import org.springframework.stereotype.Service;

@Service
public class SystemService extends AbstractService
{
    private static final long serialVersionUID = 3503236077606757111L;

    public int insertRequestRecord(RequestRecord requestRecord)
    {
        return systemMapper.insertRequestRecord(requestRecord);
    }

}
