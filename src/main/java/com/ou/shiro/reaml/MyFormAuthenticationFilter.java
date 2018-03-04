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
        String captcha = httpServletRequest.getParameter("captcha");

        // 用户名密码校验
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            httpServletRequest.setAttribute("shiroLoginFailure", "用户名或密码不能为空");
            //拒绝访问，不再校验账号和密码
            return true;
        }
        // 检查验证码
        if (StringUtils.isEmpty(captcha) || !captcha.equals(
                httpServletRequest.getSession().getAttribute("captcha").toString())) {
            httpServletRequest.setAttribute("shiroLoginFailure", "验证码错误");
            //拒绝访问，不再校验账号和密码
            return true;
        }
        return super.onAccessDenied(request, response);
    }
}