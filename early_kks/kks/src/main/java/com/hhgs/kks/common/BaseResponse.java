package com.hhgs.kks.common;

public class BaseResponse {
    //private Object data;
    private String repMessage;

    public static BaseResponse initSuccessBaseResponse(Object data) {
        BaseResponse baseResponse = new BaseResponse();
        //baseResponse.setData(data);
        baseResponse.setRepMessage("操作成功");
        return baseResponse;
    }

    public static BaseResponse initSuccessBaseResponse(String baseMessage) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setRepMessage(baseMessage);
        return baseResponse;
    }

    public static BaseResponse initErrorBaseResponse(String baseMessqge) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setRepMessage(baseMessqge);
        return baseResponse;
    }

   /* public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }*/

    public String getRepMessage() {
        return repMessage;
    }

    public void setRepMessage(String repMessqge) {
        this.repMessage = repMessqge;
    }
}
