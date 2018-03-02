package com.ou.shiro.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: kpkym
 * date: 2018/2/26
 * time: 9:34
 */
@Controller
public class PermissionController {
    @RequiresPermissions("yellow:read")
    @RequestMapping("yellow")
    public String yellow() {
        return success();
    }

    @RequiresPermissions("green:read")
    @RequestMapping("green")
    public String green() {
        return success();
    }

    @RequiresPermissions("red:read")
    @RequestMapping("red")
    public String red() {
        return success();
    }

    @RequiresPermissions("blue:read")
    @RequestMapping("blue")
    public String blue() {
        return success();
    }

    @RequiresPermissions("black:read")
    @RequestMapping("black")
    public String black() {
        return success();
    }

    public String success() {
        return "permission";
    }
}