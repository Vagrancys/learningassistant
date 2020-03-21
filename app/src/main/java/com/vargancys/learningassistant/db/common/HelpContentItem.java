package com.vargancys.learningassistant.db.common;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.ArrayList;
import java.util.List;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Unique;

import com.vagrancys.learningassistant.db.DaoSession;
import com.vagrancys.learningassistant.db.HelpCommendItemDao;
import com.vagrancys.learningassistant.db.HelpContentItemDao;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/07
 * version:1.0
 */
@Entity
public class HelpContentItem{
    @Id(autoincrement = true)
    @Unique
    private Long id;
    private long contentId;
    //帮助的数量
    private int number;
    //帮助标题
    private String title;
    //发表的时间
    private String time;
    //帮助主内容
    private String summary;
    //有帮助
    private int praise;
    //没有帮助
    private int poor;

    @ToMany(referencedJoinProperty = "commendId")
    private List<HelpCommendItem> commendItems;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1732872089)
    private transient HelpContentItemDao myDao;

    @Generated(hash = 332759328)
    public HelpContentItem(Long id, long contentId, int number, String title, String time,
            String summary, int praise, int poor) {
        this.id = id;
        this.contentId = contentId;
        this.number = number;
        this.title = title;
        this.time = time;
        this.summary = summary;
        this.praise = praise;
        this.poor = poor;
    }

    @Generated(hash = 552414700)
    public HelpContentItem() {
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 554580218)
    public List<HelpCommendItem> getCommendItems() {
        if (commendItems == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            HelpCommendItemDao targetDao = daoSession.getHelpCommendItemDao();
            List<HelpCommendItem> commendItemsNew = targetDao
                    ._queryHelpContentItem_CommendItems(id);
            synchronized (this) {
                if (commendItems == null) {
                    commendItems = commendItemsNew;
                }
            }
        }
        return commendItems;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1762582449)
    public synchronized void resetCommendItems() {
        commendItems = null;
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
    @Generated(hash = 1583742950)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getHelpContentItemDao() : null;
    }

    public Long getId() {
        return this.id;
    }

    public long getContentId() {
        return this.contentId;
    }

    public void setContentId(long contentId) {
        this.contentId = contentId;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getPraise() {
        return this.praise;
    }

    public void setPraise(int praise) {
        this.praise = praise;
    }

    public int getPoor() {
        return this.poor;
    }

    public void setPoor(int poor) {
        this.poor = poor;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
