package com.ou.util;

public class StringUtil {

    /**
     * @param string 需要处理的字符串
     * @return 如果为空或为空字符串则返回true 否则返回false
     */
    public static boolean checkNullOrEmpty(String string) {
        if (null == string || "".equals(string)) {
            return true;
        }
        return false;
    }
}
