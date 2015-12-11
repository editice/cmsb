package com.hj.biz.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2014/12/8  11:39
 */
public class BeanInitCostTimeBeanPostProcessor implements BeanPostProcessor,ApplicationListener {

    static final Logger logger= LoggerFactory.getLogger(BeanInitCostTimeBeanPostProcessor.class);

    private Map<String,Long> startTime= Maps.newHashMap();

    private List<Initialization> costTime= Lists.newArrayList();

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
       if(event instanceof ContextRefreshedEvent){
          for(Initialization initialization:costTime){
              logger.info(initialization.toString());
          }
       }
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        startTime.put(beanName, System.currentTimeMillis());
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Long start=startTime.get(beanName);
        if(start!=null){
           costTime.add(Initialization.of(beanName, System.currentTimeMillis()-start));
        }
        return bean;
    }
}
