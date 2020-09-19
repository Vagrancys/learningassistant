package com.vargancys.learningassistant.bean.home;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/28
 * version:1.0
 */
@Entity
public class HomeKnowHistoryFunction {
    @Id(autoincrement = true)
    @Unique
    private Long id;

    private long functionId;

    //函数的使用级别
    private int common;

    //函数的名称
    private String title;

    //函数的使用背景
    private String summary;

    //函数的使用
    private String explain;

    @Generated(hash = 1657792154)
    public HomeKnowHistoryFunction(Long id, long functionId, int common,
            String title, String summary, String explain) {
        this.id = id;
        this.functionId = functionId;
        this.common = common;
        this.title = title;
        this.summary = summary;
        this.explain = explain;
    }

    @Generated(hash = 1703535537)
    public HomeKnowHistoryFunction() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getFunctionId() {
        return this.functionId;
    }

    public void setFunctionId(long functionId) {
        this.functionId = functionId;
    }

    public int getCommon() {
        return this.common;
    }

    public void setCommon(int common) {
        this.common = common;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getExplain() {
        return this.explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }
}
