package com.hj.biz;

import com.hj.client.object.list.QyPowerDetailInfo;
import com.hj.client.object.list.QyPwBasicInfo;
import com.hj.client.object.list.YpPwBasicInfo;
import com.hj.dal.domain.dataobject.*;

import java.util.List;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2015/2/1  14:21
 */
public class GraphIn {

    String cpmc;
    String qym;
    String yymc;
    String ss;

    List<YpPwBasicInfo> ypPwBasicInfos;
    List<QyPwBasicInfo> qyPwBasicInfos;
    List<YgyZdcsSaleDO> ygyZdcsSaleDOs;
    List<CMSBDO> cmsbdos;
    List<QyPowerDetailInfo> qyPowerDetailInfos;
    List<SfdaQxDO> sfdaQxDOs;
    List<YiyuanCntDO> yiyuanCntDOs;
    List<CpmcZcDO> cpmcZcDOs;

    public List<CpmcZcDO> getCpmcZcDOs() {
        return cpmcZcDOs;
    }

    public void setCpmcZcDOs(List<CpmcZcDO> cpmcZcDOs) {
        this.cpmcZcDOs = cpmcZcDOs;
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

    public List<YiyuanCntDO> getYiyuanCntDOs() {
        return yiyuanCntDOs;
    }

    public void setYiyuanCntDOs(List<YiyuanCntDO> yiyuanCntDOs) {
        this.yiyuanCntDOs = yiyuanCntDOs;
    }

    public List<SfdaQxDO> getSfdaQxDOs() {
        return sfdaQxDOs;
    }

    public void setSfdaQxDOs(List<SfdaQxDO> sfdaQxDOs) {
        this.sfdaQxDOs = sfdaQxDOs;
    }

    public String getCpmc() {
        return cpmc;
    }

    public void setCpmc(String cpmc) {
        this.cpmc = cpmc;
    }

    public String getQym() {
        return qym;
    }

    public void setQym(String qym) {
        this.qym = qym;
    }

    public List<YpPwBasicInfo> getYpPwBasicInfos() {
        return ypPwBasicInfos;
    }

    public void setYpPwBasicInfos(List<YpPwBasicInfo> ypPwBasicInfos) {
        this.ypPwBasicInfos = ypPwBasicInfos;
    }

    public List<CMSBDO> getCmsbdos() {
        return cmsbdos;
    }

    public void setCmsbdos(List<CMSBDO> cmsbdos) {
        this.cmsbdos = cmsbdos;
    }

    public List<YgyZdcsSaleDO> getYgyZdcsSaleDOs() {
        return ygyZdcsSaleDOs;
    }

    public void setYgyZdcsSaleDOs(List<YgyZdcsSaleDO> ygyZdcsSaleDOs) {
        this.ygyZdcsSaleDOs = ygyZdcsSaleDOs;
    }

    public List<QyPwBasicInfo> getQyPwBasicInfos() {
        return qyPwBasicInfos;
    }

    public void setQyPwBasicInfos(List<QyPwBasicInfo> qyPwBasicInfos) {
        this.qyPwBasicInfos = qyPwBasicInfos;
    }

    public List<QyPowerDetailInfo> getQyPowerDetailInfos() {
        return qyPowerDetailInfos;
    }

    public void setQyPowerDetailInfos(List<QyPowerDetailInfo> qyPowerDetailInfos) {
        this.qyPowerDetailInfos = qyPowerDetailInfos;
    }
}
