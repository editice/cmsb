package com.hj.client.object.graph;

import java.util.List;

/**
 * 图例，每个图表最多仅有一个图例
 *
 * @author tinglang (editice@gmail.com)
 * @since 2015/2/1  14:02
 */
public class Legend {

    boolean show=true;

    List<String> data;

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
