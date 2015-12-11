package com.hj.dal.domain.dataobject;

import java.util.Date;

/**
 * 进口药品注册号相关数据，属性字段与数据库保持一致
 *
 * @author tinglang (editice@gmail.com)
 * @since 2015/1/31  13:45
 */
public class SfdaJkypDO {

    long id;

    String ROWKEY;
    /**
     * 包装规格（中文）
     */
    String bzgg;
    /**
     * 产品名称（英文）
     */
    String cpmc_en;
    /**
     * 生产厂商（英文）
     */
    String sccs_en;
    /**
     * 地址（中文）
     */
    String gsdz_cn;
    /**
     * 产品名称（中文）
     */
    String cpmc_ch;
    /**
     * 药品本位码备注
     */
    String ypbwmbz;

    /**
     * 厂商国家/地区（英文）
     */
    String csgjdq_en;

    /**
     * 地址（英文）
     */
    String gsdz_en;

    /**
     * 分包装企业地址
     */
    String fbzqydz;

    /**
     * 注册证号备注
     */
    String zczhbz;

    /**
     * 商品名（中文）
     */
    String spm_cn;

    /**
     * 生产厂商（中文）
     */
    String sccs_cn;
    /**
     * 厂商地址（英文）
     */
    String csdz_en;
    /**
     * 有效期截止日
     */
    Date yxqjzr;
    /**
     * 相关数据库查询
     */
    String related_database_query;

    /**
     * 原注册证号
     */
    String yzczh;
    /**
     * 公司名称（英文）
     */
    String gsmc_en;
    /**
     * 分包装文号有效期截止日
     */
    String fbzwhyxqjzr;
    /**
     * 发证日期
     */
    String fzrq;
    /**
     * 剂型（中文）
     */
    String jx;
    /**
     * 注册证号
     */
    String zczh;
    /**
     * 厂商国家/地区（中文）
     */
    String csgjdq_cn;
    /**
     * 规格（中文）
     */
    String gg;

    /**
     * 分包装文号批准日期
     */
    String fbzwhpzrq;
    /**
     * 商品名（英文）
     */
    String spm_en;
    /**
     * 厂商地址（中文）
     */
    String csdz_cn;

    /**
     * 药品本位码
     */
    String ypbwm;
    /**
     * 分包装企业名称
     */
    String fbzqymc;
    /**
     * 国家/地区（中文）
     */
    String gjdq_cn;
    /**
     * 国家/地区（英文）
     */
    String gjdq_en;
    /**
     * 注
     */
    String note;
    /**
     * 产品类别
     */
    String yplb;
    /**
     * 公司名称（中文）
     */
    String gsmc_cn;

    /**
     * 分包装批准文号
     */
    String fbzpzwh;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getROWKEY() {
        return ROWKEY;
    }

    public void setROWKEY(String ROWKEY) {
        this.ROWKEY = ROWKEY;
    }

    public String getBzgg() {
        return bzgg;
    }

    public void setBzgg(String bzgg) {
        this.bzgg = bzgg;
    }

    public String getCpmc_en() {
        return cpmc_en;
    }

    public void setCpmc_en(String cpmc_en) {
        this.cpmc_en = cpmc_en;
    }

    public String getSccs_en() {
        return sccs_en;
    }

    public void setSccs_en(String sccs_en) {
        this.sccs_en = sccs_en;
    }

    public String getGsdz_cn() {
        return gsdz_cn;
    }

    public void setGsdz_cn(String gsdz_cn) {
        this.gsdz_cn = gsdz_cn;
    }

    public String getCpmc_ch() {
        return cpmc_ch;
    }

    public void setCpmc_ch(String cpmc_ch) {
        this.cpmc_ch = cpmc_ch;
    }

    public String getYpbwmbz() {
        return ypbwmbz;
    }

    public void setYpbwmbz(String ypbwmbz) {
        this.ypbwmbz = ypbwmbz;
    }

    public String getCsgjdq_en() {
        return csgjdq_en;
    }

    public void setCsgjdq_en(String csgjdq_en) {
        this.csgjdq_en = csgjdq_en;
    }

    public String getGsdz_en() {
        return gsdz_en;
    }

