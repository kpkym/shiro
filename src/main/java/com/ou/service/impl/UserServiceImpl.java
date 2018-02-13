package com.ou.service.impl;

import com.ou.bean.User;
import com.ou.bean.UserExample;
import com.ou.dao.UserMapper;
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
    public int register(String username, String password) {
        if (StringUtil.checkNullOrEmpty(username) || StringUtil.checkNullOrEmpty(password)) {
            return 0;
        }

        String md5hash = new Md5Hash(password, username, 2).toString();
        User user = new User();
        user.setUsername(username);
        user.setPassword(md5hash);
        return userMapper.insertSelective(user);
    }
}
