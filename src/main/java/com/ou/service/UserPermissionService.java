package com.ou.service;

import com.ou.permission.PermissionEnum;

/**
 * @author: kpkym
 * date: 2018/2/15
 * time: 16:03
 */
public interface UserPermissionService {

    /**
     * 根据principal插入权限
     * @param permission 要插入的权限
     * @return 返回数据库影响行数
     */
    int insertUserPermission(PermissionEnum permission);
}
