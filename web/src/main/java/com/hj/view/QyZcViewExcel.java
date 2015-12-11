package com.hj.view;

import com.hj.dal.domain.dataobject.CpmcZcDO;
import com.hj.dal.domain.dataobject.SfdaQxDO;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.hj.biz.util.DateUtil.parseDate;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2015/2/4  13:59
 */
public class QyZcViewExcel extends AbstractExcelView {

    List<CpmcZcDO> qxInfos;

    public QyZcViewExcel(List<CpmcZcDO> qxInfos) {
        this.qxInfos = qxInfos;
    }

    @Override
    protected void buildExcelDocument(Map<String, Object> map, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HSSFSheet sheet = workbook.createSheet("qxInfo");
        sheet.setDefaultColumnWidth((short) 15);
        HSSFCell cell = getCell(sheet, 0, 0);
        setText(cell, "药企注册申报基本信息列表");
        HSSFCellStyle dateStyle = workbook.createCellStyle();
        cell = getCell(sheet, 1, 0);
        cell.setCellValue("下载时间："+new Date());
        //cell.setCellStyle(dateStyle);

        getCell(sheet, 2, 0).setCellValue("ID");
        getCell(sheet, 2, 1).setCellValue("ROWKEY");
        getCell(sheet, 2, 2).setCellValue("生产企业");
        getCell(sheet, 2, 3).setCellValue("注册类型");
        getCell(sheet, 2, 4).setCellValue("CFDA办理状态");
        getCell(sheet, 2, 5).setCellValue("药品英文名");
        getCell(sheet, 2, 6).setCellValue("状态开始时间");
        getCell(sheet, 2, 7).setCellValue("受理号");
        getCell(sheet, 2, 8).setCellValue("CDE承办日期");
        getCell(sheet, 2, 9).setCellValue("药品类型");
        getCell(sheet, 2, 10).setCellValue("药品中文名");
        getCell(sheet, 2, 11).setCellValue("申请类型");
        getCell(sheet, 2, 12).setCellValue("特殊审批品种");
        getCell(sheet, 2, 13).setCellValue("新报离开序列时间");
        getCell(sheet, 2, 14).setCellValue("新报当前排名");
        getCell(sheet, 2, 15).setCellValue("复审审评结论");
        getCell(sheet, 2, 16).setCellValue("新报审评任务分类");
        getCell(sheet, 2, 17).setCellValue("CDE状态");
        getCell(sheet, 2, 18).setCellValue("重大专项");
        getCell(sheet, 2, 19).setCellValue("新报排队总数");
        getCell(sheet, 2, 20).setCellValue("新报审评速度");
        getCell(sheet, 2, 21).setCellValue("CDE审评结论");
        getCell(sheet, 2, 22).setCellValue("新报进入序列时间");
        getCell(sheet, 2, 23).setCellValue("新报审评状态");

        int size= qxInfos.size();

        for(int i=0;i<size;i++){
            HSSFRow sheetRow = sheet.createRow(i+3);
            CpmcZcDO info= qxInfos.get(i);

            sheetRow.createCell(0).setCellValue(info.getId());
            sheetRow.createCell(1).setCellValue(info.getRowkey());
            sheetRow.createCell(2).setCellValue(info.getScqy());
            sheetRow.createCell(3).setCellValue(info.getZclx());
            sheetRow.createCell(4).setCellValue(info.getCfda_blzt());
            sheetRow.createCell(5).setCellValue(info.getCpmc_en());
            sheetRow.createCell(6).setCellValue(parseDate(info.getKsrq()));
            sheetRow.createCell(7).setCellValue(info.getSlh());
            sheetRow.createCell(8).setCellValue(parseDate(info.getCde_cbrq()));
            sheetRow.createCell(9).setCellValue(info.getYplx());
            sheetRow.createCell(10).setCellValue(info.getCpmc());
            sheetRow.createCell(11).setCellValue(info.getSqlx());
            sheetRow.createCell(12).setCellValue(info.getTsps());
            sheetRow.createCell(13).setCellValue(info.getXb_lksj());
            sheetRow.createCell(14).setCellValue(info.getXb_dqpm());
            sheetRow.createCell(15).setCellValue(info.getFsspjl());
            sheetRow.createCell(16).setCellValue(info.getXb_rwfl());
            sheetRow.createCell(17).setCellValue(info.getCde_zt());
            sheetRow.createCell(18).setCellValue(info.getZdzx());
            sheetRow.createCell(19).setCellValue(info.getXb_pdzs());
            sheetRow.createCell(20).setCellValue(info.getXb_spsd());
            sheetRow.createCell(21).setCellValue(info.getCde_spjl());
            sheetRow.createCell(22).setCellValue(info.getXb_jrsj());
            sheetRow.createCell(23).setCellValue(info.getXb_pszt());
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
