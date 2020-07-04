package com.vargancys.learningassistant.db.home;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Unique;

import com.vagrancys.learningassistant.db.DaoSession;
import com.vagrancys.learningassistant.db.HomeKnowFunctionDao;
import com.vagrancys.learningassistant.db.HomeKnowContentDao;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/07
 * version:1.0
 */
@Entity
public class HomeKnowContent{
    //详细知识的id
    @Id(autoincrement = true)
    @Unique
    private Long id;

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

    //详细函数的数据项
    @ToMany(referencedJoinProperty = "functionId")
    private List<HomeKnowFunction> homeKnowFunctions;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1609494126)
    private transient HomeKnowContentDao myDao;



    @Generated(hash = 390025214)
    public HomeKnowContent() {
    }

    @Generated(hash = 1975441448)
    public HomeKnowContent(Long id, String title, String summary, String show, String explain,
            String heed, String experience) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.show = show;
        this.explain = explain;
        this.heed = heed;
        this.experience = experience;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 531408006)
    public List<HomeKnowFunction> getHomeKnowFunctions() {
        if (homeKnowFunctions == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            HomeKnowFunctionDao targetDao = daoSession.getHomeKnowFunctionDao();
            List<HomeKnowFunction> homeKnowFunctionsNew = targetDao
                    ._queryHomeKnowContent_HomeKnowFunctions(id);
            synchronized (this) {
                if (homeKnowFunctions == null) {
                    homeKnowFunctions = homeKnowFunctionsNew;
                }
            }
        }
        return homeKnowFunctions;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1976930367)
    public synchronized void resetHomeKnowFunctions() {
        homeKnowFunctions = null;
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
    @Generated(hash = 381159665)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getHomeKnowContentDao() : null;
    }

    public Long getId() {
        return this.id;
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

    public void setId(Long id) {
        this.id = id;
    }
}
