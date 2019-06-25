package com.yosoro.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.yosoro.ssm.dao.ISysLogDao;
import com.yosoro.ssm.domain.SysLog;
import com.yosoro.ssm.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysLogServiceImpl implements ISysLogService {

    @Autowired
    private ISysLogDao sysLogDao;

    @Override
    public void save(SysLog log) throws Exception {
        sysLogDao.save(log);
    }

    @Override
    public List<SysLog> findAll(Integer page, Integer size) throws Exception {
        PageHelper.startPage(page,size);
        return sysLogDao.findAll();
    }

    @Override
    public void truncateAllData() throws Exception {
        sysLogDao.truncateAllData();
    }
}
