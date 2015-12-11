package com.hj.test;

import com.alibaba.fastjson.JSON;
import com.hj.biz.GraphGenerator;
import com.hj.biz.GraphIn;
import com.hj.client.object.GraphData;
import com.hj.client.object.graph.*;
import com.hj.dal.dao.YPDAO;
import com.hj.dal.domain.dataobject.YgyZdcsSaleDO;
import org.junit.Test;

import java.util.List;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2014/10/17  22:08
 */
public class JunitTest extends BaseTest {

    @Test
    public void testDb(){
        YPDAO ypdao= (YPDAO) ctx.getBean("ypDAO");
        List<YgyZdcsSaleDO> saleDOList=ypdao.getZdcsSale("Á½ÐÔÃ¹ËØB",0,20,null);
        for(YgyZdcsSaleDO ygyZdcsSaleDO:saleDOList){
            System.out.println(ygyZdcsSaleDO);
        }

    }


    @Test
    public void test1() throws Exception {
        GraphIn graphIn=new GraphIn();

        String packageName="com.hj.biz.generator.";
        Object object=Class.forName(packageName+"YaoCpProGen").getConstructors()[0].newInstance(graphIn);
        GraphGenerator generator=(GraphGenerator)object;
        generator.generate();
    }


    @Test
    public void test2(){
        GraphData graphData=new GraphData();
        XAxis XAxis =new XAxis();
        XAxis.setName("x1");
        XAxis.setType("type1");
        XAxis.setData(null);
        Legend legend=new Legend();
        ToolTip toolTip=new ToolTip();
        YAxis yAxis=new YAxis();
        yAxis.setName("y1");
        graphData.setYAxis(yAxis);
        graphData.setLegend(legend);
        graphData.setSeries(new Series());
        graphData.setToolTip(toolTip);
        graphData.setXAxis(XAxis);
        System.out.println(JSON.toJSONString(graphData));
    }
}
