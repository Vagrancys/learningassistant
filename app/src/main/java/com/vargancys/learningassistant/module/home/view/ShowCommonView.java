package com.vargancys.learningassistant.module.home.view;

import com.vargancys.learningassistant.bean.home.HomeKnowContent;
import com.vargancys.learningassistant.presenter.BaseCallBackListener;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/17
 * version:1.0
 * 知识展示公共层
 */
public interface ShowCommonView<T> extends BaseCallBackListener {
    void showFinish(T object);
    void showError(String msg);
}
