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
    private List<BookItemBean> items;

    public List<BookItemBean> getItems() {
        return items;
    }

    public void setItems(List<BookItemBean> items) {
        this.items = items;
    }

    public void addItem(BookItemBean item){
        this.items.add(item);
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
