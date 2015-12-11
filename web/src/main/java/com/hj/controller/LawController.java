package com.hj.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hj.biz.GraphIn;
import com.hj.biz.service.Fill;
import com.hj.biz.service.YiyaoService;
import com.hj.biz.util.DateUtil;
import com.hj.biz.util.YPUtil;
import com.hj.client.object.list.*;
import com.hj.client.util.FileUtil;
import com.hj.dal.domain.dataobject.CMSBDO;
import com.hj.dal.domain.dataobject.CpmcZcDO;
import com.hj.dal.domain.dataobject.YiyuanCntDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.hj.biz.util.DropBoxUtil.*;
import static com.hj.client.util.ParamUtil.getPageNum;
import static com.hj.client.util.ParamUtil.getParam;
import static com.hj.dal.Constants.idv;
import static com.hj.dal.Constants.idx;

import static com.hj.biz.util.GraphEngine.*;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2015/3/12  21:00
 */
@Controller
@RequestMapping(value = "law")
public class LawController {
    static final Logger log = LoggerFactory.getLogger(LawController.class);

    @Resource
    YiyaoService ypService;

    @RequestMapping(value = "lawBasicInfo")
    @ResponseBody
    public ModelAndView lawBasicInfo(HttpServletRequest request) throws Exception {
        int pageNum = getPageNum(request, "pageNum");
        if (pageNum <= 0) {
            pageNum =1;
            LawData lawData =new LawData();
            List<LawData> lawDatas = ypService.getLawInfo(lawData, idx, idv, YPUtil.genAttr());
            Map<String, Object> result = Maps.newHashMap();

            int size = 0;
            if (!CollectionUtils.isEmpty(lawDatas)) {
                size = lawDatas.size();
            }
            PageBean pageBean = new PageBean(pageNum, size);
            int start = pageBean.getStart();
            int end = Math.min(size, start + PageBean.SIZE);
            result.put("lawDatas", lawDatas.subList(start, end));
            result.put("pageBean", pageBean);
            result.put("info", true);
            return new ModelAndView("lawBasicInfo", "result", result);
        }

        String title = getParam(request, "title");
        String content = getParam(request, "content");
        String time = getParam(request, "time");
        String level = getParam(request, "level");
        String time_range = getParam(request, "time_range");
        String department = getParam(request, "department");

        level = filterOption(level);
        time_range = filterOption(time_range);
        department = filterOption(department);

        LawData lawData = new LawData();
        lawData.setTitle(title);
        lawData.setContent(content);
        lawData.setTime(time);
        lawData.setLevel(level);
        lawData.setTime_range(time_range);
        lawData.setDepartment(department);


        List<LawData> lawDatas = ypService.getLawInfo(lawData, idx, idv, YPUtil.genAttr());
        Map<String, Object> result = Maps.newHashMap();

        int size = 0;
        if (!CollectionUtils.isEmpty(lawDatas)) {
            size = lawDatas.size();
        }
        PageBean pageBean = new PageBean(pageNum, size);
        int start = pageBean.getStart();
        int end = Math.min(size, start + PageBean.SIZE);
        result.put("lawDatas", lawDatas.subList(start, end));
        result.put("pageBean", pageBean);
        result.put("info", true);
        result.put("title", title);
        result.put("content", content);
        result.put("time", time);
        result.put("level", level);
        result.put("time_range", time_range);
        result.put("department", department);
        return new ModelAndView("lawBasicInfo", "result", result);
    }

    @RequestMapping(value = "lawUpload")
    @ResponseBody
    public ModelAndView lawUpload(@RequestParam(value = "lawfile", required = false) CommonsMultipartFile file, HttpServletRequest request) throws Exception {
        if (file == null) {
            return new ModelAndView("lawUpload", "result", null);
        }

        String title = request.getParameter("title");
        String time = request.getParameter("time");
        String level = request.getParameter("level");
        String time_range = request.getParameter("time_range");
        String department = request.getParameter("department");


        String filePath = FileUtil.upload(file.getBytes(), file.getFileItem().getName(), "law");

        level = filterOption(level);
        time_range = filterOption(time_range);
        department = filterOption(department);

        LawData lawData = new LawData();
        lawData.setTitle(title);
        lawData.setContent("");
        lawData.setTime(time);
        lawData.setLevel(level);
        lawData.setTime_range(time_range);
        lawData.setDepartment(department);
        lawData.setPath(filePath);


        ypService.insertLawInfo(Lists.newArrayList(lawData), idx, idv, null);
        Map<String, Object> result = Maps.newHashMap();
        result.put("res", true);
        return new ModelAndView("lawUpload", "result", result);
    }

