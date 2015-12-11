package com.hj.client.object;

import com.hj.client.object.graph.*;

/**
 * 客户端获取图表数据的统一回传模型
 *
 * @author tinglang (editice@gmail.com)
 * @since 2015/2/1  13:49
 */
public class GraphData {
    Title title;

    XAxis xAxis;

    YAxis yAxis;

    ToolTip toolTip;

    Legend legend;

    Series series;

    DataTip dataTip;

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title tile) {
        this.title = tile;
    }

    public DataTip getDataTip() {
        return dataTip;
    }

    public void setDataTip(DataTip dataTip) {
        this.dataTip = dataTip;
    }

    public XAxis getXAxis() {
        return xAxis;
    }

    public void setXAxis(XAxis xAxis) {
        this.xAxis = xAxis;
    }

    public YAxis getYAxis() {
        return yAxis;
    }

    public void setYAxis(YAxis yAxis) {
        this.yAxis = yAxis;
    }

    public ToolTip getToolTip() {
        return toolTip;
    }

    public void setToolTip(ToolTip toolTip) {
        this.toolTip = toolTip;
    }

    public Legend getLegend() {
        return legend;
    }

    public void setLegend(Legend legend) {
        this.legend = legend;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }
}
