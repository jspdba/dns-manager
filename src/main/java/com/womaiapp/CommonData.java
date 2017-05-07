package com.womaiapp;
/**
 * 公共请求参数
 * Created by wuchaofei on 2017/5/7.
 *
 * https://alidns.aliyuncs.com/
 * ?Format=xml
 * &Version=2015-01-09
 * &Signature=Pc5WB8gokVn0xfeu%2FZV%2BiNM1dgI%3D
 * &SignatureMethod=HMAC-SHA1
 * &SignatureNonce=15215528852396
 * &SignatureVersion=1.0
 * &AccessKeyId=key-test
 * &Timestamp=2015-01-09T12:00:00Z
 */
public class CommonData {

//    返回值的类型，支持JSON与XML。默认为XML
    private String Format;
//    API版本号，为日期形式：YYYY-MM-DD，本版本对应为2015-01-09
    private String Version;
//    阿里云颁发给用户的访问服务所用的密钥ID
    private String AccessKeyId;
//    签名结果串，关于签名的计算方法，请参见 签名机制。
    private String Signature;
//    签名方式，目前支持HMAC-SHA1
    private String SignatureMethod;
//    请求的时间戳。日期格式按照ISO8601标准表示，并需要使用UTC时间。格式为YYYY-MM-DDThh:mm:ssZ 例如，2015-01-09T12:00:00Z（为UTC时间2015年1月9日12点0分0秒）
    private String Timestamp;
//    签名算法版本，目前版本是1.0
    private String SignatureVersion;
//    唯一随机数，用于防止网络重放攻击。用户在不同请求间要使用不同的随机数值
    private String SignatureNonce;

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }

    public String getAccessKeyId() {
        return AccessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        AccessKeyId = accessKeyId;
    }

    public String getSignature() {
        return Signature;
    }

    public void setSignature(String signature) {
        Signature = signature;
    }

    public String getSignatureMethod() {
        return SignatureMethod;
    }

    public void setSignatureMethod(String signatureMethod) {
        SignatureMethod = signatureMethod;
    }

    public String getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(String timestamp) {
        Timestamp = timestamp;
    }

    public String getSignatureVersion() {
        return SignatureVersion;
    }

    public void setSignatureVersion(String signatureVersion) {
        SignatureVersion = signatureVersion;
    }

    public String getSignatureNonce() {
        return SignatureNonce;
    }

    public void setSignatureNonce(String signatureNonce) {
        SignatureNonce = signatureNonce;
    }
}
