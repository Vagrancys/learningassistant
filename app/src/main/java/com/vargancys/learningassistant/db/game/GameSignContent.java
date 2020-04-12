package com.vargancys.learningassistant.db.game;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/11
 * version:1.0
 */
@Entity
public class GameSignContent {
    @Id
    @Unique
    private Long id;

    //打卡时间
    private String time;

    //打卡的标题
    private String title;

    //打卡的心情
    private String summary;

    //关卡的标题
    private String game_title;

    //关卡的难度
    private int level;

    //关卡的最高题数
    private int game_subject_total;

    //关卡的当前题数
    private int game_subject_current;

    //关卡的最高分数
    private int game_score_total;

    //关卡的当前分数
    private int game_score_current;

    //关卡的错题数
    private int game_error_total;

    //关卡的复习错题数
    private int game_error_current;

    @Generated(hash = 1705060593)
    public GameSignContent(Long id, String time, String title, String summary,
            String game_title, int level, int game_subject_total,
            int game_subject_current, int game_score_total, int game_score_current,
            int game_error_total, int game_error_current) {
        this.id = id;
        this.time = time;
        this.title = title;
        this.summary = summary;
        this.game_title = game_title;
        this.level = level;
        this.game_subject_total = game_subject_total;
        this.game_subject_current = game_subject_current;
        this.game_score_total = game_score_total;
        this.game_score_current = game_score_current;
        this.game_error_total = game_error_total;
        this.game_error_current = game_error_current;
    }

    @Generated(hash = 82160446)
    public GameSignContent() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getGame_title() {
        return this.game_title;
    }

    public void setGame_title(String game_title) {
        this.game_title = game_title;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getGame_subject_total() {
        return this.game_subject_total;
    }

    public void setGame_subject_total(int game_subject_total) {
        this.game_subject_total = game_subject_total;
    }

    public int getGame_subject_current() {
        return this.game_subject_current;
    }

    public void setGame_subject_current(int game_subject_current) {
        this.game_subject_current = game_subject_current;
    }

    public int getGame_score_total() {
        return this.game_score_total;
    }

    public void setGame_score_total(int game_score_total) {
        this.game_score_total = game_score_total;
    }

    public int getGame_score_current() {
        return this.game_score_current;
    }

    public void setGame_score_current(int game_score_current) {
        this.game_score_current = game_score_current;
    }

    public int getGame_error_total() {
        return this.game_error_total;
    }

    public void setGame_error_total(int game_error_total) {
        this.game_error_total = game_error_total;
    }

    public int getGame_error_current() {
        return this.game_error_current;
    }

    public void setGame_error_current(int game_error_current) {
        this.game_error_current = game_error_current;
    }
}
