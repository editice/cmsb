package com.hj.biz.generator;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hj.biz.GraphGenerator;
import com.hj.biz.GraphIn;
import com.hj.biz.util.GraphUtil;
import com.hj.client.object.ValuePair1;
import com.hj.client.object.list.QyPowerDetailInfo;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2015/2/15  15:51
 */
public class QyPowerOTCProGen extends GraphGenerator {
    public static final BizKey QyPowerOTCProGen=new BizKey(45,"企业OTC情况销售额占比","pie");

    Map<String,Long> map= Maps.newHashMap();

    public QyPowerOTCProGen(GraphIn graphIn) {
        super(graphIn);
        logger.info(QyPowerOTCProGen.toString());
    }

    @Override
    protected void init() {
        List<QyPowerDetailInfo> sales = graphIn.getQyPowerDetailInfos();
        if (CollectionUtils.isEmpty(sales)) {
            return;
        }
        for (QyPowerDetailInfo saleDO : sales) {
            String name = saleDO.getOtc();
            long sale = saleDO.getSale();
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
         title.setText(QyPowerOTCProGen.getName());
    }

    @Override
    protected void genToolTip() {

    }

    @Override
    protected void genLegend() {
        List<String> list= Lists.newArrayList();
        for(String s: map.keySet()){
            if(s==null){
                s="其他";
            }
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
            if(s==null){
                pair1.setName("其他");
            }else {
                pair1.setName(s);
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
