package com.hj.biz;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 客户端调用外部服务时返回的通用数据对象，包括了服务端调用的返回数据
 * 通过success字段标识调用是否成功，通过msgCode和msgInfo提示调用失败时的错误码和详细的错误信息
 * @author tinglang (editice@gmail.com)
 * @since 2014/9/26  17:39
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1905775611728626568L;

    protected boolean success=true;

    /**
     * 返回的对象
     */
    protected T model;

    /**
     * 错误信息error code
     */
    protected String msgCode;

    /**
     * 错误信息详情
     */
    protected String msgInfo;

    public Result(T model) {
        this.model = model;
    }

    public Result() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getModel() {
        return model;
    }

    public void setModel(T model) {
        this.model = model;
    }

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

    public String getMsgInfo() {
        return msgInfo;
    }

    public void setMsgInfo(String msgInfo) {
        this.msgInfo = msgInfo;
    }
}
