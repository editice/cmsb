package com.hj.client.object.list;

import java.util.Date;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2015/3/14  16:54
 */
public class QxQyStructInfo {
    String product_name;
    Date approval_date;
    Date validity_period;
    String gj;
    String zcdl;
    int cnt;

    public String getZcdl() {
        return zcdl;
    }

    public void setZcdl(String zcdl) {
        this.zcdl = zcdl;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Date getApproval_date() {
        return approval_date;
    }

    public void setApproval_date(Date approval_date) {
        this.approval_date = approval_date;
    }

    public Date getValidity_period() {
        return validity_period;
    }

    public void setValidity_period(Date validity_period) {
        this.validity_period = validity_period;
    }

    public String getGj() {
        return gj;
    }

    public void setGj(String gj) {
        this.gj = gj;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }
}
