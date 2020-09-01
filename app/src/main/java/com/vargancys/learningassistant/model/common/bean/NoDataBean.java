package com.vargancys.learningassistant.model.common.bean;

public class NoDataBean {
    private String message;
    private int code;

    public boolean getCode() {
        return code == 1;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
