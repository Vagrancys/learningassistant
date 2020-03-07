package com.vargancys.learningassistant.db.common;

import org.litepal.crud.LitePalSupport;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/07
 * version:1.0
 */
public class HelpContentItem extends LitePalSupport {
    //帮助的数量
    private int number;
    //帮助标题
    private String title;
    //发表的时间
    private String time;
    //帮助主内容
    private String summary;
    //有帮助
    private int praise;
    //没有帮助
    private int poor;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPoor(int poor) {
        this.poor = poor;
    }

    public int getPoor() {
        return poor;
    }

    public void setPraise(int praise) {
        this.praise = praise;
    }

    public int getPraise() {
        return praise;
    }
}
