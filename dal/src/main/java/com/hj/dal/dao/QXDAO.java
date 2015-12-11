package com.hj.dal.dao;


import com.hj.client.object.list.LawData;
import com.hj.client.object.list.RsData;
import com.hj.client.object.list.YYBasicInfo;
import com.hj.dal.domain.dataobject.CMSBDO;
import com.hj.dal.domain.dataobject.CpmcZcDO;
import com.hj.dal.domain.dataobject.SfdaQxDO;
import com.hj.dal.domain.dataobject.YiyuanCntDO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2015/3/12  20:47
 */
public interface QXDAO {
    public List<String> getQxSearchStr(String qStr, int pageIndex, int pageSize,Map<String,String>attr);
    public List<String> getQxQySearchStr(String qStr, int pageIndex, int pageSize,Map<String,String>attr);

    public List<SfdaQxDO> getGcQxByInstruName(String name, int pageIndex, int pageSize, Map<String, String> attr);
    public List<SfdaQxDO> getJkQxByInstruName(String name, int pageIndex, int pageSize, Map<String, String> attr);

    public List<SfdaQxDO> getGcQxByQyName(String name, int pageIndex, int pageSize, Map<String, String> attr);
    public List<SfdaQxDO> getJkQxByQyName(String name, int pageIndex, int pageSize, Map<String, String> attr);

    List<CMSBDO> getQxQySearchAll(int pageIndex, int pageSize);

    List<Integer> getQxQyStructCnt(List<SfdaQxDO> qxDOList);

    List<LawData> getLawInfo(LawData lawData, int idx, int idv, Map<String, String> attr);

    List<LawData> getLawInfoById(long id, int idx, int idv);

    int insertLawInfo(List<LawData> lawDatas, int idx, int idv, Map<String, String> attr);

    List<RsData> getRsInfo(RsData rsData, int idx, int idv, Map<String, String> attr);

    List<RsData> getRsInfoById(long id, int idx, int idv);

    int insertRsInfo(List<RsData> rsDatas, int idx, int idv, Map<String, String> attr);

    List<YYBasicInfo> getYiyuanBasicInfo(Map<String, Object> query, int idx, int idv);

    List<YYBasicInfo> getYiyuanBasicInfoByYymc(String yymc, int idx, int idv);

    List<YiyuanCntDO> getYiyuanCntDOs(int idx, int idv);

    List<CpmcZcDO> getCpmcZcDos(Map<String, Object> query, int pageIndex, int pageSize);

    List<CpmcZcDO> getCpmcZcDosByYpmc(String ypmc, int idx, int idv);

    List<CpmcZcDO> getCpmcZcDosByQym(String qym, int idx, int idv, Map<String, String> attr);

    List<String> getYySearchStr(String qStr, int idx, int idv, Map<String, String> attr);

    List<String> getSsSearchStr(String qStr, int pageIndex, int pageSize, Map<String, String> attr);

    List<String> getYqZcSearchStr(String qStr, int pageIndex, int pageSize, Map<String, String> attr);

    List<String> getYpZcSearchStr(String qStr, int pageIndex, int pageSize, Map<String, String> attr);

    List<CMSBDO> getZcQyBasic(String qym, int pageIndex, int pageSize, Map<String, String> attr);
}
