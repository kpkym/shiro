package com.ou.service.impl;

import com.ou.bean.*;
import com.ou.dao.RoleMapper;
import com.ou.dao.UserRoleMapper;
import com.ou.permission.RoleEnum;
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
    UserRoleMapper userRoleMapper;
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @Override
    public void renewal(RoleEnum roleEnum) {
        User user = userService.getUserByShiro();
        Role role = roleService.getRole(roleEnum);
        Integer uid = user.getUid();
        Integer rid = role.getRid();

        UserRoleExample example = new UserRoleExample();
        UserRoleExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
        criteria.andRidEqualTo(rid);

        List<UserRole> userRoles = userRoleMapper.selectByExample(example);
        // 如果没有此权限就插入
        // 否则更新过期时间
        if (userRoles.size() == 0
                || userRoles.get(0).getExpireTime().compareTo(new Date()) < 0) {
            insertUserRole(uid, rid);
            return;
        }

        // 否则更新此数据
        UserRole userRole = userRoles.get(0);

        Calendar calendar = Calendar.getInstance();
        // 设置原本过期时间
        calendar.setTime(userRole.getExpireTime());
        // 过期时间在当前时间上增加100秒
        calendar.add(Calendar.SECOND, 100);
        Date expireTime = calendar.getTime();
        // 更新过期时间
        userRole.setExpireTime(expireTime);
        userRoleMapper.updateByPrimaryKey(userRole);
    }

    @Override
    public List<Role> listRole() {
        User user = userService.getUserByShiro();
        UserRoleExample example = new UserRoleExample();
        UserRoleExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(user.getUid());
        List<UserRole> userRoles = userRoleMapper.selectByExample(example);

        // 得到Role
        List<Role> result = new ArrayList<>(userRoles.size());
        for (UserRole ur : userRoles) {
            // 如果该角色没有过期 则加入角色列表
            if (ur.getExpireTime().compareTo(new Date()) > 0) {
                Role userRole = roleService.getRoleByPrimaryKey(ur.getRid());
                result.add(userRole);
            }
        }
        return result;
    }

    private int insertUserRole(Integer uid, Integer rid) {
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
}