package com.yosoro.ssm.domain;

import java.io.Serializable;
import java.util.List;

public class Role implements Serializable{

    private static final long serialVersionUID = -3982151927715078519L;

    private String id;
    private String roleName;
    private String roleDesc;
    private List<Permission> permissions;
    private List<UserInfo> UserInfo;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<com.yosoro.ssm.domain.UserInfo> getUserInfo() {
        return UserInfo;
    }

    public void setUserInfo(List<com.yosoro.ssm.domain.UserInfo> userInfo) {
        UserInfo = userInfo;
    }
}
