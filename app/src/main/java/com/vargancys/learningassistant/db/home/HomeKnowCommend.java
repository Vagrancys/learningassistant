package com.vargancys.learningassistant.db.home;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/25
 * version:1.0
 */
@Entity
public class HomeKnowCommend {
    @Id
    @Unique
    private Long id;

    private long commendId;

    private String title;

    private String time;

    @Generated(hash = 326775949)
    public HomeKnowCommend(Long id, long commendId, String title, String time) {
        this.id = id;
        this.commendId = commendId;
        this.title = title;
        this.time = time;
    }

    @Generated(hash = 1790910422)
    public HomeKnowCommend() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getCommendId() {
        return this.commendId;
    }

    public void setCommendId(long commendId) {
        this.commendId = commendId;
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

}
