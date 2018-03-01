package com.ou.shiro.bean;

import java.util.Date;

public class UserRole {
    private Integer urid;

    private Integer uid;

    private Integer rid;

    private Date expireTime;

    public Integer getUrid() {
        return urid;
    }

    public void setUrid(Integer urid) {
        this.urid = urid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", urid=").append(urid);
        sb.append(", uid=").append(uid);
        sb.append(", rid=").append(rid);
        sb.append(", expireTime=").append(expireTime);
        sb.append("]");
        return sb.toString();
    }
}