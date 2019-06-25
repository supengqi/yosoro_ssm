package com.yosoro.ssm.service;

import com.yosoro.ssm.domain.Role;
import com.yosoro.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface IUserService {

    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    public List<UserInfo> findAll(int page, int size) throws Exception;

    /**
     * 新增用户
     * @param userInfo
     * @throws Exception
     */
    void save(UserInfo userInfo) throws Exception;

    /**
     * 更改用户
     * @param userInfo
     * @throws Exception
     */
    void update(UserInfo userInfo) throws Exception;

    /**
     * 根据ID查找详情
     * @param id
     * @return
     * @throws Exception
     */
    UserInfo findById(String id) throws Exception;

    // 用于查找可以添加的角色
    public void addRoleToUser(String userId, String[] roleId) throws Exception;

    // 查询用户以及用户可以添加的角色
    public List<Role> findOtherRole(String id);

    public void deleteUsers(String id) throws Exception;
}
