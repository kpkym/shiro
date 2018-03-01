package com.ou.shiro.service.impl;

import com.ou.shiro.bean.Permission;
import com.ou.shiro.bean.Role;
import com.ou.shiro.bean.RolePermission;
import com.ou.shiro.bean.RolePermissionExample;
import com.ou.shiro.dao.RolePermissionMapper;
import com.ou.shiro.service.PermissionService;
import com.ou.shiro.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author: kpkym
 * date: 2018/2/15
 * time: 16:06
 */
@Service
public class RolePermissionServiceImpl implements RolePermissionService {
    @Autowired
    RolePermissionMapper rolePermissionMapper;
    @Autowired
    PermissionService permissionService;

    @Override
    public List<Permission> listPermission(List<Role> roles) {
        List<Permission> result = new LinkedList<>();
        // 查询出每个角色所拥有的权限
        for (Role role : roles) {
            RolePermissionExample example = new RolePermissionExample();
            RolePermissionExample.Criteria criteria = example.createCriteria();
            criteria.andRidEqualTo(role.getRid());
            List<RolePermission> rolePermissions = rolePermissionMapper.selectByExample(example);
            // 查询出权限并添加到result
            for (RolePermission rolePermission : rolePermissions) {
                Permission permission = permissionService.getPermissionByPrimaryKey(rolePermission.getPid());
                result.add(permission);
            }
        }

        return result;
    }
}
