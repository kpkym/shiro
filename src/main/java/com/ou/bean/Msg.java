package com.ou.bean;

import java.util.Map;

/**
 * @author: kpkym
 * date: 2018/2/26
 * time: 9:35
 */
public class Msg {
    private int code;
    private Map<String, Object> content;

    public Msg() {
    }

    public Msg(int code, Map<String, Object> content) {
        this.code = code;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "code=" + code +
                ", content=" + content +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}