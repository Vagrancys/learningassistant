package com.vargancys.learningassistant.db.home;

import org.litepal.crud.LitePalSupport;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/09
 * version:1.0
 */
public class HomeKnowFunction extends LitePalSupport {
    //函数的id
    private int function_id;

    //函数的使用级别
    private int common;

    //函数的名称
    private String title;

    //函数的使用背景
    private String summary;

    //函数的使用
    private String explain;

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setCommon(int common) {
        this.common = common;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public int getCommon() {
        return common;
    }

    public int getFunction_id() {
        return function_id;
    }

    public void setFunction_id(int function_id) {
        this.function_id = function_id;
    }
}
