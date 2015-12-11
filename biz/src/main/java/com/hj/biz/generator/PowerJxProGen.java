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
 *
 * @author tinglang (editice@gmail.com)
 * @since 2015/2/8  13:15
 */
public class PowerJxProGen extends GraphGenerator{
    public static final BizKey PowerJxProGen=new BizKey(22,"特定药品各剂型特定年度销售占比","pie");

    Map<String,Integer> jxmap= Maps.newHashMap();

    public PowerJxProGen(GraphIn graphIn) {
        super(graphIn);
        logger.info(PowerJxProGen.toString());
    }

    @Override
    protected void init() {
         List<YgyZdcsSaleDO> list=graphIn.getYgyZdcsSaleDOs();
        if(CollectionUtils.isEmpty(list)){
           return;
        }
        for(YgyZdcsSaleDO saleDO:list){
            String jx=saleDO.getJx();
            int val=Integer.valueOf(saleDO.getXse());
            if(jxmap.containsKey(jx)){
                jxmap.put(jx,jxmap.get(jx)+val);
            }else{
                jxmap.put(jx,val);
            }
        }
        jxmap= GraphUtil.extra1(jxmap);
    }

    @Override
    protected void genTitle() {
        title.setText("剂型占比");
    }

    @Override
    protected void genToolTip() {

    }

    @Override
    protected void genLegend() {
        List<String> list= Lists.newArrayList();
        for(String s:jxmap.keySet()){
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
        for(String s:jxmap.keySet())
        {
            ValuePair1 pair1=new ValuePair1();
            pair1.setName(s);
            pair1.setValue(jxmap.get(s));
            list.add(pair1);
        }
        series.setData(list);
    }

    @Override
    protected void genDataTip() {

    }
}
