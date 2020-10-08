package com.vargancys.learningassistant.db.knowledge;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/9/19
 * version:1.0
 * 模块名: 临时文章数据类
 */
@Entity
public class TemporaryArticleDb {
    @Id
    private Long temporary_article_id;
    private int article_id;

    private String title;

    private String content;

    @Generated(hash = 432242821)
    public TemporaryArticleDb(Long temporary_article_id, int article_id,
            String title, String content) {
        this.temporary_article_id = temporary_article_id;
        this.article_id = article_id;
        this.title = title;
        this.content = content;
    }

    @Generated(hash = 2088415197)
    public TemporaryArticleDb() {
    }

    public Long getTemporary_article_id() {
        return this.temporary_article_id;
    }

    public void setTemporary_article_id(Long temporary_article_id) {
        this.temporary_article_id = temporary_article_id;
    }

    public int getArticle_id() {
        return this.article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