    public void setGsdz_en(String gsdz_en) {
        this.gsdz_en = gsdz_en;
    }

    public String getFbzqydz() {
        return fbzqydz;
    }

    public void setFbzqydz(String fbzqydz) {
        this.fbzqydz = fbzqydz;
    }

    public String getZczhbz() {
        return zczhbz;
    }

    public void setZczhbz(String zczhbz) {
        this.zczhbz = zczhbz;
    }

    public String getSpm_cn() {
        return spm_cn;
    }

    public void setSpm_cn(String spm_cn) {
        this.spm_cn = spm_cn;
    }

    public String getSccs_cn() {
        return sccs_cn;
    }

    public void setSccs_cn(String sccs_cn) {
        this.sccs_cn = sccs_cn;
    }

    public String getCsdz_en() {
        return csdz_en;
    }

    public void setCsdz_en(String csdz_en) {
        this.csdz_en = csdz_en;
    }

    public Date getYxqjzr() {
        return yxqjzr;
    }

    public void setYxqjzr(Date yxqjzr) {
        this.yxqjzr = yxqjzr;
    }

    public String getRelated_database_query() {
        return related_database_query;
    }

    public void setRelated_database_query(String related_database_query) {
        this.related_database_query = related_database_query;
    }

    public String getYzczh() {
        return yzczh;
    }

    public void setYzczh(String yzczh) {
        this.yzczh = yzczh;
    }

    public String getGsmc_en() {
        return gsmc_en;
    }

    public void setGsmc_en(String gsmc_en) {
        this.gsmc_en = gsmc_en;
    }

    public String getFbzwhyxqjzr() {
        return fbzwhyxqjzr;
    }

    public void setFbzwhyxqjzr(String fbzwhyxqjzr) {
        this.fbzwhyxqjzr = fbzwhyxqjzr;
    }

    public String getFzrq() {
        return fzrq;
    }

    public void setFzrq(String fzrq) {
        this.fzrq = fzrq;
    }

    public String getJx() {
        return jx;
    }

    public void setJx(String jx) {
        this.jx = jx;
    }

    public String getZczh() {
        return zczh;
    }

    public void setZczh(String zczh) {
        this.zczh = zczh;
    }

    public String getCsgjdq_cn() {
        return csgjdq_cn;
    }

    public void setCsgjdq_cn(String csgjdq_cn) {
        this.csgjdq_cn = csgjdq_cn;
    }

    public String getGg() {
        return gg;
    }

    public void setGg(String gg) {
        this.gg = gg;
    }

    public String getFbzwhpzrq() {
        return fbzwhpzrq;
    }

    public void setFbzwhpzrq(String fbzwhpzrq) {
        this.fbzwhpzrq = fbzwhpzrq;
    }

    public String getSpm_en() {
        return spm_en;
    }

    public void setSpm_en(String spm_en) {
        this.spm_en = spm_en;
    }

    public String getCsdz_cn() {
        return csdz_cn;
    }

    public void setCsdz_cn(String csdz_cn) {
        this.csdz_cn = csdz_cn;
    }

    public String getYpbwm() {
        return ypbwm;
    }

    public void setYpbwm(String ypbwm) {
        this.ypbwm = ypbwm;
    }

    public String getFbzqymc() {
        return fbzqymc;
    }

    public void setFbzqymc(String fbzqymc) {
        this.fbzqymc = fbzqymc;
    }

    public String getGjdq_cn() {
        return gjdq_cn;
    }

    public void setGjdq_cn(String gjdq_cn) {
        this.gjdq_cn = gjdq_cn;
    }

    public String getGjdq_en() {
        return gjdq_en;
    }

    public void setGjdq_en(String gjdq_en) {
        this.gjdq_en = gjdq_en;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getYplb() {
        return yplb;
    }

    public void setYplb(String yplb) {
        this.yplb = yplb;
    }

    public String getGsmc_cn() {
        return gsmc_cn;
    }

    public void setGsmc_cn(String gsmc_cn) {
        this.gsmc_cn = gsmc_cn;
    }

    public String getFbzpzwh() {
        return fbzpzwh;
    }

    public void setFbzpzwh(String fbzpzwh) {
        this.fbzpzwh = fbzpzwh;
    }
}
