package com.ou.shiro.service;

import com.ou.shiro.bean.User;
import com.ou.shiro.exception.HasUserException;

public interface UserService {
    /**
     * @param user
     * @return
     * @return 数据库响应行数
     * @throws HasUserException 存在当前用户
     */
    int register(User user) throws HasUserException;

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
