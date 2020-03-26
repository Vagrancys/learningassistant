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
public class HomeKnowHistory {
    @Id
    @Unique
    private Long id;

    private long dataId;

    @Generated(hash = 290877120)
    public HomeKnowHistory(Long id, long dataId) {
        this.id = id;
        this.dataId = dataId;
    }

    @Generated(hash = 983775255)
    public HomeKnowHistory() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getDataId() {
        return this.dataId;
    }

    public void setDataId(int dataId) {
        this.dataId = dataId;
    }

    public void setDataId(long dataId) {
        this.dataId = dataId;
    }
}
