package com.vargancys.learningassistant.module.home.view;

import com.vargancys.learningassistant.bean.home.KnowLedgeBean;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/01
 * version:1.0
 */
public interface HomeCommonView {
    void showAllDataFinish(List<KnowLedgeBean> homeKnowItems);
    void showAllDataError(int error,String message);
}
