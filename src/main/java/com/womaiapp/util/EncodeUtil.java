package com.womaiapp.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by wuchaofei on 2017/5/7.
 */
public class EncodeUtil {
    private static final String ENCODING = "UTF-8";

    public static String percentEncode(String value)
            throws UnsupportedEncodingException {
        return value != null ?
                URLEncoder.encode(value, ENCODING).replace("+", "%20")
                        .replace("*", "%2A").replace("%7E", "~")
                : null;
    }
}
