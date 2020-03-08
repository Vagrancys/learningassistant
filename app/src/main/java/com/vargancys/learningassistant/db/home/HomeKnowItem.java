package com.vargancys.learningassistant.db.home;

import org.litepal.crud.LitePalSupport;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/07
 * version:1.0
 * 首页知识item的表结构
 */
public class HomeKnowItem extends LitePalSupport{
    //知识项id
    private int item_id;

    //官方知识
    private boolean official;

    //知识项的演示需要类
    private String activity;

    //知识项演示判断
    private boolean have;

    //知识项的标题
    private String title;

    //知识项的复杂与深度度
    private int level;

    //知识项的简要概述
    private String summary;

    //知识项的观看进度
    private int progress;

    //知识项当前消耗度
    private int count;

    //知识项最大消耗度
    private int max;
    //知识项的掌握程度
    private int masterLevel;

    //知识项的学习要求
    private String studyTitle;

    //知识项内容是否创建了
    private boolean createClass;

    private HomeKnowData homeKnowData;

    public HomeKnowData getHomeKnowData() {
        return homeKnowData;
    }

    public void setHomeKnowData(HomeKnowData homeKnowData) {
        this.homeKnowData = homeKnowData;
    }

    public void setOfficial(boolean official) {
        this.official = official;
    }

    public boolean isOfficial() {
        return official;
    }

    public void setCreateClass(boolean createClass) {
        this.createClass = createClass;
    }

    public boolean isCreateClass() {
        return createClass;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getStudyTitle() {
        return studyTitle;
    }

    public void setStudyTitle(String studyTitle) {
        this.studyTitle = studyTitle;
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

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getLevel() {
        return level;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isHave() {
        return have;
    }

    public int getMasterLevel() {
        return masterLevel;
    }

    public void setHave(boolean have) {
        this.have = have;
    }

    public int getMax() {
        return max;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getProgress() {
        return progress;
    }

    public void setMasterLevel(int masterLevel) {
        this.masterLevel = masterLevel;
    }

    public String getSummary() {
        return summary;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
}
