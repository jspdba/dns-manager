package com.womaiapp.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Map;

/**
 *
 * 创建请求参数
 *
 * Created by wuchaofei on 2017/5/7.
 */
public class BuildRequestParams {
    public final String AccessKeyID="";
    public final String AccessKeySecret="";
    private String req="http://alidns.aliyuncs.com/?Format=XML&AccessKeyId=testid&Action=DescribeDomainRecords&SignatureMethod=HMAC-SHA1&DomainName=example.com&SignatureNonce=f59ed6a9-83fc-473b-9cc6-99c95df3856e&SignatureVersion=1.0&Version=2015-01-09&Timestamp=2016-03-24T16:41:54Z";

    public String build(Map<String,String> params){
        // 对参数进行排序，注意严格区分大小写
        String[] sortedKeys = params.keySet().toArray(new String[]{});
        Arrays.sort(sortedKeys);


        StringBuilder sb=new StringBuilder();
        for (Map.Entry<String,String> entry:params.entrySet()) {
            sb.append(encode(entry.getKey()));
            sb.append("=");
            sb.append(encode(entry.getValue()));
            sb.append("&");
        }
        return null;
    }

    public String encode(String str){
        String enc;
        try {
            enc = URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            enc = str;
        }
        return enc;
    }
}
