package com.hj.biz.parallel;

import java.util.concurrent.ThreadFactory;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2015/1/12  13:09
 */
public class HjThreadFactory implements ThreadFactory {



    @Override
    public Thread newThread(Runnable r) {
        Thread t =new Thread(null,r,"hj",0);
        if(t.isDaemon()){
            t.setDaemon(false);
        }
        if(t.getPriority()!=Thread.NORM_PRIORITY){
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }
}
