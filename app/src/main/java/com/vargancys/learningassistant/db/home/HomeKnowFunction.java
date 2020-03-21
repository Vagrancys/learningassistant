package com.vargancys.learningassistant.db.home;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Unique;

import com.vagrancys.learningassistant.db.DaoSession;
import com.vagrancys.learningassistant.db.HomeKnowContentDao;
import com.vagrancys.learningassistant.db.HomeKnowFunctionDao;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/09
 * version:1.0
 */
@Entity
public class HomeKnowFunction{
    //函数的id
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

    @Generated(hash = 1901341501)
    public HomeKnowFunction() {
    }

    @Generated(hash = 1875835771)
    public HomeKnowFunction(Long id, long functionId, int common, String title,
            String summary, String explain) {
        this.id = id;
        this.functionId = functionId;
        this.common = common;
        this.title = title;
        this.summary = summary;
        this.explain = explain;
    }

    public Long getId() {
        return this.id;
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

    public void setId(Long id) {
        this.id = id;
    }

    public long getFunctionId() {
        return this.functionId;
    }

    public void setFunctionId(long functionId) {
        this.functionId = functionId;
    }
}
