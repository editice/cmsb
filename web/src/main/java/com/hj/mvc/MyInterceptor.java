package com.hj.mvc;

import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.Map;
import java.util.logging.Logger;


/**
 * @author tinglang (editice@gmail.com)
 * @since 2014/9/25  22:13
 */
public class MyInterceptor implements HandlerInterceptor {

    private static final Logger log = Logger.getLogger("interCeptor");

    public MyInterceptor() {
        // TODO Auto-generated constructor stub
    }

    private String mappingURL;//利用正则映射到需要拦截的路径

    public void setMappingURL(String mappingURL) {
        this.mappingURL = mappingURL;
    }

    /**
     * 在业务处理器处理请求之前被调用
     * 如果返回false
     * 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
     *
     * 如果返回true
     * 执行下一个拦截器,直到所有的拦截器都执行完毕
     * 再执行被拦截的Controller
     * 然后进入拦截器链,
     * 从最后一个拦截器往回执行所有的postHandle()
     * 接着再从最后一个拦截器往回执行所有的afterCompletion()
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        // TODO Auto-generated method stub
        log.info("==============执行顺序: 1、preHandle================");
        request.setCharacterEncoding("utf-8");
//        if(mappingURL==null || url.matches(mappingURL)){
//            request.getRequestDispatcher("/msg.jsp").forward(request, response);
//            return false;
//        }
        attribute(request);
        return true;
    }

    private void attribute(HttpServletRequest request) throws Exception{
        String queryString=request.getQueryString();
        if (StringUtils.isBlank(queryString)) {
            return ;
        }
        String[] rr = queryString.split("&");
        for (String r : rr) {
            String[] tmp = r.split("=");
            if (tmp.length == 2) {
                request.setAttribute(tmp[0],URLDecoder.decode(tmp[1], "utf-8"));
            }
            else {
                request.setAttribute(tmp[0], "");
            }
        }
    }



    //在业务处理器处理请求执行完成后,生成视图之前执行的动作
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub
        log.info("==============执行顺序: 2、postHandle================");
    }

    /**
     * 在DispatcherServlet完全处理完请求后被调用
     *
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub
        log.info("==============执行顺序: 3、afterCompletion================");
    }

}

