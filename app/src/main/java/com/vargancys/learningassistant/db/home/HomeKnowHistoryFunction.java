package com.vargancys.learningassistant.db.home;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/28
 * version:1.0
 */
@Entity
public class HomeKnowHistoryFunction {
    @Id(autoincrement = true)
    @Unique
    private Long id;

    private long functionId;

    @Generated(hash = 2109386425)
    public HomeKnowHistoryFunction(Long id, long functionId) {
        this.id = id;
        this.functionId = functionId;
    }

    @Generated(hash = 1703535537)
    public HomeKnowHistoryFunction() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getFunctionId() {
        return this.functionId;
    }

    public void setFunctionId(long functionId) {
        this.functionId = functionId;
    }
}
