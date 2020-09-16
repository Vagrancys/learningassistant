package com.vargancys.learningassistant.presenter;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/9/16
 * version:1.0
 * 模块名: 基础请求回调接口
 */
public interface BaseCallBackListener {
    void onSuccess();
    void onSuccess(Object object);
    void onNoData();
    void onFail();
    void onError(String message);
    void onFinish();
}
