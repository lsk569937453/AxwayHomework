package com.axway.currency.vojo;

public class BaseResponse {
    /**
     * response code:0:normal,1:error
     */
    private int responseCode;
    /**
     *
     */
    private Object responseObject;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public Object getResponseObject() {
        return responseObject;
    }

    public void setResponseObject(Object responseObject) {
        this.responseObject = responseObject;
    }
}
