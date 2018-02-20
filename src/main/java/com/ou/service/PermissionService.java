package com.ou.service;

import com.ou.bean.Permission;
import com.ou.permission.PermissionEnum;

/**
 * @author: kpkym
 * date: 2018/2/16
 * time: 13:10
 */
public interface PermissionService {
    /**
     * @param permissionEnum 需要的权限
     * @return 如果有此权限则返回  否则返回空
     */
    Permission getPermission(PermissionEnum permissionEnum);
}
