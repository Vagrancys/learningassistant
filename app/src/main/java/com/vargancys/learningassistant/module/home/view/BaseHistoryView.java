package com.vargancys.learningassistant.module.home.view;

import com.vargancys.learningassistant.bean.home.HomeKnowHistory;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/28
 * version:1.0
 */
public interface BaseHistoryView {
    void showHistoryDataFinish(List<HomeKnowHistory> histories);
    void showHistoryDataError(int error, String message);
}
