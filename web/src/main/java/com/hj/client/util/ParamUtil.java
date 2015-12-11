package com.hj.client.util;

import org.springframework.cglib.core.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2015/3/22  22:08
 */
public class ParamUtil {

    public static String getParam(HttpServletRequest request,String key){
         String param = (String) request.getAttribute(key);
        if(StringUtils.isEmpty(param)){
            param = request.getParameter(key);
        }
        return param;
    }

    public static int getPageNum(HttpServletRequest request,String key){
        String num = (String) request.getAttribute("pageNum");
        int pageNum=1;
        if(!StringUtils.isEmpty(num)){
            pageNum=Integer.parseInt(num);
            return pageNum;
        }
        return -1;

    }
}
