package com.vargancys.learningassistant.db.overview;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/03
 * version:1.0
 */
@Entity
public class OverViewListItem{
    @Id
    @Unique
    private Long id;
    //content的id
    private long contentId;

    //目录创造者
    private long create;

    //目录上级
    private long parentId;

    //目录标题
    private String title;

    //目录难度等级
    private int masterLevel;

    //目录分层级别
    private int level;

    //目录是否学习
    private boolean study;

    //目录学习分
    private int score;

    @Generated(hash = 185144744)
    public OverViewListItem(Long id, long contentId, long create, long parentId,
            String title, int masterLevel, int level, boolean study, int score) {
        this.id = id;
        this.contentId = contentId;
        this.create = create;
        this.parentId = parentId;
        this.title = title;
        this.masterLevel = masterLevel;
        this.level = level;
        this.study = study;
        this.score = score;
    }

    @Generated(hash = 1807338273)
    public OverViewListItem() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getCreate() {
        return this.create;
    }

    public void setCreate(long create) {
        this.create = create;
    }

    public long getParentId() {
        return this.parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMasterLevel() {
        return this.masterLevel;
    }

    public void setMasterLevel(int masterLevel) {
        this.masterLevel = masterLevel;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean getStudy() {
        return this.study;
    }

    public void setStudy(boolean study) {
        this.study = study;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getContentId() {
        return this.contentId;
    }

    public void setContentId(long contentId) {
        this.contentId = contentId;
    }
}
