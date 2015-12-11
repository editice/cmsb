package com.hj.biz.util;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 图表映射类
 * @author tinglang (editice@gmail.com)
 * @since 2015/2/7  20:42
 */
public class BizProcess {

    public static final String BASE_PACKAGE = "com.hj.biz.generator.";

    public static final String YAO_PW_RANK_GEN="YaoPwRankGen";
    public static final String YAO_JX_PRO_GEN="YaoJxProGen";
    public static final String YAO_CP_PRO_GEN="YaoCpProGen";

    public static final String POWER_QY_PRO_GEN="PowerQyProGen";
    public static final String POWER_JX_PRO_GEN="PowerJxProGen";
    public static final String POWER_INCR_PRO_GEN="PowerIncrProGen";

    public static final String QY_PW_RANK_GEN="QyPwRankGen";
    public static final String QY_JX_PRO_GEN="QyJxProGen";
    public static final String QY_CP_PRO_GEN="QyCpProGen";

    public static final String QY_POWER_CP_PRO_GEN = "QyPowerCpProGen";
    public static final String QY_POWER_ZL_PRO_GEN = "QyPowerZlProGen";
    public static final String QY_POWER_JY_PRO_GEN = "QyPowerJyProGen";
    public static final String QY_POWER_YB_PRO_GEN = "QyPowerYbProGen";
    public static final String QY_POWER_OTC_PRO_GEN = "QyPowerOTCProGen";
    public static final String QY_POWER_SALE_ZONE_PRO_GEN = "QyPowerSaleZoneProGen";
    public static final String QY_POWER_SALE_TREND_PRO_GEN = "QyPowerSaleTrendProGen";

    public static final String QX_GCJK_PRO_GEN="QxGcjkProGen";

    public static final String QX_QY_RANK_PRO_GEN="QxQyRankProGen";

    public static final String YY_CWS_ALL_RANK_GEN="YyCwsAllRankGen";
    public static final String YY_CWS_PROVINCE_RANK_GEN="YyCwsProvinceRankGen";
    public static final String YY_RMZL_ALL_RANK_GEN="YyRmzlAllRankGen";
    public static final String YY_RMZL_PROVINCE_RANK_GEN="YyRmzlProvinceRankGen";

    public static final String ZC_POWER_RANK_PRO_GEN="ZcPowerRankProGen";
    public static final String ZC_YPLX_PRO_GEN="ZcYplxProGen";
    public static final String ZC_SQLX_PRO_GEN="ZcSqlxProGen";
    public static final String ZC_ZCFL_PRO_GEN="ZcZcflProGen";
    public static final String ZC_PSZT_PRO_GEN="ZcPsztProGen";

    public static final String ZC_QY_YPLX_PRO_GEN="ZcQyYplxProGen";
    public static final String ZC_QY_SQLX_PRO_GEN="ZcQySqlxProGen";
    public static final String ZC_QY_PSZT_PRO_GEN="ZcQyPsztProGen";


    public static final int UP_COUNT = 10;


    static Map<String,String> map= Maps.newHashMap();
    static{
        //药品基本信息
        map.put("base_yppwLine", YAO_PW_RANK_GEN);   //药品批文数量排序
        map.put("base_jxPie", YAO_JX_PRO_GEN);   // 剂型占比
        map.put("base_pcPie",YAO_CP_PRO_GEN);//产品类型占比

        //药品竞争力分析
        map.put("power_ypqyPie",POWER_QY_PRO_GEN);//药品各企业销售占比
        map.put("power_ypjxPie",POWER_JX_PRO_GEN);//药品各剂型销售占比
        map.put("power_qyLine",POWER_INCR_PRO_GEN); //企业销售

        //企业批文号信息
        map.put("piwen_rank",QY_PW_RANK_GEN);//企业批文排名
        map.put("piwen_jxPie",QY_JX_PRO_GEN);//企业剂型批文占比
        map.put("piwen_qyPie",QY_CP_PRO_GEN); //企业产品批文占比

        //企业产品线竞争力分析
        map.put("qypower_cpPie",QY_POWER_CP_PRO_GEN); //各药品销售占比
        map.put("qypower_zlPie",QY_POWER_ZL_PRO_GEN); //各药品治疗区域销售占比
        map.put("qypower_jyPie",QY_POWER_JY_PRO_GEN);//是否基药销售占比
        map.put("qypower_ybPie",QY_POWER_YB_PRO_GEN);//医保情况销售占比
        map.put("qypower_otcPie",QY_POWER_OTC_PRO_GEN); //OTC情况销售占比
        map.put("qypower_salezone",QY_POWER_SALE_ZONE_PRO_GEN);//销售区域地图
        map.put("qypower_saleline",QY_POWER_SALE_TREND_PRO_GEN); //季度销售区域

        //医疗器械产品查询
        map.put("qx_from",QX_GCJK_PRO_GEN);

        //医疗器械企业产品结构查询
        map.put("qxQy_rank",QX_QY_RANK_PRO_GEN);

        //全国等级医院查询
        map.put("yy_cws_all_rank",YY_CWS_ALL_RANK_GEN);
        map.put("yy_cws_province_rank",YY_CWS_PROVINCE_RANK_GEN);
        map.put("yy_rmzl_all_rank",YY_RMZL_ALL_RANK_GEN);
        map.put("yy_rmzl_province_rank",YY_RMZL_PROVINCE_RANK_GEN);

        //药品注册申报查询
        map.put("zc_power_rank",ZC_POWER_RANK_PRO_GEN);
        map.put("zc_yplx",ZC_YPLX_PRO_GEN);
        map.put("zc_sqlx",ZC_SQLX_PRO_GEN);
        map.put("zc_zcfl",ZC_ZCFL_PRO_GEN);
        map.put("zc_pszt",ZC_PSZT_PRO_GEN);

        //药企注册申报查询
        map.put("zc_qy_yplx",ZC_QY_YPLX_PRO_GEN);
        map.put("zc_qy_sqlx",ZC_QY_SQLX_PRO_GEN);
        map.put("zc_qy_pszt",ZC_QY_PSZT_PRO_GEN);

    }

    public static String getBizClass(String bizKey){
        return map.get(bizKey);
    }

}
