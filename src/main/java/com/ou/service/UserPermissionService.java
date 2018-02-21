package com.ou.service;

import com.ou.bean.Permission;
import com.ou.permission.PermissionEnum;

import java.util.List;


/**
 * @author: kpkym
 * date: 2018/2/15
 * time: 16:03
 */
public interface UserPermissionService {
    /**
     * @param permission 需要续费的权限
     */
    void renewal(PermissionEnum permission);

    /**
     * @return 返回该用户所拥有的权限列表
     */
    List<Permission> listPermission();
}
