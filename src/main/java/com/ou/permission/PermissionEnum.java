package com.ou.permission;

/**
 * @author: kpkym
 * date: 2018/2/17
 * time: 9:37
 */
public enum PermissionEnum {
    YELLOW("yellow:read"),
    GREEN("green:read"),
    RED("red:read"),
    BLUE("blue:read"),
    BLACK("black:read");

    private String value;

    PermissionEnum(String s) {
        this.value = s;
    }

    public String getPermission() {
        return this.value;
    }
}
