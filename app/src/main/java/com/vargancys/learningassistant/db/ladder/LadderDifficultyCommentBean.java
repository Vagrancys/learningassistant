package com.vargancys.learningassistant.db.ladder;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author Vagrancy
 * @date 2020/5/10
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 难度评论的实体类
 */
@Entity
public class LadderDifficultyCommentBean {
    @Id
    @Unique
    private Long id;

    //作者id
    private int author;
    //作者名
    private String author_title;
    //评论内容
    private String comment;
    //评论时间
    private String time;
    //评论所属的难度区
    private int type;
    @Generated(hash = 933864588)
    public LadderDifficultyCommentBean(Long id, int author, String author_title,
            String comment, String time, int type) {
        this.id = id;
        this.author = author;
        this.author_title = author_title;
        this.comment = comment;
        this.time = time;
        this.type = type;
    }
    @Generated(hash = 492235373)
    public LadderDifficultyCommentBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getAuthor() {
        return this.author;
    }
    public void setAuthor(int author) {
        this.author = author;
    }
    public String getAuthor_title() {
        return this.author_title;
    }
    public void setAuthor_title(String author_title) {
        this.author_title = author_title;
    }
    public String getComment() {
        return this.comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }

}
