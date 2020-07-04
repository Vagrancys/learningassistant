package com.vargancys.learningassistant.db.common;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/08
 * version:1.0
 */
@Entity
public class HelpCommendItem{

    @Id(autoincrement = true)
    @Unique
    private Long id;
    private long commendId;
    private String summary;
    private String time;

    @Generated(hash = 964478489)
    public HelpCommendItem(Long id, long commendId, String summary, String time) {
        this.id = id;
        this.commendId = commendId;
        this.summary = summary;
        this.time = time;
    }

    @Generated(hash = 1245335468)
    public HelpCommendItem() {
    }

    public Long getId() {
        return this.id;
    }

    public long getCommendId() {
        return this.commendId;
    }

    public void setCommendId(long commendId) {
        this.commendId = commendId;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
