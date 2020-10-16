package com.vargancys.learningassistant.model.home.bean;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/10/9
 * version:1.0
 * 模块名:
 */
public class ClassTreeBean {
    private int tree_id;
    private int position;
    private String title;
    private int count;
    private int type;
    private String summary;
    private int level;
    private List<ClassTreeItemBean> items;

    public List<ClassTreeItemBean> getItems() {
        return items;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setItems(List<ClassTreeItemBean> items) {
        this.items = items;
    }

    public void setTree_id(int tree_id) {
        this.tree_id = tree_id;
    }

    public int getTree_id() {
        return tree_id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static class ClassTreeItemBean{
        private int tree_item_id;
        private int position;
        private String title;
        private int level;
        private String summary;

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getTree_item_id() {
            return tree_item_id;
        }

        public void setTree_item_id(int tree_item_id) {
            this.tree_item_id = tree_item_id;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getPosition() {
            return position;
        }
    }
}
