package com.womaiapp;

import com.womaiapp.util.AliDnsUtil;
import com.womaiapp.util.HttpClientUtil;
import org.apache.commons.cli.*;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * 程序入口
 * Created by wuchaofei on 2017/5/7.
 */
public class Main {
    //配置文件
    private Config config;
    //服务器地址
    private String HOST = "http://alidns.aliyuncs.com/";
    //请求参数
    private String params;

    private static final String HTTP_METHOD = "GET";
    private final String SEPARATOR = "&";
    //标准请求字符串
    private String normalRequestUri;
    //签名
    private String Signature;

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.init(args);
        //开始解析请求参数
        if (main.params != null) {
            Map<String, String> map = main.parseToMap(main.params);
            //构建标准请求字符串
            main.prepareSign(map);

            //标准请求字符串签名
            main.Signature=main.signString(main.normalRequestUri);
            //最终请求字符串
            main.normalRequestUri = main.buildFinalRequestString(map);
            String url=main.HOST+"?"+main.normalRequestUri;
            System.out.println(HttpClientUtil.get(url));
        }
    }

    /**
     * 构造请求字符串
     * @param map
     * @return
     */
    private String buildFinalRequestString(Map<String, String> map) {
        //加入签名参数
        if(!map.containsKey("Signature")){
            map.put("Signature", this.Signature);
        }

        // 对参数进行排序，注意严格区分大小写
        String[] keys = map.keySet().toArray(new String[]{});


        // 生成stringToSign字符串
        StringBuilder stringToSign = new StringBuilder();

        StringBuilder canonicalizedQueryString = new StringBuilder();
        for (String key : keys) {
            // 这里注意对key和value进行编码
            canonicalizedQueryString.append("&")
                    .append(percentEncode(key)).append("=")
                    .append(percentEncode(map.get(key)));
        }

        // 这里注意对canonicalizedQueryString进行编码
        return stringToSign.append(canonicalizedQueryString.toString().substring(1)).toString();
    }


    /**
     * 构造标准请求字符串
     * @param parameters
     * @return
     */
    private String prepareSign(Map<String, String> parameters) {
        parameters.put("Version", this.config.getVersion());
        parameters.put("AccessKeyId", this.config.getAccessKeyID());
        parameters.put("Timestamp", formatIso8601Date(new Date()));
        parameters.put("SignatureMethod", this.config.getSignatureMethod());
        parameters.put("SignatureVersion", this.config.getSignatureVersion());
        parameters.put("SignatureNonce", UUID.randomUUID().toString());
        parameters.put("Format", this.config.getFormat());

        System.out.println(parameters.get("SignatureNonce"));


        // 对参数进行排序，注意严格区分大小写
        String[] sortedKeys = parameters.keySet().toArray(new String[]{});
        Arrays.sort(sortedKeys);

        // 生成stringToSign字符串
        StringBuilder stringToSign = new StringBuilder();
        stringToSign.append(HTTP_METHOD).append(SEPARATOR);
        stringToSign.append(percentEncode("/")).append(SEPARATOR);

        StringBuilder canonicalizedQueryString = new StringBuilder();
        for (String key : sortedKeys) {
            // 这里注意对key和value进行编码
            canonicalizedQueryString.append("&")
                    .append(percentEncode(key)).append("=")
                    .append(percentEncode(parameters.get(key)));
        }
        // 这里注意对canonicalizedQueryString进行编码
        stringToSign.append(percentEncode(canonicalizedQueryString.toString().substring(1)));

        //构建标准请求字符串
        this.normalRequestUri = stringToSign.toString();
        return this.normalRequestUri;
    }


    //对请求字符串进行签名
    private  String signString(String stringToSign) {
        String ret;
        try {
            ret = AliDnsUtil.sign(this.config.getAccessKeySecret() + "&", stringToSign);
            //保留签名
            this.Signature=ret;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            ret=stringToSign;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            ret=stringToSign;

        } catch (InvalidKeyException e) {
            e.printStackTrace();
            ret=stringToSign;
        }

        return ret;

    }



    private static String percentEncode(String str){
        String ret;
        try {
            ret = AliDnsUtil.percentEncode(str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            ret = str;
        }
        return ret;
    }
    private static String formatIso8601Date(Date date) {
        return AliDnsUtil.formatIso8601Date(date);
    }


    private Map<String,String> parseToMap(String params) {
        Map<String, String> map = new HashMap<String, String>();
        params = params.trim();
        String[] kvArrauy = params.split("&");
        for (int i = 0; i <kvArrauy.length; i++) {
            String entry = kvArrauy[i];
            if(entry!=null && entry.length()>0){
                String[] kv = entry.split("=");
                if(kv.length==2){
                    map.put(kv[0],kv[1]);
                }else{
                    System.out.println("err in: Main line 41");
                }
            }
        }
        return map;
    }

    private void init(String[] args) {
        try {
            parseCommand(args);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void parseCommand(String[] args) throws ParseException {
        Options options = new Options();
        options.addOption("help",false,"help information");
        Option c = Option.builder("c")  //option的名字,判断的时候要使用这个名字
                .required(false)               //是否必须有这个选项
                .hasArg()                         //带一个参数
                .argName("filename")     //参数的名字
                .desc("config file path")  //描述
                .build();
        Option p = Option.builder("p")  //参数拼接
                .required(false)        //是否必须有这个选项
                .hasArg()              //带一个参数
                .argName("params")     //参数的名字
                .desc("like this Action=abc&")  //描述
                .build();
        options.addOption(c);
        options.addOption(p);

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options,args);
//        String[] s = cmd.getArgs();
        //询问是否有help
        if(cmd.hasOption("help")) {
            //调用默认的help函数打印
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp( "-c <文件路径>", options );
            return;
        }
        String filename = null;
        //配置文件路径
        if(cmd.hasOption("c")){
            filename = cmd.getOptionValue("c");
        }else{
            filename = "config.json";
        }

        if(filename!=null){
            loadConfig(filename);
        }
        //params 参数
        String params=null;
        if(cmd.hasOption("p")){
            params = cmd.getOptionValue("p");
            if(params!=null && params.length()>0){
                this.params=params;
            }
        }

    }

    private void loadConfig(String filename) throws ParseException {
        this.config = AliDnsUtil.loadConfig(filename);
    }

    public String getHOST() {
        return HOST;
    }
}
