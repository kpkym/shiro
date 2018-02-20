package com.ou.service.impl;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.ou.bean.*;
import com.ou.dao.PermissionMapper;
import com.ou.dao.RoleMapper;
import com.ou.dao.UserPermissionMapper;
import com.ou.dao.UserRoleMapper;
import com.ou.permission.RoleEnum;
import com.ou.service.PermissionService;
import com.ou.service.RoleService;
import com.ou.service.UserRoleService;
import com.ou.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author: kpkym
 * date: 2018/2/20
 * time: 11:07
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    RoleMapper roleMapper;

    @Override
    public int insertUserRole(RoleEnum roleEnum) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        Role role = roleService.getRole(roleEnum);

        Integer uid = user.getUid();
        Integer rid = role.getRid();
        Calendar calendar = Calendar.getInstance();
        // 过期时间在当前时间上增加100秒
        calendar.add(Calendar.SECOND, 100);
        Date expireTime = calendar.getTime();

        UserRole userRole = new UserRole();
        userRole.setUid(uid);
        userRole.setRid(rid);
        userRole.setExpireTime(expireTime);
        return userRoleMapper.insertSelective(userRole);
    }

    @Override
    public List<Role> listRole() {
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();

        UserRoleExample example = new UserRoleExample();
        UserRoleExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(user.getUid());
        List<UserRole> userRoles = userRoleMapper.selectByExample(example);

        // 得到Role
        List<Role> result = new ArrayList<>(userRoles.size());
        for (UserRole ur : userRoles) {
            // 如果该角色没有过期 则加入角色列表
            if (ur.getExpireTime().compareTo(new Date()) > 0) {
                Role userRole = roleMapper.selectByPrimaryKey(ur.getRid());
                result.add(userRole);
            }
        }
        return result;
    }
}