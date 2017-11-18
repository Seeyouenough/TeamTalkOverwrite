package com.grpc.java.kernel.entity;

public class IMAdmin {
    private Integer userId;

    private String uname="";

    private String pwd=null;

    private Byte status=0;

    private Integer roleId=0;

    private String power=" ";

    private String createTime="";

    private String updateTime="";

    public IMAdmin(){

    }

    public IMAdmin(Integer userId, String uname, String pwd, Byte status, Integer roleId, String power, String createTime, String updateTime) {
        this.userId = userId;
        this.uname = uname;
        this.pwd = pwd;
        this.status = status;
        this.roleId = roleId;
        this.power = power;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUname() {
        return uname;
    }

    public String getPwd() {
        return pwd;
    }

    public Byte getStatus() {
        return status;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public String getPower() {
        return power;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}