//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cs.domain;

import java.io.Serializable;

public class Response<T> implements Serializable {
    private String xid;
    private int code;
    private String msg;
    private T data;

    public Response() {
    }

    public static <T> Response<T> success(T data) {
        Response<T> res = new Response();
        res.setXid("xid");
        res.setData(data);
        return res;
    }

    public static Response error() {
        Response res = new Response();
        res.setXid("xid");
        return res;
    }

    public static Response error(Integer errorCode, String errMessage) {
        Response res = new Response();
        res.setXid("xid");
        res.setCode(errorCode);
        res.setMsg(errMessage);
        return res;
    }

    public String getXid() {
        return this.xid;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public T getData() {
        return this.data;
    }

    public void setXid(String xid) {
        this.xid = xid;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String toString() {
        return "Response(xid=" + this.getXid() + ", code=" + this.getCode() + ", msg=" + this.getMsg() + ", data=" + this.getData() + ")";
    }
}
