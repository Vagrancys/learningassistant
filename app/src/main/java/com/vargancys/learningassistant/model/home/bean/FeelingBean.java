package com.vargancys.learningassistant.model.home.bean;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/10/22
 * version:1.0
 * 模块名: 感悟型知识实体类
 */
public class FeelingBean {
    private String title;
    private int count;
    private String summary;
    private String explain;
    private int level;
    private int father_id;
    private List<FeelingItemBean> items;

    public String getSummary() {
        return summary;
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

    public List<FeelingItemBean> getItems() {
        return items;
    }

    public void setItems(List<FeelingItemBean> items) {
        this.items = items;
    }

    public static class FeelingItemBean{
        private String title;
        private String summary;
        private int type;
        private int level;
        private int position;
        private int item_id;

        public int getItem_id() {
            return item_id;
        }

        public void setItem_id(int item_id) {
            this.item_id = item_id;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
