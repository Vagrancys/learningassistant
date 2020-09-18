package com.vargancys.learningassistant.module.home.view;

import com.vargancys.learningassistant.presenter.BaseCallBackListener;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/09
 * version:1.0
 */
public interface InsertArticleView extends BaseCallBackListener {
    boolean isEmpty();
    void isEmptyError(int error);
    void isEmptyFinish();
    void addArticle();
}
