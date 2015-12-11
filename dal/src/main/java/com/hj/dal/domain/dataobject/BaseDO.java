package com.hj.dal.domain.dataobject;

import com.hj.dal.domain.ToStringStyleUtil;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.io.Serializable;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2014/9/20  23:38
 */

/**
 * 所有dataobject的基类
 */
public class BaseDO implements Serializable{

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, new ToStringStyleUtil());
    }
}
