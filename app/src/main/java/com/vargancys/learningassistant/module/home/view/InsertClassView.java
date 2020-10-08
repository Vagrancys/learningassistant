package com.vargancys.learningassistant.module.home.view;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/09
 * version:1.0
 */
public interface InsertClassView {
    boolean isFourthEmpty();
    void saveFourthKnowItem();
    void isFourthEqualsError(int error, String msg);
    void isFourthEmptyError(int error, String msg);
    void isFourthEqualsItem();
    void saveFourthItemFinish();
    void saveFourthItemError(int error, String msg);
    boolean addFunctionData(int common, String title, String summary, String explain);
    void addFunctionFinish();
    void addFunctionError(int error, String msg);
    boolean isFunctionEmpty(int common, String title, String summary, String explain);
    void showFunctionWindow();
}
