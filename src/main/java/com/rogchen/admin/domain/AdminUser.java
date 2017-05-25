package com.rogchen.admin.domain; /**
 * Copyright (c) 2016
 * Created with AdminUser.java
 */


import com.rogchen.common.BaseEntity;

import java.util.Date;


/**
 *  管理员 实体
 *
 * @author yucx on  16-3-19
 */
public class AdminUser extends BaseEntity<AdminUser> {
    private static final long serialVersionUID = 1L;

    private Long adminUserId;
    private String userName;
    private int adminUserStatusCd;    // 100：禁用  101:启用  -1：删除
    private String realName;
    private String userImageUrl;
    private Date createTime;
    private Date lastLoginTime;
    private String lastLoginIp;
    private String password;
    private String roleIds;


    // 不映射的
    private String unencodedPassword;   // 未加密的密码
    private String roles;   // 关联的角色

    public Long getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(Long adminUserId) {
        this.adminUserId = adminUserId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUnencodedPassword() {
        return unencodedPassword;
    }

    public void setUnencodedPassword(String unencodedPassword) {
        this.unencodedPassword = unencodedPassword;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public int getAdminUserStatusCd() {
        return adminUserStatusCd;
    }

    public void setAdminUserStatusCd(int adminUserStatusCd) {
        this.adminUserStatusCd = adminUserStatusCd;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }
}
