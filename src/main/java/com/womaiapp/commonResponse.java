package com.womaiapp;

/**
 * 公共请求返回结果
 * Created by wuchaofei on 2017/5/7.
 */
public class commonResponse {
    private String RequestId;
    private String HostId;
    private String Code;
    private String Message;

    public String getRequestId() {
        return RequestId;
    }

    public void setRequestId(String requestId) {
        RequestId = requestId;
    }

    public String getHostId() {
        return HostId;
    }

    public void setHostId(String hostId) {
        HostId = hostId;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
