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
public class GameMultipleItem {
    @Id
    @Unique
    private Long id;


    //多项选择的问题标题
    private String title;

    //多项选择的第一个判断
    private boolean first_answer;

    //多项选择的第二个判断
    private boolean second_answer;

    //多项选择的第三个判断
    private boolean third_answer;

    //多项选择的第四个判断
    private boolean fourth_answer;

    //多项选择的第一个答案
    private String first_title;

    //多项选择的第二个答案
    private String second_title;

    //多项选择的第三个答案
    private String third_title;

    //多项选择的第四个答案
    private String fourth_title;

    @Generated(hash = 2025467836)
    public GameMultipleItem(Long id, String title, boolean first_answer,
            boolean second_answer, boolean third_answer, boolean fourth_answer,
            String first_title, String second_title, String third_title,
            String fourth_title) {
        this.id = id;
        this.title = title;
        this.first_answer = first_answer;
        this.second_answer = second_answer;
        this.third_answer = third_answer;
        this.fourth_answer = fourth_answer;
        this.first_title = first_title;
        this.second_title = second_title;
        this.third_title = third_title;
        this.fourth_title = fourth_title;
    }

    @Generated(hash = 436485932)
    public GameMultipleItem() {
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

    public boolean getFirst_answer() {
        return this.first_answer;
    }

    public void setFirst_answer(boolean first_answer) {
        this.first_answer = first_answer;
    }

    public boolean getSecond_answer() {
        return this.second_answer;
    }

    public void setSecond_answer(boolean second_answer) {
        this.second_answer = second_answer;
    }

    public boolean getThird_answer() {
        return this.third_answer;
    }

    public void setThird_answer(boolean third_answer) {
        this.third_answer = third_answer;
    }

    public boolean getFourth_answer() {
        return this.fourth_answer;
    }

    public void setFourth_answer(boolean fourth_answer) {
        this.fourth_answer = fourth_answer;
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
