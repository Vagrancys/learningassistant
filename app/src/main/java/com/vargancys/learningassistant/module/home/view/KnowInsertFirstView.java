package com.vargancys.learningassistant.module.home.view;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/09
 * version:1.0
 */
public interface KnowInsertFirstView {
    boolean isFirstEmpty();
    void saveFirstKnowItem();
    void isFirstEqualsError(int error,String msg);
    void isFirstEmptyError(int error,String msg);
    void isFirstEqualsItem();
    void saveFirstItemFinish();
    void saveFirstItemError(int error,String msg);
}
