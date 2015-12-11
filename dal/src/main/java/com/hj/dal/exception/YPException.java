package com.hj.dal.exception;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2014/9/21  0:54
 */
public class YPException extends Exception {
    private static final long serialVersionUID = -2744317836123125767L;

    public YPException() {
        super();
    }

    public YPException(String message, Throwable cause) {
        super(message, cause);
    }

    public YPException(String message) {
        super(message);
    }

    public YPException(Throwable cause) {
        super(cause);
    }

}
