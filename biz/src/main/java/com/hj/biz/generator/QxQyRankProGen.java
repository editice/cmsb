package com.hj.biz.generator;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hj.biz.GraphGenerator;
import com.hj.biz.GraphIn;
import com.hj.client.object.SeriesOption;
import com.hj.dal.domain.dataobject.CMSBDO;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2015/3/14  16:46
 */
public class QxQyRankProGen extends GraphGenerator {
    public static final BizKey QxQyRankProGen = new BizKey(61, "医疗器械企业产品结构查询", "bar");

    public QxQyRankProGen(GraphIn graphIn) {
        super(graphIn);
        logger.info(QxQyRankProGen.toString());
    }

    @Override
    protected void init() {

    }

    @Override
    protected void genTitle() {
        title.setText("频数图");
    }

    @Override
    protected void genToolTip() {

    }

    @Override
    protected void genLegend() {
        List<String> list = Lists.newArrayList();
        list.add("批文注册号数量");
        legend.setData(list);
    }

    @Override
    protected void genXAxis() {
        List<String> rank = Lists.newArrayList();
        rank.add("0-99");
        rank.add("100-199");
        rank.add("200-299");
        rank.add("300-399");
        rank.add("400-499");
        rank.add("500-599");
        rank.add("600-699");
        rank.add("700-799");
        rank.add("800-899");
        rank.add("900-999");
        rank.add("1000+");
        xAxis.setData(rank);
    }

    @Override
    protected void genYAxis() {

    }

    @Override
    protected void genSeries() {
        List<Integer> list = Lists.newArrayList();
        Map<Integer,Integer> map= Maps.newHashMap();
        if (CollectionUtils.isEmpty(graphIn.getCmsbdos())) {
            return;
        }
        for (CMSBDO cmsbdo : graphIn.getCmsbdos()) {
            int cnt=cmsbdo.getCnt();
            int key=1;
            int x = cnt/100;
            if(x>=10){
                key=11;
            }else{
                key=x+1;
            }
            if(map.containsKey(key)){
                map.put(key,map.get(key)+1);
            }      else{
                map.put(key,1);
            }
        }
        for(int i=1;i<=11;i++){
            Integer k=map.get(i);
            if(k==null){
                k=0;
            }
           list.add(k);
        }
        SeriesOption seriesOption = new SeriesOption();
        seriesOption.setData(list);
        seriesOption.setType("line");
        seriesOption.setName("批文注册号数量");
        series.setData(Lists.newArrayList(seriesOption));
    }

    @Override
    protected void genDataTip() {
        List<String> data = Lists.newArrayList();
        List<String> value = Lists.newArrayList();

        List<CMSBDO> cmsbdoList = graphIn.getCmsbdos();
        String qym = graphIn.getQym();
        if (CollectionUtils.isEmpty(cmsbdoList)) {
            return;
        }
        for (int i = 0; i < cmsbdoList.size(); i++) {
            CMSBDO cmsbdo = cmsbdoList.get(i);
            if (StringUtils.equals(qym, cmsbdo.getBname())) {
                int cnt=cmsbdo.getCnt();
                int key=cnt/100;
                if(key>11){
                    key=1;
                }
                data.add(String.valueOf(key));
                value.add(String.valueOf(cmsbdo.getCnt()));
            }
        }
        dataTip.setData(data);
        if(CollectionUtils.isEmpty(data)){
            data.add("19000");
        }
        if(CollectionUtils.isEmpty(value)){
            value.add("1");
        }
        dataTip.setValue(value);
    }
}
