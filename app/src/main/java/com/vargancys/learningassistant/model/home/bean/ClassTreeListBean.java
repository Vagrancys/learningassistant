package com.vargancys.learningassistant.model.home.bean;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/10/11
 * version:1.0
 * 模块名: 函数树列表实体类
 */
public class ClassTreeListBean {
    private int type;
    private ClassTreeHeader mHeader;
    private ClassTreeItem mItem;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ClassTreeHeader getHeader() {
        return mHeader;
    }

    public void setHeader(ClassTreeHeader mHeader) {
        this.mHeader = mHeader;
    }

    public ClassTreeItem getItem() {
        return mItem;
    }

    public void setItem(ClassTreeItem mItem) {
        this.mItem = mItem;
    }

    public static class ClassTreeHeader{
        private int count;
        private int position;
        private int header_id;
        private String title;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public int getHeader_id() {
            return header_id;
        }

        public void setHeader_id(int header_id) {
            this.header_id = header_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class ClassTreeItem{
        private int item_id;
        private int father_id;
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

        public int getItem_id() {
            return item_id;
        }

        public void setItem_id(int item_id) {
            this.item_id = item_id;
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
    }
}
