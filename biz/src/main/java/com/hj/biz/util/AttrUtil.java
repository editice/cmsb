package com.hj.biz.util;

import com.hj.dal.Constants;
import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Map;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2014/11/10  13:59
 */
public class AttrUtil {

    private Map<String,String> attr;

    public AttrUtil(Map<String, String> attr) {
        if(attr==null){
            this.attr= Maps.newHashMap();
        }else {
            this.attr = attr;
        }
    }

    public int getIndex(){
        String indexStr=attr.get(Constants.pageIndex);
        if(StringUtils.isBlank(indexStr)){
            return -1;
        }
        return Integer.parseInt(indexStr);
    }

    public int getSize(){
        String pageSizeStr=attr.get(Constants.pageSize);
        if(StringUtils.isBlank(pageSizeStr)){
            return -1;
        }
        return Integer.parseInt(pageSizeStr);
    }

    public static void convert(Map<String,String> attr){
        if(CollectionUtils.isEmpty(attr)){
            return;
        }
        String indexStr=attr.get(Constants.pageIndex);
        String pageSizeStr=attr.get(Constants.pageSize);
        if(StringUtils.isBlank(indexStr) || StringUtils.isBlank(pageSizeStr) ){
            return;
        }
        int index=Integer.parseInt(indexStr);
        if(index<1){
            return;
        }
        int size=Integer.parseInt(pageSizeStr);
        attr.put(Constants.pageIndex,(index-1)*size+"");
        attr.put(Constants.pageSize,size+"");
    }
}
