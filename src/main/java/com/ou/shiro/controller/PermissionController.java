package com.ou.shiro.controller;

import com.ou.shiro.bean.Msg;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: kpkym
 * date: 2018/2/26
 * time: 9:34
 */
@Controller
public class PermissionController {
    @RequiresPermissions("yellow:read")
    @ResponseBody
    @RequestMapping("yellow")
    public Msg yellow() {
        return success();
    }

    @RequiresPermissions("green:read")
    @ResponseBody
    @RequestMapping("green")
    public Msg green() {
        return success();
    }

    @RequiresPermissions("red:read")
    @ResponseBody
    @RequestMapping("red")
    public Msg red() {
        return success();
    }

    @RequiresPermissions("blue:read")
    @ResponseBody
    @RequestMapping("blue")
    public Msg blue() {
        return success();
    }

    @RequiresPermissions("black:read")
    @ResponseBody
    @RequestMapping("black")
    public Msg black() {
        return success();
    }

    public Msg success() {
        Msg msg = new Msg(0);
        msg.add("msg", "OK");
        return msg;
    }
}