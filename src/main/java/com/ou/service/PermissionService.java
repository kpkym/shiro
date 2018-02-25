package com.ou.service;

import com.ou.bean.Permission;

/**
 * @author: kpkym
 * date: 2018/2/16
 * time: 13:10
 */
public interface PermissionService {
    /**
     * @param pid
     * @return 此权限
     */
    Permission getPermissionByPrimaryKey(Integer pid);
}
