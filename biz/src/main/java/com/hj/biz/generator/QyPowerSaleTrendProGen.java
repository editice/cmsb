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
 * @since 2015/2/15  15:55
 */
public class QyPowerSaleTrendProGen extends GraphGenerator {
    public static final BizKey QyPowerSaleTrendProGen=new BizKey(47,"企业季度销售趋势","line");

    List<FR> frs = Lists.newArrayList();

    public QyPowerSaleTrendProGen(GraphIn graphIn) {
        super(graphIn);
        logger.info(QyPowerSaleTrendProGen.toString());
    }

    @Override
    protected void init() {
        frs= YPUtil.getFrFromSale(graphIn.getYgyZdcsSaleDOs());
    }

    @Override
    protected void genTitle() {
        title.setText("公司销售趋势");
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
