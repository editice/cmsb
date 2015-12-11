package com.hj.client.object.graph;

import java.util.List;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2015/2/1  13:57
 */
public class YAxis {
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
    List<String> data;

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

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
