package com.ou.service;

import com.ou.bean.Permission;
import com.ou.bean.Role;
import com.ou.permission.PermissionEnum;
import com.ou.permission.RoleEnum;

import java.util.List;

/**
 * @author: kpkym
 * date: 2018/2/20
 * time: 11:04
 */
public interface UserRoleService {
    /**
     * 根据principal插入权限
     * @param roleEnum 要插入的角色
     * @return 返回数据库影响行数
     */
    int insertUserRole(RoleEnum roleEnum);


    /**
     * @return 返回该用户所拥有的角色列表
     */
    List<Role> listRole();
}
