package com.ou.shiro.service;

import com.ou.shiro.bean.Role;
import com.ou.shiro.permission.RoleEnum;

/**
 * @author: kpkym
 * date: 2018/2/18
 * time: 10:21
 */
public interface RoleService {
    /**
     * @param role 需要的角色
     * @return 如果有此角色则返回  否则返回空
     */
    Role getRole(RoleEnum role);

    /**
     * @param rid
     * @return 此角色
     */
    Role getRoleByPrimaryKey(Integer rid);
}
