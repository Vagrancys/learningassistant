package com.vargancys.learningassistant.bean.home;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/9/20
 * version:1.0
 * 模块名: 文章知识的数据实体类
 */
public class ArticleDataBean {
    private int father_id;
    private int article_id;
    private String title;
    private int level;
    private int master;
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMaster() {
        return master;
    }

    public void setMaster(int master) {
        this.master = master;
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public int getFather_id() {
        return father_id;
    }

    public void setFather_id(int father_id) {
        this.father_id = father_id;
    }
}
