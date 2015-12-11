package com.hj.biz.error;


/**
 * 错误基本类型
 * @author null.zj
 */
public class Error {
    /**错误代码*/
    private String errorCode;

    /**错误详细信息*/
    private String errorMsg;

    /**错误跳转（外部重定向）*/
    private String redirectUrl;

    /**错误跳转（内部重定向）*/
    private String redirectTarget;

    /*错误类型，1表示客户端错误，2表示服务端系统错误，3，表示服务端依赖的系统错误*/
    private String errorType;

    public Error() {

    }

    public Error(String errorCode, String errorMsg, String redirectUrl, String redirectTarget, String errorType) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.redirectUrl = redirectUrl;
        this.redirectTarget = redirectTarget;
        this.errorType = errorType;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getRedirectTarget() {
        return redirectTarget;
    }

    public void setRedirectTarget(String redirectTarget) {
        this.redirectTarget = redirectTarget;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }
}
