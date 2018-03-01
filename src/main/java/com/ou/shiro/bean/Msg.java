package com.ou.shiro.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: kpkym
 * date: 2018/2/26
 * time: 9:35
 */
public class Msg {
    /**
     * code:0 成功  1:失败
     */
    private int code;
    private Map<String, Object> content;

    public Msg() {
    }

    /**
     * @param code 0:成功  1:失败
     */
    public Msg(int code) {
        this.code = code;
        content = new HashMap<>();
    }

    /**
     * 在content中添加键值对
     *
     * @param key 键
     * @param value 值
     * @return 自身
     */
    public Msg add(String key, Object value) {
        this.getContent().put(key, value);
        return this;
    }

    public Map<String, Object> getContent() {
        return content;
    }

    public void setContent(Map<String, Object> content) {
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