package com.hj.biz;

import com.hj.client.object.GraphData;
import com.hj.client.object.graph.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 图形数据生成基类，指定输入输出
 *
 * @author tinglang (editice@gmail.com)
 * @since 2015/2/1  14:14
 */
public abstract class GraphGenerator {

    public static final Logger logger = LoggerFactory.getLogger(GraphGenerator.class);

    /**
     * 从数据库查询到的输入信息
     */
    protected GraphIn graphIn;

    /**
     * 图表输出信息
     */
    protected GraphData graphData;

    protected Title title;
    protected ToolTip toolTip;
    protected Legend legend;
    protected XAxis xAxis;
    protected YAxis yAxis;
    protected Series series;

    protected DataTip dataTip;


    public GraphGenerator(GraphIn graphIn) {
        this.graphIn = graphIn;
        this.graphData = new GraphData();
        this.title = new Title();
        this.toolTip = new ToolTip();
        this.legend = new Legend();
        this.xAxis = new XAxis();
        this.yAxis = new YAxis();
        this.series = new Series();
        this.dataTip = new DataTip();
    }

    public GraphData generate() {
        //初始化
        init();

        genTitle();
        genToolTip();
        genLegend();
        genXAxis();
        genYAxis();
        genSeries();
        genDataTip();

        //填充图表数据
        fillBody();
        return graphData;
    }

    private void fillBody() {
        graphData.setTitle(title);
        graphData.setToolTip(toolTip);
        graphData.setLegend(legend);
        graphData.setXAxis(xAxis);
        graphData.setYAxis(yAxis);
        graphData.setSeries(series);
        graphData.setDataTip(dataTip);
    }

    protected abstract void init();

    protected abstract void genTitle();

    protected abstract void genToolTip();

    protected abstract void genLegend();

    protected abstract void genXAxis();

    protected abstract void genYAxis();

    protected abstract void genSeries();

    protected abstract void genDataTip();
}
