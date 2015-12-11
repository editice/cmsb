package com.hj.view;

import com.hj.client.object.list.QyPowerDetailInfo;
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
public class QyPowerViewExcel extends AbstractExcelView {

    List<QyPowerDetailInfo> detailInfos;

    public QyPowerViewExcel(List<QyPowerDetailInfo> pwBasicInfoList) {
        this.detailInfos = pwBasicInfoList;
    }

    @Override
    protected void buildExcelDocument(Map<String, Object> map, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HSSFSheet sheet = workbook.createSheet("qypwInfo");
        sheet.setDefaultColumnWidth((short) 15);
        HSSFCell cell = getCell(sheet, 0, 0);
        setText(cell, "企业销售额占比详情");
        HSSFCellStyle dateStyle = workbook.createCellStyle();
        cell = getCell(sheet, 1, 0);
        cell.setCellValue("下载时间："+new Date());
        //cell.setCellStyle(dateStyle);

        getCell(sheet, 2, 0).setCellValue("药品名");
        getCell(sheet, 2, 1).setCellValue("销售额");
        getCell(sheet, 2, 2).setCellValue("销售额占比");
        getCell(sheet, 2, 3).setCellValue("是否基药");
        getCell(sheet, 2, 4).setCellValue("医保情况");

        int size= detailInfos.size();

        for(int i=0;i<size;i++){
            HSSFRow sheetRow = sheet.createRow(i+3);
            QyPowerDetailInfo info= detailInfos.get(i);
            sheetRow.createCell(0).setCellValue(info.getCpmc());
            sheetRow.createCell(1).setCellValue(info.getSale());
            sheetRow.createCell(2).setCellValue(info.getPercent());
            sheetRow.createCell(3).setCellValue(info.getIsJy());
            sheetRow.createCell(4).setCellValue(info.getYibao());
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
