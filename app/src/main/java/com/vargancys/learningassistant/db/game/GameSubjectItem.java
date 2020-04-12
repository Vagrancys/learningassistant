package com.vargancys.learningassistant.db.game;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/12
 * version:1.0
 */
@Entity
public class GameSubjectItem {
    @Id
    @Unique
    private Long id;

    //父id
    private long subjectId;

    @Generated(hash = 457386790)
    public GameSubjectItem(Long id, long subjectId) {
        this.id = id;
        this.subjectId = subjectId;
    }

    @Generated(hash = 1713026714)
    public GameSubjectItem() {
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
}
