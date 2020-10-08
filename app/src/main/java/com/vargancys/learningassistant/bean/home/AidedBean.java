package com.vargancys.learningassistant.bean.home;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/10/7
 * version:1.0
 * 模块名: 辅助知识实体类
 */
public class AidedBean {
    private int id;
    private String title;
    private int count;
    private String summary;
    private String explain;
    private int level;
    private int father_id;
    private String directory;
    private String now_explain;
    private String deep_explain;
    private String advance;
    private String experience;
    private String publicize;
    private String Case;

    public String getCase() {
        return Case;
    }

    public void setCase(String aCase) {
        Case = aCase;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getNow_explain() {
        return now_explain;
    }

    public void setNow_explain(String now_explain) {
        this.now_explain = now_explain;
    }

    public String getDeep_explain() {
        return deep_explain;
    }

    public void setDeep_explain(String deep_explain) {
        this.deep_explain = deep_explain;
    }

    public String getAdvance() {
        return advance;
    }

    public void setAdvance(String advance) {
        this.advance = advance;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getPublicize() {
        return publicize;
    }

    public void setPublicize(String publicize) {
        this.publicize = publicize;
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

    public int getFather_id() {
        return father_id;
    }

    public void setFather_id(int father_id) {
        this.father_id = father_id;
    }
}
