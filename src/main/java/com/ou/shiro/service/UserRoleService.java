package com.ou.shiro.service;

import com.ou.shiro.bean.Role;
import com.ou.shiro.permission.RoleEnum;

import java.util.List;

/**
 * @author: kpkym
 * date: 2018/2/20
 * time: 11:04
 */
public interface UserRoleService {
    /**
     * @param roleEnum 需要续费的角色
     */
    void renewal(RoleEnum roleEnum);

    /**
     * @return 返回该用户所拥有的角色列表
     */
    List<Role> listRole();
}
