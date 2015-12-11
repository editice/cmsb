package com.hj.biz.error;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 装在错误信息map的装载类
 * @author null.zj
 */
public class ErrorDOLoader {
    public static Map<String, Error> loadXml(File file) {
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            return loadXml(in);
        }
        catch (Exception e) {
            throw new RuntimeException("the error config file is available,please check file:" + file.getName(), e);
        }
        finally {
            if (in != null) {
                IOUtils.closeQuietly(in);
            }
        }
    }

    public static Map<String, Error> loadXml(InputStream in) {
        Map<String, Error> errorDOMap = new ConcurrentHashMap<String, Error>();
        Error error;
        String errorCode = "", errorMsg = "", redirectUrl = "", redirectTarget = "", errorType = "";
        try {
            XMLInputFactory xif = XMLInputFactory.newInstance();
            XMLStreamReader reader = xif.createXMLStreamReader(in);
            while (reader.hasNext()) {
                int event = reader.next();
                if (event == XMLStreamReader.START_ELEMENT) {
                    String localName = reader.getLocalName();
                    if ("error".equals(localName)) {
                        for (int i = 0; i < reader.getAttributeCount(); i++) {
                            if ("code".equals(reader.getAttributeLocalName(i))) {
                                errorCode = reader.getAttributeValue(i);
                            }
                            else if ("msg".equals(reader.getAttributeLocalName(i))) {
                                errorMsg = reader.getAttributeValue(i);
                            }
                            else if ("redirectUrl".equals(reader.getAttributeLocalName(i))) {
                                redirectUrl = reader.getAttributeValue(i);
                            }
                            else if ("redirectTarget".equals(reader.getAttributeLocalName(i))) {
                                redirectTarget = reader.getAttributeValue(i);
                            }
                            else if ("errorType".equals(reader.getAttributeLocalName(i))) {
                                errorType = reader.getAttributeValue(i);
                            }
                        }
                    }
                    if ("msg".equals(localName)) {
                        errorMsg = reader.getElementText();
                    }
                }
                else if (event == XMLStreamReader.END_ELEMENT) {
                    String localName = reader.getLocalName();
                    if ("error".equals(localName)) {
                        if (StringUtils.isNotBlank(errorCode)) {
                            error = new Error(errorCode, errorMsg,  redirectUrl, redirectTarget, errorType);
                            String key = errorCode;
                            errorDOMap.put(key, error);
                        }
                        // 对所有的参数进行设置为null
                        errorCode = errorMsg = redirectUrl = redirectTarget = errorType = "";
                    }
                }
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            if (in != null) {
                try {
                    in.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return errorDOMap;
    }
}
