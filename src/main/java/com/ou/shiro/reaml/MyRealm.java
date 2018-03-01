package com.ou.shiro.reaml;

import com.ou.shiro.bean.Permission;
import com.ou.shiro.bean.Role;
import com.ou.shiro.bean.User;
import com.ou.shiro.service.RolePermissionService;
import com.ou.shiro.service.UserRoleService;
import com.ou.shiro.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MyRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    RolePermissionService rolePermissionService;

    @Override
    public String getName() {
        return "myRealm";
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        User user = userService.getUserByUsername(username);
        // 没有此用户名则返回空
        if (null == user) {
            return null;
        }
        String password = user.getPassword();
        String salt = username;
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user,
                password, ByteSource.Util.bytes(salt), getName());
        return authenticationInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        List<Role> roles = userRoleService.listRole();
        List<Permission> permissions = rolePermissionService.listPermission(roles);
        for (Role role : roles) {
            authorizationInfo.addRole(role.getRole());
        }
        for (Permission permission : permissions) {
            authorizationInfo.addStringPermission(permission.getPermission());
        }

        return authorizationInfo;
    }
}
