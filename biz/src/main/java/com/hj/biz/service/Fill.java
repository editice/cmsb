package com.hj.biz.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.hj.biz.util.YPUtil;
import com.hj.client.object.list.*;
import com.hj.dal.domain.dataobject.CpmcZcDO;
import com.hj.dal.domain.dataobject.SfdaGcypDO;
import com.hj.dal.domain.dataobject.SfdaJkypDO;
import com.hj.dal.domain.dataobject.YgyZdcsSaleDO;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

/**
 * Ä£ÐÍÌî³ä
 * @author tinglang (editice@gmail.com)
 * @since 2015/2/8  11:50
 */
public class Fill {

    public static void fillYpPower(List<YpPowerInfo> ypPowerInfos, List<YgyZdcsSaleDO> jcxxDOs) {
        if(CollectionUtils.isEmpty(jcxxDOs)){
            return;
        }
        Set<String> cpmcs=Sets.newHashSet();

        for(YgyZdcsSaleDO jcxxDO:jcxxDOs){
            String cpmc=jcxxDO.getCpmc_ch();
            if(cpmcs.contains(cpmc)){
                continue;
            }
            cpmcs.add(cpmc);
            YpPowerInfo ypPowerInfo=new YpPowerInfo();
            ypPowerInfo.setCnName(jcxxDO.getCpmc_ch());
            ypPowerInfo.setDisease("/");
            ypPowerInfo.setTym(jcxxDO.getCpmc_ch());
            ypPowerInfo.setZldl(jcxxDO.getZldl_1());
            ypPowerInfo.setZlxl(jcxxDO.getZldl_2());
            ypPowerInfos.add(ypPowerInfo);
        }
    }

    public static void fillYpPw(List<YpPwBasicInfo> ypPwBasicInfos, List<SfdaGcypDO> gcypDOs, List<SfdaJkypDO> jkypDOs) {
        ypPwBasicInfos.addAll(YPUtil.genGcPwName(gcypDOs));
        ypPwBasicInfos.addAll(YPUtil.genJkPwName(jkypDOs));
    }

    public static  void fillYpBasicYb(List<YpBasicInfo> ypBasicInfos, List<Boolean> YbCheckList) {
        if (CollectionUtils.isEmpty(ypBasicInfos)) {
            return;
        }
        for (int i = 0; i < ypBasicInfos.size(); i++) {
            ypBasicInfos.get(i).setYbyw(YbCheckList.get(i));
        }
    }

    public static void fillYpBasicJy(List<YpBasicInfo> ypBasicInfos, List<Boolean> jyCheckList) {
        if (CollectionUtils.isEmpty(ypBasicInfos)) {
            return;
        }
        for (int i = 0; i < ypBasicInfos.size(); i++) {
            ypBasicInfos.get(i).setJbyw(jyCheckList.get(i));
        }
    }

    public static void fillJyInfo(List<YpPowerInfo> ypPowerInfos, List<Boolean> jyCheckList){
        if (CollectionUtils.isEmpty(ypPowerInfos)) {
            return;
        }
        for (int i = 0; i < ypPowerInfos.size(); i++) {
            ypPowerInfos.get(i).setJbyw(jyCheckList.get(i));
        }
    }

    public static void fillQyJyInfo(List<QyPowerDetailInfo> infos, List<Boolean> jyCheckList){
        if (CollectionUtils.isEmpty(infos)) {
            return;
        }
        for (int i = 0; i < infos.size(); i++) {
            infos.get(i).setIsJy(jyCheckList.get(i) ? "ÊÇ" : "·ñ");
        }
    }

    public static void fillYbInfo(List<YpPowerInfo> ypPowerInfos, List<String> yblxs){
        if (CollectionUtils.isEmpty(ypPowerInfos)) {
            return;
        }
        for (int i = 0; i < ypPowerInfos.size(); i++) {
            ypPowerInfos.get(i).setYbfl(yblxs.get(i));
        }
    }

    public static void fillQyYbInfo(List<QyPowerDetailInfo> qyPowerDetailInfos,List<String> yblxs){
        if (CollectionUtils.isEmpty(qyPowerDetailInfos)) {
            return;
        }
        for (int i = 0; i < qyPowerDetailInfos.size(); i++) {
            qyPowerDetailInfos.get(i).setYibao(yblxs.get(i));
        }
    }

    public static void fillYpPowerOTC(List<YpPowerInfo> ypPowerInfos,List<String> otcfl){
        if (CollectionUtils.isEmpty(ypPowerInfos)) {
            return;
        }
        for (int i = 0; i < ypPowerInfos.size(); i++) {
            ypPowerInfos.get(i).setOtc(otcfl.get(i));
        }
    }

    public static void fillQyOTCInfo(List<QyPowerDetailInfo> ypPowerInfos,List<String> otcfl){
        if (CollectionUtils.isEmpty(ypPowerInfos)) {
            return;
        }
        for (int i = 0; i < ypPowerInfos.size(); i++) {
            ypPowerInfos.get(i).setOtc(otcfl.get(i));
        }
    }

