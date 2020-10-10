package com.vargancys.learningassistant.db.knowledge;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/10/10
 * version:1.0
 * 模块名: 函数本地缓存
 */
@Entity
public class TemporaryClassDb {
    @Id
    private long id;
    private int class_id;
    @Generated(hash = 178241319)
    public TemporaryClassDb(long id, int class_id) {
        this.id = id;
        this.class_id = class_id;
    }
    @Generated(hash = 217389412)
    public TemporaryClassDb() {
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
