package com.ou.shiro.service;

import com.ou.shiro.bean.User;
import com.ou.shiro.exception.IllegalStringException;
import com.ou.shiro.exception.HasUserException;

public interface UserService {
    /**
     * @param username 用户名
     * @param password 用户密码
     * @return 数据库响应行数
     * @throws IllegalStringException 非法字符串
     * @throws HasUserException 存在当前用户
     */
    int register(String username, String password) throws IllegalStringException, HasUserException;

    /**
     * @param username 需要查找的用户名
     * @return 返回相应的用户名  如果找不到则返回空
     */
    User getUserByUsername(String username);

    /**
     * @return 通过shiro获取主键得到对象
     */
    User getUserByShiro();
}
