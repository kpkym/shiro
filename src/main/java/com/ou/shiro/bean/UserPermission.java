package com.ou.shiro.bean;

import java.util.Date;

public class UserPermission {
    private Integer upid;

    private Integer uid;

    private Integer pid;

    private Date expireTime;

    public Integer getUpid() {
        return upid;
    }

    public void setUpid(Integer upid) {
        this.upid = upid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
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
        sb.append(", upid=").append(upid);
        sb.append(", uid=").append(uid);
        sb.append(", pid=").append(pid);
        sb.append(", expireTime=").append(expireTime);
        sb.append("]");
        return sb.toString();
    }
}