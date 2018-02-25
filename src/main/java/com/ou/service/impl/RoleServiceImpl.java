package com.ou.service.impl;

import com.ou.bean.Role;
import com.ou.bean.RoleExample;
import com.ou.dao.RoleMapper;
import com.ou.permission.RoleEnum;
import com.ou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: kpkym
 * date: 2018/2/18
 * time: 10:25
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;

    @Override
    public Role getRole(RoleEnum role) {
        RoleExample example = new RoleExample();
        RoleExample.Criteria criteria = example.createCriteria();
        criteria.andRoleEqualTo(role.name());
        List<Role> roles = roleMapper.selectByExample(example);
        return roles.get(0);
    }

    @Override
    public Role getRoleByPrimaryKey(Integer rid) {
        return roleMapper.selectByPrimaryKey(rid);
    }
}