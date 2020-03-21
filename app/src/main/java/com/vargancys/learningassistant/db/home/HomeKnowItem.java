package com.vargancys.learningassistant.db.home;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.vagrancys.learningassistant.db.DaoSession;
import com.vagrancys.learningassistant.db.HomeKnowDataDao;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;

import com.vagrancys.learningassistant.db.HomeKnowContentDao;
import com.vagrancys.learningassistant.db.HomeKnowItemDao;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/07
 * version:1.0
 * 首页知识item的表结构
 */
@Entity
public class HomeKnowItem{
    //知识项id
    @Id(autoincrement = true)
    @Unique
    private Long id;

    private long contentId;
    private long dataId;

    //官方知识
    private boolean official;

    //知识项的演示需要类
    private String activity;

    //知识项演示判断
    private boolean have;

    //知识项的标题
    private String title;

    //知识项的复杂与深度度
    private int level;

    //知识项的简要概述
    private String summary;

    //知识项的观看进度
    private int progress;

    //知识项当前消耗度
    private int count;

    //知识项最大消耗度
    private int max;
    //知识项的掌握程度
    private int masterLevel;

    //知识项的学习要求
    private String studyTitle;

    //知识项内容是否创建了
    private boolean createClass;

    @ToOne(joinProperty = "contentId")
    private HomeKnowContent homeKnowContent;
    @ToOne(joinProperty = "dataId")
    private HomeKnowData homeKnowData;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1329440142)
    private transient HomeKnowItemDao myDao;

    @Generated(hash = 246857869)
    public HomeKnowItem(Long id, long contentId, long dataId, boolean official, String activity,
            boolean have, String title, int level, String summary, int progress, int count, int max,
            int masterLevel, String studyTitle, boolean createClass) {
        this.id = id;
        this.contentId = contentId;
        this.dataId = dataId;
        this.official = official;
        this.activity = activity;
        this.have = have;
        this.title = title;
        this.level = level;
        this.summary = summary;
        this.progress = progress;
        this.count = count;
        this.max = max;
        this.masterLevel = masterLevel;
        this.studyTitle = studyTitle;
        this.createClass = createClass;
    }

    @Generated(hash = 2077989770)
    public HomeKnowItem() {
    }

    @Generated(hash = 1769914776)
    private transient Long homeKnowContent__resolvedKey;

    @Generated(hash = 967281064)
    private transient Long homeKnowData__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 754192494)
    public HomeKnowContent getHomeKnowContent() {
        long __key = this.contentId;
        if (homeKnowContent__resolvedKey == null || !homeKnowContent__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            HomeKnowContentDao targetDao = daoSession.getHomeKnowContentDao();
            HomeKnowContent homeKnowContentNew = targetDao.load(__key);
            synchronized (this) {
                homeKnowContent = homeKnowContentNew;
                homeKnowContent__resolvedKey = __key;
            }
        }
        return homeKnowContent;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 935763122)
    public void setHomeKnowContent(@NotNull HomeKnowContent homeKnowContent) {
        if (homeKnowContent == null) {
            throw new DaoException(
                    "To-one property 'contentId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.homeKnowContent = homeKnowContent;
            contentId = homeKnowContent.getId();
            homeKnowContent__resolvedKey = contentId;
        }
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 2039737166)
    public HomeKnowData getHomeKnowData() {
        long __key = this.dataId;
        if (homeKnowData__resolvedKey == null || !homeKnowData__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            HomeKnowDataDao targetDao = daoSession.getHomeKnowDataDao();
            HomeKnowData homeKnowDataNew = targetDao.load(__key);
            synchronized (this) {
                homeKnowData = homeKnowDataNew;
                homeKnowData__resolvedKey = __key;
            }
        }
        return homeKnowData;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1590170270)
    public void setHomeKnowData(@NotNull HomeKnowData homeKnowData) {
        if (homeKnowData == null) {
            throw new DaoException(
                    "To-one property 'dataId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.homeKnowData = homeKnowData;
            dataId = homeKnowData.getId();
            homeKnowData__resolvedKey = dataId;
        }
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
    @Generated(hash = 786071706)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getHomeKnowItemDao() : null;
    }

    public Long getId() {
        return this.id;
    }

    public boolean getOfficial() {
        return this.official;
    }

    public void setOfficial(boolean official) {
        this.official = official;
    }

    public String getActivity() {
        return this.activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public boolean getHave() {
        return this.have;
    }

    public void setHave(boolean have) {
        this.have = have;
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

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getProgress() {
        return this.progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getMax() {
        return this.max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMasterLevel() {
        return this.masterLevel;
    }

    public void setMasterLevel(int masterLevel) {
        this.masterLevel = masterLevel;
    }

    public String getStudyTitle() {
        return this.studyTitle;
    }

    public void setStudyTitle(String studyTitle) {
        this.studyTitle = studyTitle;
    }

    public boolean getCreateClass() {
        return this.createClass;
    }

    public void setCreateClass(boolean createClass) {
        this.createClass = createClass;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getContentId() {
        return this.contentId;
    }

    public void setContentId(long contentId) {
        this.contentId = contentId;
    }

    public long getDataId() {
        return this.dataId;
    }

    public void setDataId(long dataId) {
        this.dataId = dataId;
    }
}
