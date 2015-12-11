package com.hj.biz.generator;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hj.biz.GraphGenerator;
import com.hj.biz.GraphIn;
import com.hj.biz.util.GraphUtil;
import com.hj.client.object.ValuePair1;
import com.hj.dal.domain.dataobject.SfdaQxDO;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2015/3/12  21:51
 */
public class QxGcjkProGen extends GraphGenerator {

    public static final BizKey QxGcjkProGen=new BizKey(51,"医疗器械国产进口占比饼状图","pie");
    public QxGcjkProGen(GraphIn graphIn) {
        super(graphIn);
        logger.info(QxGcjkProGen.toString());
    }

    Map<String,Integer> map= Maps.newHashMap();

    @Override
    protected void init() {
       List<SfdaQxDO> qxDOList=graphIn.getSfdaQxDOs();
        if(CollectionUtils.isEmpty(qxDOList)){
            return;
        }
        for(SfdaQxDO qxDO:qxDOList){
            String name=qxDO.getGj();
            if(map.containsKey(name)){
                map.put(name,map.get(name)+1);
            }else{
                map.put(name,1);
            }

        }
//        map= GraphUtil.extra1(map);
    }

    @Override
    protected void genTitle() {
        title.setText("国产/进口");
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
