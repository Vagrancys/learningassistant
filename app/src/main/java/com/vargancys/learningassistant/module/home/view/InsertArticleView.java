package com.vargancys.learningassistant.module.home.view;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/09
 * version:1.0
 */
public interface InsertArticleView{
    boolean isEmpty();
    void isEmptyFail(int error);
    void isEmptySuccess();
    void nativeQueryFinish(Object object);
}
