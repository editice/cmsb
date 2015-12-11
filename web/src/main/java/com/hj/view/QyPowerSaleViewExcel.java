package com.hj.view;

import com.hj.biz.util.YPUtil;
import com.hj.client.FR;
import com.hj.dal.domain.dataobject.YgyZdcsSaleDO;
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
 * @since 2015/3/7  16:32
 */
public class QyPowerSaleViewExcel extends AbstractExcelView {

    List<YgyZdcsSaleDO> saleDOs;

    public QyPowerSaleViewExcel(List<YgyZdcsSaleDO> saleDOs) {
        this.saleDOs = saleDOs;
    }

    @Override
    protected void buildExcelDocument(Map<String, Object> map, HSSFWorkbook workbook, HttpServletRequest httpServletRequest, HttpServletResponse response) throws Exception {
        HSSFSheet sheet = workbook.createSheet("qySales");
        sheet.setDefaultColumnWidth((short) 15);
        HSSFCell cell = getCell(sheet, 0, 0);
        setText(cell, "销售增长详情");
        HSSFCellStyle dateStyle = workbook.createCellStyle();
        cell = getCell(sheet, 1, 0);
        cell.setCellValue("下载时间：" + new Date());
        //cell.setCellStyle(dateStyle);

        getCell(sheet, 2, 0).setCellValue("季度");
        getCell(sheet, 2, 1).setCellValue("销售额");
        getCell(sheet, 2, 2).setCellValue("同比增长率");

        List<FR> frs = YPUtil.getFrFromSale(saleDOs);

        int size = frs.size();

        for (int i = 0; i < size; i++) {
            HSSFRow sheetRow = sheet.createRow(i + 3);
            FR info = frs.get(i);
            sheetRow.createCell(0).setCellValue(info.getDetailJD());
            sheetRow.createCell(1).setCellValue(info.getXse());
            sheetRow.createCell(2).setCellValue(info.getRate());
        }
        long time = System.currentTimeMillis();
        String filename = time + ".xls";//设置下载时客户端Excel的名称

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
