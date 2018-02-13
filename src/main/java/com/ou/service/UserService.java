package com.ou.service;

import com.ou.bean.User;

import java.util.List;

public interface UserService {

    /**
     * @param username 用户名
     * @param password 用户密码
     * @return 数据库响应行数
     */
    int register(String username, String password);
}
