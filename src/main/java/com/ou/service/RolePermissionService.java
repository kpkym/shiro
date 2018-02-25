package com.ou.service;

import com.ou.bean.Permission;
import com.ou.bean.Role;

import java.util.List;


/**
 * @author: kpkym
 * date: 2018/2/15
 * time: 16:03
 */
public interface RolePermissionService {
    /**
     * @return 返回该用户角色所拥有的权限列表
     */
    List<Permission> listPermission(List<Role> roles);
}
