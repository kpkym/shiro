package com.ou.service.impl;

import com.ou.bean.User;
import com.ou.dao.UserPermissionMapper;
import com.ou.service.UserPermissionService;
import com.ou.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: kpkym
 * date: 2018/2/15
 * time: 16:06
 */
public class UserPermissionServiceImpl implements UserPermissionService {

    @Autowired
    UserPermissionMapper userPermissionMapper;
    @Autowired
    UserService userService;

    @Override
    public int insertUserPermission(String permission) {
        Subject subject = SecurityUtils.getSubject();
        String principal = (String)subject.getPrincipal();
        User user = userService.getUserByUsername(principal);
        Integer uid = user.getUid();
        // TODO 得到permission后插入userpermission

        return 0;
    }
}
