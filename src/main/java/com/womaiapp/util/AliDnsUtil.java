package com.womaiapp.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.womaiapp.Config;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

/**
 * 使用到的工具类
 * Created by cofco on 2017/5/8.
 */
public class AliDnsUtil {
    private static final String ISO8601_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private static final String ALGORITHM = "HmacSHA1";
    private static final String ENCODING = "UTF-8";


    /**
     * 日期格式化
     * @param date
     * @return
     */
    public static String formatIso8601Date(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(ISO8601_DATE_FORMAT);
        df.setTimeZone(new SimpleTimeZone(0, "GMT"));
        return df.format(date);
    }

    /**
     * url编码
     * @param value
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String percentEncode(String value)
            throws UnsupportedEncodingException {
        return value != null ?
                URLEncoder.encode(value, ENCODING).replace("+", "%20")
                        .replace("*", "%2A").replace("%7E", "~")
                : null;
    }

    /**
     * 读取配置文件
     * @param path
     * @return
     */
    public static Config loadConfig(String path) {
        ObjectMapper objMapper = new ObjectMapper();
        Config config = null;
        try {
            config = objMapper.readValue(new File(path), Config.class);
        }catch (JsonProcessingException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }


    /**
     * 签名算法
     * @param key
     * @param stringToSign
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public static String sign(String key, String stringToSign) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance(ALGORITHM);
        mac.init(new SecretKeySpec( key.getBytes(ENCODING), ALGORITHM));
        byte[] signData = mac.doFinal(stringToSign.getBytes(ENCODING));

        return new String(Base64.encodeBase64(signData));
    }
}
