package com.hj.dal.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hj.client.constants.Delimiters;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2014/9/21  11:22
 */
public class AttributeUtil {
    static final String SP = ";";
    static final String SSP = ":";

    static final String R_SP = "#3A";
    static final String R_SSP = "#3B";

    /**
     * 通过Map转换成String
     * @param attrs
     * @return
     */
    public static final String fromMapToString(Map<String, String> attrs) {
        if(CollectionUtils.isEmpty(attrs)){
            return null;
        }
        StringBuilder sb = new StringBuilder();
            sb.append(SP);
            for (String key : attrs.keySet()) {
                String val = attrs.get(key);
                if (StringUtils.isNotEmpty(val)) {
                    sb.append(encode(key)).append(SSP).append(encode(val)).append(SP);
                }
            }
        return sb.toString();
    }

    public static final String toString(String key, String val) {
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(val)) {
            sb.append(SP);
            sb.append(encode(key)).append(SSP).append(encode(val));
            sb.append(SP);
        }
        return sb.toString();
    }

    /**
     * 通过字符串解析成attributes
     * @param str
     * @return
     */
    public static final Map<String, String> fromString(String str) {
        Map<String, String> attrs = Maps.newHashMap();
        if (StringUtils.isNotBlank(str)) {
            String[] arr = str.split(SP);
            if (null != arr) {
                for (String kv : arr) {
                    if (StringUtils.isNotBlank(kv)) {
                        String[] ar = kv.split(SSP);
                        if (null != ar && ar.length == 2) {
                            String key = decode(ar[0]);
                            String val = decode(ar[1]);
                            if (StringUtils.isNotEmpty(val)) {
                                attrs.put(key, val);
                            }
                        }
                    }
                }
            }
        }
        return attrs;
    }

    private static String encode(String val) {
        return StringUtils.replace(StringUtils.replace(val, SP, R_SP), SSP, R_SSP);
    }

    private static String decode(String val) {
        return StringUtils.replace(StringUtils.replace(val, R_SP, SP), R_SSP, SSP);
    }

    private static final int DEFAULT_BUFFER_SIZE = 2048;

    private static final String DEFAULT_ENCODE = "ISO-8859-1";


    public static String zipString(String sourceStr) {
        return zipString(sourceStr, DEFAULT_ENCODE);
    }

    public static String zipString(String sourceStr, String encode) {
        ByteArrayOutputStream bos = null;
        GZIPOutputStream os = null;
        byte[] bs = null;
        try {
            bos = new ByteArrayOutputStream();
            os = new GZIPOutputStream(bos);
            os.write(sourceStr.getBytes());
            os.close();
            bos.close();
            bs = bos.toByteArray();
            return new String(bs, encode);
        } catch (Exception ex) {
            return sourceStr;
        } finally {
            bs = null;
            bos = null;
            os = null;
        }
    }

    public static String unzipString(String str) {
        return unzipString(str, DEFAULT_ENCODE);
    }

    public static String unzipString(String str, String encode) {
        ByteArrayInputStream bis = null;
        ByteArrayOutputStream bos = null;
        GZIPInputStream is = null;
        byte[] buf = null;
        try {
            bis = new ByteArrayInputStream(str.getBytes(encode));
            bos = new ByteArrayOutputStream();
            is = new GZIPInputStream(bis);
            buf = new byte[DEFAULT_BUFFER_SIZE];
            int len;
            while ((len = is.read(buf)) != -1) {
                bos.write(buf, 0, len);
            }
            is.close();
            bis.close();
            bos.close();
            return bos.toString();
        } catch (Exception ex) {
            return str;
        } finally {
            bis = null;
            bos = null;
            is = null;
            buf = null;
        }
    }

    public static String getStringFromList(List list){
        if(CollectionUtils.isEmpty(list)){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for(Object o:list){
             sb.append(Delimiters.COMMA).append(o);
        }
        return sb.substring(1);
    }

    public static List<Long> getLongListFromString(String str){
        if(StringUtils.isBlank(str)){
            return null;
        }
        String[] tmp=str.split(Delimiters.COMMA);
        List<Long> list= Lists.newArrayList();
        for(String t:tmp){
            list.add(Long.parseLong(t));
        }
        return list;
    }

    public static List<String> getStringListFromString(String str){
        if(StringUtils.isBlank(str)){
            return null;
        }
        String[] tmp=str.split(Delimiters.COMMA);
        List<String> list= Lists.newArrayList();
        for(String t:tmp){
            list.add(t);
        }
        return list;
    }
}

