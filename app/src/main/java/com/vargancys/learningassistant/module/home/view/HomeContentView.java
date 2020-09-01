package com.vargancys.learningassistant.module.home.view;

import com.vargancys.learningassistant.db.home.KnowLedgeBean;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/05
 * version:1.0
 */
public interface  HomeContentView {
    void showContentBean(List<KnowLedgeBean> bean);
    void showError(int error,String msg);
    void hideEmpty();
    void showEmpty();
    void deleteFinish(long item_id);
    void deleteError(int error,String msg);
    void showRefreshContentBean(List<KnowLedgeBean> bean);
}
