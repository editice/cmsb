package com.hj.client.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2015/3/14  21:34
 */
public class FileUtil {

    static final Logger log = LoggerFactory.getLogger(FileUtil.class);
    public static final String localPath = "/home/admin/file/";


    public static String upload(byte[] file,String fileName, String packName) {
        String filePath = localPath + packName + "/" + fileName;
        File uploadFile = new File(filePath);
        try {
            //上传
            FileCopyUtils.copy(file, uploadFile);
        }
        catch (IOException e) {
            log.error("upload file fail", e);
        }
        return filePath;
    }

    public static HttpServletResponse download(String path, HttpServletResponse response, String pack) {
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path );
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        }
        catch (IOException ex) {
            log.error("download file", ex);
        }
        return response;
    }

    public static void downloadLocal(HttpServletResponse response, String path) throws FileNotFoundException {
        // 下载本地文件
        // 读到流中
        InputStream inStream = new FileInputStream(path);// 文件的存放路径
        // 设置输出的格式
        response.reset();
        response.setContentType("bin");
        response.addHeader("Content-Disposition", "attachment; filename=\"" + System.currentTimeMillis() + "\"");
        // 循环取出流中的数据
        byte[] b = new byte[100];
        int len;
        try {
            while ((len = inStream.read(b)) > 0)
                response.getOutputStream().write(b, 0, len);
            inStream.close();
        }
        catch (IOException e) {
            log.error("download file", e);
        }
    }

    public static void downloadNet(HttpServletResponse response, String path) throws MalformedURLException {
        // 下载网络文件
        int bytesum = 0;
        int byteread = 0;

        URL url = new URL(path);

        try {
            URLConnection conn = url.openConnection();
            InputStream inStream = conn.getInputStream();
            FileOutputStream fs = new FileOutputStream("c:/abc.gif");

            byte[] buffer = new byte[1204];
            int length;
            while ((byteread = inStream.read(buffer)) != -1) {
                bytesum += byteread;
                System.out.println(bytesum);
                fs.write(buffer, 0, byteread);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