    public static List<YpBasicInfo> fillYpBasicName(List<YpBasicInfo> ypBasicInfos, List<SfdaGcypDO> gcypDOs, List<SfdaJkypDO> jkypDOs) {
        ypBasicInfos.addAll(YPUtil.genGcName(gcypDOs));
        ypBasicInfos.addAll(YPUtil.genJkName(jkypDOs));
        return merge(ypBasicInfos);
    }

    public static List<String> getCpmcFromBasic(List<YpBasicInfo> ypBasicInfos, boolean isEn){
        List<String> cpmcs = Lists.newArrayList();
        if (isEn) {
            for (YpBasicInfo ypBasicInfo : ypBasicInfos) {
                cpmcs.add(ypBasicInfo.getEnName());
            }
        }
        else {
            for (YpBasicInfo ypBasicInfo : ypBasicInfos) {
                cpmcs.add(ypBasicInfo.getCnName());
            }
        }
        return cpmcs;
    }

    public static List<String> getCpmcFromPower(List<YpPowerInfo> ypPowerInfos){
        List<String> cpmcs=Lists.newArrayList();
        for(YpPowerInfo ypPowerInfo:ypPowerInfos){
           cpmcs.add(ypPowerInfo.getTym());
        }
        return cpmcs;
    }

    public static List<String> getCpmcFromQyPower(List<YgyZdcsSaleDO> qyPowerDetailInfos){
        Set<String> cpmcs=Sets.newHashSet();
        for(YgyZdcsSaleDO info:qyPowerDetailInfos){
            cpmcs.add(info.getCpmc_ch());
        }
        return Lists.newArrayList(cpmcs);
    }


    private static List<YpBasicInfo> merge(List<YpBasicInfo> ypBasicInfos) {
        Set<String> set= Sets.newHashSet();
        List<YpBasicInfo> res=Lists.newArrayList();
        for(YpBasicInfo ypBasicInfo:ypBasicInfos){
            String key=ypBasicInfo.getCnName()+ypBasicInfo.getEnName();
            if(set.contains(key)){
                for(YpBasicInfo info:res){
                    String key1=info.getCnName()+info.getEnName();
                    if(key.equals(key1)){
                        info.setPwCj(info.getPwCj()+ypBasicInfo.getPwCj());
                    }
                }
            }
            else{
                set.add(key);
                res.add(ypBasicInfo);
            }
        }
        return res;
    }

    public static void fillQyBasic(List<QyBasicInfo> qyBasicInfos, List<SfdaGcypDO> gcypDOs, List<SfdaJkypDO> jkypDOs){
         qyBasicInfos.addAll(YPUtil.genQyBasic(gcypDOs, jkypDOs));
    }

    public static void fillQyPwBasic(List<QyPwBasicInfo> qyBasicInfos, List<SfdaGcypDO> gcypDOs, List<SfdaJkypDO> jkypDOs){
        qyBasicInfos.addAll(YPUtil.genQyPwBasic(gcypDOs, jkypDOs));
    }

    public static void fillQyPowerInfo(List<QyPowerInfo> res, List<YgyZdcsSaleDO> raw){
        res.addAll(YPUtil.genQyPowerInfo(raw));
    }

    public static void fillQyPowerDetailJyInfo(List<QyPowerDetailInfo> res,List<YgyZdcsSaleDO> raw){
        res.addAll(YPUtil.genQyPowerDetailInfo(raw));
    }

    public static List<YpZcBasicInfo> fillYpZcBasicInfo(List<CpmcZcDO> cpmcZcDOs) {
       List<YpZcBasicInfo> zcBasicInfos=Lists.newArrayList();
        if(CollectionUtils.isEmpty(cpmcZcDOs)){
            return zcBasicInfos;
        }
        for(CpmcZcDO cpmcZcDO:cpmcZcDOs){
            YpZcBasicInfo zcBasicInfo=new YpZcBasicInfo();
            zcBasicInfo.setBlzt(cpmcZcDO.getCfda_blzt());
            zcBasicInfo.setCbrq(cpmcZcDO.getCde_cbrq());
            zcBasicInfo.setQymc(cpmcZcDO.getScqy());
            zcBasicInfo.setSlhm(cpmcZcDO.getSlh());
            zcBasicInfo.setYplx(cpmcZcDO.getYplx());
            zcBasicInfo.setZcfl(cpmcZcDO.getZclx());
            zcBasicInfo.setZtrq(cpmcZcDO.getKsrq());
            zcBasicInfo.setYpmc(cpmcZcDO.getCpmc());
            zcBasicInfo.setSqlx(cpmcZcDO.getSqlx());
            zcBasicInfos.add(zcBasicInfo);
        }
        return zcBasicInfos;

    }
}
