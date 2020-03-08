package com.vargancys.learningassistant.base;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/07
 * version:1.0
 */
public interface BaseView {
    void saveFinish();
    void saveError();
    void updateFinish();
    void updateError();
    void deleteFinish();
    void deleteError();
    void findFinish(Object object);
    void findError(int error,String msg);
    void findAllFinish();
    void findAllError();
}
