package com.ou.service.impl;

import com.ou.bean.Permission;
import com.ou.bean.PermissionExample;
import com.ou.dao.PermissionMapper;
import com.ou.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author: kpkym
 * date: 2018/2/16
 * time: 13:12
 */
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public Permission getPermission(String permission) {
        PermissionExample example = new PermissionExample();
        PermissionExample.Criteria criteria = example.createCriteria();
        criteria.andPermissionEqualTo(permission);
        List<Permission> permissions = permissionMapper.selectByExample(example);

        return permissions.size()==0 ? null : permissions.get(0);
    }
}