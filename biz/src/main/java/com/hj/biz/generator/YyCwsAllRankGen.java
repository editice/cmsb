package com.hj.biz.generator;

import com.google.common.collect.Lists;
import com.hj.biz.GraphGenerator;
import com.hj.biz.GraphIn;
import com.hj.client.object.SeriesOption;
import com.hj.dal.domain.dataobject.CMSBDO;
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
public class YyCwsAllRankGen extends GraphGenerator {

    public static final BizKey YyCwsAllRankGen = new BizKey(91, "医院全国床位数排名", "line");

    List<YiyuanCntDO> yiyuanCntDOs=Lists.newArrayList();

    public YyCwsAllRankGen(GraphIn graphIn) {
        super(graphIn);
        logger.info(YyCwsAllRankGen.toString());
    }

    @Override
    protected void init() {
        Collections.sort(graphIn.getYiyuanCntDOs(), new Comparator<YiyuanCntDO>() {
            @Override
            public int compare(YiyuanCntDO o1, YiyuanCntDO o2) {
                return Integer.compare(o2.getCws(),o1.getCws());
            }
        });
    }

    @Override
    protected void genTitle() {
        title.setText("全国床位数排名");
    }

    @Override
    protected void genToolTip() {

    }

    @Override
    protected void genLegend() {
        List<String> list = Lists.newArrayList();
        list.add("床位数量");
        legend.setData(list);
    }

    @Override
    protected void genXAxis() {
        List<String> rank = Lists.newArrayList();
        if (CollectionUtils.isEmpty(graphIn.getYiyuanCntDOs())) {
            return;
        }
        int size = graphIn.getYiyuanCntDOs().size();
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
        if (CollectionUtils.isEmpty(graphIn.getYiyuanCntDOs())) {
            return;
        }
        for (YiyuanCntDO cmsbdo : graphIn.getYiyuanCntDOs()) {
            list.add(cmsbdo.getCws());
        }
        SeriesOption seriesOption = new SeriesOption();
        seriesOption.setData(list);
        seriesOption.setType("line");
        seriesOption.setName("床位数量");
        series.setData(Lists.newArrayList(seriesOption));
    }

    @Override
    protected void genDataTip() {
        List<String> data = Lists.newArrayList();
        List<String> value = Lists.newArrayList();

        List<YiyuanCntDO> cmsbdoList = graphIn.getYiyuanCntDOs();
        String yymc = graphIn.getYymc();
        if (CollectionUtils.isEmpty(cmsbdoList)) {
            return;
        }
        for (int i = 0; i < cmsbdoList.size(); i++) {
            YiyuanCntDO cmsbdo = cmsbdoList.get(i);
            if (StringUtils.equals(yymc, cmsbdo.getYymc())) {
                data.add(String.valueOf(i + 1));
                value.add(String.valueOf(cmsbdo.getCws()));
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
