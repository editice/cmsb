package com.hj.biz.util;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2014/12/8  11:44
 */
public class Initialization {

    private String beanTime;

    private long cost;

    public String getBeanTime() {
        return beanTime;
    }

    public void setBeanTime(String beanTime) {
        this.beanTime = beanTime;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public static Initialization of(String beanName, long cost){
        Initialization initialization=new Initialization();
        initialization.setBeanTime(beanName);
        initialization.setCost(cost);
        return initialization;
    }

    @Override
    public String toString() {
        return "Initialization{" +
               "beanTime='" + beanTime + '\'' +
               ", cost=" + cost +
               '}';
    }
}
