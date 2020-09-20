package com.vargancys.learningassistant.module.home.view;

import com.vargancys.learningassistant.bean.home.HomeKnowCommend;
import com.vargancys.learningassistant.bean.home.HomeKnowContent;
import com.vargancys.learningassistant.bean.home.HomeKnowData;
import com.vargancys.learningassistant.bean.home.HomeKnowHistory;
import com.vargancys.learningassistant.presenter.BaseCallBackListener;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/25
 * version:1.0
 */
public interface DataArticleView extends BaseCallBackListener {
    void showDataFinish(HomeKnowData homeKnowData);
    void showDataError(int error, String message);
    void showCommendEmptyError(int error, String message);
    void showCommendSaveFinish(HomeKnowCommend homeKnowCommend);
    void showCommendSaveError(int error, String message);
    void showSaveCommend();

    void deleteDataFinish();

    void deleteDataError(int error, String message);

    void showRefreshHistoryFinish(List<HomeKnowHistory> homeKnowHistories, HomeKnowContent homeKnowContent);

    void showRefreshHistoryError(int error, String message);
}