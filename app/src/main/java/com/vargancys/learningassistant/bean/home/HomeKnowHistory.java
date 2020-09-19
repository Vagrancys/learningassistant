package com.vargancys.learningassistant.bean.home;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

import java.util.List;
import org.greenrobot.greendao.DaoException;
import com.vagrancys.learningassistant.db.DaoSession;
import com.vagrancys.learningassistant.db.HomeKnowHistoryFunctionDao;
import com.vagrancys.learningassistant.db.HomeKnowHistoryDao;

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

    //详细知识的标题
    private String title;

    //详细知识的简介
    private String summary;

    //详细知识的示范
    private String show;

    //详细知识的解释
    private String explain;

    //详细知识的注意点
    private String heed;

    //详细知识的使用心得
    private String experience;

    //修改的时间
    private String time;

    //详细函数的数据项
    @ToMany(referencedJoinProperty = "functionId")
    private List<HomeKnowHistoryFunction> homeKnowHistoryFunctions;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 432117761)
    private transient HomeKnowHistoryDao myDao;

    @Generated(hash = 490405695)
    public HomeKnowHistory(Long id, long dataId, String title, String summary, String show,
            String explain, String heed, String experience, String time) {
        this.id = id;
        this.dataId = dataId;
        this.title = title;
        this.summary = summary;
        this.show = show;
        this.explain = explain;
        this.heed = heed;
        this.experience = experience;
        this.time = time;
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

    public void setDataId(long dataId) {
        this.dataId = dataId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getShow() {
        return this.show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public String getExplain() {
        return this.explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getHeed() {
        return this.heed;
    }

    public void setHeed(String heed) {
        this.heed = heed;
    }

    public String getExperience() {
        return this.experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1077049711)
    public List<HomeKnowHistoryFunction> getHomeKnowHistoryFunctions() {
        if (homeKnowHistoryFunctions == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            HomeKnowHistoryFunctionDao targetDao = daoSession
                    .getHomeKnowHistoryFunctionDao();
            List<HomeKnowHistoryFunction> homeKnowHistoryFunctionsNew = targetDao
                    ._queryHomeKnowHistory_HomeKnowHistoryFunctions(id);
            synchronized (this) {
                if (homeKnowHistoryFunctions == null) {
                    homeKnowHistoryFunctions = homeKnowHistoryFunctionsNew;
                }
            }
        }
        return homeKnowHistoryFunctions;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1716480003)
    public synchronized void resetHomeKnowHistoryFunctions() {
        homeKnowHistoryFunctions = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 703076555)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getHomeKnowHistoryDao() : null;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
