package com.hj.biz.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hj.client.FR;
import com.hj.client.object.ValuePair1;
import com.hj.client.object.list.*;
import com.hj.dal.domain.dataobject.SfdaGcypDO;
import com.hj.dal.domain.dataobject.SfdaJkypDO;
import com.hj.dal.domain.dataobject.YgyZdcsSaleDO;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.text.NumberFormat;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2015/2/3  16:45
 */
public class YPUtil {

    public static boolean isEn(String cpmc){
          return cpmc.matches("^[a-zA-Z]*");
    }

    /**
     * 模糊匹配
     * @return
     */
    public static Map<String, String> genAttr() {
        Map<String, String> attr = Maps.newHashMap();
        attr.put("type", "p");
        return attr;
    }

    public static List<YpBasicInfo> genGcName(List<SfdaGcypDO> gcypDOs) {
        List<YpBasicInfo> ypBasicInfos = Lists.newArrayList();
        Map<String, Integer> cpmc_count = Maps.newHashMap();
        if (CollectionUtils.isEmpty(gcypDOs)) {
            return ypBasicInfos;
        }
        for (SfdaGcypDO gcypDO : gcypDOs) {
            YpBasicInfo ypBasicInfo = new YpBasicInfo();
            String cpmc_ch = gcypDO.getCpmc_ch();
            if (cpmc_count.keySet().contains(cpmc_ch)) {
                cpmc_count.put(cpmc_ch, cpmc_count.get(cpmc_ch) + 1);
                continue;
            }
            cpmc_count.put(cpmc_ch, 1);
            ypBasicInfo.setCnName(gcypDO.getCpmc_ch());
            ypBasicInfo.setEnName(gcypDO.getCpmc_en());
            ypBasicInfos.add(ypBasicInfo);
        }
        for (YpBasicInfo ypBasicInfo : ypBasicInfos) {
            String name = ypBasicInfo.getCnName();
            int cnt = cpmc_count.get(name);
            ypBasicInfo.setPwCj(cnt);
        }
        return ypBasicInfos;
    }

    public static List<YpBasicInfo> genJkName(List<SfdaJkypDO> jkypDOs) {
        List<YpBasicInfo> ypBasicInfos = Lists.newArrayList();
        Map<String, Integer> cpmc_count = Maps.newHashMap();
        if (CollectionUtils.isEmpty(jkypDOs)) {
            return ypBasicInfos;
        }
        for (SfdaJkypDO jkypDO : jkypDOs) {
            String cpmc_en = jkypDO.getCpmc_en();
            if (cpmc_count.keySet().contains(cpmc_en)) {
                cpmc_count.put(cpmc_en, cpmc_count.get(cpmc_en) + 1);
                continue;
            }
            cpmc_count.put(cpmc_en, 1);
            YpBasicInfo ypBasicInfo = new YpBasicInfo();
            ypBasicInfo.setCnName(jkypDO.getCpmc_ch());
            ypBasicInfo.setEnName(jkypDO.getCpmc_en());
            ypBasicInfos.add(ypBasicInfo);
        }
        for (YpBasicInfo ypBasicInfo : ypBasicInfos) {
            String name = ypBasicInfo.getEnName();
            int cnt = cpmc_count.get(name);
            ypBasicInfo.setPwCj(cnt);
        }
        return ypBasicInfos;
    }

    public static List<YpPwBasicInfo> genGcPwName(List<SfdaGcypDO> gcypDOs) {
        List<YpPwBasicInfo> res = Lists.newArrayList();
        if (CollectionUtils.isEmpty(gcypDOs)) {
            return res;
        }
        for (SfdaGcypDO gcypDO : gcypDOs) {
            YpPwBasicInfo ypPwBasicInfo = new YpPwBasicInfo();
            ypPwBasicInfo.setPzwh(gcypDO.getPzwh());
            ypPwBasicInfo.setCpmc_ch(gcypDO.getCpmc_ch());
            ypPwBasicInfo.setCpmhc_en(gcypDO.getCpmc_en());
            ypPwBasicInfo.setSpm(gcypDO.getSpm());
            ypPwBasicInfo.setCplx(gcypDO.getCplb());
            ypPwBasicInfo.setJx(gcypDO.getJx());
            ypPwBasicInfo.setScdw(gcypDO.getScdw());
            ypPwBasicInfo.setGcjk("国产");
            res.add(ypPwBasicInfo);
        }
        return res;
    }

