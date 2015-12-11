package com.hj.biz.generator;

import com.google.common.collect.Lists;
import com.hj.biz.GraphGenerator;
import com.hj.biz.GraphIn;
import com.hj.client.object.SeriesOption;
import com.hj.dal.domain.dataobject.YiyuanCntDO;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2015/3/22  23:44
 */
public class YyRmzlProvinceRankGen extends GraphGenerator {

    public static final BizKey YyCwsProvinceRankGen = new BizKey(92, "医院当省日门诊量排名", "line");

    List<YiyuanCntDO> provinceCnt=Lists.newArrayList();

    public YyRmzlProvinceRankGen(GraphIn graphIn) {
        super(graphIn);
        logger.info(YyCwsProvinceRankGen.toString());
    }

    @Override
    protected void init() {
        String ss=graphIn.getSs();
        if(CollectionUtils.isEmpty(graphIn.getYiyuanCntDOs())){
            return;
        }
        for(YiyuanCntDO yiyuanCntDO:graphIn.getYiyuanCntDOs()){
            if(StringUtils.equals(yiyuanCntDO.getSs(),ss)){
                provinceCnt.add(yiyuanCntDO);
            }
        }

        Collections.sort(provinceCnt, new Comparator<YiyuanCntDO>() {
            @Override
            public int compare(YiyuanCntDO o1, YiyuanCntDO o2) {
                return Integer.compare(o2.getRmzl(),o1.getRmzl());
            }
        });
    }

    @Override
    protected void genTitle() {
        title.setText("日门诊量");
    }

    @Override
    protected void genToolTip() {

    }

    @Override
    protected void genLegend() {
        List<String> list = Lists.newArrayList();
        list.add("日门诊量");
        legend.setData(list);
    }

    @Override
    protected void genXAxis() {
        List<String> rank = Lists.newArrayList();
        if (CollectionUtils.isEmpty(provinceCnt)) {
            return;
        }
        int size = provinceCnt.size();
        for (int i = 1; i <= size; i++) {
            rank.add(i + "");
        }
        xAxis.setData(rank);
    }

    @Override
    protected void genYAxis() {

    }

    @Override
    protected void genSeries() {
        List<Integer> list = Lists.newArrayList();
        if (CollectionUtils.isEmpty(provinceCnt)) {
            return;
        }
        for (YiyuanCntDO cmsbdo : provinceCnt) {
            list.add(cmsbdo.getRmzl());
        }
        SeriesOption seriesOption = new SeriesOption();
        seriesOption.setData(list);
        seriesOption.setType("line");
        seriesOption.setName("日门诊量");
        series.setData(Lists.newArrayList(seriesOption));
    }

    @Override
    protected void genDataTip() {
        List<String> data = Lists.newArrayList();
        List<String> value = Lists.newArrayList();

        List<YiyuanCntDO> cmsbdoList = provinceCnt;
        String yymc = graphIn.getYymc();
        if (CollectionUtils.isEmpty(cmsbdoList)) {
            return;
        }
        for (int i = 0; i < cmsbdoList.size(); i++) {
            YiyuanCntDO cmsbdo = cmsbdoList.get(i);
            if (StringUtils.equals(yymc, cmsbdo.getYymc())) {
                data.add(String.valueOf(i + 1));
                value.add(String.valueOf(cmsbdo.getRmzl()));
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
