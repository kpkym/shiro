package com.ou.service.impl;

import com.ou.bean.User;
import com.ou.bean.UserExample;
import com.ou.dao.UserMapper;
import com.ou.exception.IllegalStringException;
import com.ou.exception.HasUserException;
import com.ou.service.UserService;
import com.ou.util.StringUtil;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public int register(String username, String password) throws IllegalStringException, HasUserException {
        if (StringUtil.checkNullOrEmpty(username) || StringUtil.checkNullOrEmpty(password)) {
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
}
