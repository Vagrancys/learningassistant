package com.vargancys.learningassistant.module.overview.view;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/06
 * version:1.0
 */
public interface OverViewAddView extends BaseOverView{
    void saveDataFinish();

    void saveDataError(int error, String message);

    void TidyAllData();
}
