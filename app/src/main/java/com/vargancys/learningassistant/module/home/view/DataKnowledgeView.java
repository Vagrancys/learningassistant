package com.vargancys.learningassistant.module.home.view;

import com.vargancys.learningassistant.presenter.BaseCallBackListener;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/25
 * version:1.0
 */
public interface DataKnowledgeView extends BaseCallBackListener {
    void deleteSuccess();

    void deleteFail();

    void deleteError(String message);
}
