package com.grpc.java.kernel.entity;

public class IMAdmin {
    private Integer id;

    private String uname="";

    private String pwd="";

    private Byte status;

    private Integer created;

    private Integer updated;

    public IMAdmin()
    {

    }

    public void setId(Integer id) {
        this.id = id;
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

    public void setCreated(Integer created) {
        this.created = created;
    }

    public void setUpdated(Integer updated) {
        this.updated = updated;
    }

    public IMAdmin(Integer id, String uname, String pwd, Byte status, Integer created, Integer updated) {
        this.id = id;
        this.uname = uname;
        this.pwd = pwd;
        this.status = status;

        this.created = created;
        this.updated = updated;
    }

    public Integer getId() {
        return id;
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

    public Integer getCreated() {
        return created;
    }

    public Integer getUpdated() {
        return updated;
    }
}