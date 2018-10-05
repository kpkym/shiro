package com.ou.shiro.controller;

import com.ou.shiro.bean.User;
import com.ou.shiro.exception.HasUserException;
import com.ou.shiro.exception.IllegalStringException;
import com.ou.shiro.permission.RoleEnum;
import com.ou.shiro.service.UserRoleService;
import com.ou.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * @author: kpkym
 * date: 2018/2/23
 * time: 10:17
 */
@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserRoleService userRoleService;

    @RequestMapping("login")
    public String login(Model model, HttpSession session, String captcha, @RequestParam(defaultValue = "false") Boolean rememberMe, @Valid User user, BindingResult result) throws IllegalStringException {
        // 错误提示消息
        if (result.hasErrors()) {
            throw new IllegalStringException("非法输入");
        }
        // 检查验证码
        if (!session.getAttribute("captcha").toString().equals(captcha)) {
            model.addAttribute("msg", "验证码错误");
            return "error/error";
        }

        String msg = null;
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        token.setRememberMe(rememberMe);

        try {
            currentUser.login(token);
            return "redirect:/";
        } catch (UnknownAccountException uae) {
            msg = "用户名错误";
        } catch (IncorrectCredentialsException ice) {
            msg = "密码错误";
        } catch (AuthenticationException ae) {
            msg = "未知错误";
        }
        model.addAttribute("msg", msg);
        return "error/error";
    }


    @RequestMapping(value = "register")
    public String register(Model model, HttpSession session, String captcha, @Valid User user, BindingResult result) throws IllegalStringException, HasUserException {
        if (result.hasErrors()) {
            throw new IllegalStringException("非法输入");
        }
        // 检查验证码
        if (StringUtils.isEmpty(captcha) || !captcha.equals(session.getAttribute("captcha").toString())) {
            model.addAttribute("msg", "验证码错误");
            return "error/error";
        }
        userService.register(user);
        return "/login.jsp";
    }

    @RequestMapping(value = "renewal")
    public String renewal(RoleEnum role) {
        userRoleService.renewal(role);
        return "permission";
    }

    @RequestMapping("users")
    public String list(Model model) {
        List<User> users = userService.listUser();
        model.addAttribute("users", users);
        return "users";
    }
}