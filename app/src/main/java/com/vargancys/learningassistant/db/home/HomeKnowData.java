package com.vargancys.learningassistant.db.home;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Unique;

import com.vagrancys.learningassistant.db.DaoSession;
import com.vagrancys.learningassistant.db.HomeKnowItemDao;
import com.vagrancys.learningassistant.db.HomeKnowDataDao;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/07
 * version:1.0
 */
@Entity
public class HomeKnowData{
    @Id(autoincrement = true)
    @Unique
    private Long id;

    @ToMany(referencedJoinProperty = "commendId")
    private HomeKnowCommend homeKnowCommend;

    @Generated(hash = 430302506)
    public HomeKnowData() {
    }

    @Generated(hash = 551303484)
    public HomeKnowData(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
