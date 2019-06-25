package com.yosoro.ssm.service;

import com.yosoro.ssm.domain.Permission;
import com.yosoro.ssm.domain.Role;

import java.util.List;

public interface IRoleService {

    public List<Role> findAll() throws Exception;

    public void save(Role role) throws Exception;

    public Role findById(String id) throws Exception;

    public void addPermissionToRole(String roleId,String[] PermissionId);

    public List<Permission> findOtherPermission(String roleId);

    public void deleteRoleById(String roleId) throws Exception;
}