    public static List<YpPwBasicInfo> genJkPwName(List<SfdaJkypDO> jkypDOs) {
        List<YpPwBasicInfo> res = Lists.newArrayList();
        if (CollectionUtils.isEmpty(jkypDOs)) {
            return res;
        }
        for (SfdaJkypDO jkypDO : jkypDOs) {
            YpPwBasicInfo ypPwBasicInfo = new YpPwBasicInfo();
            ypPwBasicInfo.setPzwh(jkypDO.getZczh());
            ypPwBasicInfo.setCpmc_ch(jkypDO.getCpmc_ch());
            ypPwBasicInfo.setCpmhc_en(jkypDO.getCpmc_en());
            ypPwBasicInfo.setSpm(jkypDO.getSpm_en());
            ypPwBasicInfo.setCplx(jkypDO.getYplb());
            ypPwBasicInfo.setJx(jkypDO.getJx());
            ypPwBasicInfo.setScdw(jkypDO.getCsdz_en());
            ypPwBasicInfo.setGcjk("进口");
            res.add(ypPwBasicInfo);
        }
        return res;
    }

    public static List<QyBasicInfo> genQyBasic(List<SfdaGcypDO> gcypDOs, List<SfdaJkypDO> jkypDOs) {
         List<QyBasicInfo> qyBasicInfos = Lists.newArrayList();
        Map<String,Integer> qy_cnt=Maps.newHashMap();
        if(!CollectionUtils.isEmpty(gcypDOs)){
            for(SfdaGcypDO sfdaGcypDO:gcypDOs){
                String qym=sfdaGcypDO.getScdw();
                if(qy_cnt.containsKey(qym)){
                    qy_cnt.put(qym,qy_cnt.get(qym)+1);
                }
                else{
                    qy_cnt.put(qym,1);
                }
            }
        }
        if(!CollectionUtils.isEmpty(jkypDOs)){
            for(SfdaJkypDO sfdaJkypDO:jkypDOs){
                String qym=sfdaJkypDO.getSccs_en();
                if(qy_cnt.containsKey(qym)){
                    qy_cnt.put(qym,qy_cnt.get(qym)+1);
                }
                else{
                    qy_cnt.put(qym,1);
                }
            }
        }
        List<ValuePair1> tmp=Lists.newArrayList();
        if(CollectionUtils.isEmpty(qy_cnt)){
            return qyBasicInfos;
        }
        for(String s:qy_cnt.keySet()){
            ValuePair1 valuePair1=new ValuePair1();
            valuePair1.setValue(qy_cnt.get(s));
            valuePair1.setName(s);
            tmp.add(valuePair1);
        }
        Collections.sort(tmp);
        for(int i=0;i<tmp.size();i++){
            QyBasicInfo info=new QyBasicInfo();
            ValuePair1 pair=tmp.get(i);
            info.setQym(pair.getName());
            info.setPwsl(pair.getValue());
            info.setPwrank(i+1);
            qyBasicInfos.add(info);
        }
        return qyBasicInfos;
    }

    public static List<QyPwBasicInfo> genQyPwBasic(List<SfdaGcypDO> gcypDOs, List<SfdaJkypDO> jkypDOs) {
        List<QyPwBasicInfo> res=Lists.newArrayList();
        if(!CollectionUtils.isEmpty(gcypDOs)) {
            for (SfdaGcypDO gcypDO : gcypDOs) {
                QyPwBasicInfo info = new QyPwBasicInfo();
                info.setQym(gcypDO.getScdw());
                info.setCplx(gcypDO.getCplb());
                info.setCpmc(gcypDO.getCpmc_ch());
                info.setJx(gcypDO.getJx());
                info.setSpm(gcypDO.getSpm());
                info.setPzwh(gcypDO.getPzwh());
                info.setYpm(gcypDO.getCpmc_ch());
                res.add(info);
            }
        }
        if(!CollectionUtils.isEmpty(jkypDOs)){
            for(SfdaJkypDO jkypDO:jkypDOs){
                QyPwBasicInfo info = new QyPwBasicInfo();
                info.setQym(jkypDO.getSccs_en());
                info.setCplx(jkypDO.getYplb());
                info.setCpmc(jkypDO.getCpmc_en());
                info.setJx(jkypDO.getJx());
                info.setSpm(jkypDO.getSpm_en());
                info.setPzwh(jkypDO.getZczh());
                info.setYpm(jkypDO.getCpmc_en());
            }
        }
        return res;
    }

