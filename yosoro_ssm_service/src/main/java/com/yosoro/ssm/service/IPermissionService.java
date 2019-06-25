package com.yosoro.ssm.service;

import com.yosoro.ssm.domain.Permission;

import java.util.List;

public interface IPermissionService {

    public List<Permission> findAll() throws Exception;

    public Permission findById(String id) throws Exception;

    public void save(Permission permission) throws Exception;

    public void deleteById(String id) throws Exception;
}
