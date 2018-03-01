package com.ou.shiro.reaml;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: kpkym
 * date: 2018/2/23
 * time: 11:17
 */
public class MyFormAuthenticationFilter extends FormAuthenticationFilter {
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");

        // 用户名密码校验
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            httpServletRequest.setAttribute("shiroLoginFailure", "用户名或密码不能为空");
            //拒绝访问，不再校验账号和密码
            return true;
        }

        return super.onAccessDenied(request, response);
    }
}