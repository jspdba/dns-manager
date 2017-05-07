package com.womaiapp.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by wuchaofei on 2017/5/7.
 */
public class SignUtil {
    // 以下是一段计算签名的示例代码
    private static final  String ALGORITHM = "HmacSHA1";
    private static final String ENCODING = "UTF-8";

    public static String sign(String key, String stringToSign) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance(ALGORITHM);
        mac.init(new SecretKeySpec( key.getBytes(ENCODING), ALGORITHM));
        byte[] signData = mac.doFinal(stringToSign.getBytes(ENCODING));

        return new String(Base64.encodeBase64(signData));
    }
}
