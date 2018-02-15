package com.ou.shiro;

import com.ou.bean.User;
import com.ou.service.UserService;
import com.ou.util.StringUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    @Override
    public String getName() {
        return "myRealm";
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        // 没有输入用户名 或则空用户名则返回空
        if (StringUtil.checkNullOrEmpty(username)) {
            return null;
        }
        User user = userService.getUserByUsername(username);
        // 没有此用户名则返回空
        if (null == user) {
            return null;
        }
        String password = user.getPassword();
        String salt = username;
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username,
                password, ByteSource.Util.bytes(salt), getName());
        return authenticationInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }
}
