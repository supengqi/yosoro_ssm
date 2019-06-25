package com.yosoro.ssm.dao;

import com.yosoro.ssm.domain.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionDao {

    @Select("select * from permission where id in " +
            "(select permissionId from role_permission where roleId = #{id})")
    public List<Permission> findPermissionByRoleId(String roleId) throws Exception;

    @Select("select * from permission")
    public List<Permission> findAll() throws Exception;

    @Select("select * from permission where id = #{id}")
    public Permission findById(String id) throws Exception;

    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    public void save(Permission permission) throws Exception;

    @Delete("delete from role_permission where permissionId = #{id}")
    public void deleteFromRole_Permission(String id) throws Exception;

    @Delete("delete from permission where id = #{id}")
    public void deleteById(String id) throws Exception;


}
