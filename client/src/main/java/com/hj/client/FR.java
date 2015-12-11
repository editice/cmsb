package com.hj.client;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2015/3/7  16:27
 */
public class FR implements Comparable {

    //季度的描述
    String detailJD;

    //时间表示，用于排序
    int time;

    //当前季度的销售额
    long xse;

    //增长率
    String rate;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getDetailJD() {
        return detailJD;
    }

    public void setDetailJD(String detailJD) {
        this.detailJD = detailJD;
    }

    public long getXse() {
        return xse;
    }

    public void setXse(long xse) {
        this.xse = xse;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    @Override
    public int compareTo(Object o) {
        return Integer.compare(time, ((FR) o).time);
    }
}
