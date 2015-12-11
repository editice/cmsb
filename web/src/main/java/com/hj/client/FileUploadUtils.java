package com.hj.client;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2014/11/24  19:41
 */
public class FileUploadUtils {
    // 默认大小5M
    public static final long DEFAULT_MAX_SIZE = 5242880;
    // 默认上传的地址
    public static String defaultBaseDir = "upload";

    public static String upload(HttpServletRequest request, MultipartFile file) {
        try {
            String filename = extractFilename(file, defaultBaseDir);
            File desc = getAbsoluteFile(extractUploadDir(request), filename);
            file.transferTo(desc);
            return filename;
        }catch(Exception e){
            return null;
        }
    }

    private static File getAbsoluteFile(String uploadDir, String filename) throws IOException {
        if (uploadDir.endsWith("/")) {
            uploadDir = uploadDir.substring(0, uploadDir.length() - 1);
        }
        if (filename.startsWith("/")) {
            filename = filename.substring(0, uploadDir.length() - 1);
        }
        File desc = new File(uploadDir + "/" + filename);
        if (!desc.getParentFile().exists()) {
            desc.getParentFile().mkdirs();
        }
        if (!desc.exists()) {
            desc.createNewFile();
        }
        return desc;
    }

    public static String extractFilename(MultipartFile file, String baseDir) throws UnsupportedEncodingException {
        String filename = file.getOriginalFilename();
        int slashIndex = filename.indexOf("/");
        if (slashIndex >= 0) {
            filename = filename.substring(slashIndex + 1);
        }
        filename = baseDir + "/" + filename;
        return filename;
    }

    public static String extractUploadDir(HttpServletRequest request) {
        return request.getSession().getServletContext().getRealPath("/");
    }
}
