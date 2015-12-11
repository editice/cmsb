package com.hj.biz.util;


import com.hj.client.object.list.BaseInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;
import java.util.List;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2015/3/21  0:13
 */
public class DateUtil {
    static final Logger log= LoggerFactory.getLogger(DateUtil.class);

    private static final DateTimeFormatter FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

    public static String parseDate(Date date) {
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(FORMATTER);
    }

    public static Date parseDate(String time){
        try {
            DateTime dateTime = DateTime.parse(time);
            return dateTime.toDate();
        }catch (Exception e){
            log.error("parse date fail",e);
            return null;

        }
    }
}
