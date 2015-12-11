package com.hj.biz.parallel;


import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2015/1/12  13:07
 */
public class Executor {


    private static ThreadPoolExecutor poolExecutor =new ThreadPoolExecutor(15,15,0L, TimeUnit.MILLISECONDS,
                                                                    new LinkedBlockingDeque<Runnable>(),
                                                                    new HjThreadFactory(),
                                                                    new ThreadPoolExecutor.CallerRunsPolicy());


}
