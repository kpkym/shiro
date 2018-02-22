package com.ou.service.impl;

import com.ou.bean.Permission;
import com.ou.bean.User;
import com.ou.bean.UserPermission;
import com.ou.bean.UserPermissionExample;
import com.ou.dao.PermissionMapper;
import com.ou.dao.UserPermissionMapper;
import com.ou.permission.PermissionEnum;
import com.ou.service.PermissionService;
import com.ou.service.UserPermissionService;
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
 * date: 2018/2/15
 * time: 16:06
 */
@Service
public class UserPermissionServiceImpl implements UserPermissionService {
    @Autowired
    UserPermissionMapper userPermissionMapper;
    @Autowired
    PermissionMapper permissionMapper;
    @Autowired
    UserService userService;
    @Autowired
    PermissionService permissionService;

    @Override
    public void renewal(PermissionEnum permission) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        Permission perm = permissionService.getPermission(permission);
        Integer uid = user.getUid();
        Integer pid = perm.getPid();
        UserPermissionExample example = new UserPermissionExample();
        UserPermissionExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
        criteria.andPidEqualTo(pid);

        List<UserPermission> userPermissions = userPermissionMapper.selectByExample(example);
        // 如果没有此权限就插入
        // 否则更新过期时间
        if (userPermissions.size() == 0
                || userPermissions.get(0).getExpireTime().compareTo(new Date()) < 0) {
            insertUserPermission(uid, pid);
            return;
        }

        // 否则更新此数据
        UserPermission userPermission = userPermissions.get(0);

        Calendar calendar = Calendar.getInstance();
        // 设置原本过期时间
        calendar.setTime(userPermission.getExpireTime());
        // 过期时间在当前时间上增加100秒
        calendar.add(Calendar.SECOND, 100);
        Date expireTime = calendar.getTime();
        // 更新过期时间
        userPermission.setExpireTime(expireTime);
        userPermissionMapper.updateByPrimaryKey(userPermission);
    }

    @Override
    public List<Permission> listPermission() {
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();

        UserPermissionExample example = new UserPermissionExample();
        UserPermissionExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(user.getUid());
        List<UserPermission> userPermissions = userPermissionMapper.selectByExample(example);

        // 得到Permission
        List<Permission> result = new ArrayList<>(userPermissions.size());
        for (UserPermission up : userPermissions) {
            // 如果该权限没有过期 则加入权限列表
            if (up.getExpireTime().compareTo(new Date()) > 0) {
                Permission permission = permissionMapper.selectByPrimaryKey(up.getPid());
                result.add(permission);
            }
        }
        return result;
    }

    private int insertUserPermission(Integer uid, Integer pid) {
        Calendar calendar = Calendar.getInstance();
        // 过期时间在当前时间上增加100秒
        calendar.add(Calendar.SECOND, 100);
        Date expireTime = calendar.getTime();

        UserPermission userPermission = new UserPermission();
        userPermission.setUid(uid);
        userPermission.setPid(pid);
        userPermission.setExpireTime(expireTime);
        return userPermissionMapper.insertSelective(userPermission);
    }
}
