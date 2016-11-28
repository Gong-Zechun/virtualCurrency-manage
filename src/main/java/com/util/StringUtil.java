package com.util;

/**
 * 字符串工具类
 * @author AllenGong
 * @version V1.0
 * @date 2016-11-28 16:54
 */
public class StringUtil {

    /**
     * 对象为空判断
     * @param str Object对象
     * @return
     */
    public static boolean isEmpty(Object str) {
        return str == null || "".equals(str);
    }

    /**
     * 对象不为空判断
     * @param str Object对象
     * @return
     */
    public static boolean isNotEmpty(Object str) {
        return !isEmpty(str);
    }

    /**
     * ======================字符串取默认值处理start===============================
     */

    /**
     * 取默认值
     * @param s 字符串
     * @param defaultValue 默认的long型数值
     * @return
     */
    public static long getLongValue(String s, long defaultValue) {
        if(s == null) {
            return defaultValue;
        } else {
            try {
                return Long.parseLong(s);
            } catch (Exception var4) {
                return defaultValue;
            }
        }
    }

    /**
     * 取默认值
     * @param s 字符串
     * @param defaultValue 默认的float型值
     * @return
     */
    public static float getFloatValue(String s, float defaultValue) {
        if(s == null) {
            return defaultValue;
        } else {
            try {
                return Float.parseFloat(s);
            } catch (Exception var3) {
                return defaultValue;
            }
        }
    }

    /**
     * 取默认值
     * @param s 字符串
     * @param defaultValue 默认的short型值
     * @return
     */
    public static short getShortValue(String s, short defaultValue) {
        if(s == null) {
            return defaultValue;
        } else {
            try {
                return Short.parseShort(s);
            } catch (Exception var3) {
                return defaultValue;
            }
        }
    }

    /**
     * 取默认值
     * @param s 字符串
     * @param defaultValue 默认的double型值
     * @return
     */
    public static double getDoubleValue(String s, double defaultValue) {
        if(s == null) {
            return defaultValue;
        } else {
            try {
                return Double.parseDouble(s);
            } catch (Exception var4) {
                return defaultValue;
            }
        }
    }

    /**
     * 取默认值
     * @param s 字符串
     * @param defaultValue 默认的int型值
     * @return
     */
    public static int getIntValue(String s, int defaultValue) {
        if(s != null && s.length() != 0) {
            try {
                return Integer.parseInt(s);
            } catch (Exception var3) {
                return defaultValue;
            }
        } else {
            return defaultValue;
        }
    }

    /**
     * 取默认值
     * @param s 字符串
     * @param defaultValue 默认的boolean型值
     * @return
     */
    public static boolean getBooleanValue(String s, boolean defaultValue) {
        if(s == null) {
            return defaultValue;
        } else {
            try {
                return Boolean.parseBoolean(s);
            } catch (Exception var3) {
                return defaultValue;
            }
        }
    }

    /**
     * ======================字符串取默认值处理end===============================
     */

    /**
     * 字符串为空格的判断
     * @param str 字符串
     * @return
     */
    public static boolean isBlank(String str) {
        int strLen;
        if(str != null && (strLen = str.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if(!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }
    }

    /**
     * 字符串不为空格的判断
     * @param str 字符串
     * @return
     */
    public static boolean isNotBlank(String str) {
        return !StringUtil.isBlank(str);
    }

    //测试 字符串"   "，属于blank，但是不属于empty
    public static void main(String[] args) {
        String str = "123";
        System.out.println(StringUtil.isEmpty(str));
        System.out.println(StringUtil.isBlank(str));
    }
}
