package com.ou.shiro.service.impl;

import com.ou.shiro.bean.User;
import com.ou.shiro.bean.UserExample;
import com.ou.shiro.dao.UserMapper;
import com.ou.shiro.exception.HasUserException;
import com.ou.shiro.exception.IllegalStringException;
import com.ou.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public int register(String username, String password) throws IllegalStringException, HasUserException {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new IllegalStringException("不合法的用户名或密码");
        }
        if (getUserByUsername(username) != null) {
            throw new HasUserException("已存在当前用户名");
        }

        String md5hash = new Md5Hash(password, username, 2).toString();
        User user = new User();
        user.setUsername(username);
        user.setPassword(md5hash);
        return userMapper.insertSelective(user);
    }

    @Override
    public User getUserByUsername(String username) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(userExample);
        return users.size() == 0 ? null : users.get(0);
    }

    @Override
    public User getUserByShiro() {
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();

        return user;
    }
}
