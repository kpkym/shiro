package com.ou.service.impl;

import com.ou.bean.Permission;
import com.ou.bean.PermissionExample;
import com.ou.dao.PermissionMapper;
import com.ou.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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