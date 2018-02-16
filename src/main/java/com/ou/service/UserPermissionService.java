package com.ou.service;

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
    int insertUserPermission(String permission);
}
