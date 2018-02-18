package com.ou.service;

import com.ou.bean.Role;
import com.ou.permission.RoleEnum;

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
}
