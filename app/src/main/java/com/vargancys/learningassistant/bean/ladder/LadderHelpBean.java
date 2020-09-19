package com.vargancys.learningassistant.bean.ladder;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author Vagrancy
 * @date 2020/5/11
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 天梯帮助中心数据实体类
 */
@Entity
public class LadderHelpBean {
    @Id
    @Unique
    private Long id;

    //帮助的编号
    private int number;

    //帮助的标题
    private String title;

    //帮助的简介
    private String summary;

    //帮助的内容
    private String content;

    //帮助的时间
    private String time;

    @Generated(hash = 452596665)
    public LadderHelpBean(Long id, int number, String title, String summary,
            String content, String time) {
        this.id = id;
        this.number = number;
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.time = time;
    }

    @Generated(hash = 616924693)
    public LadderHelpBean() {
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

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
