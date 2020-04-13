package com.vargancys.learningassistant.db.game;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/13
 * version:1.0
 */
@Entity
public class GameRadioItem {
    @Id
    @Unique
    private Long id;
    //问题项的Id
    private long subjectId;

    //单项选择的问题标题
    private String title;

    //单项选择的答案编号
    private int yes;

    //单项选择的第一个答案
    private String first_title;

    //单项选择的第二个答案
    private String second_title;

    //单项选择的第三个答案
    private String third_title;
    //单项选择的第四个答案
    private String fourth_title;
    @Generated(hash = 1697011375)
    public GameRadioItem(Long id, long subjectId, String title, int yes,
            String first_title, String second_title, String third_title,
            String fourth_title) {
        this.id = id;
        this.subjectId = subjectId;
        this.title = title;
        this.yes = yes;
        this.first_title = first_title;
        this.second_title = second_title;
        this.third_title = third_title;
        this.fourth_title = fourth_title;
    }
    @Generated(hash = 1430460322)
    public GameRadioItem() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public long getSubjectId() {
        return this.subjectId;
    }
    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getYes() {
        return this.yes;
    }
    public void setYes(int yes) {
        this.yes = yes;
    }
    public String getFirst_title() {
        return this.first_title;
    }
    public void setFirst_title(String first_title) {
        this.first_title = first_title;
    }
    public String getSecond_title() {
        return this.second_title;
    }
    public void setSecond_title(String second_title) {
        this.second_title = second_title;
    }
    public String getThird_title() {
        return this.third_title;
    }
    public void setThird_title(String third_title) {
        this.third_title = third_title;
    }
    public String getFourth_title() {
        return this.fourth_title;
    }
    public void setFourth_title(String fourth_title) {
        this.fourth_title = fourth_title;
    }
}
