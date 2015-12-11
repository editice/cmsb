package com.hj.dal.dao;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.hj.client.object.list.LawData;
import com.hj.client.object.list.RsData;
import com.hj.client.object.list.YYBasicInfo;
import com.hj.dal.domain.dataobject.CMSBDO;
import com.hj.dal.domain.dataobject.CpmcZcDO;
import com.hj.dal.domain.dataobject.SfdaQxDO;
import com.hj.dal.domain.dataobject.YiyuanCntDO;
import com.hj.dal.util.IbatisParamMap;
import com.ibatis.sqlmap.client.SqlMapExecutor;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.util.CollectionUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2015/3/12  20:49
 */
@SuppressWarnings("all")
public class QXDAOImpl extends SqlMapClientDaoSupport implements QXDAO {
    @Override
    public List<String> getQxSearchStr(String qStr, int pageIndex, int pageSize, Map<String, String> attr) {
        IbatisParamMap map = new IbatisParamMap("qStr", qStr, pageIndex, pageSize);
            List<String> list = getSqlMapClientTemplate().queryForList("yp.qSearchQx", map);
            return list;
    }

    @Override
    public List<String> getQxQySearchStr(String qStr, int pageIndex, int pageSize, Map<String, String> attr) {
        IbatisParamMap map = new IbatisParamMap("qStr", qStr, pageIndex, pageSize);
        List<String> list = getSqlMapClientTemplate().queryForList("yp.qSearchQxQy", map);
        return list;
    }

    @Override
    public List<SfdaQxDO> getGcQxByInstruName(String name, int pageIndex, int pageSize, Map<String, String> attr) {
        IbatisParamMap map = new IbatisParamMap("name", name, pageIndex, pageSize);
        map.setMap(attr);
        List<SfdaQxDO> list = getSqlMapClientTemplate().queryForList("yp.getGcQxInfo", map);
        return list;
    }

    @Override
    public List<SfdaQxDO> getJkQxByInstruName(String name, int pageIndex, int pageSize, Map<String, String> attr) {
        IbatisParamMap map = new IbatisParamMap("name", name, pageIndex, pageSize);
        map.setMap(attr);
        List<SfdaQxDO> list = getSqlMapClientTemplate().queryForList("yp.getJkQxInfo", map);
        return list;
    }

    @Override
    public List<SfdaQxDO> getGcQxByQyName(String name, int pageIndex, int pageSize, Map<String, String> attr) {
        IbatisParamMap map = new IbatisParamMap("name", name, pageIndex, pageSize);
        map.setMap(attr);
        List<SfdaQxDO> list = getSqlMapClientTemplate().queryForList("yp.getGcQxQyInfo", map);
        if(CollectionUtils.isEmpty(list)){
            return list;
        }
        return list;
    }

    @Override
    public List<SfdaQxDO> getJkQxByQyName(String name, int pageIndex, int pageSize, Map<String, String> attr) {
        IbatisParamMap map = new IbatisParamMap("name", name, pageIndex, pageSize);
        map.setMap(attr);
        List<SfdaQxDO> list = getSqlMapClientTemplate().queryForList("yp.getJkQxQyInfo", map);
        return list;
    }

    @Override
    public List<CMSBDO> getQxQySearchAll(int pageIndex, int pageSize) {
        IbatisParamMap map = new IbatisParamMap( pageIndex, pageSize);
        List<CMSBDO> list = getSqlMapClientTemplate().queryForList("yp.qSearchQxQyAll", map);
        return list;
    }

