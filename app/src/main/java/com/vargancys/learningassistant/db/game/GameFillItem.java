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
public class GameFillItem {
    @Id
    @Unique
    private Long id;

    //父Id
    private long subjectId;

    //填空的问题标题
    private String title;

    //填空的答案
    private int answer;

    //填空的第一个答案
    private String first_answer;

    //填空的第二个答案
    private String second_answer;

    //填空的第三个答案
    private String third_answer;

    //填空的第四个答案
    private String fourth_answer;

    @Generated(hash = 221129465)
    public GameFillItem(Long id, long subjectId, String title, int answer,
            String first_answer, String second_answer, String third_answer,
            String fourth_answer) {
        this.id = id;
        this.subjectId = subjectId;
        this.title = title;
        this.answer = answer;
        this.first_answer = first_answer;
        this.second_answer = second_answer;
        this.third_answer = third_answer;
        this.fourth_answer = fourth_answer;
    }

    @Generated(hash = 1309750846)
    public GameFillItem() {
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

    public int getAnswer() {
        return this.answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public String getFirst_answer() {
        return this.first_answer;
    }

    public void setFirst_answer(String first_answer) {
        this.first_answer = first_answer;
    }

    public String getSecond_answer() {
        return this.second_answer;
    }

    public void setSecond_answer(String second_answer) {
        this.second_answer = second_answer;
    }

    public String getThird_answer() {
        return this.third_answer;
    }

    public void setThird_answer(String third_answer) {
        this.third_answer = third_answer;
    }

    public String getFourth_answer() {
        return this.fourth_answer;
    }

    public void setFourth_answer(String fourth_answer) {
        this.fourth_answer = fourth_answer;
    }
}
