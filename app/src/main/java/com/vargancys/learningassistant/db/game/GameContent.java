package com.vargancys.learningassistant.db.game;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/10
 * version:1.0
 */
@Entity
public class GameContent {
    @Id
    @Unique
    private Long id;
    //关卡的名称
    private String title;

    //关卡的题目数
    private int subject;

    //关卡的错误数
    private int error;

    //关卡的得分
    private int score;

    //关卡的难度
    private int difficulty;

    //知识集的id
    private long overviewId;

    @Generated(hash = 1487407364)
    public GameContent(Long id, String title, int subject, int error, int score,
            int difficulty, long overviewId) {
        this.id = id;
        this.title = title;
        this.subject = subject;
        this.error = error;
        this.score = score;
        this.difficulty = difficulty;
        this.overviewId = overviewId;
    }

    @Generated(hash = 1552660531)
    public GameContent() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSubject() {
        return this.subject;
    }

    public void setSubject(int subject) {
        this.subject = subject;
    }

    public int getError() {
        return this.error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getDifficulty() {
        return this.difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public long getOverviewId() {
        return this.overviewId;
    }

    public void setOverviewId(long overviewId) {
        this.overviewId = overviewId;
    }
}
