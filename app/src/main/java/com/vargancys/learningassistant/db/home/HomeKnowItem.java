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
    private int item_id;
    private String activity;
    private boolean have;
    private String title;
    private int level;
    private String summary;
    private int progress;
    private int count;
    private int max;
    private int masterLevel;
    private String studyTitle;

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
