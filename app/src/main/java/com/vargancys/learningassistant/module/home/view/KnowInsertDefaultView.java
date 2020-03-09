package com.vargancys.learningassistant.module.home.view;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/09
 * version:1.0
 */
public interface KnowInsertDefaultView {
    boolean isDefaultEmpty();
    void saveDefaultKnowItem();
    void isDefaultEqualsError(int error,String msg);
    void isDefaultEmptyError(int error,String msg);
    void isDefaultEqualsItem();
    void saveDefaultItemFinish();
    void saveDefaultItemError(int error,String msg);
}
