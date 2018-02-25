package com.ou.service.impl;

import com.ou.bean.Permission;
import com.ou.bean.Role;
import com.ou.bean.RolePermission;
import com.ou.bean.RolePermissionExample;
import com.ou.dao.PermissionMapper;
import com.ou.dao.RolePermissionMapper;
import com.ou.service.PermissionService;
import com.ou.service.RolePermissionService;
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
        RolePermissionExample example = new RolePermissionExample();
        // 查询出每个角色所拥有的权限
        for (Role role : roles) {
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
