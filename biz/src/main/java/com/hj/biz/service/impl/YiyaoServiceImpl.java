package com.hj.biz.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hj.biz.service.YiyaoService;
import com.hj.biz.util.YPUtil;
import com.hj.client.object.AssociateData;
import com.hj.client.object.GraphData;
import com.hj.client.object.list.*;
import com.hj.dal.dao.QXDAO;
import com.hj.dal.dao.YPDAO;
import com.hj.dal.domain.dataobject.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static com.hj.biz.service.Fill.*;
import static com.hj.dal.Constants.idv;
import static com.hj.dal.Constants.idx;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2015/2/1  13:23
 */
public class YiyaoServiceImpl implements YiyaoService {
    static final Logger logger = LoggerFactory.getLogger(YiyaoServiceImpl.class);

    @Resource
    YPDAO ypRepo;

    @Resource
    QXDAO qxRepo;

    @Override
    public String getSearchAssociation(Map<String, String> qs) {
        String cb = qs.get("callback");
        String qStr = qs.get("q");
        String biz = qs.get("biz");

        //模糊匹配要求最多只能提示10条字符串，无需查询全集，因此直接写死即可
        List<String> ass = Lists.newArrayList();
        Map<String, String> attr = Maps.newHashMap();
        if ("3".equals(biz)) {
            ass = ypRepo.getQySearchStr(qStr, 0, 10, attr);
        }
        else if ("1".equals(biz)) {
            ass = ypRepo.getYpSearchStr(qStr, 0, 10, attr);
        }
        else if ("4".equals(biz)) {
            attr.put("sale", "1");
            ass = ypRepo.getQySearchStr(qStr, 0, 10, attr);
        }
        else if ("2".equals(biz)) {
            attr.put("sale", "1");
            ass = ypRepo.getYpSearchStr(qStr, 0, 10, attr);
        }
        else if ("5".equals(biz)) {
            ass = qxRepo.getQxSearchStr(qStr, 0, 10, attr);
        }
        else if ("6".equals(biz)) {
            ass = qxRepo.getQxQySearchStr(qStr, 0, 10, attr);
        }else if("7".equals(biz)){
            ass = qxRepo.getYySearchStr(qStr,0,10,attr);
        }else if("8".equals(biz)){
            ass= qxRepo.getSsSearchStr(qStr,0,Integer.MAX_VALUE,attr);
        }  else if("9".equals(biz)){
            ass= qxRepo.getYqZcSearchStr(qStr, 0, 10, attr);
        }else if("10".equals(biz)){
            ass= qxRepo.getYpZcSearchStr(qStr, 0, 10, attr);
        }

        List<AssociateData> list = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(ass)) {
            for (String s : ass) {
                AssociateData associateData = new AssociateData(s);
                list.add(associateData);
            }
        }
        return cb + "(" + JSON.toJSONString(list) + ")";
    }

    @Override
    public GraphData getGraphData(Map<String, String> queryString) {
        return null;
    }

    @Override
    public List<YpBasicInfo> getYpBasicInfo(String cpmc, int pageIndex, int pageSize) {

        boolean isEn = false;//判断用户检索词为英文 or 中文
        if (cpmc.matches("^[a-zA-Z]*")) {
            isEn = true;
        }
        List<YpBasicInfo> ypBasicInfos = Lists.newArrayList();
        List<SfdaJkypDO> jkypDOs = Lists.newArrayList();
        List<SfdaGcypDO> gcypDOs = ypRepo.getGcYpByCpmc(cpmc, pageIndex, pageSize, YPUtil.genAttr());
        int size = gcypDOs.size();
        pageSize = pageSize - size;
        if (pageSize > 0 || pageSize + size == idv) {
            jkypDOs = ypRepo.getJkYpByCpmc(cpmc, pageIndex, pageSize, YPUtil.genAttr());
        }

        //填充药品名称信息 和 持有批文厂家数量信息
        ypBasicInfos = fillYpBasicName(ypBasicInfos, gcypDOs, jkypDOs);
        List<String> cpmcs = getCpmcFromBasic(ypBasicInfos, isEn);
        //填充是否是基础药物信息
        fillYpBasicJy(ypBasicInfos, ypRepo.isJbYw(cpmcs));
        //填充是否是医保药物信息
        fillYpBasicYb(ypBasicInfos, ypRepo.isYbYw(cpmcs));
        return ypBasicInfos;
    }


    @Override
    public List<YpPwBasicInfo> getYpPwBasicInfo(String cpmc, int pageIndex, int pageSize) {
        List<YpPwBasicInfo> ypPwBasicInfos = Lists.newArrayList();
        if (StringUtils.isBlank(cpmc)) {
            return ypPwBasicInfos;
        }
        List<SfdaJkypDO> jkypDOs = Lists.newArrayList();
        List<SfdaGcypDO> gcypDOs = ypRepo.getGcYpByCpmc(cpmc, pageIndex, pageSize, null);
        int size = gcypDOs.size();
        pageSize = pageSize - size;
        if (pageSize > 0 || pageSize + size == idv) {
            jkypDOs = ypRepo.getJkYpByCpmc(cpmc, pageIndex, pageSize, null);
        }
        fillYpPw(ypPwBasicInfos, gcypDOs, jkypDOs);
        return ypPwBasicInfos;
    }

    @Override
    public List<YpPowerInfo> getYpPowerInfo(String cpmc, int pageIndex, int pageSize, boolean blur) {
        List<YpPowerInfo> ypPowerInfos = Lists.newArrayList();
        if (StringUtils.isEmpty(cpmc)) {
            return ypPowerInfos;
        }
        List<YgyZdcsSaleDO> saleDOs = Lists.newArrayList();
        if (blur) {
            saleDOs = ypRepo.getZdcsSale(cpmc, pageIndex, pageSize, YPUtil.genAttr());
        }
        else {
            saleDOs = ypRepo.getZdcsSale(cpmc, pageIndex, pageSize, null);
        }

        fillYpPower(ypPowerInfos, saleDOs);
        List<String> cpmcs = getCpmcFromPower(ypPowerInfos);
        fillJyInfo(ypPowerInfos, ypRepo.isJbYw(cpmcs));
        fillYbInfo(ypPowerInfos, ypRepo.getYblx(cpmcs));
        fillYpPowerOTC(ypPowerInfos, ypRepo.getOTCfl(cpmcs));
        return ypPowerInfos;
    }

    @Override
    public List<YgyZdcsSaleDO> getZdcsSale(String cpmc, int pageIndex, int pageSize) {
        return ypRepo.getZdcsSale(cpmc, pageIndex, pageSize, null);
    }

    @Override
    public List<YgyZdcsSaleDO> getZdcsSaleByQy(String qy, int pageIndex, int pageSize) {
        return ypRepo.getZdcsSaleByQy(qy, pageIndex, pageSize, null);
    }


    @Override
    public List<CMSBDO> getYPPwRankInfo(String cpmc, int pageIndex, int pageSize) {
        List<CMSBDO> list = ypRepo.getYPPWRank(cpmc, pageIndex, pageSize);
        return list;
    }

    @Override
    public List<CMSBDO> getYpAll(int pageIndex, int pageSize) {
        List<CMSBDO> list = ypRepo.getYpSearchAll(pageIndex, pageSize);
        return list;
    }

    @Override
    public List<CMSBDO> getQyPwRankInfo(String qy, int pageIndex, int pageSize) {
        List<CMSBDO> list = ypRepo.getQyPWRank(qy, pageIndex, pageSize);
        return list;
    }

    @Override
    public List<CMSBDO> getQyAll(int pageIndex, int pageSize) {
        List<CMSBDO> list = ypRepo.getQySearchAll(pageIndex, pageSize);
        return list;
    }

    @Override
    public List<CMSBDO> getQxQyAll(int pageIndex, int pageSize) {
        List<CMSBDO> list = qxRepo.getQxQySearchAll(pageIndex, pageSize);
        return list;
    }

    @Override
    public List<QyBasicInfo> getQyBasicInfo(String qy, int pageIndex, int pageSize) {
        List<SfdaGcypDO> gcypDOs = ypRepo.getGcYpByQy(qy, pageIndex, pageSize, YPUtil.genAttr());
        List<SfdaJkypDO> jkypDOs = ypRepo.getJkYpByQy(qy, pageIndex, pageSize, YPUtil.genAttr());
        List<QyBasicInfo> qyBasicInfos = Lists.newArrayList();
        fillQyBasic(qyBasicInfos, gcypDOs, jkypDOs);
        return qyBasicInfos;
    }

    @Override
    public List<QyPwBasicInfo> getQyPwBasicInfo(String qy, int pageIndex, int pageSize) {
        List<SfdaGcypDO> gcypDOs = ypRepo.getGcYpByQy(qy, pageIndex, pageSize, null);
        List<SfdaJkypDO> jkypDOs = ypRepo.getJkYpByQy(qy, pageIndex, pageSize, null);
        List<QyPwBasicInfo> qyPwBasicInfos = Lists.newArrayList();
        fillQyPwBasic(qyPwBasicInfos, gcypDOs, jkypDOs);
        return qyPwBasicInfos;
    }

    @Override
    public List<QyPowerInfo> getQyPowerInfo(String qy, int pageIndex, int pageSize) {
        List<QyPowerInfo> raw=ypRepo.getQyPowerInfo(qy,pageIndex,pageSize,YPUtil.genAttr());
        return raw;
    }

    @Override
    public List<QyPowerDetailInfo> getQyPowerDetailInfo(String qy, int pageIndex, int pageSize, List<YgyZdcsSaleDO> saleDOs) {
        List<YgyZdcsSaleDO> raw;
        if (saleDOs != null) {
            raw = saleDOs;
        }
        else {
            raw = ypRepo.getZdcsSaleByQy(qy, pageIndex, pageSize, null);
        }
        List<QyPowerDetailInfo> res = Lists.newArrayList();
        List<String> cpmcs = getCpmcFromQyPower(raw);
        fillQyPowerDetailJyInfo(res, raw);
        fillQyJyInfo(res, ypRepo.isJbYw(cpmcs));
        fillQyYbInfo(res, ypRepo.getYblx(cpmcs));
        fillQyOTCInfo(res, ypRepo.getOTCfl(cpmcs));
        YPUtil.calSalePercent(res);
        return res;
    }

    @Override
    public List<SfdaQxDO> getQxInfo(String name, int pageIndex, int pageSize, Map<String, String> attr) {
        List<SfdaQxDO> gcqxDOs = qxRepo.getGcQxByInstruName(name, pageIndex, pageSize, attr);
        int size = 0;
        if (!CollectionUtils.isEmpty(gcqxDOs)) {
            size = gcqxDOs.size();
        }
        pageSize -= size;
        List<SfdaQxDO> jkqxDOs = qxRepo.getJkQxByInstruName(name, pageIndex, pageSize, attr);
        List<SfdaQxDO> res = Lists.newArrayList();
        res.addAll(gcqxDOs);
        res.addAll(jkqxDOs);
        return res;
    }

    @Override
    public List<SfdaQxDO> getQxInfoByQy(String name, int pageIndex, int pageSize, Map<String, String> attr) {
        List<SfdaQxDO> gcqxDOs = qxRepo.getGcQxByQyName(name, pageIndex, pageSize, attr);
        int size = 0;
        if (!CollectionUtils.isEmpty(gcqxDOs)) {
            size = gcqxDOs.size();
        }
        pageSize -= size;
        List<SfdaQxDO> jkqxDOs = qxRepo.getJkQxByQyName(name, pageIndex, pageSize, attr);
        List<SfdaQxDO> res = Lists.newArrayList();
        res.addAll(gcqxDOs);
        res.addAll(jkqxDOs);
        return res;
    }

    @Override
    public List<Integer> getQxQyStructCnt(List<SfdaQxDO> qxDOList) {
        List<Integer> cnts=qxRepo.getQxQyStructCnt(qxDOList);
        return cnts;
    }

    @Override
    public List<LawData> getLawInfo(LawData lawData, int idx, int idv, Map<String, String> attr) {
        List<LawData> lawDatas=qxRepo.getLawInfo(lawData,idx,idv,attr);
        return lawDatas;
    }

    @Override
    public List<LawData> getLawInfoById(long id, int idx, int idv) {
        List<LawData> lawDatas=qxRepo.getLawInfoById(id,idx,idv);
        return lawDatas;
    }

    @Override
    public int insertLawInfo(List<LawData> lawDatas, int idx, int idv, Map<String, String> attr) {
        int res=qxRepo.insertLawInfo(lawDatas,idx,idv,attr);
        return res;
    }

    @Override
    public List<RsData> getRsInfo(RsData rsData, int idx, int idv, Map<String, String> attr) {
        List<RsData> rsDatas=qxRepo.getRsInfo(rsData, idx, idv, attr);
        return rsDatas;
    }

    @Override
    public List<RsData> getRsInfoById(long id, int idx, int idv) {
        List<RsData> rsDatas=qxRepo.getRsInfoById(id, idx, idv);
        return rsDatas;
    }

    @Override
    public int insertRsInfo(List<RsData> rsDatas, int idx, int idv, Map<String, String> attr) {
        int res=qxRepo.insertRsInfo(rsDatas,idx,idv,attr);
        return res;
    }

    @Override
    public List<YYBasicInfo> getYyBasicInfo(Map<String, Object> query, int idx, int idv) {
        return qxRepo.getYiyuanBasicInfo(query, idx, idv);
    }

    @Override
    public List<YYBasicInfo> getYyBasicInfoByYymc(String yymc, int idx, int idv) {
        List<YYBasicInfo> basicInfos=qxRepo.getYiyuanBasicInfoByYymc(yymc, idx, idv);
        return basicInfos;
    }

    @Override
    public List<YiyuanCntDO> getYiyuanCntDOs(int idx, int idv) {
        List<YiyuanCntDO> basicInfos=qxRepo.getYiyuanCntDOs(idx, idv);
        return basicInfos;
    }

    @Override
    public List<CpmcZcDO> getCpmcZc(Map<String, Object> query, int pageIndex, int pageSize) {
        List<CpmcZcDO> basicInfos=qxRepo.getCpmcZcDos(query,pageIndex, pageSize);
        return basicInfos;
    }

    @Override
    public List<CpmcZcDO> getCpmcZcByYpmc(String ypmc, int idx, int idv) {
        List<CpmcZcDO> basicInfos=qxRepo.getCpmcZcDosByYpmc(ypmc, idx, idv);
        return basicInfos;
    }

    @Override
    public List<CpmcZcDO> getCpmcZcByQym(String qym, int idx, int idv, Map<String, String> attr) {
        List<CpmcZcDO> basicInfos=qxRepo.getCpmcZcDosByQym(qym, idx, idv,attr);
        return basicInfos;
    }

    @Override
    public List<CMSBDO> getZcYpAll(int idx, int idv) {
        List<CMSBDO> list = ypRepo.getZcYpAll(idx,idv);
        return list;
    }

    @Override
    public List<CMSBDO> getZcQyBasicInfo(String qym, int pageIndex, int pageSize, Map<String, String> attr) {
        List<CMSBDO> list = qxRepo.getZcQyBasic(qym,idx,idv,attr);
        return list;
    }


}
