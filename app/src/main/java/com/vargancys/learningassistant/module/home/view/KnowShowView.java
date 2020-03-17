package com.vargancys.learningassistant.module.home.view;

import com.vargancys.learningassistant.db.home.HomeKnowContent;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/17
 * version:1.0
 */
public interface KnowShowView {
    void showContentFinish(HomeKnowContent homeKnowContent);
    void showContentError(int error,String msg);

}