    @Override
    public List<Integer> getQxQyStructCnt(final List<SfdaQxDO> qxDOList) {
        final List<Integer> res= Lists.newArrayList();
        if(CollectionUtils.isEmpty(qxDOList)){
            return res;
        }
//        for(String cpmc: cpmcs){
//            int size= (int) getSqlMapClientTemplate().queryForObject("yp.isJbYw", cpmc);
//            res.add(size>0);
//        }
        getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
            @Override
            public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
                executor.startBatch();
                for (SfdaQxDO qxDO : qxDOList) {
                    String qxm=qxDO.getProduct_name();
                    int size = (int) executor.queryForObject("yp.getQxCnt", qxm);
                    res.add(size);
                }
                executor.executeBatch();
                return 0;
            }
        });
        return res;
    }

    @Override
    public List<LawData> getLawInfo(LawData lawData, int pageIndex, int pageSize, Map<String, String> attr) {
        List<LawData> list=  getSqlMapClientTemplate().queryForList("yp.getLawInfo", lawData);
        fillTimeRangeDesc(list);
        return list;
    }

    @Override
    public List<LawData> getLawInfoById(long id, int idx, int idv) {
        IbatisParamMap map = new IbatisParamMap("id", id, idx, idv);
        List<LawData> list=  getSqlMapClientTemplate().queryForList("yp.getLawInfoById", map);
        return list;
    }

    @Override
    public int insertLawInfo(final List<LawData> lawDatas, int idx, int idv, Map<String, String> attr) {
        if (lawDatas == null) {
            return -1;
        }
        getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
            public Object doInSqlMapClient(SqlMapExecutor executor)
                    throws SQLException {
                executor.startBatch();
                for (LawData user : lawDatas) {
                    executor.insert("yp.insertLawInfo", user);
                }
                executor.executeBatch();
                return lawDatas.size();
            }
        });
        return 0;
    }

    @Override
    public List<RsData> getRsInfo(RsData rsData, int idx, int idv, Map<String, String> attr) {
        List<RsData> list=  getSqlMapClientTemplate().queryForList("yp.getRsInfo", rsData);
        return list;
    }

    @Override
    public List<RsData> getRsInfoById(long id, int idx, int idv) {
        IbatisParamMap map = new IbatisParamMap("id", id, idx, idv);
        List<RsData> list=  getSqlMapClientTemplate().queryForList("yp.getRsInfoById", map);
        return list;
    }

    @Override
    public int insertRsInfo(final List<RsData> rsDatas, int idx, int idv, Map<String, String> attr) {
        if (rsDatas == null) {
            return -1;
        }
        getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
            public Object doInSqlMapClient(SqlMapExecutor executor)
                    throws SQLException {
                executor.startBatch();
                for (RsData user : rsDatas) {
                    executor.insert("yp.insertRsInfo", user);
                }
                executor.executeBatch();
                return rsDatas.size();
            }
        });
        return 0;
    }

    @Override
    public List<YYBasicInfo> getYiyuanBasicInfo(Map<String, Object> query, int idx, int idv) {
        IbatisParamMap map = new IbatisParamMap(idx, idv);
        map.setQueryMap(query);
        List<YYBasicInfo> list=  getSqlMapClientTemplate().queryForList("yp.getYiyuanBasicInfo", map);
        return list;
    }

    @Override
    public List<YYBasicInfo> getYiyuanBasicInfoByYymc(String yymc, int idx, int idv) {
        IbatisParamMap map = new IbatisParamMap("yymc",yymc,idx, idv);
        List<YYBasicInfo> list=  getSqlMapClientTemplate().queryForList("yp.getYiyuanBasicInfoByYymc", map);
        return list;
    }

    @Override
    public List<YiyuanCntDO> getYiyuanCntDOs(int idx, int idv) {
        IbatisParamMap map = new IbatisParamMap(idx, idv);
        List<YiyuanCntDO> list=  getSqlMapClientTemplate().queryForList("yp.getYiyuanCnt", map);
        return list;
    }

    @Override
    public List<CpmcZcDO> getCpmcZcDos(Map<String, Object> query, int pageIndex, int pageSize) {
        IbatisParamMap map = new IbatisParamMap(pageIndex, pageSize);
        map.setQueryMap(query);
        List<CpmcZcDO> list=  getSqlMapClientTemplate().queryForList("yp.getCpmcZcDO", map);
        return list;
    }

    @Override
    public List<CpmcZcDO> getCpmcZcDosByYpmc(String ypmc, int idx, int idv) {
        IbatisParamMap map = new IbatisParamMap("ypmc",ypmc,idx, idv);
        List<CpmcZcDO> list=  getSqlMapClientTemplate().queryForList("yp.getCpmcZcDOByYpmc", map);
        return list;
    }

    @Override
    public List<CpmcZcDO> getCpmcZcDosByQym(String qym, int idx, int idv, Map<String, String> attr) {
        IbatisParamMap map = new IbatisParamMap("qym",qym,idx, idv);
        map.setMap(attr);
        List<CpmcZcDO> list=  getSqlMapClientTemplate().queryForList("yp.getCpmcZcDOByQym", map);
        return list;
    }

    @Override
    public List<String> getYySearchStr(String qStr, int idx, int idv, Map<String, String> attr) {
        IbatisParamMap map = new IbatisParamMap("qStr", qStr, idx, idv);
        List<String> list = getSqlMapClientTemplate().queryForList("yp.qSearchYy", map);
        return list;
    }

    @Override
    public List<String> getSsSearchStr(String qStr, int pageIndex, int pageSize, Map<String, String> attr) {
        IbatisParamMap map = new IbatisParamMap("qStr", qStr, pageIndex, pageSize);
        List<String> list = getSqlMapClientTemplate().queryForList("yp.qSearchSs", map);
        Set<String> sets= Sets.newHashSet(list);
        return Lists.newArrayList(sets);
    }

    @Override
    public List<String> getYqZcSearchStr(String qStr, int pageIndex, int pageSize, Map<String, String> attr) {
        IbatisParamMap map = new IbatisParamMap("qStr", qStr, pageIndex, pageSize);
        List<String> list = getSqlMapClientTemplate().queryForList("yp.qSearchYqZc", map);
        return list;
    }

    @Override
    public List<String> getYpZcSearchStr(String qStr, int pageIndex, int pageSize, Map<String, String> attr) {
        IbatisParamMap map = new IbatisParamMap("qStr", qStr, pageIndex, pageSize);
        List<String> list = getSqlMapClientTemplate().queryForList("yp.qSearchYpZc", map);
        return list;
    }

    @Override
    public List<CMSBDO> getZcQyBasic(String qym, int pageIndex, int pageSize, Map<String, String> attr) {
        IbatisParamMap map = new IbatisParamMap("qym",qym,pageIndex, pageSize);
        map.setMap(attr);
        List<CMSBDO> list=  getSqlMapClientTemplate().queryForList("yp.getZcQyBasic", map);
        return list;
    }

    private void fillTimeRangeDesc(List<LawData> list){
        if(CollectionUtils.isEmpty(list)){
            return ;
        }
        for(LawData lawData:list){
            String time_range=lawData.getTime_range();
            if ("0".equals(time_range)) {
                lawData.setTime_range_desc("通用");
            }
            else if ("1".equals(time_range)) {
                lawData.setTime_range_desc("征求意见稿");
            }
            else if ("2".equals(time_range)) {
                lawData.setTime_range_desc("现行有效");
            }
            else if ("3".equals(time_range)) {
                lawData.setTime_range_desc("已失效");
            }
            else if ("4".equals(time_range)) {
                lawData.setTime_range_desc("已废止");
            }
            else {
                lawData.setTime_range_desc("其他");
            }
        }


    }
}
