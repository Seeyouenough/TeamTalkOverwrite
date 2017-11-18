package com.grpc.java.kernel.entity;

public class IMAdmin_Role {
    private Integer userRoleId;

    private Integer userId;

    private Integer roleId;

    private Byte status=0;

    private String createTime="";

    private String updateTime="";

    public IMAdmin_Role(){

    }

    public IMAdmin_Role(Integer userRoleId, Integer userId, Integer roleId, Byte status, String createTime, String updateTime) {
        this.userRoleId = userRoleId;
        this.userId = userId;
        this.roleId = roleId;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getUserRoleId() {
        return userRoleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public Byte getStatus() {
        return status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}