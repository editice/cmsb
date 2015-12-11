package com.hj.client.object.list;

import java.text.DecimalFormat;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2015/2/15  16:12
 */
public class QyPowerInfo implements Comparable{
    String qyName;

    long sale;

    static final DecimalFormat df= new DecimalFormat("###,###.##");


    public String getQyName() {
        return qyName;
    }

    public void setQyName(String qyName) {
        this.qyName = qyName;
    }

    public long getSale() {
        return sale;
    }

    public void setSale(long sale) {
        this.sale = sale;
    }

    public String getSaleStr(){
        return df.format(sale/10000.0);
    }

    @Override
    public int compareTo(Object o) {
        return Long.compare(sale,((QyPowerInfo)o).sale);
    }
}
