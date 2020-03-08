package com.vargancys.learningassistant.module.home.view;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/08
 * version:1.0
 */
public interface HomeAddView {
    void queryRepeatFinish();

    void queryRepeatError();

    void saveFinish();

    void saveError(int i, String s);
}
