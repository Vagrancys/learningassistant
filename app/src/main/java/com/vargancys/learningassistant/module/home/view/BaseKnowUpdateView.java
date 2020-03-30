package com.vargancys.learningassistant.module.home.view;

import com.vargancys.learningassistant.db.home.HomeKnowContent;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/28
 * version:1.0
 */
public interface BaseKnowUpdateView {
    void showKnowDataFinish(HomeKnowContent content);
    void showKnowDataError(int error,String message);
    boolean isKnowUpdateDefaultEmpty();
    void showKnowEmptyError(int error,String message);
    boolean isKnowUpdateDefaultEquals();
    void saveKnowUpdateContent();
    void showKnowEqualsError(int error,String message);
    void showKnowSaveFinish();
    void showKnowSaveError(int error,String message);
}
