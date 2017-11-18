package com.grpc.java.kernel.entity;

public class IMPower {
    private Integer powerId;

    private String powerName="";

    private String power="";

    private Byte status=0;

    private String createTime="";

    private String updateTime="";

    public IMPower(){

    }

    public IMPower(Integer powerId, String powerName, String power, Byte status, String createTime, String updateTime) {
        this.powerId = powerId;
        this.powerName = powerName;
        this.power = power;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getPowerId() {
        return powerId;
    }

    public String getPowerName() {
        return powerName;
    }

    public String getPower() {
        return power;
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

    public void setPowerId(Integer powerId) {
        this.powerId = powerId;
    }

    public void setPowerName(String powerName) {
        this.powerName = powerName;
    }

    public void setPower(String power) {
        this.power = power;
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