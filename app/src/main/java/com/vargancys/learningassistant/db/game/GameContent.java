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
 * 关卡的数据统计中心
 * 关卡的总数据
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

    //关卡通过的题目数
    private int subject_current;

    //关卡的错误数
    private int error;

    //关卡复习的错题数
    private int error_current;

    //关卡的得分
    private int score;

    //关卡得到的分数
    private int score_current;

    //关卡的难度
    private int difficulty;

    //知识集的id
    private long overviewId;

    //知识集的标题
    private String game_title;


    @Generated(hash = 1083096567)
    public GameContent(Long id, String title, int subject, int subject_current,
            int error, int error_current, int score, int score_current,
            int difficulty, long overviewId, String game_title) {
        this.id = id;
        this.title = title;
        this.subject = subject;
        this.subject_current = subject_current;
        this.error = error;
        this.error_current = error_current;
        this.score = score;
        this.score_current = score_current;
        this.difficulty = difficulty;
        this.overviewId = overviewId;
        this.game_title = game_title;
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

    public int getSubject_current() {
        return this.subject_current;
    }

    public void setSubject_current(int subject_current) {
        this.subject_current = subject_current;
    }

    public int getError_current() {
        return this.error_current;
    }

    public void setError_current(int error_current) {
        this.error_current = error_current;
    }

    public int getScore_current() {
        return this.score_current;
    }

    public void setScore_current(int score_current) {
        this.score_current = score_current;
    }

    public String getGame_title() {
        return this.game_title;
    }

    public void setGame_title(String game_title) {
        this.game_title = game_title;
    }
}
