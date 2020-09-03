package com.vargancys.learningassistant.db.common;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Unique;

import com.vagrancys.learningassistant.db.DaoSession;
import com.vagrancys.learningassistant.db.HelpCommendItemDao;
import com.vagrancys.learningassistant.db.HelpContentItemDao;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/07
 * version:1.0
 */

public class HelpContentBean {
    public static String ID = "id";
    public static String NUMBER = "number";
    public static String TITLE = "title";
    public static String TIME = "time";
    public static String SUMMARY = "summary";
    public static String PRAISE = "praise";
    public static String POOR = "poor";
    private int id;
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

    public int getId() {
        return this.id;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getPraise() {
        return this.praise;
    }

    public void setPraise(int praise) {
        this.praise = praise;
    }

    public int getPoor() {
        return this.poor;
    }

    public void setPoor(int poor) {
        this.poor = poor;
    }

    public void setId(int id) {
        this.id = id;
    }
}
