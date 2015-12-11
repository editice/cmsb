package com.hj.biz.generator;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hj.biz.GraphGenerator;
import com.hj.biz.GraphIn;
import com.hj.biz.util.GraphUtil;
import com.hj.client.object.ValuePair1;
import com.hj.client.object.list.QyPwBasicInfo;
import com.hj.dal.domain.dataobject.CpmcZcDO;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2015/2/8  14:20
 */
public class ZcYplxProGen extends GraphGenerator{

    public static final BizKey ZcYplxProGen=new BizKey(102,"药品类型分类","pie");

    Map<String,Integer> map = Maps.newHashMap();

    public ZcYplxProGen(GraphIn graphIn) {
        super(graphIn);
        logger.info(ZcYplxProGen.toString());
    }

    @Override
    protected void init() {
        List<CpmcZcDO> pws=graphIn.getCpmcZcDOs();
        if(CollectionUtils.isEmpty(pws)){
            return;
        }
        for(CpmcZcDO ypPwBasicInfo:pws){
            String cplx=ypPwBasicInfo.getYplx();
            if(StringUtils.isEmpty(cplx)){
               cplx="其他";
            }
            if(map.containsKey(cplx)){
                map.put(cplx, map.get(cplx)+1);
            }else{
                map.put(cplx, 1);
            }
        }
        map= GraphUtil.extra1(map);
    }

    @Override
    protected void genTitle() {
        title.setText("药品类型分类");
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
