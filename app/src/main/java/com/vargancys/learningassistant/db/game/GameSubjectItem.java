package com.vargancys.learningassistant.db.game;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.vagrancys.learningassistant.db.DaoSession;
import com.vagrancys.learningassistant.db.GameSubjectiveItemDao;
import org.greenrobot.greendao.annotation.NotNull;
import com.vagrancys.learningassistant.db.GameFillItemDao;
import com.vagrancys.learningassistant.db.GameMultipleItemDao;
import com.vagrancys.learningassistant.db.GameRadioItemDao;
import com.vagrancys.learningassistant.db.GameSubjectItemDao;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/12
 * version:1.0
 */
@Entity
public class GameSubjectItem {
    @Id
    @Unique
    private Long id;

    //父id
    private long subjectId;

    //问题的标题
    private String title;

    //选择的答题类型
    private int select;

    //添加的时间
    private String time;

    @ToOne(joinProperty = "subjectId")
    private GameRadioItem radioItem;

    @ToOne(joinProperty = "subjectId")
    private GameMultipleItem multipleItem;

    @ToOne(joinProperty = "subjectId")
    private GameFillItem fillItem;

    @ToOne(joinProperty = "subjectId")
    private GameSubjectiveItem subjectiveItem;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1826748989)
    private transient GameSubjectItemDao myDao;

    @Generated(hash = 1509208787)
    private transient Long radioItem__resolvedKey;

    @Generated(hash = 497059811)
    private transient Long multipleItem__resolvedKey;

    @Generated(hash = 231822228)
    private transient Long fillItem__resolvedKey;

    @Generated(hash = 604438555)
    private transient Long subjectiveItem__resolvedKey;

    @Generated(hash = 1332245646)
    public GameSubjectItem(Long id, long subjectId, String title, int select,
            String time) {
        this.id = id;
        this.subjectId = subjectId;
        this.title = title;
        this.select = select;
        this.time = time;
    }

    @Generated(hash = 1713026714)
    public GameSubjectItem() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getSubjectId() {
        return this.subjectId;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSelect() {
        return this.select;
    }

    public void setSelect(int select) {
        this.select = select;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1684566623)
    public GameRadioItem getRadioItem() {
        long __key = this.subjectId;
        if (radioItem__resolvedKey == null
                || !radioItem__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            GameRadioItemDao targetDao = daoSession.getGameRadioItemDao();
            GameRadioItem radioItemNew = targetDao.load(__key);
            synchronized (this) {
                radioItem = radioItemNew;
                radioItem__resolvedKey = __key;
            }
        }
        return radioItem;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1262973406)
    public void setRadioItem(@NotNull GameRadioItem radioItem) {
        if (radioItem == null) {
            throw new DaoException(
                    "To-one property 'subjectId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.radioItem = radioItem;
            subjectId = radioItem.getId();
            radioItem__resolvedKey = subjectId;
        }
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1087996899)
    public GameMultipleItem getMultipleItem() {
        long __key = this.subjectId;
        if (multipleItem__resolvedKey == null
                || !multipleItem__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            GameMultipleItemDao targetDao = daoSession.getGameMultipleItemDao();
            GameMultipleItem multipleItemNew = targetDao.load(__key);
            synchronized (this) {
                multipleItem = multipleItemNew;
                multipleItem__resolvedKey = __key;
            }
        }
        return multipleItem;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 249535328)
    public void setMultipleItem(@NotNull GameMultipleItem multipleItem) {
        if (multipleItem == null) {
            throw new DaoException(
                    "To-one property 'subjectId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.multipleItem = multipleItem;
            subjectId = multipleItem.getId();
            multipleItem__resolvedKey = subjectId;
        }
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 2067410595)
    public GameFillItem getFillItem() {
        long __key = this.subjectId;
        if (fillItem__resolvedKey == null || !fillItem__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            GameFillItemDao targetDao = daoSession.getGameFillItemDao();
            GameFillItem fillItemNew = targetDao.load(__key);
            synchronized (this) {
                fillItem = fillItemNew;
                fillItem__resolvedKey = __key;
            }
        }
        return fillItem;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 759547252)
    public void setFillItem(@NotNull GameFillItem fillItem) {
        if (fillItem == null) {
            throw new DaoException(
                    "To-one property 'subjectId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.fillItem = fillItem;
            subjectId = fillItem.getId();
            fillItem__resolvedKey = subjectId;
        }
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 2022034785)
    public GameSubjectiveItem getSubjectiveItem() {
        long __key = this.subjectId;
        if (subjectiveItem__resolvedKey == null
                || !subjectiveItem__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            GameSubjectiveItemDao targetDao = daoSession.getGameSubjectiveItemDao();
            GameSubjectiveItem subjectiveItemNew = targetDao.load(__key);
            synchronized (this) {
                subjectiveItem = subjectiveItemNew;
                subjectiveItem__resolvedKey = __key;
            }
        }
        return subjectiveItem;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 593459639)
    public void setSubjectiveItem(@NotNull GameSubjectiveItem subjectiveItem) {
        if (subjectiveItem == null) {
            throw new DaoException(
                    "To-one property 'subjectId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.subjectiveItem = subjectiveItem;
            subjectId = subjectiveItem.getId();
            subjectiveItem__resolvedKey = subjectId;
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
    @Generated(hash = 1108286315)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getGameSubjectItemDao() : null;
    }
}
