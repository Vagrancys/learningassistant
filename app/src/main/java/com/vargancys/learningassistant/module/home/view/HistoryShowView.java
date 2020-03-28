package com.vargancys.learningassistant.module.home.view;

import com.vargancys.learningassistant.db.home.HomeKnowHistory;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/28
 * version:1.0
 */
public interface HistoryShowView {
    void showContentFinish(HomeKnowHistory homeKnowHistory);
    void showContentError(int error,String msg);
}
