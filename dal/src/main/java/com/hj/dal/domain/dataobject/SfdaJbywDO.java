package com.hj.dal.domain.dataobject;

/**
 * 国家基本药物目录2012年版,属性与数据库字段保持一致
 *
 * @author tinglang (editice@gmail.com)
 * @since 2015/1/31  14:03
 */
public class SfdaJbywDO {

    long id;

    String ROWKEY;
    /**
     * 二级类别
     */
    String lb_2;
    /**
     * 三级类别
     */
    String lb_3;
    /**
     * 品种名称（药品名称）
     */
    String cpmc;
    /**
     * 剂型说明
     */
    String dosage_desc;
    /**
     * 药品分类
     */
    String ypfl;
    /**
     * 英文名称
     */
    String cpmc_en;
    /**
     * 注
     */
    String note;
    /**
     * 剂型/规格
     */
    String jx;
    /**
     * 一级类别
     */
    String lb_1;
    /**
     * 备注
     */
    String bz;

    /**
     * 使用范围
     */
    String use_range;

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

    public String getLb_2() {
        return lb_2;
    }

    public void setLb_2(String lb_2) {
        this.lb_2 = lb_2;
    }

    public String getLb_3() {
        return lb_3;
    }

    public void setLb_3(String lb_3) {
        this.lb_3 = lb_3;
    }

    public String getCpmc() {
        return cpmc;
    }

    public void setCpmc(String cpmc) {
        this.cpmc = cpmc;
    }

    public String getDosage_desc() {
        return dosage_desc;
    }

    public void setDosage_desc(String dosage_desc) {
        this.dosage_desc = dosage_desc;
    }

    public String getYpfl() {
        return ypfl;
    }

    public void setYpfl(String ypfl) {
        this.ypfl = ypfl;
    }

    public String getCpmc_en() {
        return cpmc_en;
    }

    public void setCpmc_en(String cpmc_en) {
        this.cpmc_en = cpmc_en;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getJx() {
        return jx;
    }

    public void setJx(String jx) {
        this.jx = jx;
    }

    public String getLb_1() {
        return lb_1;
    }

    public void setLb_1(String lb_1) {
        this.lb_1 = lb_1;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getUse_range() {
        return use_range;
    }

    public void setUse_range(String use_range) {
        this.use_range = use_range;
    }
}
