package com.hj.biz.util;

import com.alibaba.fastjson.JSON;
import com.hj.biz.GraphGenerator;
import com.hj.biz.GraphIn;
import com.hj.biz.service.YiyaoService;
import com.hj.client.object.GraphData;
import com.hj.client.object.list.QyPowerDetailInfo;
import com.hj.client.object.list.QyPwBasicInfo;
import com.hj.client.object.list.YpPwBasicInfo;
import com.hj.dal.domain.dataobject.CMSBDO;
import com.hj.dal.domain.dataobject.YgyZdcsSaleDO;

import java.util.List;

import static com.hj.biz.util.BizProcess.*;
import static com.hj.dal.Constants.idv;
import static com.hj.dal.Constants.idx;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2015/3/12  19:46
 */
public class GraphEngine {

    /**
     * 生成图表输入数据
     */
    public static  GraphIn genGraphIn(YiyaoService ypService,String className, String name) {
        GraphIn graphIn = new GraphIn();
        if (className.equals(YAO_CP_PRO_GEN) || className.equals(YAO_JX_PRO_GEN)) {
            List<YpPwBasicInfo> list = ypService.getYpPwBasicInfo(name, idx, idv);
            graphIn.setYpPwBasicInfos(list);
        }
        if (className.equals(YAO_PW_RANK_GEN)) {
            List<CMSBDO> list = ypService.getYPPwRankInfo(name, 0, 20);
            graphIn.setCmsbdos(list);
        }
        if (className.equals(POWER_QY_PRO_GEN) || className.equals(POWER_JX_PRO_GEN)) {
            //先mock数据
            List<YgyZdcsSaleDO> list = ypService.getZdcsSale(name, idx, idv);
            graphIn.setYgyZdcsSaleDOs(list);
        }
        if (className.equals(QY_CP_PRO_GEN) || className.equals(QY_JX_PRO_GEN)) {
            List<QyPwBasicInfo> list = ypService.getQyPwBasicInfo(name, idx, idv);
            graphIn.setQyPwBasicInfos(list);
        }
        if (className.equals(QY_PW_RANK_GEN)) {
            List<CMSBDO> list = ypService.getQyPwRankInfo(name, 0, 20);
            graphIn.setCmsbdos(list);
        }
        if (className.equals(QY_POWER_CP_PRO_GEN) || className.equals(QY_POWER_ZL_PRO_GEN)) {
            List<YgyZdcsSaleDO> list = ypService.getZdcsSaleByQy(name, idx, idv);
            graphIn.setYgyZdcsSaleDOs(list);
        }
        if (className.equals(QY_POWER_JY_PRO_GEN) || className.equals(QY_POWER_YB_PRO_GEN) || className.equals(QY_POWER_OTC_PRO_GEN)) {
            List<QyPowerDetailInfo> list = ypService.getQyPowerDetailInfo(name, idx, idv,null);
            graphIn.setQyPowerDetailInfos(list);
        }
        return graphIn;
    }

    public static String getGraphStr(YiyaoService ypService,String className, String name, GraphIn graphIn) throws Exception {
        className = BizProcess.getBizClass(className);
        if (graphIn == null) {
            graphIn = genGraphIn(ypService, className, name);
        }
        String packageName = BASE_PACKAGE;
        Object object = Class.forName(packageName + className).getConstructors()[0].newInstance(graphIn);
        GraphGenerator generator = (GraphGenerator) object;
        GraphData graphData = generator.generate();
        return JSON.toJSONString(graphData);
    }
}
