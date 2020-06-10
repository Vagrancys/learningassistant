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

    //使用人数
    private int people;

    //使用次数
    private int use;

    //数量
    private int count;

    //用户id
    private long mineId;

    //语言类型
    private int language;

    //父id
    private long subjectId;

    //单选的id
    private long radioId;

    //多选的id
    private long multipleId;
    //填空的id
    private long fillId;
    //主观的id
    private long subjectiveId;
    //问题的标题
    private String title;

    //选择的答题类型
    private int select;

    //添加的时间
    private String time;

    //问题的难度
    private int level;

    //是否答对  答错false  答对true
    private boolean isError;

    //是否已经答对 true 没有答过 false 答过
    private boolean isRepeat;

    @ToOne(joinProperty = "radioId")
    private GameRadioItem radioItem;

    @ToOne(joinProperty = "multipleId")
    private GameMultipleItem multipleItem;

    @ToOne(joinProperty = "fillId")
    private GameFillItem fillItem;

    @ToOne(joinProperty = "subjectiveId")
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

    @Generated(hash = 1286584924)
    public GameSubjectItem(Long id, int people, int use, int count, long mineId, int language, long subjectId,
            long radioId, long multipleId, long fillId, long subjectiveId, String title, int select, String time,
            int level, boolean isError, boolean isRepeat) {
        this.id = id;
        this.people = people;
        this.use = use;
        this.count = count;
        this.mineId = mineId;
        this.language = language;
        this.subjectId = subjectId;
        this.radioId = radioId;
        this.multipleId = multipleId;
        this.fillId = fillId;
        this.subjectiveId = subjectiveId;
        this.title = title;
        this.select = select;
        this.time = time;
        this.level = level;
        this.isError = isError;
        this.isRepeat = isRepeat;
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
    @Generated(hash = 1861874478)
    public GameRadioItem getRadioItem() {
        long __key = this.radioId;
        if (radioItem__resolvedKey == null || !radioItem__resolvedKey.equals(__key)) {
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
    @Generated(hash = 806157913)
    public void setRadioItem(@NotNull GameRadioItem radioItem) {
        if (radioItem == null) {
            throw new DaoException(
                    "To-one property 'radioId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.radioItem = radioItem;
            radioId = radioItem.getId();
            radioItem__resolvedKey = radioId;
        }
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1762537331)
    public GameMultipleItem getMultipleItem() {
        long __key = this.multipleId;
        if (multipleItem__resolvedKey == null || !multipleItem__resolvedKey.equals(__key)) {
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
    @Generated(hash = 1169367028)
    public void setMultipleItem(@NotNull GameMultipleItem multipleItem) {
        if (multipleItem == null) {
            throw new DaoException(
                    "To-one property 'multipleId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.multipleItem = multipleItem;
            multipleId = multipleItem.getId();
            multipleItem__resolvedKey = multipleId;
        }
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1455310777)
    public GameFillItem getFillItem() {
        long __key = this.fillId;
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
    @Generated(hash = 252998454)
    public void setFillItem(@NotNull GameFillItem fillItem) {
        if (fillItem == null) {
            throw new DaoException(
                    "To-one property 'fillId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.fillItem = fillItem;
            fillId = fillItem.getId();
            fillItem__resolvedKey = fillId;
        }
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1829881203)
    public GameSubjectiveItem getSubjectiveItem() {
        long __key = this.subjectiveId;
        if (subjectiveItem__resolvedKey == null || !subjectiveItem__resolvedKey.equals(__key)) {
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
    @Generated(hash = 411586755)
    public void setSubjectiveItem(@NotNull GameSubjectiveItem subjectiveItem) {
        if (subjectiveItem == null) {
            throw new DaoException(
                    "To-one property 'subjectiveId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.subjectiveItem = subjectiveItem;
            subjectiveId = subjectiveItem.getId();
            subjectiveItem__resolvedKey = subjectiveId;
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

    public long getRadioId() {
        return this.radioId;
    }

    public void setRadioId(long radioId) {
        this.radioId = radioId;
    }

    public long getMultipleId() {
        return this.multipleId;
    }

    public void setMultipleId(long multipleId) {
        this.multipleId = multipleId;
    }

    public long getFillId() {
        return this.fillId;
    }

    public void setFillId(long fillId) {
        this.fillId = fillId;
    }

    public long getSubjectiveId() {
        return this.subjectiveId;
    }

    public void setSubjectiveId(long subjectiveId) {
        this.subjectiveId = subjectiveId;
    }

    public boolean getIsError() {
        return this.isError;
    }

    public void setIsError(boolean isError) {
        this.isError = isError;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean getIsRepeat() {
        return this.isRepeat;
    }

    public void setIsRepeat(boolean isRepeat) {
        this.isRepeat = isRepeat;
    }

    public long getMineId() {
        return this.mineId;
    }

    public void setMineId(long mineId) {
        this.mineId = mineId;
    }

    public int getLanguage() {
        return this.language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

    public int getPeople() {
        return this.people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public int getUse() {
        return this.use;
    }

    public void setUse(int use) {
        this.use = use;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
