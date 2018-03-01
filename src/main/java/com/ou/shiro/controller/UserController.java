package com.ou.shiro.controller;

import com.ou.shiro.bean.User;
import com.ou.shiro.exception.HasUserException;
import com.ou.shiro.exception.IllegalStringException;
import com.ou.shiro.service.UserService;
import org.apache.shiro.authc.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author: kpkym
 * date: 2018/2/23
 * time: 10:17
 */
@Controller
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("login")
    public void login(HttpServletRequest request) {
        // 错误提示消息
        String msg = null;
        String errorClassName = (String) request.getAttribute("shiroLoginFailure");
        // DisabledAccountException （禁用的帐号）
        // LockedAccountException （锁定的帐号）
        // UnknownAccountException（错误的帐号）
        // ExcessiveAttemptsException（登录失败次数过多）
        // IncorrectCredentialsException （错误的凭证）
        // ExpiredCredentialsException （过期的凭证）

        if (UnknownAccountException.class.getName().equals(errorClassName)) {
            msg = "用户名错误";
        } else if (IncorrectCredentialsException.class.getName().equals(
                errorClassName)) {
            msg = "密码错误";
        } else if (errorClassName != null) {
            msg = "未知错误：" + errorClassName;
        }
        request.setAttribute("msg", msg);
    }

    @RequestMapping(value = "register")
    public String register(@Valid User user, BindingResult result) throws IllegalStringException, HasUserException {
        if (result.hasErrors()) {
            throw new IllegalStringException("非法输入");
        }
        userService.register(user);
        return "login";
    }
}