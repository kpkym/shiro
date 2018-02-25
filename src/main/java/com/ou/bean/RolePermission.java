package com.ou.bean;

public class RolePermission {
    private Integer rpid;

    private Integer rid;

    private Integer pid;

    public Integer getRpid() {
        return rpid;
    }

    public void setRpid(Integer rpid) {
        this.rpid = rpid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", rpid=").append(rpid);
        sb.append(", rid=").append(rid);
        sb.append(", pid=").append(pid);
        sb.append("]");
        return sb.toString();
    }
}