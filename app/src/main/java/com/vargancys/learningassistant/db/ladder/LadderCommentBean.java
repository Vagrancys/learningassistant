package com.vargancys.learningassistant.db.ladder;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author Vagrancy
 * @date 2020/5/8
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 天梯交流评论中心数据
 */
@Entity
public class LadderCommentBean {
    @Id
    @Unique
    private Long id;
    //交流分区id
    private int current;

    //评论作者
    private String author;

    //评论作者名称
    private String author_title;

    //评论作者头像
    private String avatar;

    //赞数
    private int praise;

    //踩数
    private int step;

    //评论时间
    private String time;

    //评论内容
    private String comment;

    //回复数
    private int reply_count;

    @Generated(hash = 488697889)
    public LadderCommentBean(Long id, int current, String author,
            String author_title, String avatar, int praise, int step, String time,
            String comment, int reply_count) {
        this.id = id;
        this.current = current;
        this.author = author;
        this.author_title = author_title;
        this.avatar = avatar;
        this.praise = praise;
        this.step = step;
        this.time = time;
        this.comment = comment;
        this.reply_count = reply_count;
    }

    @Generated(hash = 1363383988)
    public LadderCommentBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor_title() {
        return this.author_title;
    }

    public void setAuthor_title(String author_title) {
        this.author_title = author_title;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getPraise() {
        return this.praise;
    }

    public void setPraise(int praise) {
        this.praise = praise;
    }

    public int getStep() {
        return this.step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getReply_count() {
        return this.reply_count;
    }

    public void setReply_count(int reply_count) {
        this.reply_count = reply_count;
    }

    public int getCurrent() {
        return this.current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }
}
