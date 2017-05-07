package com.womaiapp;

/**
 * 配置文件
 * Created by wuchaofei on 2017/5/7.
 */
public class Config {
    public String Version;
    public String AccessKeyID;
    public String AccessKeySecret;
    public String SignatureMethod="HMAC-SHA1";
    public String SignatureVersion="1.0";
    public String Format="1.0";

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }

    public String getAccessKeyID() {
        return AccessKeyID;
    }

    public void setAccessKeyID(String accessKeyID) {
        AccessKeyID = accessKeyID;
    }

    public String getAccessKeySecret() {
        return AccessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        AccessKeySecret = accessKeySecret;
    }

    public String getSignatureMethod() {
        return SignatureMethod;
    }

    public void setSignatureMethod(String signatureMethod) {
        SignatureMethod = signatureMethod;
    }

    public String getSignatureVersion() {
        return SignatureVersion;
    }

    public void setSignatureVersion(String signatureVersion) {
        SignatureVersion = signatureVersion;
    }

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }

    @Override
    public String toString() {
        return "Config{" +
                "Version='" + Version + '\'' +
                ", AccessKeyID='" + AccessKeyID + '\'' +
                ", AccessKeySecret='" + AccessKeySecret + '\'' +
                ", SignatureMethod='" + SignatureMethod + '\'' +
                ", SignatureVersion='" + SignatureVersion + '\'' +
                ", Format='" + Format + '\'' +
                '}';
    }
}
