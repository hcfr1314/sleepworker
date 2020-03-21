package com.huanghe.alarm.api.utils;

import com.huanghe.alarm.api.common.Pages;

import java.io.Serializable;

public class BaseResponse implements Serializable {

    private String message;

    private Object data;

    private int code;

    private Pages pages;

    public  static BaseResponse initSuccess(Object data,Pages pages) {
        BaseResponse base = new BaseResponse("success", data, 200,pages);
        return base;
    }


    public static BaseResponse initError(Object data,String message) {
        return new BaseResponse(message, data, 500, null);
    }

    public BaseResponse() {
    }

    public BaseResponse(String message, Object data, int code,Pages pages) {
        this.message = message;
        this.data = data;
        this.code = code;
        this.pages  =pages;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Pages getPages() {
        return pages;
    }

    public void setPages(Pages pages) {
        this.pages = pages;
    }
}
