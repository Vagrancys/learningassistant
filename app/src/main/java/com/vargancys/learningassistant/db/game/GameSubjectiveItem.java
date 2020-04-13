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
public class GameSubjectiveItem {
    @Id
    @Unique
    private Long id;

    //父Id
    private long subjectId;

    //主观题的问题标题
    private String title;

    //主观题的答案
    private String answer;

    @Generated(hash = 1852551770)
    public GameSubjectiveItem(Long id, long subjectId, String title,
            String answer) {
        this.id = id;
        this.subjectId = subjectId;
        this.title = title;
        this.answer = answer;
    }

    @Generated(hash = 1998284849)
    public GameSubjectiveItem() {
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

    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
