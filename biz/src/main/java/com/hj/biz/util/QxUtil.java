package com.hj.biz.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.hj.biz.service.YiyaoService;
import com.hj.client.object.list.QxQyBasicInfo;
import com.hj.client.object.list.QxQyStructInfo;
import com.hj.dal.domain.dataobject.CMSBDO;
import com.hj.dal.domain.dataobject.SfdaQxDO;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2015/3/12  21:05
 */
public class QxUtil {

    public static List<SfdaQxDO> filterDuplicate(List<SfdaQxDO> input){
        if(CollectionUtils.isEmpty(input)){
            return input;
        }
        Map<String,SfdaQxDO> map=Maps.newHashMap();
        List<SfdaQxDO> qxDOs= Lists.newArrayList();
        for(SfdaQxDO qxDO:input){
            String name=qxDO.getProduct_name();
            if(map.containsKey(name)){
                SfdaQxDO qxDO1=map.get(name);
                qxDO1.setTlzcCnt(qxDO1.getTlzcCnt()+1);
                if("中国".equals(qxDO.getGj())) {
                    qxDO1.setGczcCnt(qxDO1.getGczcCnt() + 1);
                }else {
                    qxDO1.setJkzcCnt(qxDO1.getJkzcCnt() + 1);
                }
            }else {
                qxDO.setTlzcCnt(1);
                if("中国".equals(qxDO.getGj())) {
                    qxDO.setGczcCnt(1);
                }else {
                    qxDO.setJkzcCnt(1);
                }
                map.put(name, qxDO);
            }
        }
        for(String s:map.keySet()){
            qxDOs.add(map.get(s));
        }
        return qxDOs;
    }

    public static int getQyCnt(List<SfdaQxDO> input){
        if(CollectionUtils.isEmpty(input)){
            return 0;
        }
        Set<String> set=Sets.newHashSet();
        for(SfdaQxDO qxDO:input){
            set.add(qxDO.getProduct_company());
        }
        return set.size();
    }

    public static List<String> getScope(List<SfdaQxDO> input) {
        Set<String> set=Sets.newHashSet();
        if(CollectionUtils.isEmpty(input)){
            return Lists.newArrayList(set);
        }
        for(SfdaQxDO qxDO:input){
            set.add(qxDO.getProduct_scope());
        }
        return Lists.newArrayList(set);
    }

    public static List<String> getPsc(List<SfdaQxDO> input) {
        Set<String> set=Sets.newHashSet();
        if(CollectionUtils.isEmpty(input)){
            return Lists.newArrayList(set);
        }
        for(SfdaQxDO qxDO:input){
            set.add(qxDO.getProduct_struct_composition());
        }
        return Lists.newArrayList(set);
    }

    public static List<QxQyBasicInfo> getQyQxCnt(List<SfdaQxDO> qxDOList) {
        Map<String,Integer> map= Maps.newHashMap();
        if(CollectionUtils.isEmpty(qxDOList)){
            return Lists.newArrayList();
        }
        for(SfdaQxDO qxDO:qxDOList){
            String company=qxDO.getProduct_company();
            if(map.containsKey(company)){
                map.put(company,map.get(company)+1);
            }                          else{
                map.put(company,1);
            }
        }
        List<QxQyBasicInfo> res=Lists.newArrayList();
        for(String s:map.keySet()){
            QxQyBasicInfo basicInfo=new QxQyBasicInfo();
            basicInfo.setName(s);
            basicInfo.setVal(map.get(s));
            res.add(basicInfo);

        }
        return res;
    }

    public static int getRank(String qym, List<CMSBDO> cmsbdos) {
        int rank=0;
        if(CollectionUtils.isEmpty(cmsbdos)){
            return rank;
        }
        int size=cmsbdos.size();
        for(int i=0;i<size;i++){
            if(StringUtils.equals(cmsbdos.get(i).getBname(),qym)){
                rank=i+1;
                break;
            }
        }
        return rank;
    }

    public static List<QxQyStructInfo> parseSfdaQxInfo(YiyaoService ypService, List<SfdaQxDO> qxDOList) {
        List<QxQyStructInfo> structInfos= Lists.newArrayList();
        if(CollectionUtils.isEmpty(qxDOList)){
            return structInfos;
        }
        for(SfdaQxDO qxDO:qxDOList){
            QxQyStructInfo structInfo=new QxQyStructInfo();
            structInfo.setApproval_date(qxDO.getApproval_date());
            structInfo.setValidity_period(qxDO.getValidity_period());
            structInfo.setProduct_name(qxDO.getProduct_name());
            structInfo.setGj(qxDO.getGj());
            structInfo.setZcdl(qxDO.getZcdl());
            structInfos.add(structInfo);
        }
        List<Integer> res=ypService.getQxQyStructCnt(qxDOList);
        int size=structInfos.size();
        for(int i=0;i<size;i++){
            structInfos.get(i).setCnt(res.get(i));
        }
        return structInfos;
    }

    public static String getFirst(List<String> scopes) {
        String s="略";
        if(CollectionUtils.isEmpty(scopes)){
            return s;
        }
        for(String t: scopes){
            if(!StringUtils.isEmpty(t)){
                s=t;
                return s;
            }
        }
        return s;
    }

    public static int getZchCnt(List<SfdaQxDO> input) {
        if(CollectionUtils.isEmpty(input)){
            return 0;
        }
        Set<String> set=Sets.newHashSet();
        for(SfdaQxDO qxDO:input){
            set.add(qxDO.getRegist_num());
        }
        return set.size();
    }

    public static int getGcCnt(List<SfdaQxDO> input) {
        if(CollectionUtils.isEmpty(input)){
            return 0;
        }
        Set<String> set=Sets.newHashSet();
        for(SfdaQxDO qxDO:input){
            if("中国".equals(qxDO.getGj())) {
                set.add(qxDO.getRegist_num());
            }
        }
        return set.size();
    }

    public static int getJkCnt(List<SfdaQxDO> input) {
        if(CollectionUtils.isEmpty(input)){
            return 0;
        }
        Set<String> set=Sets.newHashSet();
        for(SfdaQxDO qxDO:input){
            if(!"中国".equals(qxDO.getGj())) {
                set.add(qxDO.getRegist_num());
            }
        }
        return set.size();
    }
}
