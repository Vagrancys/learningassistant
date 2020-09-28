package com.vargancys.learningassistant.module.home.view;

import com.vargancys.learningassistant.presenter.BaseCallBackListener;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/25
 * version:1.0
 * 书籍view抽象类
 */
public interface DataBookView extends BaseCallBackListener {
    void deleteBookSuccess();

    void deleteBookFail();

    void deleteBookError(String message);
}
