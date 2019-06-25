package com.yosoro.ssm.dao;

import com.yosoro.ssm.domain.Permission;
import com.yosoro.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {

    @Select("select * from role where id in (select roleId from users_role where userId = #{userId}) ")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(
                    select = "com.yosoro.ssm.dao.IPermissionDao.findPermissionByRoleId"))
    })
    public List<Role> findRoleByUserId(String userId) throws Exception;

    @Select("select * from role")
    public List<Role> findAll() throws Exception;

    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    public void save(Role role) throws Exception;

    @Select("select * from role where id = #{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,
            many = @Many(select = "com.yosoro.ssm.dao.IPermissionDao.findPermissionByRoleId"))
    })
    public Role findById(String id) throws Exception;

    // 用于绑定角色与权限的关系
    @Insert("insert into role_permission(roleId,permissionId) values (#{roleId},#{permissionId}) ")
    public void addPermissionToRole(@Param("roleId")  String roleId,@Param("permissionId") String permissionId);

    // 用于查找可以添加的权限
    @Select("select * from permission where id not in (select permissionId from ROLE_PERMISSION where roleId = #{roleId})")
    public List<Permission> findOtherPermission(String roleId);

    @Delete("delete from users_role where roleId=#{roleId}")
    void deleteFromUser_RoleByRoleId(String roleId);

    @Delete("delete from role_permission where roleId=#{roleId}")
    void deleteFromRole_PermissionByRoleId(String roleId);

    @Delete("delete from role where id=#{roleId}")
    void deleteRoleById(String roleId);

}
