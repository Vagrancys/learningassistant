package com.vargancys.learningassistant.model.home.bean;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/10/9
 * version:1.0
 * 模块名: 函数型实体类
 */
public class ClassBean {
    private String title;
    private int count;
    private String summary;
    private String explain;
    private int level;
    private int father_id;
    private List<ClassTreeBean> trees;

    public List<ClassTreeBean> getTrees() {
        return trees;
    }

    public void setTrees(List<ClassTreeBean> trees) {
        this.trees = trees;
    }

    public int getFather_id() {
        return father_id;
    }

    public void setFather_id(int father_id) {
        this.father_id = father_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
