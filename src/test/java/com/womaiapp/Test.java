package com.womaiapp;
import com.womaiapp.util.DateFormatUtil;
import com.womaiapp.util.EncodeUtil;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by wuchaofei on 2017/5/7.
 */
public class Test {
    private static final String HTTP_METHOD = "GET";

    public static void main(String[] args){
        Map<String, String> parameters = new HashMap<String, String>();
        // 加入请求参数
        parameters.put("Action", "DescribeDomainRecords");
        parameters.put("DomainName", "example.com");
        parameters.put("Version", "2015-01-09");
        parameters.put("AccessKeyId", "testid");
        parameters.put("TimeStamp", formatIso8601Date(new Date()));
        parameters.put("SignatureMethod", "HMAC-SHA1");
        parameters.put("SignatureVersion", "1.0");
        parameters.put("SignatureNonce", UUID.randomUUID().toString());
        parameters.put("Format", "JSON");

        // 对参数进行排序，注意严格区分大小写
        String[] sortedKeys = parameters.keySet().toArray(new String[]{});
        Arrays.sort(sortedKeys);

        final String SEPARATOR = "&";

        // 生成stringToSign字符串
        StringBuilder stringToSign = new StringBuilder();
        stringToSign.append(HTTP_METHOD).append(SEPARATOR);
        stringToSign.append(percentEncode("/")).append(SEPARATOR);

        StringBuilder canonicalizedQueryString = new StringBuilder();
        for(String key : sortedKeys) {
            // 这里注意对key和value进行编码
            canonicalizedQueryString.append("&")
                    .append(percentEncode(key)).append("=")
                    .append(percentEncode(parameters.get(key)));
        }

        // 这里注意对canonicalizedQueryString进行编码
        stringToSign.append(percentEncode(canonicalizedQueryString.toString().substring(1)));
    }

    private static String percentEncode(String str){
        String ret;
        try {
            ret = EncodeUtil.percentEncode(str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            ret = str;
        }
        return ret;
    }

    private static String formatIso8601Date(Date date) {
        return DateFormatUtil.formatIso8601Date(date);
    }
}
