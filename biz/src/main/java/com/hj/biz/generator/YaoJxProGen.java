package com.hj.biz.generator;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hj.biz.GraphGenerator;
import com.hj.biz.GraphIn;
import com.hj.biz.util.GraphUtil;
import com.hj.client.object.ValuePair1;
import com.hj.client.object.list.YpPwBasicInfo;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2015/2/1  14:37
 */
public class YaoJxProGen extends GraphGenerator {
    public static final BizKey YaoJxProGen=new BizKey(2,"药品各机型的批文数量占比","pie");

    Map<String,Integer> map = Maps.newHashMap();

    public YaoJxProGen(GraphIn graphIn) {
        super(graphIn);
        logger.info(YaoJxProGen.toString());
    }

    @Override
    protected void init() {
        List<YpPwBasicInfo> pws=graphIn.getYpPwBasicInfos();
        if(CollectionUtils.isEmpty(pws)){
            return;
        }
        for(YpPwBasicInfo ypPwBasicInfo:pws){
            String jx=ypPwBasicInfo.getJx();
            if(map.containsKey(jx)){
                map.put(jx, map.get(jx)+1);
            }else{
                map.put(jx, 1);
            }
        }
        map= GraphUtil.extra1(map);
    }

    @Override
    protected void genTitle() {
        title.setText("剂型占比");
    }


    @Override
    protected void genToolTip() {
        System.out.println(2);
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
