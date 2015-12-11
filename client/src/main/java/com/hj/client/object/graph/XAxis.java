package com.hj.client.object.graph;

import java.util.List;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2015/2/1  13:54
 */
public class XAxis<T> {
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
