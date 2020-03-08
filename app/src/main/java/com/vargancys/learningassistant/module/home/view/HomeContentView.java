package com.vargancys.learningassistant.module.home.view;

import com.vargancys.learningassistant.model.home.bean.HomeContentBean;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/05
 * version:1.0
 */
public interface  HomeContentView {
    void showContentBean(List<?> bean);
    void showError(int error,String msg);
    void hideEmpty();
    void showEmpty();
    void deleteFinish(int item_id);
    void deleteError(int error,String msg);
}
