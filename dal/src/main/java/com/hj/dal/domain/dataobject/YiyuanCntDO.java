package com.hj.dal.domain.dataobject;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2015/3/22  23:27
 */
public class YiyuanCntDO {
    long id;
    String yymc;
    String ss;
    int cws;
    int rmzl;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getYymc() {
        return yymc;
    }

    public void setYymc(String yymc) {
        this.yymc = yymc;
    }

    public String getSs() {
        return ss;
    }

    public void setSs(String ss) {
        this.ss = ss;
    }

    public int getCws() {
        return cws;
    }

    public void setCws(int cws) {
        this.cws = cws;
    }

    public int getRmzl() {
        return rmzl;
    }

    public void setRmzl(int rmzl) {
        this.rmzl = rmzl;
    }
}
