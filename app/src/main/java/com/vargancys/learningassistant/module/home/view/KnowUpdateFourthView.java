package com.vargancys.learningassistant.module.home.view;

import com.vargancys.learningassistant.db.home.HomeKnowContent;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/28
 * version:1.0
 */
public interface KnowUpdateFourthView extends BaseKnowUpdateView{
    void showFunctionWindow();
    boolean addFunctionData(int common, String title, String summary, String explain);

    void addFunctionError(int error, String msg);

    void addFunctionFinish();
    boolean isFunctionEmpty(int common, String title, String summary, String explain);
}
