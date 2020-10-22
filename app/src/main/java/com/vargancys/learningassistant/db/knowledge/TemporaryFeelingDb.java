package com.vargancys.learningassistant.db.knowledge;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/10/22
 * version:1.0
 * 模块名: 临时缓存感悟型
 */
@Entity
public class TemporaryFeelingDb {
    @Id
    private long id;
    private int class_id;
    @Generated(hash = 1123817700)
    public TemporaryFeelingDb(long id, int class_id) {
        this.id = id;
        this.class_id = class_id;
    }
    @Generated(hash = 487301302)
    public TemporaryFeelingDb() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public int getClass_id() {
        return this.class_id;
    }
    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }
}
