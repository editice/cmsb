package com.hj.client.object;

import java.util.List;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2015/2/6  2:10
 */
public class SeriesOption<T> {

    String type;

    String name;

    List<T> data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
