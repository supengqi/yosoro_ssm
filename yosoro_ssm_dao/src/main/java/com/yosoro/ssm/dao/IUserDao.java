package com.yosoro.ssm.dao;

import com.yosoro.ssm.domain.Role;
import com.yosoro.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserDao {

    /**
     * 根据姓名查user
     * @param username
     * @return
     * @throws Exception
     */
    @Select("select * from users where username = #{username}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class, many = @Many(
                    select = "com.yosoro.ssm.dao.IRoleDao.findRoleByUserId"
            )),
    })
    public UserInfo findByUsername(String username) throws Exception;

    @Select("select * from users")
    public List<UserInfo> findAll() throws Exception;

    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    public void save(UserInfo user) throws Exception;

    /**
     * 修改用户
     * @param userInfo
     */
    @Update("update users set email = #{email}, username = #{username} ,phoneNum = #{phoneNum},status = #{status} where id = #{id}")
    void update(UserInfo userInfo);

    /**
     * 根据ID查询user
     * @param id
     * @return
     * @throws Exception
     */
    @Select("select * from users where id = #{id}")
    @Results({
            @Result(id = true,property = "id", column = "id"),
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class,
            many = @Many(select = "com.yosoro.ssm.dao.IRoleDao.findRoleByUserId"))
    })
    public UserInfo findById(String id) throws Exception;

    // 用于查找可以添加的角色
    @Insert("insert into users_role (userId, roleId) values(#{userId}, #{roleId})")
    public void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId) throws Exception;

    // 查询用户以及用户可以添加的角色
    @Select("select * from role where id not in (select roleId from users_role where userId = #{id})")
    public List<Role> findOtherRole(String id);

    @Delete("delete from users where id = #{id}")
    public void delete(String id) throws Exception;

    @Delete("delete from USERS_ROLE where userId = #{userId}")
    public void deleteFromUsers_Role(String id) throws Exception;


}
