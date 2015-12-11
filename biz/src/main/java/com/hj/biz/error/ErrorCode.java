package com.hj.biz.error;

/**
 * 请注意在代码中，尽量不要在多处使用同一个error code，尽量保证每一个error code只在一处使用
 * @author tinglang (editice@gmail.com)
 * @since 2014/10/1  22:10
 */
public enum  ErrorCode {
    /**
     * 参数为空异常
     */
    NULL_PARAMETER_ERROR,

    /**
     * 客户端参数解析失败
     */
    INPUT_PARSE_FAIL,

    /**
     * 登陆失败，由于用户名密码不匹配
     */
    ACCOUNT_PASSWORD_WRONG,

    /**
     * 用户不存在
     */
    ACCOUNT_NOT_EXIST,

    /**
     * 用户名已存在，请使用其他用户名
     */
    ACCOUNT_NAME_ALREADY_EXIST,

    /**
     * 用户注册失败
     */
    ACCOUNT_CREATED_FAILED,

    /**
     * 重置密码失败
     */
    RESET_PASSWORD_FAILED,

    /**
     * 该圈子名称已经存在
     */
    CIRCLE_TITLE_ALREADY_EXIST,

    /**
     * 该圈子不存在
     */
    CIRCLE_NOT_EXIST,

    /**
     * 该账户没有权限
     */
    ACCOUNT_NO_PERMISSION,

    /**
     * 该活动不存在
     */
    ACTIVITY_NOT_EXIST,

    /**
     * 人数已经达到上限
     */
    MEMBER_ENOUGH,

    /**
     * 该账户没有权限编辑活动
     */
    ACCOUNT_NO_PERMISSION_HANDLE_ACTIVITY,

    /**
     * 系统异常，该活动没有成员
     */
    ACIIVITY_HAS_NO_ACCOUNT,

    /**
     * 账户未参加该活动
     */
    ACCOUNT_NOT_IN_ACTIVITY,

    /**
     * 发送短信验证码失败
     */
    SEND_SMS_CODE_FAIL,

    /**
     * 存储短信验证码失败
     */
    INSERT_SMS_CODE_FAIL,

    /**
     * 短信验证码校验失败
     */
    SMS_CODE_VERTIFY_FAIL,

    /**
     * 系统繁忙，请稍后再试
     */
    SYSTEM_ERROR,

    /**
     * 发布状态失败
     */
    PUBLISH_STATUS_FAILED,

    CIRCLE_TITLE_ALREADY_BEEN_APPLYED,

    MAXIUM_CIRCLE_PEOPLE_NUM,

    CREATE_OPENFIRE_ACCOUNT_FAILED,

    ACCOUNT_NOT_SET_NEW_PASSWORD,

    ACCOUNT_CHANGE_OPENFIRE_PASSWD_FAILED, ACCOUNT_NO_PERMISSION_HANDLE_ANNOUNCE, RECORD_NOT_EXIST, GET_TOKEN_FAIL, CIRCLE_OWNER_CANNOT_DELETE, STATUS_NOT_EXIST, PROVIDER_NOT_EXIST;


    public String getUserText() {
        return ErrorUtils.getError(name()).getErrorMsg();
    }
}
