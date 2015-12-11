package com.hj.test;

import com.alibaba.fastjson.JSON;
import com.hj.client.object.GraphData;
import com.hj.client.object.graph.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 测试专用
 * @author tinglang (editice@gmail.com)
 * @since 2014/10/20  14:05
 */
public class Test {

    public static void test1(){
        String s="0-1-1-2014-0-0";
        String[] tmp=s.split("-");
        System.out.println(tmp.length);
    }

    public static void test2(){
        String s="%7B%22skuExtra%22%3A%5B%22136152291_38898%22%2C%22136260417_11484299%22%2C%22136296329_6232794%22%5D%7D";
        try {
            String res= URLDecoder.decode(s, "GBK");
            System.out.println(res);
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }



    public static void test4() throws UnsupportedEncodingException {
        String s="我是中国人";
        String en= URLEncoder.encode(s,"utf-8");
        String de=URLDecoder.decode(en,"utf-8");
        System.out.println(en);
        System.out.println(de);
    }

    public static void test5(){
        GraphData graphData=new GraphData();
        XAxis XAxis =new XAxis();
        XAxis.setName("x1");
        XAxis.setType("type1");
        XAxis.setData(null);
        System.out.println(JSON.toJSONString(XAxis));

        YAxis yAxis=new YAxis();
        Legend legend=new Legend();
        ToolTip toolTip=new ToolTip();

        graphData.setLegend(legend);
        graphData.setSeries(new Series());
        graphData.setToolTip(toolTip);


        System.out.println(JSON.toJSONString(graphData));
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        test5();
//        System.out.println(new Date());
    }
}
