package com.grpc.java.kernel.entity;

public class IMRole {
    private Integer roleId;

    private String roleName="";

    private String rolePower="";

    private String rolePowerName="";

    private Byte status=0;

    private String createTime="";

    private String updateTime="";

    public IMRole(Integer roleId, String roleName, String rolePower, String rolePowerName, Byte status, String createTime, String updateTime) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.rolePower = rolePower;
        this.rolePowerName = rolePowerName;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getRolePower() {
        return rolePower;
    }

    public String getRolePowerName() {
        return rolePowerName;
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

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setRolePower(String rolePower) {
        this.rolePower = rolePower;
    }

    public void setRolePowerName(String rolePowerName) {
        this.rolePowerName = rolePowerName;
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