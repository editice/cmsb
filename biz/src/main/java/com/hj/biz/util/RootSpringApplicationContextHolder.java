package com.hj.biz.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import static com.google.common.base.Preconditions.checkState;
import static org.apache.commons.lang.Validate.notEmpty;

/**
 * @author tinglang (null.zj@alibaba-inc.com)
 * @since 2014/8/18  17:38
 */
public class RootSpringApplicationContextHolder implements ApplicationContextAware{
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        RootSpringApplicationContextHolder.context = context;
    }

    public static ApplicationContext getApplicationContext(){
        return context;
    }

    public static Object getSpringBean(String beanName) {
        notEmpty(beanName, "bean name is required");
        checkState(context != null, "spring application context is not injected");
        return context.getBean(beanName);
    }

    public static String[] getBeanDefinitionNames() {
        return context.getBeanDefinitionNames();
    }
}
