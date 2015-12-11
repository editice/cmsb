package com.hj.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hj.biz.service.YiyaoService;
import com.hj.client.object.list.*;
import com.hj.client.util.FileUtil;
import com.hj.dal.domain.dataobject.CpmcZcDO;
import com.hj.dal.domain.dataobject.SfdaQxDO;
import com.hj.dal.domain.dataobject.YgyZdcsSaleDO;
import com.hj.view.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import static com.hj.dal.Constants.idv;
import static com.hj.dal.Constants.idx;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2015/2/4  13:55
 */
@Controller
@RequestMapping(value="excel")
public class ExcelController {

    @Resource
    YiyaoService yiyaoService;

    @RequestMapping(value = "basicDetail")
     @ResponseBody
     public ModelAndView basicDetail(HttpServletRequest request,HttpServletResponse response)  {
        String cpmc = request.getParameter("cpmc");
        List<YpPwBasicInfo> pwBasicInfoList= yiyaoService.getYpPwBasicInfo(cpmc,idx,idv);
        Map<String,Object> map= Maps.newHashMap();
        ViewExcel viewExcel=new ViewExcel(pwBasicInfoList);
        return new ModelAndView(viewExcel,map);
    }

    @RequestMapping(value = "qypw")
    @ResponseBody
    public ModelAndView qypw(HttpServletRequest request,HttpServletResponse response)  {
        String qym = request.getParameter("qym");
        List<QyPwBasicInfo> pwBasicInfoList= yiyaoService.getQyPwBasicInfo(qym, idx, idv);
        Map<String,Object> map= Maps.newHashMap();
        QyViewExcel viewExcel=new QyViewExcel(pwBasicInfoList);
        return new ModelAndView(viewExcel,map);
    }

    @RequestMapping(value = "qyPowerDetail")
    @ResponseBody
    public ModelAndView qyPowerDetail(HttpServletRequest request,HttpServletResponse response)  {
        String qym = request.getParameter("qym");
        List<QyPowerDetailInfo> infos= yiyaoService.getQyPowerDetailInfo(qym, idx, idv,null);
        Map<String,Object> map= Maps.newHashMap();
        QyPowerViewExcel viewExcel=new QyPowerViewExcel(infos);
        return new ModelAndView(viewExcel,map);
    }

    @RequestMapping(value = "qyPowerSaleDetail")
    @ResponseBody
    public ModelAndView qyPowerSaleDetail(HttpServletRequest request,HttpServletResponse response)  {
        String qym = request.getParameter("qym");
        List<YgyZdcsSaleDO> infos= yiyaoService.getZdcsSaleByQy(qym, idx, idv);
        Map<String,Object> map= Maps.newHashMap();
        QyPowerSaleViewExcel viewExcel=new QyPowerSaleViewExcel(infos);
        return new ModelAndView(viewExcel,map);
    }

    @RequestMapping(value = "ypSaleDetail")
    @ResponseBody
    public ModelAndView ypSaleDetail(HttpServletRequest request,HttpServletResponse response)  {
        String name = request.getParameter("cpmc");
        List<YgyZdcsSaleDO> infos= yiyaoService.getZdcsSale(name, idx, idv);
        Map<String,Object> map= Maps.newHashMap();
        QyPowerSaleViewExcel viewExcel=new QyPowerSaleViewExcel(infos);
        return new ModelAndView(viewExcel,map);
    }

    @RequestMapping(value = "downloadLaw")
    @ResponseBody
    public ModelAndView downloadLaw(HttpServletRequest request,HttpServletResponse response)  {
        String id= (String) request.getAttribute("id");
        List<LawData> datas= yiyaoService.getLawInfoById(Long.parseLong(id), idx, idv);
        if(CollectionUtils.isEmpty(datas)){
            return null;
        }
        String path=datas.get(0).getPath();
        FileUtil.download(path,response,"law");
        return null;
    }

    @RequestMapping(value = "downloadRs")
    @ResponseBody
    public ModelAndView downloadRs(HttpServletRequest request,HttpServletResponse response)  {
        String id= (String) request.getAttribute("id");
        List<RsData> datas= yiyaoService.getRsInfoById(Long.parseLong(id), idx, idv);
        if(CollectionUtils.isEmpty(datas)){
            return null;
        }
        String path=datas.get(0).getPath();
        FileUtil.download(path,response,"rs");
        return null;
    }


    @RequestMapping(value = "qxBasicDetail")
    @ResponseBody
    public ModelAndView qxBasicDetail(HttpServletRequest request,HttpServletResponse response)  {
        String qxm = request.getParameter("qxm");
        List<SfdaQxDO> qxInfo= yiyaoService.getQxInfo(qxm,idx,idv,null);
        Map<String,Object> map= Maps.newHashMap();
        QxViewExcel viewExcel=new QxViewExcel(qxInfo);
        return new ModelAndView(viewExcel,map);
    }

    @RequestMapping(value = "qxQyDetail")
    @ResponseBody
    public ModelAndView qxQyDetail(HttpServletRequest request,HttpServletResponse response)  {
        String qym = request.getParameter("qym");
        List<SfdaQxDO> qxInfo= yiyaoService.getQxInfoByQy(qym,idx,idv,null);
        Map<String,Object> map= Maps.newHashMap();
        QxViewExcel viewExcel=new QxViewExcel(qxInfo);
        return new ModelAndView(viewExcel,map);
    }

    @RequestMapping(value = "qyZcDetail")
    @ResponseBody
    public ModelAndView qyZcDetail(HttpServletRequest request,HttpServletResponse response)  {
        String qym = request.getParameter("qym");
        List<CpmcZcDO> qxInfo= yiyaoService.getCpmcZcByQym(qym, idx, idv, null);
        Map<String,Object> map= Maps.newHashMap();
        QyZcViewExcel viewExcel=new QyZcViewExcel(qxInfo);
        return new ModelAndView(viewExcel,map);
    }


}
