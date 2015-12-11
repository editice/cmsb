package com.hj.biz.generator;

import com.google.common.collect.Lists;
import com.hj.biz.GraphGenerator;
import com.hj.biz.GraphIn;
import com.hj.client.object.SeriesOption;
import com.hj.dal.domain.dataobject.CMSBDO;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2015/3/28  21:04
 */
public class ZcPowerRankProGen extends GraphGenerator {
    public static final BizKey ZcPowerRankProGen=new BizKey(101,"注册竞争程度排名","line");

    public ZcPowerRankProGen(GraphIn graphIn) {
        super(graphIn);
        logger.info(ZcPowerRankProGen.toString());
    }

    @Override
    protected void init() {

    }

    @Override
    protected void genTitle() {
        title.setText("竞争程度排名");
    }

    @Override
    protected void genToolTip() {

    }

    @Override
    protected void genLegend() {
        List<String> list = Lists.newArrayList();
        list.add("竞争程度排名");
        legend.setData(list);
    }

    @Override
    protected void genXAxis() {
        List<String> rank = Lists.newArrayList();
        if (CollectionUtils.isEmpty(graphIn.getCmsbdos())) {
            return;
        }
        int size = graphIn.getCmsbdos().size();
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
        if (CollectionUtils.isEmpty(graphIn.getCmsbdos())) {
            return;
        }
        for (CMSBDO cmsbdo : graphIn.getCmsbdos()) {
            list.add(cmsbdo.getCnt());
        }
        SeriesOption seriesOption = new SeriesOption();
        seriesOption.setData(list);
        seriesOption.setType("line");
        seriesOption.setName("竞争程度排名");
        series.setData(Lists.newArrayList(seriesOption));
    }

    @Override
    protected void genDataTip() {
        List<String> data = Lists.newArrayList();
        List<String> value = Lists.newArrayList();

        List<CMSBDO> cmsbdoList = graphIn.getCmsbdos();
        String qym = graphIn.getCpmc();
        if (CollectionUtils.isEmpty(cmsbdoList)) {
            return;
        }
        for (int i = 0; i < cmsbdoList.size(); i++) {
            CMSBDO cmsbdo = cmsbdoList.get(i);
            if (StringUtils.equals(qym, cmsbdo.getBname())) {
                data.add(String.valueOf(i + 1));
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
