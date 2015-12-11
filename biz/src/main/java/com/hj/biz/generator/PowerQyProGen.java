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
 * @since 2015/2/8  13:28
 */
public class PowerQyProGen extends GraphGenerator {
    public static final BizKey PowerQyProGen=new BizKey(21,"特定药品各生产企业特定年度销售占比","pie");

    Map<String,Integer> map = Maps.newHashMap();

    public PowerQyProGen(GraphIn graphIn) {
        super(graphIn);
        logger.info(PowerQyProGen.toString());
    }

    @Override
    protected void init() {
        List<YgyZdcsSaleDO> list=graphIn.getYgyZdcsSaleDOs();
        if(CollectionUtils.isEmpty(list)){
            return;
        }
        for(YgyZdcsSaleDO saleDO:list){
            String qy=saleDO.getQymc();
            int val=Integer.valueOf(saleDO.getXse());
            if(map.containsKey(qy)){
                map.put(qy, map.get(qy)+val);
            }else{
                map.put(qy,val);
            }
        }
        map= GraphUtil.extra1(map);
    }

    @Override
    protected void genTitle() {
        title.setText("企业占比");
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
            pair1.setValue(map.get(s));
            list.add(pair1);
        }
        series.setData(list);
    }

    @Override
    protected void genDataTip() {

    }
}