    @RequestMapping(value = "rsBasicInfo")
    @ResponseBody
    public ModelAndView rsBasicInfo(HttpServletRequest request) throws Exception {
        int pageNum = getPageNum(request, "pageNum");
        if (pageNum <= 0) {
            pageNum =1;
            RsData rsData = new RsData();

            List<RsData> lawDatas = ypService.getRsInfo(rsData, idx, idv, YPUtil.genAttr());
            Collections.sort(lawDatas);
            Map<String, Object> result = Maps.newHashMap();
            int size = 0;
            if (!CollectionUtils.isEmpty(lawDatas)) {
                size = lawDatas.size();
            }
            PageBean pageBean = new PageBean(pageNum, size);
            int start = pageBean.getStart();
            int end = Math.min(size, start + PageBean.SIZE);
            result.put("pageBean", pageBean);
            result.put("rsDatas", lawDatas.subList(start, end));
            result.put("info", true);
            result.put("rank",true);
            return new ModelAndView("rsBasicInfo", "result", result);
        }

        String title = getParam(request, "title");

        RsData rsData = new RsData();
        rsData.setTitle(title);

        List<RsData> lawDatas = ypService.getRsInfo(rsData, idx, idv, YPUtil.genAttr());
        Map<String, Object> result = Maps.newHashMap();
        int size = 0;
        if (!CollectionUtils.isEmpty(lawDatas)) {
            size = lawDatas.size();
        }
        PageBean pageBean = new PageBean(pageNum, size);
        int start = pageBean.getStart();
        int end = Math.min(size, start + PageBean.SIZE);
        result.put("pageBean", pageBean);
        result.put("rsDatas", lawDatas.subList(start, end));
        result.put("info", true);
        result.put("title", title);
        return new ModelAndView("rsBasicInfo", "result", result);
    }

    @RequestMapping(value = "rsUpload")
    @ResponseBody
    public ModelAndView rsUpload(@RequestParam(value = "rsfile", required = false) CommonsMultipartFile file, HttpServletRequest request) throws Exception {
        if (file == null) {
            return new ModelAndView("rsUpload", "result", null);
        }
        String title = request.getParameter("title");

        String filePath = FileUtil.upload(file.getBytes(), file.getFileItem().getName(), "rs");

        RsData rsData = new RsData();
        rsData.setTitle(title);
        rsData.setUpload_time(new Date());
        rsData.setPath(filePath);
        rsData.setFile_size(file.getSize());

        ypService.insertRsInfo(Lists.newArrayList(rsData), idx, idv, null);
        Map<String, Object> result = Maps.newHashMap();
        result.put("res", true);
        return new ModelAndView("rsUpload", "result", result);
    }


    @RequestMapping(value = "yyBasicInfo")
    @ResponseBody
    public ModelAndView yyBasicInfo(HttpServletRequest request) throws Exception {
        int pageNum = getPageNum(request, "pageNum");
        if (pageNum <= 0) {
            return new ModelAndView("yyBasicInfo", "result", null);
        }

        String yymc = getParam(request, "yymc");
        String ss = getParam(request, "ss");
        String dj = getParam(request, "dj");
        String cws = getParam(request, "cws");
        String rmzl = getParam(request, "rmzl");
        String yyks = getParam(request, "yyks");
        String yysb = getParam(request, "yysb");

        Map<String, Object> query = Maps.newHashMap();
        filterDjOption(query, dj);
        filterCwsOption(query, cws);
        filterRmzlOption(query, rmzl);
        query.put("yymc", yymc);
        query.put("ss", ss);
        query.put("yyks", yyks);
        query.put("yysb", yysb);

        List<YYBasicInfo> yyBasicInfo = ypService.getYyBasicInfo(query, idx, idv);
        Map<String, Object> result = Maps.newHashMap();
        int size = 0;
        if (!CollectionUtils.isEmpty(yyBasicInfo)) {
            size = yyBasicInfo.size();
        }
        PageBean pageBean = new PageBean(pageNum, size);
        int start = pageBean.getStart();
        int end = Math.min(size, start + PageBean.SIZE);
        result.put("yyBasicInfo", yyBasicInfo.subList(start, end));
        result.put("pageBean", pageBean);
        result.put("yymc", yymc);
        result.put("ss", ss);
        result.put("dj", dj);
        result.put("cws", cws);
        result.put("rmzl", rmzl);
        result.put("yyks", yyks);
        result.put("yysb", yysb);
        result.put("info", true);
        return new ModelAndView("yyBasicInfo", "result", result);
    }

