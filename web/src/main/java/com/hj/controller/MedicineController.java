package com.hj.controller;

import com.google.common.collect.Maps;
import com.hj.biz.GraphIn;
import com.hj.biz.service.YiyaoService;
import com.hj.biz.util.BizProcess;
import com.hj.biz.util.YPUtil;
import com.hj.client.object.list.*;
import com.hj.dal.domain.dataobject.CMSBDO;
import com.hj.dal.domain.dataobject.YgyZdcsSaleDO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Writer;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.hj.dal.Constants.idv;
import static com.hj.dal.Constants.idx;
import static com.hj.biz.util.GraphEngine.*;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2015/1/30  20:02
 */
@Controller
@RequestMapping(value = "medicine")
public class MedicineController {

    static final Logger log = LoggerFactory.getLogger(MedicineController.class);

    @Resource
    YiyaoService ypService;

    @RequestMapping(value="iframe")
    @ResponseBody
    public ModelAndView iframe(HttpServletRequest request) throws Exception{
        String cpmc= (String) request.getAttribute("cpmc");
        List<YpPwBasicInfo> list = ypService.getYpPwBasicInfo(cpmc, 0, 10);
        Map<String, Object> res = Maps.newHashMap();
        res.put("pzwh", list);
        return new ModelAndView("iframe", "result", res);
    }

    @RequestMapping(value = "basicInfo")
    @ResponseBody
    public ModelAndView basiciInfo(HttpServletRequest request, HttpServletResponse response) {
        String num = (String) request.getAttribute("pageNum");
        String cpmc= (String) request.getAttribute("cpmc");
        int pageNum=1;
        if(!StringUtils.isEmpty(num)){
            pageNum=Integer.parseInt(num);
        }
        if(StringUtils.isEmpty(cpmc)) {
            cpmc = request.getParameter("cpmc");
        }
        Map<String, Object> result = Maps.newHashMap();
        if (StringUtils.isEmpty(cpmc)) {
            return new ModelAndView("basicInfo", "result", result);
        }
        List<YpBasicInfo> ypBasicInfos = ypService.getYpBasicInfo(cpmc, idx, idv);

        result.put("info", true);
        boolean isEn = false;
        if (YPUtil.isEn(cpmc)) {
            isEn = true;
        }
        result.put("isEn", isEn);
        int size = 0;
        if (!CollectionUtils.isEmpty(ypBasicInfos)) {
            size = ypBasicInfos.size();
        }
        PageBean pageBean = new PageBean(pageNum, size);
        int start = pageBean.getStart();
        int end= Math.min(size,start+PageBean.SIZE);
        result.put("ypBasic", ypBasicInfos.subList(pageBean.getStart(), end));
        result.put("pageBean",pageBean);
        result.put("cpmcHidden",cpmc);
        return new ModelAndView("basicInfo", "result", result);
    }

    @RequestMapping(value = "basicDetail")
    @ResponseBody
    public ModelAndView basicDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, String> qs = parseQueryString(request.getQueryString());
        String cpmc = qs.get("cpmc");
        String isjbyw = qs.get("isJbyw").equals("y") ? "是" : "否";
        String isYbyw = qs.get("isYbyw").equals("y") ? "是" : "否";
        String pwcj = qs.get("pwcj");
        List<YpPwBasicInfo> list = ypService.getYpPwBasicInfo(cpmc, 0, 10);
        Map<String, Object> res = Maps.newHashMap();

        res.put("cpmcHidden", cpmc);
        res.put("spm", cpmc);
        res.put("isjbyw", isjbyw);
        res.put("isYbyw", isYbyw);
        res.put("pwcj", pwcj);

        //演示 for kunsat
        List<CMSBDO> cmsbdos = ypService.getYpAll(idx, idv);
        Collections.sort(cmsbdos);
        GraphIn graphIn = new GraphIn();
        graphIn.setCpmc(cpmc);
        graphIn.setCmsbdos(cmsbdos);
        graphIn.setYpPwBasicInfos(list);
        String jxPie = getGraphJsonStr("base_jxPie", null, graphIn);//只有获取药品批文数量排行信息才需要第二个字段
        String cpPie = getGraphJsonStr("base_pcPie", null, graphIn);
        String pwLine = getGraphJsonStr("base_yppwLine", cpmc, graphIn);

