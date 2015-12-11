package com.hj.biz.error;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.lang.*;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2014/10/25  15:39
 */
public class ErrorUtils {
    private static Logger logger = LoggerFactory.getLogger(ErrorUtils.class);

    private static final String[] errorFiles = new String[]{
       "baoshi_common_error.xml"
    };

    //保存所有的错误码
    private static Map<String, ErrorCode> string2ErrorCodeMap = new ConcurrentHashMap<String, ErrorCode>();

    // 保存所有的错误对象
    private static Map<String, Error> errorMap = new ConcurrentHashMap<String, Error>();

    /**
     *  初始化 string2ErrorCodeMap，errorMap
     */
    static {
        for (ErrorCode errorCode : ErrorCode.values()) {
            string2ErrorCodeMap.put(errorCode.name(), errorCode);
        }

        for (String errorFile : errorFiles) {
            InputStream in = null;
            try {
                in = ErrorUtils.class.getClassLoader().getResourceAsStream(errorFile);
                errorMap.putAll(ErrorDOLoader.loadXml(in));
            } finally {
                if (in != null) {
                    IOUtils.closeQuietly(in);
                }
            }
        }
    }

    /*
    * 初始化所有的错误数据（访问这个方法时，将触发 static{。。。}）
     */
    public static void initErrorData() {

    }

    /**
     * 将错误码（字符串）转换成 ErrorCode对象
     */
    public static ErrorCode ConvertToErrorCode(String errorCode, String from) {
        ErrorCode code = string2ErrorCodeMap.get(errorCode);
        if (code == null) {
            logger.warn("can not convert to Errorcode, errorCode:" + errorCode + ",from:" + from);
            code = ErrorCode.SYSTEM_ERROR;
        }
        return code;
    }

    /**
     * 获取错误对象
     */
    public static Error getError(String errorCode) {
        Error error = errorMap.get(errorCode);
        if (error == null) {
            logger.warn("can not convert to buy Errorcode, errorCode:" + errorCode);
            return new Error();
        }
        return error;
    }


    public static String objToString(Object obj) {
        if (obj == null) {
            return "";
        }
        if (obj.getClass().isArray()) {
            if (obj.getClass().getComponentType() == Integer.TYPE) {
                return Arrays.toString((int[]) obj);
            } else if (obj.getClass().getComponentType() == Long.TYPE) {
                return Arrays.toString((long[]) obj);
            } else if (obj.getClass().getComponentType() == Short.TYPE) {
                return Arrays.toString((short[]) obj);
            } else if (obj.getClass().getComponentType() == Byte.TYPE) {
                return Arrays.toString((byte[]) obj);
            } else if (obj.getClass().getComponentType() == Character.TYPE) {
                return Arrays.toString((char[]) obj);
            } else if (obj.getClass().getComponentType() == Boolean.TYPE) {
                return Arrays.toString((boolean[]) obj);
            } else if (obj.getClass().getComponentType() == Float.TYPE) {
                return Arrays.toString((float[]) obj);
            } else if (obj.getClass().getComponentType() == Double.TYPE) {
                return Arrays.toString((double[]) obj);
            } else {
                return Arrays.toString((Object[]) obj);
            }
        }
        if (obj instanceof Map) {
            StringBuilder sb = new StringBuilder("{");
            Map<?, ?> map = (Map<?, ?>) obj;
            boolean metFirst = false;
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                if (metFirst) sb.append(", ");
                sb.append(objToString(entry.getKey()))
                  .append("=>")
                  .append(objToString(entry.getValue()));
                metFirst = true;
            }
            sb.append("}");
            return sb.toString();
        }
        return obj.toString();
    }
}
