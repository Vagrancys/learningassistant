package com.vargancys.learningassistant.module.home.view;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/09
 * version:1.0
 */
public interface KnowInsertThirdView {
    boolean isThirdEmpty();
    void saveThirdKnowItem();
    void isThirdEqualsError(int error, String msg);
    void isThirdEmptyError(int error, String msg);
    void isThirdEqualsItem();
    void saveThirdItemFinish();
    void saveThirdItemError(int error, String msg);
}
