package com.hj.dal.dao;

import com.google.common.collect.Lists;
import com.hj.client.object.list.QyPowerInfo;
import com.hj.dal.domain.dataobject.*;
import com.hj.dal.util.IbatisParamMap;
import com.ibatis.sqlmap.client.SqlMapExecutor;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.util.CollectionUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2015/1/31  15:03
 */
@SuppressWarnings("ALL")
public class YPDAOImpl extends SqlMapClientDaoSupport implements YPDAO {
    @Override
    public List<String> getYpSearchStr(String qStr, int pageIndex, int pageSize,Map<String,String> attr) {
        IbatisParamMap map = new IbatisParamMap("qStr", qStr, pageIndex, pageSize);
        if(!attr.containsKey("sale")) {
            List<String> list = getSqlMapClientTemplate().queryForList("yp.qSearch", map);
            return list;
        }else{
            List<String> list = getSqlMapClientTemplate().queryForList("yp.qSearchSale", map);
            return list;
        }

    }

    @Override
    public List<CMSBDO> getYpSearchAll(int pageIndex, int pageSize) {
        IbatisParamMap map = new IbatisParamMap(pageIndex, pageSize);
        List<CMSBDO> list = getSqlMapClientTemplate().queryForList("yp.qSearchAll", map);
        return list;
    }

    @Override
    public List<String> getQySearchStr(String qStr, int pageIndex, int pageSize,Map<String,String> attr) {
        IbatisParamMap map = new IbatisParamMap("qStr", qStr, pageIndex, pageSize);

        if(!attr.containsKey("sale")) {
            List<String> list = getSqlMapClientTemplate().queryForList("yp.qSearchQy", map);
            return list;
        }else{
            List<String> list = getSqlMapClientTemplate().queryForList("yp.qSearchQySale", map);
            return list;
        }

    }

    @Override
    public List<CMSBDO> getQySearchAll(int pageIndex, int pageSize) {
        IbatisParamMap map = new IbatisParamMap(pageIndex, pageSize);
        List<CMSBDO> list = getSqlMapClientTemplate().queryForList("yp.qSearchQyAll", map);
        return list;
    }

    @Override
    public List<SfdaGcypDO> getGcYpByCpmc(String cpmc, int pageIndex, int pageSize,Map<String,String> attr) {
        IbatisParamMap map = new IbatisParamMap("ypm", cpmc, pageIndex, pageSize);
        map.setMap(attr);
        List<SfdaGcypDO> list = getSqlMapClientTemplate().queryForList("yp.getGcYpByCpmc", map);
        return list;
    }

    @Override
    public List<SfdaGcypDO> getGcYpByQy(String qy, int pageIndex, int pageSize, Map<String, String> attr) {
        IbatisParamMap map = new IbatisParamMap("qy", qy, pageIndex, pageSize);
        map.setMap(attr);
        List<SfdaGcypDO> list = getSqlMapClientTemplate().queryForList("yp.getGcYpByQy", map);
        return list;
    }

    @Override
    public List<SfdaJkypDO> getJkYpByCpmc(String cpmc, int pageIndex, int pageSize,Map<String,String> attr) {
        IbatisParamMap map = new IbatisParamMap("ypm", cpmc, pageIndex, pageSize);
        map.setMap(attr);
        List<SfdaJkypDO> list = getSqlMapClientTemplate().queryForList("yp.getJkYpByCpmc", map);
        return list;
    }

    @Override
    public List<SfdaJkypDO> getJkYpByQy(String qy, int pageIndex, int pageSize, Map<String, String> attr) {
        IbatisParamMap map = new IbatisParamMap("qy", qy, pageIndex, pageSize);
        map.setMap(attr);
        List<SfdaJkypDO> list = getSqlMapClientTemplate().queryForList("yp.getJkYpByQy", map);
        return list;
    }

    @Override
    public List<SfdaJbywDO> getJbYwByCpmc(String cpmc, int pageIndex, int pageSize) {
        IbatisParamMap map = new IbatisParamMap("ypm", cpmc, pageIndex, pageSize);
        List<SfdaJbywDO> list = getSqlMapClientTemplate().queryForList("yp.getJbYwByCpmc", map);
        return list;
    }

    @Override
    public List<MenetYibaoDO> getYbYwByCpmc(String cpmc, int pageIndex, int pageSize) {
        IbatisParamMap map = new IbatisParamMap("ypm", cpmc, pageIndex, pageSize);
        List<MenetYibaoDO> list = getSqlMapClientTemplate().queryForList("yp.getYbYwByCpmc", map);
        return list;
    }

