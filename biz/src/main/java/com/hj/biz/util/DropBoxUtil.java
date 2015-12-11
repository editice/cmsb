package com.hj.biz.util;

import org.apache.commons.lang.StringUtils;

import java.util.Map;

/**
 * 下拉框选项前后台转换工具类
 *
 * @author tinglang (editice@gmail.com)
 * @since 2015/3/22  20:56
 */
public class DropBoxUtil {
    public static String filterOption(String key) {
        if (StringUtils.equals(key, "0")) {
            return "";
        }
        return key;
    }

    public static String filterZtOption(String key) {
        if (StringUtils.equals(key, "0")) {
            return "";
        }
        String val = "";
        if ("1".equals(key)) {
            val = "已发件";
        }
        else if ("2".equals(key)) {
            val = "待审评";
        }
        else if ("3".equals(key)) {
            val = "在审评";
        }
        else if ("4".equals(key)) {
            val = "审批完毕";
        }
        else if ("5".equals(key)) {
            val = "在审批";
        }
        else if ("6".equals(key)) {
            val = "待审批";
        }
        else if ("7".equals(key)) {
            val = "制证完毕";
        }
        else if ("8".equals(key)) {
            val = "已发通知件";
        }
        else if ("9".equals(key)) {
            val = "制证结束";
        }
        return val;
    }

    public static String filterSqlxOption(String key) {
        if (StringUtils.equals(key, "0")) {
            return "";
        }
        String val = "";
        if ("1".equals(key)) {
            val = "新药";
        }
        else if ("2".equals(key)) {
            val = "进口";
        }
        else if ("3".equals(key)) {
            val = "仿制";
        }
        else if ("4".equals(key)) {
            val = "补充申请";
        }
        else if ("5".equals(key)) {
            val = "复审";
        }
        else if ("6".equals(key)) {
            val = "已有国家标准";
        }
        else if ("7".equals(key)) {
            val = "排队待审评";
        }
        else if ("8".equals(key)) {
            val = "进口再注册";
        }
        else if ("9".equals(key)) {
            val = "进口重新注册";
        }
        else if ("10".equals(key)) {
            val = "药械组合产品";
        }
        return val;
    }

    public static void filterDjOption(Map<String, Object> map, String key) {
        if (StringUtils.equals(key, "0")) {
            return;
        }
        String val = "";
        if ("1".equals(key)) {
            val = "三甲";
        }
        else if ("2".equals(key)) {
            val = "二甲";
        }
        else if ("3".equals(key)) {
            val = "一甲";
        }
        else if ("4".equals(key)) {
            val = "三乙";
        }
        else if ("5".equals(key)) {
            val = "二乙";
        }
        else if ("6".equals(key)) {
            val = "一乙";
        }
        else if ("7".equals(key)) {
            val = "三丙";
        }
        else if ("8".equals(key)) {
            val = "二丙";
        }
        else if ("9".equals(key)) {
            val = "一丙";
        }
        map.put("dj", val);
    }

    public static void filterCwsOption(Map<String, Object> map, String key) {
        if (StringUtils.equals(key, "0")) {
            return;
        }
        int f = 0;
        int n = 0;
        if ("1".equals(key)) {
            f = 0;
            n = 99;
        }
        else if ("2".equals(key)) {
            f = 100;
            n = 499;
        }
        else if ("3".equals(key)) {
            f = 500;
            n = 999;
        }
        else if ("4".equals(key)) {
            f = 1000;
            n = Integer.MAX_VALUE;
        }
        map.put("cws_f", f);
        map.put("cws_n", n);
    }

    public static void filterRmzlOption(Map<String, Object> map, String key) {
        if (StringUtils.equals(key, "0")) {
            return;
        }
        int f = 0;
        int n = 0;
        if ("1".equals(key)) {
            f = 0;
            n = 99;
        }
        else if ("2".equals(key)) {
            f = 100;
            n = 999;
        }
        else if ("3".equals(key)) {
            f = 1000;
            n = 4999;
        }
        else if ("4".equals(key)) {
            f = 5000;
            n = Integer.MAX_VALUE;
        }
        map.put("rmzl_f", f);
        map.put("rmzl_n", n);
    }
}
