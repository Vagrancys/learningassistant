package com.vargancys.learningassistant.module.home.view;

import com.vargancys.learningassistant.presenter.BaseCallBackListener;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/28
 * version:1.0
 */
public interface BaseKnowLedgeUpdateView extends BaseCallBackListener {
    boolean isPass();
    void isPassSuccess();
    void isPassFail();
    void onUpdateSuccess();
    void onUpdateFail();
}
