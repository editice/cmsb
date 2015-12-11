package com.hj.dal.domain.dataobject;

import java.util.Date;

/**
 * Ò½ÁÆÆ÷Ðµ¶ÔÏó
 * @author tinglang (editice@gmail.com)
 * @since 2015/3/12  20:21
 */
public class SfdaQxDO {
    long id;
    String gj;
    String product_struct_composition;
    Date approval_date;
    String product_scope;
    Date validity_period;
    String regist_num;
    String productSite;
    String product_company;
    String product_standard;
    String product_name;
    String specification_model;
    String zcdl;
    long tlzcCnt;
    long gczcCnt;
    long jkzcCnt;

    public long getTlzcCnt() {
        return tlzcCnt;
    }

    public void setTlzcCnt(long tlzcCnt) {
        this.tlzcCnt = tlzcCnt;
    }

    public long getGczcCnt() {
        return gczcCnt;
    }

    public void setGczcCnt(long gczcCnt) {
        this.gczcCnt = gczcCnt;
    }

    public long getJkzcCnt() {
        return jkzcCnt;
    }

    public void setJkzcCnt(long jkzcCnt) {
        this.jkzcCnt = jkzcCnt;
    }

    public String getZcdl() {
        return zcdl;
    }

    public void setZcdl(String zcdl) {
        this.zcdl = zcdl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProduct_struct_composition() {
        return product_struct_composition;
    }

    public void setProduct_struct_composition(String product_struct_composition) {
        this.product_struct_composition = product_struct_composition;
    }

    public String getGj() {
        return gj;
    }

    public void setGj(String gj) {
        this.gj = gj;
    }

    public Date getApproval_date() {
        return approval_date;
    }

    public void setApproval_date(Date approval_date) {
        this.approval_date = approval_date;
    }

    public String getProduct_scope() {
        return product_scope;
    }

    public void setProduct_scope(String product_scope) {
        this.product_scope = product_scope;
    }

    public Date getValidity_period() {
        return validity_period;
    }

    public void setValidity_period(Date validity_period) {
        this.validity_period = validity_period;
    }

    public String getRegist_num() {
        return regist_num;
    }

    public void setRegist_num(String regist_num) {
        this.regist_num = regist_num;
    }

    public String getProductSite() {
        return productSite;
    }

    public void setProductSite(String productSite) {
        this.productSite = productSite;
    }

    public String getProduct_company() {
        return product_company;
    }

    public void setProduct_company(String product_company) {
        this.product_company = product_company;
    }

    public String getProduct_standard() {
        return product_standard;
    }

    public void setProduct_standard(String product_standard) {
        this.product_standard = product_standard;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getSpecification_model() {
        return specification_model;
    }

    public void setSpecification_model(String specification_model) {
        this.specification_model = specification_model;
    }
}
