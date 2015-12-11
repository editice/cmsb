package com.hj.client.http;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.IOException;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2015/1/14  17:28
 */
public class HttpUtil {

    public static String doGet(String url) throws IOException {
        String resp = "";
        HttpClient httpClient = new HttpClient();
        HttpMethod method = new GetMethod(url);
        httpClient.executeMethod(method);
        resp = method.getResponseBodyAsString();
        method.releaseConnection();
        return resp;
    }
}
