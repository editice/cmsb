package com.hj.client.weixin;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hj.client.http.HttpUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2015/1/13  21:47
 */
public class SecretUtil {

    static Logger logger = LoggerFactory.getLogger(SecretUtil.class);

    private static final String APP_ID = "wx5f0c120feffcd045";

    private static final String APP_SECRET = "619b9cb56b50c2dbc66cc503c4ed100f";

    public static String token = "xxdfsdfsae";

    private static long expire = 7200;//µ•Œª£∫√Î

    public static boolean check_signature(String signature, String timestamp, String nonce) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String[] arr = new String[]{timestamp, nonce, token};
        Arrays.sort(arr);
        String s = arr[0] + arr[1] + arr[2];
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] digest = md.digest(s.getBytes("utf-8"));
        return false;
//        return signature == bytes2HexString(digest);
    }


    public String getAccessToken() {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APP_ID + "&secret=" + APP_SECRET;
        String resp = null;
        try {
            resp = HttpUtil.doGet(url);
        }
        catch (IOException e) {
            logger.error("get token fail", e);
            return null;
        }
        JSONObject jsonObject = JSON.parseObject(resp);
        String access_token = (String) jsonObject.get("access_token");
        if (StringUtils.isBlank(access_token)) {
            logger.error("get token fail, resp:" + resp);
        }
        String expir = (String) jsonObject.get("expires_in");
        if (!StringUtils.isBlank(expir)) {
            expire = Long.valueOf(expir);
        }
        token = access_token;
        return token;
    }


    public static void main(String[] args) throws IOException {

    }
}
