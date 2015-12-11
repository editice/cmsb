package com.hj.view;

import com.hj.client.object.list.YpPwBasicInfo;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2015/2/4  13:59
 */
public class ViewExcel extends AbstractExcelView {

    List<YpPwBasicInfo> ypPwBasicInfos;

    public ViewExcel(List<YpPwBasicInfo> pwBasicInfoList) {
        this.ypPwBasicInfos = pwBasicInfoList;
    }

    @Override
    protected void buildExcelDocument(Map<String, Object> map, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HSSFSheet sheet = workbook.createSheet("pwInfo");
        sheet.setDefaultColumnWidth((short) 15);
        HSSFCell cell = getCell(sheet, 0, 0);
        setText(cell, "药品批文列表详情");
        HSSFCellStyle dateStyle = workbook.createCellStyle();
        cell = getCell(sheet, 1, 0);
        cell.setCellValue("下载时间："+new Date());
        //cell.setCellStyle(dateStyle);

        getCell(sheet, 2, 0).setCellValue("批准文号");
        getCell(sheet, 2, 1).setCellValue("中文产品名称");
        getCell(sheet, 2, 2).setCellValue("英文产品名称");
        getCell(sheet, 2, 3).setCellValue("商品名");
        getCell(sheet, 2, 4).setCellValue("产品类型");
        getCell(sheet, 2, 5).setCellValue("剂型");
        getCell(sheet, 2, 6).setCellValue("生产单位");
        getCell(sheet, 2, 7).setCellValue("国产/进口");

        int size=ypPwBasicInfos.size();

        for(int i=0;i<size;i++){
            HSSFRow sheetRow = sheet.createRow(i+3);
            YpPwBasicInfo info=ypPwBasicInfos.get(i);
            sheetRow.createCell(0).setCellValue(info.getPzwh());
            sheetRow.createCell(1).setCellValue(info.getCpmc_ch());
            sheetRow.createCell(2).setCellValue(info.getCpmhc_en());
            sheetRow.createCell(3).setCellValue(info.getSpm());
            sheetRow.createCell(4).setCellValue(info.getCplx());
            sheetRow.createCell(5).setCellValue(info.getJx());
            sheetRow.createCell(6).setCellValue(info.getScdw());
            sheetRow.createCell(7).setCellValue(info.getGcjk());
        }
        long time=System.currentTimeMillis();
        String filename = time+".xls";//设置下载时客户端Excel的名称

        /**
         * 获取客户端浏览器和操作系统信息
         * 在IE浏览器中得到的是：User-Agent=Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; Maxthon; Alexa Toolbar)
         * 在Firefox中得到的是：User-Agent=Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.7.10) Gecko/20050717 Firefox/1.0.6
         */
//        String agent = request.getHeader("USER-AGENT");
//        filename = YPUtil.encodeFilename(filename, agent);//处理中文文件名
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + filename);
        OutputStream ouputStream = response.getOutputStream();
        workbook.write(ouputStream);
        ouputStream.flush();
        ouputStream.close();
    }
}
