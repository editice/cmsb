package com.hj.dal.util;

import java.util.List;

/**
 * @author fanshen
 */
public class CallbackOnMissExecutionException extends RuntimeException {

    private static final long serialVersionUID = -2472267712302281065L;

    public CallbackOnMissExecutionException(Class<?> klass, List<?> ids, Throwable cause) {
        super(cause);
        this.klass = klass;
        this.ids = ids;
    }

    private final Class<?> klass;
    private final List<?> ids;

    public List<?> getIds() {
        return ids;
    }

    public Class<?> getKlass() {
        return klass;
    }

}