    @RequestMapping(value = "yyDetail")
    @ResponseBody
    public ModelAndView yyDetail(HttpServletRequest request) throws Exception {
        String yymc = getParam(request, "yymc");
        List<YYBasicInfo> basicInfos = ypService.getYyBasicInfoByYymc(yymc, idx, idv);
        if (CollectionUtils.isEmpty(basicInfos)) {
            return new ModelAndView("yyDetail", "result", null);
        }
        List<YiyuanCntDO> yiyuanCntDOs = ypService.getYiyuanCntDOs(idx, idv);
        String ss = basicInfos.get(0).getSs();

        Map<String, Object> res = Maps.newHashMap();

        GraphIn graphIn = new GraphIn();
        graphIn.setYymc(yymc);
        graphIn.setSs(ss);
        graphIn.setYiyuanCntDOs(yiyuanCntDOs);
        String cwsAll = getGraphJsonStr("yy_cws_all_rank", yymc, graphIn);
        String cwsProvince = getGraphJsonStr("yy_cws_province_rank", yymc, graphIn);
        String rmzlAll = getGraphJsonStr("yy_rmzl_all_rank", yymc, graphIn);
        String rmzlProvince = getGraphJsonStr("yy_rmzl_province_rank", yymc, graphIn);
        res.put("yy_cws_all_rank", cwsAll);//取后缀那部分的标识即可
        res.put("yy_cws_province_rank", cwsProvince);
        res.put("yy_rmzl_all_rank", rmzlAll);
        res.put("yy_rmzl_province_rank", rmzlProvince);

        res.put("basicInfos", basicInfos);
        res.put("yymc", yymc);
        return new ModelAndView("yyDetail", "result", res);
    }

    @RequestMapping(value = "ypzcBasicInfo")
    @ResponseBody
    public ModelAndView ypzcBasicInfo(HttpServletRequest request) throws Exception {
        int pageNum = getPageNum(request, "pageNum");
        if (pageNum <= 0) {
            return new ModelAndView("ypzcBasicInfo", "result", null);
        }

        String ypmc = getParam(request, "ypmc");
        String slhm = getParam(request, "slhm");
        String qymc = getParam(request, "qymc");
        String zcfl = getParam(request, "zcfl");
        String ztrq = getParam(request, "ztrq");
        String cbrq = getParam(request, "cbrq");
        String blzt = getParam(request, "blzt");
        String yplx = getParam(request, "yplx");
        String sqlx = getParam(request, "sqlx");

        Map<String, Object> query = Maps.newHashMap();
        query.put("ypmc", ypmc);
        query.put("slhm", slhm);
        query.put("qymc", qymc);
        query.put("zcfl", zcfl);
        query.put("ztrq",ztrq);
        query.put("cbrq", cbrq);
        query.put("blzt", filterZtOption(blzt));
        query.put("yplx", yplx);
        query.put("sqlx", filterSqlxOption(sqlx));

        List<CpmcZcDO> cpmcZcDOs = ypService.getCpmcZc(query, idx, idv);
        List<YpZcBasicInfo> zcBasicInfos = Fill.fillYpZcBasicInfo(cpmcZcDOs);
        Map<String, Object> result = Maps.newHashMap();
        int size = 0;
        if (!CollectionUtils.isEmpty(zcBasicInfos)) {
            size = zcBasicInfos.size();
        }
        PageBean pageBean = new PageBean(pageNum, size);
        int start = pageBean.getStart();
        int end = Math.min(size, start + PageBean.SIZE);
        result.put("zcBasicInfo", zcBasicInfos.subList(start, end));
        result.put("pageBean", pageBean);
        result.put("ypmc", ypmc);
        result.put("slhm", slhm);
        result.put("qymc", qymc);
        result.put("zcfl", zcfl);
        result.put("ztrq", ztrq);
        result.put("cbrq", cbrq);
        result.put("blzt", blzt);
        result.put("yplx", yplx);
        result.put("sqlx", sqlx);
        result.put("info", true);
        return new ModelAndView("ypzcBasicInfo", "result", result);
    }

