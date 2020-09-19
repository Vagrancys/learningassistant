package com.vargancys.learningassistant.bean.home;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/9/16
 * version:1.0
 * 模块名: 文章型知识实体类
 */
public class ArticleBean {
    //id
    public static String ARTICLE_ID = "article_id";
    public static String KNOWLEDGE_ID = "knowledge_id";
    public static String TITLE = "title";
    public static String CONTENT = "content";

    private int id;
    private int knowledge_id;
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getKnowledge_id() {
        return knowledge_id;
    }

    public void setKnowledge_id(int knowledge_id) {
        this.knowledge_id = knowledge_id;
    }

    public String title;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //标题
}
