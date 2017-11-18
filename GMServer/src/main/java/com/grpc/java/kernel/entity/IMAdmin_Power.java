package com.grpc.java.kernel.entity;

public class IMAdmin_Power {
    private Integer userPowerId;

    private Integer userId;

    private Integer powerId;

    private Byte status=0;

    private String createTime="";

    private String updateTime="";

    public IMAdmin_Power(){

    }

    public IMAdmin_Power(Integer userPowerId, Integer userId, Integer powerId, Byte status, String createTime, String updateTime) {
        this.userPowerId = userPowerId;
        this.userId = userId;
        this.powerId = powerId;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getUserPowerId() {
        return userPowerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getPowerId() {
        return powerId;
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

    public void setUserPowerId(Integer userPowerId) {
        this.userPowerId = userPowerId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setPowerId(Integer powerId) {
        this.powerId = powerId;
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