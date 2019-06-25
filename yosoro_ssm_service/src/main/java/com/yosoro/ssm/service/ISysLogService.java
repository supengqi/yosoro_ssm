package com.yosoro.ssm.service;

import com.yosoro.ssm.domain.SysLog;

import java.util.List;

public interface ISysLogService {

    public void save(SysLog log) throws Exception;

    public List<SysLog> findAll(Integer page, Integer size) throws Exception;

    public void truncateAllData() throws Exception;
}
