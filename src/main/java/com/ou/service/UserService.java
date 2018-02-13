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

    /**
     * @param username 需要查找的用户名
     * @return 返回相应的用户名  如果找不到则返回空
     */
    User getUserByUsername(String username);
}
