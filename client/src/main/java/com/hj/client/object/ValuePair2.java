package com.hj.client.object;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2015/2/6  0:55
 */
public class ValuePair2 implements Comparable{
    long value;

    String name;

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Object o) {
        return Long.compare(((ValuePair2)o).value,value);
    }
}