    @RequestMapping(value = "ypzcDetail")
    @ResponseBody
    public ModelAndView ypzcDetail(HttpServletRequest request) throws Exception {
        String ypmc = getParam(request, "ypmc");
        List<CpmcZcDO> basicInfos = ypService.getCpmcZcByYpmc(ypmc, idx, idv);
        if (CollectionUtils.isEmpty(basicInfos)) {
            return new ModelAndView("ypzcDetail", "result", null);
        }
        List<CMSBDO> cmsbdos = ypService.getZcYpAll(idx,idv);
        Collections.sort(cmsbdos);

        Map<String, Object> res = Maps.newHashMap();

        GraphIn graphIn = new GraphIn();
        graphIn.setCpmc(ypmc);
        graphIn.setCpmcZcDOs(basicInfos);
        graphIn.setCmsbdos(cmsbdos);
        String rank = getGraphJsonStr("zc_power_rank", ypmc, graphIn);
        String yplx = getGraphJsonStr("zc_yplx", ypmc, graphIn);
        String sqlx = getGraphJsonStr("zc_sqlx", ypmc, graphIn);
        String zcfl = getGraphJsonStr("zc_zcfl", ypmc, graphIn);
        String pszt = getGraphJsonStr("zc_pszt", ypmc, graphIn);
        res.put("zc_power_rank",rank);
        res.put("zc_yplx",yplx);
        res.put("zc_sqlx",sqlx);
        res.put("zc_zcfl",zcfl);
        res.put("zc_pszt",pszt);

        res.put("basicInfos", basicInfos);
        res.put("ypmc", ypmc);
        return new ModelAndView("ypzcDetail", "result", res);
    }

    @RequestMapping(value = "yqzcBasicInfo")
    @ResponseBody
    public ModelAndView yqzcBasicInfo(HttpServletRequest request) throws Exception {
        int pageNum = getPageNum(request, "pageNum");
        if (pageNum < 0) {
            return new ModelAndView("yqzcBasicInfo", "result", null);
        }

        String qym = getParam(request, "qym");

//        List<CpmcZcDO> cpmcZcDOs = ypService.getCpmcZcByQym(qym, idx, idv,YPUtil.genAttr());
//        List<YpZcBasicInfo> zcBasicInfos = Fill.fillYpZcBasicInfo(cpmcZcDOs);
        Map<String, Object> result = Maps.newHashMap();

        List<CMSBDO> cmsbdos = ypService.getZcQyBasicInfo(qym,idx,idv,YPUtil.genAttr());
        int size = 0;
        if (!CollectionUtils.isEmpty(cmsbdos)) {
            size = cmsbdos.size();
        }
        PageBean pageBean = new PageBean(pageNum, size);
        int start = pageBean.getStart();
        int end = Math.min(size, start + PageBean.SIZE);
        result.put("zcBasicInfo", cmsbdos.subList(start, end));
        result.put("pageBean", pageBean);
        result.put("qym", qym);
        result.put("info",true);
        return new ModelAndView("yqzcBasicInfo", "result", result);
    }


    @RequestMapping(value = "yqzcDetail")
    @ResponseBody
    public ModelAndView yqzcDetail(HttpServletRequest request) throws Exception {
        String qym = getParam(request, "qym");
        List<CpmcZcDO> basicInfos = ypService.getCpmcZcByQym(qym, idx, idv,null);
        if (CollectionUtils.isEmpty(basicInfos)) {
            return new ModelAndView("yqzcDetail", "result", null);
        }

        Map<String, Object> res = Maps.newHashMap();

        GraphIn graphIn = new GraphIn();
        graphIn.setQym(qym);
        graphIn.setCpmcZcDOs(basicInfos);
        String yplx = getGraphJsonStr("zc_qy_yplx", qym, graphIn);
        String sqlx = getGraphJsonStr("zc_qy_sqlx", qym, graphIn);
        String pszt = getGraphJsonStr("zc_qy_pszt", qym, graphIn);
        res.put("cnt",basicInfos.size());
        res.put("zc_qy_yplx",yplx);
        res.put("zc_qy_sqlx",sqlx);
        res.put("zc_qy_pszt",pszt);

        res.put("basicInfos", basicInfos);
        res.put("qym", qym);
        return new ModelAndView("yqzcDetail", "result", res);
    }


    private String getGraphJsonStr(String className, String name, GraphIn graphIn) throws Exception {
        return getGraphStr(ypService, className, name, graphIn);
    }

}
