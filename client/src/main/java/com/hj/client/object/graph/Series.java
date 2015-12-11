package com.hj.client.object.graph;

import java.util.List;

/**
 * 驱动图表生成的数据内容数组，数组中每一项为一个系列的选项及数据，其中个别选项仅在部分图表类型中有效
 *
 * @author tinglang (editice@gmail.com)
 * @since 2015/2/1  14:05
 */
public class Series<T>  {

    /**
     * 坐标轴名称
     */
    String name;

    /**
     * 坐标轴类型
     */
    String type;

    /**
     * 坐标轴数据
     */
    List<T> data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

}
