package com.yosoro.ssm.service.impl;


import com.github.pagehelper.PageHelper;
import com.yosoro.ssm.dao.IUserDao;
import com.yosoro.ssm.domain.Role;
import com.yosoro.ssm.domain.UserInfo;
import com.yosoro.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service("iuserService")
@Transactional
public class IUserServiceImpl implements IUserService{

    @Autowired
    private IUserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserInfo> findAll(int page, int size) throws Exception {
        //参数pageNum 是页码值   参数pageSize 代表是每页显示条数
        PageHelper.startPage(page,size);
        return userDao.findAll();
    }

    @Override
    public void save(UserInfo user) throws Exception {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }

    @Override
    public void update(UserInfo userInfo) throws Exception {
        userDao.update(userInfo);
    }

    @Override
    public UserInfo findById(String id) throws Exception {
        return userDao.findById(id);
    }

    @Override
    public void addRoleToUser(String userId, String[] roleIds) throws Exception {

        for (String roleId : roleIds) {
            userDao.addRoleToUser(userId,roleId);
        }
    }

    @Override
    public List<Role> findOtherRole(String id) {
        return userDao.findOtherRole(id);
    }

    @Override
    public void deleteUsers(String id) throws Exception {
        userDao.deleteFromUsers_Role(id);
        userDao.delete(id);



    }


}
