package com.hj.client.object.list;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2015/2/4  0:18
 */
public class YpPwBasicInfo {
    String pzwh;
    String cpmc_ch;
    String cpmhc_en;
    String spm;
    String cplx;
    String jx;
    String scdw;
    String gcjk;

    public String getPzwh() {
        return pzwh;
    }

    public void setPzwh(String pzwh) {
        this.pzwh = pzwh;
    }

    public String getCpmc_ch() {
        return cpmc_ch;
    }

    public void setCpmc_ch(String cpmc_ch) {
        this.cpmc_ch = cpmc_ch;
    }

    public String getCpmhc_en() {
        return cpmhc_en;
    }

    public void setCpmhc_en(String cpmhc_en) {
        this.cpmhc_en = cpmhc_en;
    }

    public String getSpm() {
        return spm;
    }

    public void setSpm(String spm) {
        this.spm = spm;
    }

    public String getCplx() {
        return cplx;
    }

    public void setCplx(String cplx) {
        this.cplx = cplx;
    }

    public String getJx() {
        return jx;
    }

    public void setJx(String jx) {
        this.jx = jx;
    }

    public String getScdw() {
        return scdw;
    }

    public void setScdw(String scdw) {
        this.scdw = scdw;
    }

    public String getGcjk() {
        return gcjk;
    }

    public void setGcjk(String gcjk) {
        this.gcjk = gcjk;
    }

    @Override
    public String toString() {
        return "YpPwBasicInfo{" +
               "pzwh='" + pzwh + '\'' +
               ", cpmc_ch='" + cpmc_ch + '\'' +
               ", cpmhc_en='" + cpmhc_en + '\'' +
               ", spm='" + spm + '\'' +
               ", cplx='" + cplx + '\'' +
               ", jx='" + jx + '\'' +
               ", scdw='" + scdw + '\'' +
               ", gcjk='" + gcjk + '\'' +
               '}';
    }
}
