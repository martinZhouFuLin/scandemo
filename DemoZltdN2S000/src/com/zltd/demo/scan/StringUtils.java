/**
 * Project Name:YTOInfield
 * File Name:StringUtils.java
 * Package Name:cn.net.yto.utils
 * Date:2013-3-4 pm 1:34:41
 * Copyright (c) 2013, zhiliantiandi All Rights Reserved.
 *
 */

package com.zltd.demo.scan;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工�?
 * 
 * @author Date:2013-9-1
 * @version 1.0
 */
public class StringUtils {

    /**
     * 判断是否是空字符�?
     * 
     * @param str
     * @return
     */
    public static boolean isEmpty(CharSequence str) {
        if (str == null || "".equals(str)) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否是空字符�?
     * 
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str)) {
            return true;
        }
        return false;
    }

    /**
     * 获取字符串的长度
     * 
     * @param str
     * @return
     */
    public static int length(CharSequence str) {
        if (str == null) {
            return 0;
        } else {
            return str.length();
        }
    }

    /**
     * 是否有小写字�?
     * 
     * @param str
     * @return
     */
    public static boolean hasLowerChar(CharSequence str) {
        if (isEmpty(str)) {
            return false;
        }
        for (char c : str.toString().toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                return true;
            }
        }
        return false;
    }

    public static boolean strEquals(String str1, String str2) {
        if (isEmpty(str1) && isEmpty(str2)) {
            return true;
        } else if (str1 != null && str1.equals(str2)) {
            return true;
        }
        return false;
    }

    public static boolean isNumeric(String str) {
        if (isEmpty(str)) {
            return false;
        }

        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
}
