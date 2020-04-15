package com.vargancys.learningassistant.db.overview;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.Unique;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.vagrancys.learningassistant.db.DaoSession;
import com.vagrancys.learningassistant.db.OverViewListItemDao;
import com.vagrancys.learningassistant.db.OverViewListContentDao;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/03
 * version:1.0
 * 知识结构化的总数据
 */
@Entity
public class OverViewListContent {
    @Id
    @Unique
    private Long id;

    //知识集的标题
    private String title;

    //知识集的作者id
    private long authorId;

    //知识集的作者
    private String author;

    //知识集的推荐
    private boolean recommend;

    //知识集的等级
    private int level;
    //知识集的个数
    private int number;

    //知识集的分数
    private int grade;

    //知识集的简单介绍
    private String summary;

    //知识集的时间
    private String time;

    //知识集的使用人数
    private int people;

    //知识集的层级
    private int layer;

    @ToMany(referencedJoinProperty = "contentId")
    private List<OverViewListItem> overViewListItem;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1178930275)
    private transient OverViewListContentDao myDao;

    @Generated(hash = 927019350)
    public OverViewListContent(Long id, String title, long authorId, String author,
            boolean recommend, int level, int number, int grade, String summary,
            String time, int people, int layer) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.author = author;
        this.recommend = recommend;
        this.level = level;
        this.number = number;
        this.grade = grade;
        this.summary = summary;
        this.time = time;
        this.people = people;
        this.layer = layer;
    }

    @Generated(hash = 1159729379)
    public OverViewListContent() {
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

    public long getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean getRecommend() {
        return this.recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getGrade() {
        return this.grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPeople() {
        return this.people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public int getLayer() {
        return this.layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1541269031)
    public List<OverViewListItem> getOverViewListItem() {
        if (overViewListItem == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            OverViewListItemDao targetDao = daoSession.getOverViewListItemDao();
            List<OverViewListItem> overViewListItemNew = targetDao
                    ._queryOverViewListContent_OverViewListItem(id);
            synchronized (this) {
                if (overViewListItem == null) {
                    overViewListItem = overViewListItemNew;
                }
            }
        }
        return overViewListItem;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1605987789)
    public synchronized void resetOverViewListItem() {
        overViewListItem = null;
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
    @Generated(hash = 1233201326)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getOverViewListContentDao() : null;
    }
}
