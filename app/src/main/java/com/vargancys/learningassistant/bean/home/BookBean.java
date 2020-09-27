package com.vargancys.learningassistant.bean.home;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/9/24
 * version:1.0
 * 模块名: 书籍类实体类
 */
public class BookBean {
    private String title;
    private int count;
    private String summary;
    private String explain;
    private int level;
    private int father_id;

    private List<BookItemBean> items;

    public int getFather_id() {
        return father_id;
    }

    public void setFather_id(int father_id) {
        this.father_id = father_id;
    }

    public List<BookItemBean> getItems() {
        return items;
    }

    public void setItems(List<BookItemBean> items) {
        this.items = items;
    }

    public void addItem(BookItemBean item){
        this.items.add(item);
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

    public static class BookItemBean{
        private int index;
        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }
}
