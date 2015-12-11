package com.hj.dal.domain.dataobject;

import java.util.Date;

/**
 * 国产药品批准文号相关数据对象，数据来源食药监总局-sfda
 * 对象内属性命名与数据库表中命名保持一致
 *
 * @author tinglang (editice@gmail.com)
 * @since 2015/1/31  13:31
 */
public class SfdaGcypDO {

    /**
     * 主键
     */
    long ID;

    String ROWKEY;

    /**
     * 相关数据库查询
     */
    String relate_database_query;

    /**
     * 批准日期
     */
    Date pzrq;

    /**
     * 剂型
     */
    String jx;

    /**
     * 药品本位码备注
     */
    String medicine_standardcode_remarks;

    /**
     * 英文名称
     */
    String cpmc_en;

    /**
     * 原批准文号
     */
    String ypzwh;

    /**
     * 批准文号
     */
    String pzwh;

    /**
     * 生产单位
     */
    String scdw;

    /**
     * 生产地址
     */
    String scdz;

    /*
    产品名称
     */
    String cpmc_ch;

    /**
     * 药品本位码
     */
    String ypbwm;

    /**
     * 规格
     */
    String gg;

    /**
     * 产品类别
     */
    String cplb;

    /**
     * 注
     */
    String note;

    String spm;

    public String getSpm() {
        return spm;
    }

    public void setSpm(String spm) {
        this.spm = spm;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getROWKEY() {
        return ROWKEY;
    }

    public void setROWKEY(String ROWKEY) {
        this.ROWKEY = ROWKEY;
    }

    public String getRelate_database_query() {
        return relate_database_query;
    }

    public void setRelate_database_query(String relate_database_query) {
        this.relate_database_query = relate_database_query;
    }

    public Date getPzrq() {
        return pzrq;
    }

    public void setPzrq(Date pzrq) {
        this.pzrq = pzrq;
    }

    public String getJx() {
        return jx;
    }

    public void setJx(String jx) {
        this.jx = jx;
    }

    public String getMedicine_standardcode_remarks() {
        return medicine_standardcode_remarks;
    }

    public void setMedicine_standardcode_remarks(String medicine_standardcode_remarks) {
        this.medicine_standardcode_remarks = medicine_standardcode_remarks;
    }

    public String getCpmc_en() {
        return cpmc_en;
    }

    public void setCpmc_en(String cpmc_en) {
        this.cpmc_en = cpmc_en;
    }

    public String getYpzwh() {
        return ypzwh;
    }

    public void setYpzwh(String ypzwh) {
        this.ypzwh = ypzwh;
    }

    public String getPzwh() {
        return pzwh;
    }

    public void setPzwh(String pzwh) {
        this.pzwh = pzwh;
    }

    public String getScdw() {
        return scdw;
    }

    public void setScdw(String scdw) {
        this.scdw = scdw;
    }

    public String getScdz() {
        return scdz;
    }

    public void setScdz(String scdz) {
        this.scdz = scdz;
    }

    public String getCpmc_ch() {
        return cpmc_ch;
    }

    public void setCpmc_ch(String cpmc_ch) {
        this.cpmc_ch = cpmc_ch;
    }

    public String getYpbwm() {
        return ypbwm;
    }

    public void setYpbwm(String ypbwm) {
        this.ypbwm = ypbwm;
    }

    public String getGg() {
        return gg;
    }

    public void setGg(String gg) {
        this.gg = gg;
    }

    public String getCplb() {
        return cplb;
    }

    public void setCplb(String cplb) {
        this.cplb = cplb;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
