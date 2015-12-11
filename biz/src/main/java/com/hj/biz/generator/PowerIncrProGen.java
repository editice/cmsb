package com.hj.biz.generator;

import com.google.common.collect.Lists;
import com.hj.biz.GraphGenerator;
import com.hj.biz.GraphIn;
import com.hj.biz.util.YPUtil;
import com.hj.client.FR;
import com.hj.client.object.SeriesOption;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2015/2/8  13:30
 */
public class PowerIncrProGen extends GraphGenerator {
    public static final BizKey PowerIncrProGen = new BizKey(23, "特定药品各企业销售增长曲线", "line");

    List<FR> frs = Lists.newArrayList();

    public PowerIncrProGen(GraphIn graphIn) {
        super(graphIn);
        logger.info(PowerIncrProGen.toString());
    }

    @Override
    protected void init() {
        frs= YPUtil.getFrFromSale(graphIn.getYgyZdcsSaleDOs());

    }

    @Override
    protected void genTitle() {
        title.setText("销售增长曲线图");
    }

    @Override
    protected void genToolTip() {

    }

    @Override
    protected void genLegend() {

    }

    @Override
    protected void genXAxis() {
        List<String> rank = Lists.newArrayList();
        if (CollectionUtils.isEmpty(frs)) {
            return;
        }
        for (FR fr : frs) {
            rank.add(fr.getDetailJD());
        }
        xAxis.setData(rank);
    }

    @Override
    protected void genYAxis() {

    }

    @Override
    protected void genSeries() {
        List<Long> list = Lists.newArrayList();
        if (CollectionUtils.isEmpty(frs)) {
            return;
        }
        for (FR fr : frs) {
            list.add(fr.getXse());
        }
        SeriesOption seriesOption = new SeriesOption();
        seriesOption.setData(list);
        seriesOption.setType("line");
        seriesOption.setName("销售额");
        series.setData(Lists.newArrayList(seriesOption));
    }

    @Override
    protected void genDataTip() {
        List<String> list = Lists.newArrayList();
        if (CollectionUtils.isEmpty(frs)) {
            return;
        }
        for (FR fr : frs) {
            list.add(fr.getRate());
        }
        dataTip.setData(list);
    }

}
