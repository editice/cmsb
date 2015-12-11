package com.hj.controller;

import com.hj.biz.Result;
import com.hj.biz.error.ErrorCode;
import com.hj.biz.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2014/10/26  1:28
 */
@Controller
public class ErrorController {

    Logger logger= LoggerFactory.getLogger(ErrorController.class);

    @RequestMapping(value="/error",produces = "application/json")
    @ResponseBody
    public Result<?> error(){
        //这种方法主要的弊端是无法获取具体的异常信息
        return ResultUtil.forkFailResult(ErrorCode.SYSTEM_ERROR);
    }

    /** 基于@ExceptionHandler异常处理 */
    @ExceptionHandler({Exception.class})
    public Result<?> exp(HttpServletRequest request, Exception ex) {

        logger.error("\n system error: \n",ex);
        /**
         * 这里其实可以根据不同的错误做不同的处理的
         */
        return ResultUtil.forkFailResult(ErrorCode.SYSTEM_ERROR);
    }
}

