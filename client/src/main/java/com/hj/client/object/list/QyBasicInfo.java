package com.hj.client.object.list;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2015/2/8  14:34
 */
public class QyBasicInfo implements Comparable{
     String qym;
    int pwsl;
    int pwrank;

    public void setQym(String qym) {
        this.qym = qym;
    }

    public void setPwsl(int pwsl) {
        this.pwsl = pwsl;
    }

    public void setPwrank(int pwrank) {
        this.pwrank = pwrank;
    }

    public String getQym() {
        return qym;
    }

    public int getPwsl() {
        return pwsl;
    }

    public int getPwrank() {
        return pwrank;
    }

    @Override
    public int compareTo(Object o) {
        return Integer.compare(((QyBasicInfo)o).pwsl,pwsl);
    }
}
