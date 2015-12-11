package com.hj.controller;

import com.google.common.collect.Maps;
import com.hj.biz.GraphIn;
import com.hj.biz.service.YiyaoService;
import com.hj.biz.util.BizProcess;
import com.hj.biz.util.QxUtil;
import com.hj.biz.util.YPUtil;
import com.hj.client.object.list.PageBean;
import com.hj.client.object.list.QxQyBasicInfo;
import com.hj.client.object.list.QxQyStructInfo;
import com.hj.dal.domain.dataobject.CMSBDO;
import com.hj.dal.domain.dataobject.SfdaQxDO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.hj.dal.Constants.idv;
import static com.hj.dal.Constants.idx;
import static com.hj.biz.util.GraphEngine.*;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2015/3/12  21:00
 */
@Controller
@RequestMapping(value = "qx")
public class QxController {
    static final Logger log = LoggerFactory.getLogger(QxController.class);

    @Resource
    YiyaoService ypService;

    @RequestMapping(value = "qxBasicInfo")
    @ResponseBody
    public ModelAndView qxBasicInfo(HttpServletRequest request) throws Exception {
        String num = (String) request.getAttribute("pageNum");
        String qxm= (String) request.getAttribute("qxm");
        int pageNum=1;
        if(!StringUtils.isEmpty(num)){
            pageNum=Integer.parseInt(num);
        }
        if(StringUtils.isEmpty(qxm)) {
            qxm = request.getParameter("qxm");
        }
        Map<String, Object> result = Maps.newHashMap();
        if (StringUtils.isEmpty(qxm)) {
            return new ModelAndView("qxBasicInfo", "result", result);
        }
        List<SfdaQxDO> qxDOList = ypService.getQxInfo(qxm, idx, idv, YPUtil.genAttr());
        List<SfdaQxDO> innerQxs = QxUtil.filterDuplicate(qxDOList);

        int size = 0;
        if (!CollectionUtils.isEmpty(innerQxs)) {
            size = innerQxs.size();
        }
        PageBean pageBean = new PageBean(pageNum, size);
        int start = pageBean.getStart();
        int end= Math.min(size,start+PageBean.SIZE);
        result.put("qxDOs", innerQxs.subList(start,end));
        result.put("info", true);
        result.put("cpmcHidden",qxm);
        result.put("pageBean",pageBean);
        return new ModelAndView("qxBasicInfo", "result", result);
    }

    @RequestMapping(value = "qxBasicDetail")
    @ResponseBody
    public ModelAndView qxBasicDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String qxm = (String) request.getAttribute("qxm");
        Map<String, Object> res = Maps.newHashMap();
        if (StringUtils.isEmpty(qxm)) {
            return new ModelAndView("qxBasicDetail", "result", res);
        }
        List<SfdaQxDO> qxDOList = ypService.getQxInfo(qxm, idx, idv, null);

        res.put("info", true);

        int zchCnt=QxUtil.getZchCnt(qxDOList);
        int qyCnt=QxUtil.getQyCnt(qxDOList);
        int gcCnt=QxUtil.getGcCnt(qxDOList);
        int jkCnt=QxUtil.getJkCnt(qxDOList);
        List<String> scopes=QxUtil.getScope(qxDOList);
        List<String> pscs=QxUtil.getPsc(qxDOList);
        String scope = QxUtil.getFirst(scopes);
        String psc=QxUtil.getFirst(pscs);

        GraphIn graphIn=new GraphIn();
        graphIn.setSfdaQxDOs(qxDOList);
        String qxPie = getGraphJsonStr("qx_from", null, graphIn);

        res.put("qx_from", qxPie);
        res.put("zchCnt",zchCnt);
        res.put("gcCnt",gcCnt);
        res.put("jkCnt",jkCnt);
        res.put("qyCnt",qyCnt);
        res.put("scope",scope);
        res.put("psc",psc);
        res.put("qxm",qxm);

//        if(!CollectionUtils.isEmpty(qxDOList) && qxDOList.size()> BizProcess.UP_COUNT){
//            qxDOList = qxDOList.subList(0,BizProcess.UP_COUNT);
//        }
        res.put("qxBasicDetail", qxDOList);
        return new ModelAndView("qxBasicDetail", "result", res);
    }

    @RequestMapping(value = "qxQyBasicInfo")
    @ResponseBody
    public ModelAndView qxQyBasicInfo(HttpServletRequest request) throws Exception {
        String num = (String) request.getAttribute("pageNum");
        String qym= (String) request.getAttribute("qym");
        int pageNum=1;
        if(!StringUtils.isEmpty(num)){
            pageNum=Integer.parseInt(num);
        }
        if(StringUtils.isEmpty(qym)) {
            qym = request.getParameter("qym");
        }
        Map<String, Object> result = Maps.newHashMap();
        if (StringUtils.isEmpty(qym)) {
            return new ModelAndView("qxQyBasicInfo", "result", result);
        }
        List<SfdaQxDO> qxDOList = ypService.getQxInfoByQy(qym, idx, idv, YPUtil.genAttr());
        List<QxQyBasicInfo> res=QxUtil.getQyQxCnt(qxDOList);

        int size = 0;
        if (!CollectionUtils.isEmpty(res)) {
            size = res.size();
        }
        PageBean pageBean = new PageBean(pageNum, size);
        int start = pageBean.getStart();
        int end= Math.min(size,start+PageBean.SIZE);
        result.put("qxDOs", res.subList(start,end));
        result.put("info", true);
        result.put("pageBean",pageBean);
        result.put("qymHidden",qym);
        result.put("qym",qym);
        return new ModelAndView("qxQyBasicInfo", "result", result);
    }

    @RequestMapping(value = "qxQyDetail")
    @ResponseBody
    public ModelAndView qxQyDetail(HttpServletRequest request) throws Exception {
        String qym = (String) request.getAttribute("qym");
        Map<String, Object> res = Maps.newHashMap();
        if (StringUtils.isEmpty(qym)) {
            return new ModelAndView("qxQyDetail", "result", res);
        }
        List<SfdaQxDO> qxDOList = ypService.getQxInfoByQy(qym, idx, idv, null);
        List<QxQyStructInfo> qxs= QxUtil.parseSfdaQxInfo(ypService,qxDOList);
        List<CMSBDO> cmsbdos=ypService.getQxQyAll(idx, idv);
        Collections.sort(cmsbdos);

        res.put("info", true);

        int zchCnt=QxUtil.getZchCnt(qxDOList);
        int qyCnt=cmsbdos.size();
        int rank=QxUtil.getRank(qym,cmsbdos);

        GraphIn graphIn=new GraphIn();
        graphIn.setCmsbdos(cmsbdos);
        graphIn.setQym(qym);
        String qxPie = getGraphJsonStr("qxQy_rank", null, graphIn);

        res.put("qxQy_rank", qxPie);
        res.put("zchCnt",zchCnt);
        res.put("qyCnt",qyCnt);
        res.put("rank",rank);
        res.put("qxDOs",qxs);
        res.put("qym",qym);
        return new ModelAndView("qxQyDetail", "result", res);
    }

    private String getGraphJsonStr(String className, String name, GraphIn graphIn) throws Exception {
        return getGraphStr(ypService,className,name,graphIn);
    }

}
