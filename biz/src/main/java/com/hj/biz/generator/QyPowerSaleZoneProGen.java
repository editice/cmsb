package com.hj.biz.generator;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hj.biz.GraphGenerator;
import com.hj.biz.GraphIn;
import com.hj.client.object.ValuePair2;
import com.hj.dal.domain.dataobject.YgyZdcsSaleDO;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2015/2/15  15:53
 */
public class QyPowerSaleZoneProGen extends GraphGenerator {
    public static final BizKey QyPowerSaleZoneProGen=new BizKey(46,"企业销售区域地图","map");

    Map<String,Long> map= Maps.newHashMap();

    public QyPowerSaleZoneProGen(GraphIn graphIn) {
        super(graphIn);
        logger.info(QyPowerSaleZoneProGen.toString());
    }

    @Override
    protected void init() {
         List<YgyZdcsSaleDO> saleDOList=graphIn.getYgyZdcsSaleDOs();
        if(CollectionUtils.isEmpty(saleDOList)){
            return;
        }
        for(YgyZdcsSaleDO saleDO:saleDOList){
            String city=saleDO.getCity();
            long xse=Long.parseLong(saleDO.getXse());
            if(map.containsKey(city)){
                map.put(city,map.get(city)+xse);
            }
            else{
                map.put(city,xse);
            }
        }
    }

    @Override
    protected void genTitle() {
          title.setText("企业销售量");
    }

    @Override
    protected void genToolTip() {

    }

    @Override
    protected void genLegend() {

    }

    @Override
    protected void genXAxis() {

    }

    @Override
    protected void genYAxis() {

    }

    @Override
    protected void genSeries() {
        List<ValuePair2>  list= Lists.newArrayList();
        for(String s:map.keySet())
        {
            ValuePair2 pair1=new ValuePair2();
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
