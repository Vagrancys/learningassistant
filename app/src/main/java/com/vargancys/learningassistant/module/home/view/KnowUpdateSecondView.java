package com.vargancys.learningassistant.module.home.view;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/30
 * version:1.0
 */
public interface KnowUpdateSecondView extends BaseKnowUpdateView{
    void showFunctionWindow();
    boolean addFunctionData(int common, String title, String summary, String explain);

    void addFunctionError(int error, String msg);

    void addFunctionFinish();
    boolean isFunctionEmpty(int common, String title, String summary, String explain);
}

