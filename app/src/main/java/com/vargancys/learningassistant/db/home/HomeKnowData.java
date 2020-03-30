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

import java.util.List;
import com.vagrancys.learningassistant.db.HomeKnowCommendDao;
import com.vagrancys.learningassistant.db.HomeKnowHistoryDao;

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

    //数据的标题
    private String title;

    //数据的等级
    private int level;

    //学习次数
    private int count;

    //掌握层次
    private String master;

    //上次测试时间
    private String time;

    //修改的历史次数
    private int historycount;

    //修改的历史时间
    private String historytime;

    //评论数
    private int commendcount;

    @ToMany(referencedJoinProperty = "dataId")
    private List<HomeKnowHistory> homeKnowHistorys;

    @ToMany(referencedJoinProperty = "commendId")
    private List<HomeKnowCommend> homeKnowCommends;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1256924068)
    private transient HomeKnowDataDao myDao;

    @Generated(hash = 430302506)
    public HomeKnowData() {
    }

    @Generated(hash = 2064845631)
    public HomeKnowData(Long id, String title, int level, int count, String master, String time,
            int historycount, String historytime, int commendcount) {
        this.id = id;
        this.title = title;
        this.level = level;
        this.count = count;
        this.master = master;
        this.time = time;
        this.historycount = historycount;
        this.historytime = historytime;
        this.commendcount = commendcount;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMaster() {
        return this.master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getHistorycount() {
        return this.historycount;
    }

    public void setHistorycount(int historycount) {
        this.historycount = historycount;
    }

    public String getHistorytime() {
        return this.historytime;
    }

    public void setHistorytime(String historytime) {
        this.historytime = historytime;
    }



    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 159825267)
    public synchronized void resetHomeKnowHistorys() {
        homeKnowHistorys = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1715037468)
    public List<HomeKnowCommend> getHomeKnowCommends() {
        if (homeKnowCommends == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            HomeKnowCommendDao targetDao = daoSession.getHomeKnowCommendDao();
            List<HomeKnowCommend> homeKnowCommendsNew = targetDao
                    ._queryHomeKnowData_HomeKnowCommends(id);
            synchronized (this) {
                if (homeKnowCommends == null) {
                    homeKnowCommends = homeKnowCommendsNew;
                }
            }
        }
        return homeKnowCommends;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 53352566)
    public synchronized void resetHomeKnowCommends() {
        homeKnowCommends = null;
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
    @Generated(hash = 1246873565)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getHomeKnowDataDao() : null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 273287508)
    public List<HomeKnowHistory> getHomeKnowHistorys() {
        if (homeKnowHistorys == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            HomeKnowHistoryDao targetDao = daoSession.getHomeKnowHistoryDao();
            List<HomeKnowHistory> homeKnowHistorysNew = targetDao
                    ._queryHomeKnowData_HomeKnowHistorys(id);
            synchronized (this) {
                if (homeKnowHistorys == null) {
                    homeKnowHistorys = homeKnowHistorysNew;
                }
            }
        }
        return homeKnowHistorys;
    }

    public int getCommendcount() {
        return this.commendcount;
    }

    public void setCommendcount(int commendcount) {
        this.commendcount = commendcount;
    }

}
