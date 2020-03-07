package com.vargancys.learningassistant.module.common.view;

import com.vargancys.learningassistant.base.BaseView;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/07
 * version:1.0
 */
public interface HelpContentView extends BaseView {
    void showContentBean(List<?> bean);
    void showError(int error,String msg);
    void hideEmpty();
    void showEmpty();
}
