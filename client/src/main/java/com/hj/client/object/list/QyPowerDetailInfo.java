package com.hj.client.object.list;

import java.text.DecimalFormat;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2015/2/15  16:44
 */
public class QyPowerDetailInfo  implements Comparable{
    String cpmc;
    long sale;
    String percent;
    String isJy;
    String yibao;
    String otc;

    static final DecimalFormat df= new DecimalFormat("###,###.##");

    public String getCpmc() {
        return cpmc;
    }

    public void setCpmc(String cpmc) {
        this.cpmc = cpmc;
    }

    public long getSale() {
        return sale;
    }

    public void setSale(long sale) {
        this.sale = sale;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getIsJy() {
        return isJy;
    }

    public void setIsJy(String isJy) {
        this.isJy = isJy;
    }

    public String getYibao() {
        return yibao;
    }

    public void setYibao(String yibao) {
        this.yibao = yibao;
    }

    public String getOtc() {
        return otc;
    }

    public void setOtc(String otc) {
        this.otc = otc;
    }

    public String getSaleStr(){
         return df.format(sale/10000.0);
    }

    @Override
    public String toString() {
        return "QyPowerDetailInfo{" +
               "cpmc='" + cpmc + '\'' +
               ", sale=" + sale +
               ", percent='" + percent + '\'' +
               ", isJy='" + isJy + '\'' +
               ", yibao='" + yibao + '\'' +
               ", otc='" + otc + '\'' +
               '}';
    }

    @Override
    public int compareTo(Object o) {
        return Long.compare(((QyPowerDetailInfo)o).sale,sale);
    }
}
