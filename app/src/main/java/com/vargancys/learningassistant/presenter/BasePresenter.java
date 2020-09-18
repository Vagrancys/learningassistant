package com.vargancys.learningassistant.presenter;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/9/16
 * version:1.0
 * 模块名: 基础封装presenter
 */
public interface BasePresenter<T> {
    void add(T object);
    void delete(int id);
    void delete(int[] ids);
    void query(int id);
    void query(int[] ids);
    void nativeQuery(int id);
    void update(T object);
}
