package com.hj.biz.util;

import com.hj.biz.Result;
import com.hj.biz.error.ErrorCode;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2014/10/11  11:07
 */
public class ResultUtil<T> {

    public static <T> Result<T> forkFailResult(ErrorCode errorCode) {
        Result<T> result = new Result<T>();
        result.setSuccess(false);
        result.setMsgCode(errorCode.name());
        result.setMsgInfo(errorCode.getUserText());
        return result;
    }

    public static <T> Result<T> forkFailResult(ErrorCode errorCode, String msg) {
        Result<T> result = new Result<T>();
        result.setSuccess(false);
        result.setMsgCode(errorCode.name());
        result.setMsgInfo(msg);
        return result;
    }
}
