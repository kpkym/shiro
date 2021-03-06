package com.ou.shiro.service.impl;

import com.ou.shiro.bean.Role;
import com.ou.shiro.bean.User;
import com.ou.shiro.bean.UserRole;
import com.ou.shiro.bean.UserRoleExample;
import com.ou.shiro.dao.UserRoleMapper;
import com.ou.shiro.permission.RoleEnum;
import com.ou.shiro.service.RoleService;
import com.ou.shiro.service.UserRoleService;
import com.ou.shiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    /**
     * 每次开通增加权限的秒数
     */
    @Value("${permission.time}")
    Integer increTime;

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
        // 如果没有此权限就插入    否则更新过期时间
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
        calendar.add(Calendar.SECOND, increTime);
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

    private void insertUserRole(Integer uid, Integer rid) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, increTime);
        Date expireTime = calendar.getTime();

        UserRole userRole = new UserRole();
        userRole.setUid(uid);
        userRole.setRid(rid);
        userRole.setExpireTime(expireTime);
        userRoleMapper.insertSelective(userRole);
    }
}