    @Override
    public List<Boolean> isJbYw(final List<String> cpmcs) {
        final List<Boolean> res= Lists.newArrayList();
        if(CollectionUtils.isEmpty(cpmcs)){
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
                for (String cpmc : cpmcs) {
                    int size = (int) executor.queryForObject("yp.isJbYw", cpmc);
                    res.add(size>0);
                }
                executor.executeBatch();
                return 0;
            }
        });
        return res;
    }

    @Override
    public List<Boolean> isYbYw(final List<String> cpmcs) {
        final List<Boolean> res= Lists.newArrayList();
        if(CollectionUtils.isEmpty(cpmcs)){
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
                for (String cpmc : cpmcs) {
                    int size = (int) executor.queryForObject("yp.isYbYw", cpmc);
                    res.add(size>0);
                }
                executor.executeBatch();
                return 0;
            }
        });
        return res;
    }

    @Override
    public List<String> getYblx(final List<String> cpmcs) {
        final List<String> res= Lists.newArrayList();
        if(CollectionUtils.isEmpty(cpmcs)){
            return res;
        }
//        for(String cpmc: cpmcs){
//            String yblx= (String) getSqlMapClientTemplate().queryForObject("yp.getYblx", cpmc);
//            res.add(yblx);
//        }
        getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
            @Override
            public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
                executor.startBatch();
                for (String cpmc : cpmcs) {
                    String yblx = (String) executor.queryForObject("yp.getYblx", cpmc);
                    res.add(yblx);
                }
                executor.executeBatch();
                return 0;
            }
        });
        return res;
    }

    @Override
    public List<String> getOTCfl(final List<String> cpmcs) {
        final List<String> res= Lists.newArrayList();
        if(CollectionUtils.isEmpty(cpmcs)){
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
                for (String cpmc : cpmcs) {
                    String otcfl = (String) executor.queryForObject("yp.getOTCfl", cpmc);
                    res.add(otcfl);
                }
                executor.executeBatch();
                return 0;
            }
        });
        return res;
    }

    @Override
    public List<CMSBDO> getYPPWRank(String cpmc, int pageIndex, int pageSize) {
        IbatisParamMap map = new IbatisParamMap("cpmc", cpmc, pageIndex, pageSize);
        List<CMSBDO> list = getSqlMapClientTemplate().queryForList("yp.qSearchPW", map);
        return list;
    }

    @Override
    public List<CMSBDO> getQyPWRank(String qy, int pageIndex, int pageSize) {
        IbatisParamMap map = new IbatisParamMap("qy", qy, pageIndex, pageSize);
        List<CMSBDO> list = getSqlMapClientTemplate().queryForList("yp.qSearchQyPW", map);
        return list;
    }

    @Override
    public List<XywyJcxxDO> getXywyJcxx(String cpmc, int pageIndex, int pageSize, Map<String, String> attr) {
        IbatisParamMap map = new IbatisParamMap("ypm", cpmc, pageIndex, pageSize);
        map.setMap(attr);
        List<XywyJcxxDO> list = getSqlMapClientTemplate().queryForList("yp.getXywyJcxx", map);
        return list;
    }

    @Override
    public List<YgyZdcsSaleDO> getZdcsSale(String cpmc, int pageIndex, int pageSize, Map<String, String> attr) {
        IbatisParamMap map = new IbatisParamMap("ypm", cpmc, pageIndex, pageSize);
        map.setMap(attr);
        List<YgyZdcsSaleDO> list = getSqlMapClientTemplate().queryForList("yp.getZdcsSale", map);
        return list;
    }

    @Override
    public List<YgyZdcsSaleDO> getZdcsSaleByQy(String qy, int pageIndex, int pageSize, Map<String, String> attr) {
        IbatisParamMap map = new IbatisParamMap("qy", qy, pageIndex, pageSize);
        map.setMap(attr);
        List<YgyZdcsSaleDO> list = getSqlMapClientTemplate().queryForList("yp.getZdcsSaleByQy", map);
        return list;
    }

    @Override
    public List<QyPowerInfo> getQyPowerInfo(String qy, int pageIndex, int pageSize, Map<String, String> attr) {
        IbatisParamMap map = new IbatisParamMap("qy", qy, pageIndex, pageSize);
        map.setMap(attr);
        List<SaleDO> list=getSqlMapClientTemplate().queryForList("yp.getQyPowerInfo", map);
        List<QyPowerInfo> res=Lists.newArrayList();
        if(CollectionUtils.isEmpty(list)){
            return res;
        }
        for(SaleDO cmsbdo:list){
            QyPowerInfo powerInfo=new QyPowerInfo();
            powerInfo.setQyName(cmsbdo.getQyName());
            powerInfo.setSale(cmsbdo.getXse());
            res.add(powerInfo);
        }
        return res;
    }

    @Override
    public List<CMSBDO> getZcYpAll(int idx, int idv) {
        IbatisParamMap map = new IbatisParamMap(idx, idv);
        List<CMSBDO> list = getSqlMapClientTemplate().queryForList("yp.getZcYpAll", map);
        return list;
    }


}
