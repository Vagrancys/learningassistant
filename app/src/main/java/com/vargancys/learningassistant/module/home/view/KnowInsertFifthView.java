package com.vargancys.learningassistant.module.home.view;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/09
 * version:1.0
 */
public interface KnowInsertFifthView {
    boolean isFifthEmpty();
    void saveFifthKnowItem();
    void isFifthEqualsError(int error, String msg);
    void isFifthEmptyError(int error, String msg);
    void isFifthEqualsItem();
    void saveFifthItemFinish();
    void saveFifthItemError(int error, String msg);
}
