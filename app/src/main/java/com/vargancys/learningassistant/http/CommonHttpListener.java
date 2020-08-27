package com.vargancys.learningassistant.http;

public interface CommonHttpListener<T> {
    void onSuccess(int code,T data);
    void onError(Throwable t);
    void onFinish();
}
