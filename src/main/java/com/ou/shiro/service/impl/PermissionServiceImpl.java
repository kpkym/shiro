package com.ou.shiro.service.impl;

import com.ou.shiro.bean.Permission;
import com.ou.shiro.dao.PermissionMapper;
import com.ou.shiro.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: kpkym
 * date: 2018/2/16
 * time: 13:12
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public Permission getPermissionByPrimaryKey(Integer pid) {
        return permissionMapper.selectByPrimaryKey(pid);
    }
}