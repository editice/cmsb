package com.hj.controller;

import com.hj.biz.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.Callable;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2014/10/25  9:48
 */

@Controller
@RequestMapping("/async")
public class AysncServiceController extends ErrorController {

    /**
     * “Ï≤Ω«Î«Û≤‚ ‘¿‡
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,value = "/test")
    @ResponseBody
    public Callable<Result<?>> testAsync(){
        Callable<Result<?>> callable=new Callable<Result<?>>() {
            @Override
            public Result call() throws Exception {
                Thread.sleep(2000);
                return new Result<>();
            }
        };
        return callable;
    }
}
