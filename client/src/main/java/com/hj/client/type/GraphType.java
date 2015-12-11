package com.hj.client.type;

/**
 * 图表类型，折线图line，饼图pie，柱形图bar，属于series内容
 *
 * @author tinglang (editice@gmail.com)
 * @since 2015/2/1  12:48
 */
public enum GraphType {

    LINE("line"),

    BAR("bar"),

    MAP("map"),

    K("k"),//k线图

    FORCE("force"),//力导向图

    SCATTER("scatter"),//散点图

    PIE("pie");

    String desc;

    GraphType(String desc) {
        this.desc = desc;
    }

    public GraphType of(String type) {
        for (GraphType graphType : GraphType.values()) {
            if (graphType.desc.equals(type)) {
                return graphType;
            }
        }
        return GraphType.PIE;
    }
}
