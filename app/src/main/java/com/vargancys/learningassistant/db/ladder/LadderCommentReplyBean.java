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
 * Description: 交流评论回复的数据
 */
@Entity
public class LadderCommentReplyBean {
    @Id
    @Unique
    private Long id;

    private long commentId;

    //评论作者
    private long author;

    //评论作者名称
    private String author_title;

    //作者等级
    private String level;

    //评论作者头像
    private String avatar;

    //评论时间
    private String time;

    //评论内容
    private String comment;

    @Generated(hash = 881516712)
    public LadderCommentReplyBean(Long id, long commentId, long author,
            String author_title, String level, String avatar,
            String time, String comment) {
        this.id = id;
        this.commentId = commentId;
        this.author = author;
        this.author_title = author_title;
        this.level = level;
        this.avatar = avatar;
        this.time = time;
        this.comment = comment;
    }

    @Generated(hash = 2030624059)
    public LadderCommentReplyBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getCommentId() {
        return this.commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public long getAuthor() {
        return this.author;
    }

    public void setAuthor(long author) {
        this.author = author;
    }

    public String getAuthor_title() {
        return this.author_title;
    }

    public void setAuthor_title(String author_title) {
        this.author_title = author_title;
    }

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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
}
