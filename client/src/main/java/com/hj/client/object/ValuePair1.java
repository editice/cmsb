package com.hj.client.object;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2015/2/6  0:55
 */
public class ValuePair1 implements Comparable{
    int value;

    String name;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
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
        return Integer.compare(((ValuePair1)o).value,value);
    }
}
