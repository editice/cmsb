package com.hj.client.object;

/**
 * autoComplete 插件需求的返回数据模型
 *
 * @author tinglang (editice@gmail.com)
 * @since 2015/1/31  0:25
 */
public class AssociateData {
    String label;

    String value;

    public AssociateData(String v) {
        this.label = v;
        this.value = v;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