    public static List<QyPowerInfo> genQyPowerInfo(List<YgyZdcsSaleDO> raw) {
        List<QyPowerInfo> res= Lists.newArrayList();
        Map<String,Long> map=Maps.newHashMap();
        if(CollectionUtils.isEmpty(raw)){
            return res;
        }
        for(YgyZdcsSaleDO saleDO:raw){
            String qy=saleDO.getQymc();
            long sale=Long.parseLong(saleDO.getXse());
            if(map.containsKey(qy)){
                 map.put(qy,map.get(qy)+sale);
            }else{
                map.put(qy,sale);
            }
        }
        for(String s:map.keySet()){
            QyPowerInfo info=new QyPowerInfo();
            info.setQyName(s);
            info.setSale(map.get(s));
            res.add(info);
        }
        Collections.sort(res);
        return res;
    }

    public static List<QyPowerDetailInfo> genQyPowerDetailInfo(List<YgyZdcsSaleDO> raw) {
        List<QyPowerDetailInfo> res= Lists.newArrayList();
        Map<String,Long> map=Maps.newHashMap();
        if(CollectionUtils.isEmpty(raw)){
            return res;
        }
        for(YgyZdcsSaleDO saleDO:raw){
            String qy=saleDO.getCpmc_ch();
            long sale=Long.parseLong(saleDO.getXse());
            if(map.containsKey(qy)){
                map.put(qy,map.get(qy)+sale);
            }else{
                map.put(qy,sale);
            }
        }
        for(String s:map.keySet()){
            QyPowerDetailInfo info=new QyPowerDetailInfo();
            info.setCpmc(s);
            info.setSale(map.get(s));
            res.add(info);
        }
        Collections.sort(res);
        return res;
    }

    public static void calSalePercent(List<QyPowerDetailInfo> res) {
         if(CollectionUtils.isEmpty(res)){
             return;
         }
        int len=res.size();
        long total=0L;
        double all=1.0;
        for(QyPowerDetailInfo info:res){
            total+=info.getSale();
        }
        for(QyPowerDetailInfo info:res){
            long sale=info.getSale();
            double per=1.0* sale/total;
            all -=per;
            double actualPer=per;
            String percent= NumberFormat.getPercentInstance().format(actualPer);
            info.setPercent(percent);
        }
    }

    public static List<FR> getFrFromSale(List<YgyZdcsSaleDO> saleDOList){

        List<FR> frs= Lists.newArrayList();
        Map<String, FR> map = Maps.newHashMap();

        if (CollectionUtils.isEmpty(saleDOList)) {
            return frs;
        }
        for (YgyZdcsSaleDO saleDO : saleDOList) {
            String detail = saleDO.getYear() + "-" + saleDO.getJd() + "Q";
            long xse = Long.parseLong(saleDO.getXse());
            if (map.containsKey(detail)) {
                FR fr = map.get(detail);
                fr.setXse(fr.getXse() + xse);
            }
            else {
                FR fr = new FR();
                String year = saleDO.getYear();
                String jd = saleDO.getJd();
                if (StringUtils.isEmpty(year) || StringUtils.isEmpty(jd)) {
                    continue;//视为脏数据
                }
                int time = Integer.parseInt(year) * 10 + Integer.parseInt(jd);
                fr.setTime(time);
                fr.setDetailJD(detail);
                fr.setXse(xse);
                map.put(detail, fr);
            }
        }

        List<FR> frRaw = Lists.newArrayList();
        for (String s : map.keySet()) {
            frRaw.add(map.get(s));
        }
        Collections.sort(frRaw);
        if (CollectionUtils.isEmpty(frRaw)) {
            return frs;
        }
        int size=frRaw.size();
        if (size > 20) {
            frs = frRaw.subList(size-20, size);
        }else{
            frs = frRaw;
        }

        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMaximumFractionDigits(2); //设置小数点保留几位

        //计算增长率
        for (int i = 1; i < frs.size(); i++) {
            long xsePre = frs.get(i - 1).getXse();
            long xseNow = frs.get(i).getXse();
            String rate = nf.format(1.00* (xseNow - xsePre) / xsePre);
            frs.get(i).setRate(rate);
        }
        frs.get(0).setRate("");
        return frs;
    }
}