        if(!CollectionUtils.isEmpty(list) && list.size()> BizProcess.UP_COUNT){
            list = list.subList(0,BizProcess.UP_COUNT);
        }
        res.put("pzwh", list);
        res.put("base_jxPie", jxPie);//取后缀那部分的标识即可
        res.put("base_pcPie", cpPie);
        res.put("base_yppwLine", pwLine);
        return new ModelAndView("medicineInfo1", "result", res);
    }

    @RequestMapping(value = "powerInfo")
    @ResponseBody
    public ModelAndView powerInfo(HttpServletRequest request, HttpServletResponse response) {
        String num = (String) request.getAttribute("pageNum");
        String cpmc= (String) request.getAttribute("cpmc");
        int pageNum=1;
        if(!StringUtils.isEmpty(num)){
            pageNum=Integer.parseInt(num);
        }
        if(StringUtils.isEmpty(cpmc)) {
            cpmc = request.getParameter("cpmc");
        }
        Map<String, Object> result = Maps.newHashMap();
        if (StringUtils.isEmpty(cpmc)) {
            return new ModelAndView("powerInfo", "result", result);
        }
        List<YpPowerInfo> ypPowerInfo = ypService.getYpPowerInfo(cpmc, idx, idv, true);
        result.put("info", true);
        boolean isEn = false;
        if (YPUtil.isEn(cpmc)) {
            isEn = true;
        }
        result.put("isEn", isEn);
        int size = 0;
        if (!CollectionUtils.isEmpty(ypPowerInfo)) {
            size = ypPowerInfo.size();
        }
        PageBean pageBean = new PageBean(pageNum, size);
        int start = pageBean.getStart();
        int end= Math.min(size,start+PageBean.SIZE);
        result.put("ypPower", ypPowerInfo.subList(pageBean.getStart(), end));
        result.put("pageBean",pageBean);
        result.put("cpmcHidden",cpmc);
        return new ModelAndView("powerInfo", "result", result);
    }

    @RequestMapping(value = "powerDetail")
    @ResponseBody
    public ModelAndView powerDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, String> qs = parseQueryString(request.getQueryString());
        String cpmc = qs.get("cpmc");
        String isjbyw = qs.get("isJbyw").equals("y") ? "是" : "否";
        String disease = qs.get("disease");
        String tym = qs.get("tym");
        String ybfl = qs.get("ybfl");
        String otc = qs.get("otc");
        String zldl = qs.get("zldl");
        String zlxl = qs.get("zlxl");
        Map<String, Object> res = Maps.newHashMap();
        res.put("cpmcHidden", cpmc);
        res.put("isjbyw", isjbyw);
        res.put("disease", disease);
        res.put("tym", tym);
        res.put("ybfl", ybfl);
        res.put("otc", otc);
        res.put("zldl", zldl);
        res.put("zlxl", zlxl);
        List<YgyZdcsSaleDO> saleDOs = ypService.getZdcsSale(cpmc, idx, idv);
        GraphIn graphIn = new GraphIn();
        graphIn.setYgyZdcsSaleDOs(saleDOs);
        String qyPie = getGraphJsonStr("power_ypqyPie", null, graphIn);//只有获取药品批文数量排行信息才需要第二个字段
        String jxPie = getGraphJsonStr("power_ypjxPie", null, graphIn);
        String qyLine = getGraphJsonStr("power_qyLine", cpmc, graphIn);
        res.put("power_ypqyPie", qyPie);//取后缀那部分的标识即可
        res.put("power_ypjxPie", jxPie);
        res.put("power_qyLine", qyLine);

        return new ModelAndView("powerDetail", "result", res);
    }

    @RequestMapping(value = "qyPowerInfo")
    @ResponseBody
    public ModelAndView getQyPowerInfo(HttpServletRequest request) {
        String num = (String) request.getAttribute("pageNum");
        String name= (String) request.getAttribute("name");
        int pageNum=1;
        if(!StringUtils.isEmpty(num)){
            pageNum=Integer.parseInt(num);
        }
        if(StringUtils.isEmpty(name)) {
            name = request.getParameter("name");
        }

        Map<String, Object> result = Maps.newHashMap();
        ModelAndView modelAndView = new ModelAndView("qyPowerInfo", "result", result);
        if (StringUtils.isEmpty(name)) {
            return modelAndView;
        }
        List<QyPowerInfo> qyPowerInfos = ypService.getQyPowerInfo(name, idx, idv);
        int size = 0;
        if (!CollectionUtils.isEmpty(qyPowerInfos)) {
            size = qyPowerInfos.size();
        }
        PageBean pageBean = new PageBean(pageNum, size);
        int start = pageBean.getStart();
        int end= Math.min(size,start+PageBean.SIZE);
        result.put("qyPower", qyPowerInfos.subList(pageBean.getStart(), end));
        result.put("pageBean",pageBean);
        result.put("cpmcHidden",name);
        result.put("info", true);
        return modelAndView;
    }

    @RequestMapping(value = "qyPowerDetail")
    @ResponseBody
    public ModelAndView getQyPowerDetail(HttpServletRequest request) throws Exception {
        String name = (String) request.getAttribute("qym");
        Map<String, Object> res = Maps.newHashMap();
        ModelAndView modelAndView = new ModelAndView("qyPowerDetail", "result", res);
        if (StringUtils.isEmpty(name)) {
            return modelAndView;
        }

        long time1 = System.currentTimeMillis();
        List<YgyZdcsSaleDO> saleDOs = ypService.getZdcsSaleByQy(name, idx, idv);
        List<QyPowerDetailInfo> qyPowerInfos = ypService.getQyPowerDetailInfo(name, idx, idv, saleDOs);
        long time3 = System.currentTimeMillis();
        log.info("query db time cost: " + (time3 - time1) + "ms");

        res.put("qym", name);
        res.put("info", true);

        GraphIn graphIn = new GraphIn();
        graphIn.setYgyZdcsSaleDOs(saleDOs);
        graphIn.setQyPowerDetailInfos(qyPowerInfos);
        String cpPie = getGraphJsonStr("qypower_cpPie", null, graphIn);//只有获取药品批文数量排行信息才需要第二个字段
        String zlPie = getGraphJsonStr("qypower_zlPie", null, graphIn);
        String jyPie = getGraphJsonStr("qypower_jyPie", null, graphIn);
        String ybPie = getGraphJsonStr("qypower_ybPie", null, graphIn);
        String otcPie = getGraphJsonStr("qypower_otcPie", null, graphIn);
        String saleZone = getGraphJsonStr("qypower_salezone", null, graphIn);
        String saleTrend = getGraphJsonStr("qypower_saleline", null, graphIn);
        long time5 = System.currentTimeMillis();
        log.info("gen graph time cost: " + (time5 - time3) + "ms");


        res.put("qypower_cpPie", cpPie);
        res.put("qypower_zlPie", zlPie);
        res.put("qypower_jyPie", jyPie);
        res.put("qypower_ybPie", ybPie);
        res.put("qypower_otcPie", otcPie);
        res.put("qypower_salezone", saleZone);
        res.put("qypower_saleline", saleTrend);

        if(!CollectionUtils.isEmpty(qyPowerInfos) && qyPowerInfos.size()> BizProcess.UP_COUNT){
            qyPowerInfos = qyPowerInfos.subList(0,BizProcess.UP_COUNT);
        }
        res.put("qyPowers", qyPowerInfos);
        return modelAndView;
    }

    @RequestMapping(value = "qypw")
    @ResponseBody
    public ModelAndView getQypw(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String num = (String) request.getAttribute("pageNum");
        String qym= (String) request.getAttribute("qym");
        int pageNum=1;
        if(!StringUtils.isEmpty(num)){
            pageNum=Integer.parseInt(num);
        }
        if(StringUtils.isEmpty(qym)) {
            qym = request.getParameter("qym");
        }

        Map<String, Object> res = Maps.newHashMap();
        if (StringUtils.isEmpty(qym)) {
            return new ModelAndView("qypw", "result", res);
        }
        List<QyBasicInfo> qyBasicInfo = ypService.getQyBasicInfo(qym, idx, idv);
        res.put("info", true);
        boolean isEn = false;
        if (YPUtil.isEn(qym)) {
            isEn = true;
        }
        res.put("isEn", isEn);
        int size = 0;
        if (!CollectionUtils.isEmpty(qyBasicInfo)) {
            size = qyBasicInfo.size();
        }
        PageBean pageBean = new PageBean(pageNum, size);
        int start = pageBean.getStart();
        int end= Math.min(size,start+PageBean.SIZE);
        res.put("basic", qyBasicInfo.subList(pageBean.getStart(), end));
        res.put("pageBean",pageBean);
        res.put("cpmcHidden",qym);

        return new ModelAndView("qypw", "result", res);
    }

    @RequestMapping(value = "qypwDetail")
    @ResponseBody
    public ModelAndView qypwDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String qym= (String) request.getAttribute("qym");
        Map<String, Object> res = Maps.newHashMap();
        List<QyPwBasicInfo> qyPwBasicInfos = ypService.getQyPwBasicInfo(qym, idx, idv);
        res.put("qym", qym);

        if(!CollectionUtils.isEmpty(qyPwBasicInfos) && qyPwBasicInfos.size()> BizProcess.UP_COUNT){
            qyPwBasicInfos = qyPwBasicInfos.subList(0,BizProcess.UP_COUNT);
        }
        res.put("qypwBasicInfos", qyPwBasicInfos);


        List<QyPwBasicInfo> saleDOs = ypService.getQyPwBasicInfo(qym, idx, idv);
        List<CMSBDO> cmsbdos = ypService.getQyAll(idx, idv);
        Collections.sort(cmsbdos);
        GraphIn graphIn = new GraphIn();
        graphIn.setQym(qym);
        graphIn.setQyPwBasicInfos(saleDOs);
        graphIn.setCmsbdos(cmsbdos);
        String qyPie = getGraphJsonStr("piwen_qyPie", null, graphIn);//只有获取药品批文数量排行信息才需要第二个字段
        String jxPie = getGraphJsonStr("piwen_jxPie", null, graphIn);
        String qyLine = getGraphJsonStr("piwen_rank", qym, graphIn);
        res.put("piwen_qyPie", qyPie);//取后缀那部分的标识即可
        res.put("piwen_jxPie", jxPie);
        res.put("piwen_rank", qyLine);
        return new ModelAndView("qypwDetail", "result", res);
    }

    @RequestMapping(value = "getAssociation", method = RequestMethod.GET)
    public String test3(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, String> qs = parseQueryString(request.getQueryString());
        response.setContentType("text/javascript");
        response.setCharacterEncoding("UTF-8");
        Writer out = response.getWriter();
        out.write(ypService.getSearchAssociation(qs));
        return null;
    }

    @RequestMapping(value = "getGraph", method = RequestMethod.GET)
    @ResponseBody
    public String getGraph(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, String> qs = parseQueryString(request.getQueryString());
        String search = qs.get("serch");
        String biz = qs.get("name");
        return getGraphJsonStr(biz, search, null);
    }

    private String getGraphJsonStr(String className, String name, GraphIn graphIn) throws Exception {
        return getGraphStr(ypService, className, name, graphIn);
    }

    Map<String, String> parseQueryString(String s) throws Exception {
        if (StringUtils.isBlank(s)) {
            return Maps.newHashMap();
        }
        String[] rr = s.split("&");
        Map<String, String> map = Maps.newHashMap();
        for (String r : rr) {
            String[] tmp = r.split("=");
            if (tmp.length == 2) {
                map.put(tmp[0], URLDecoder.decode(tmp[1], "utf-8"));
            }
            else {
                map.put(tmp[0], "/");
            }
        }
        return map;
    }

}
