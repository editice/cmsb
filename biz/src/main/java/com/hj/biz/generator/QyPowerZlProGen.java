package com.hj.biz.generator;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hj.biz.GraphGenerator;
import com.hj.biz.GraphIn;
import com.hj.biz.util.GraphUtil;
import com.hj.client.object.ValuePair1;
import com.hj.dal.domain.dataobject.YgyZdcsSaleDO;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2015/2/15  15:46
 */
public class QyPowerZlProGen extends GraphGenerator {
    public static final BizKey QyPowerZlProGen=new BizKey(42,"企业药品对应各治疗领域销售额占比","pie");

    Map<String, Long> map = Maps.newHashMap();

    public QyPowerZlProGen(GraphIn graphIn) {
        super(graphIn);
        logger.info(QyPowerZlProGen.toString());
    }

    @Override
    protected void init() {
        List<YgyZdcsSaleDO> sales = graphIn.getYgyZdcsSaleDOs();
        if (CollectionUtils.isEmpty(sales)) {
            return;
        }
        for (YgyZdcsSaleDO saleDO : sales) {
            String name = saleDO.getZldl_1();
            long sale = Long.parseLong(saleDO.getXse());
            if (map.containsKey(name)) {
                map.put(name, map.get(name) + sale);
            }
            else {
                map.put(name, sale);
            }
        }
        map= GraphUtil.extra2(map);
    }

    @Override
    protected void genTitle() {
       title.setText("治疗领域占比");
    }

    @Override
    protected void genToolTip() {

    }

    @Override
    protected void genLegend() {
        List<String> list= Lists.newArrayList();
        for(String s: map.keySet()){
            list.add(s);
        }
        legend.setData(list);
    }

    @Override
    protected void genXAxis() {

    }

    @Override
    protected void genYAxis() {

    }

    @Override
    protected void genSeries() {
        List<ValuePair1>  list=Lists.newArrayList();
        for(String s: map.keySet())
        {
            ValuePair1 pair1=new ValuePair1();
            pair1.setName(s);
            if(s==null){
                pair1.setName("其他");
            }
            long v= map.get(s);
            int val=(int)v;
            pair1.setValue(val);
            list.add(pair1);
        }
        series.setData(list);
    }

    @Override
    protected void genDataTip() {

    }
}
