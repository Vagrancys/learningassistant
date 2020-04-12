package com.vargancys.learningassistant.db.game;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.Unique;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.vagrancys.learningassistant.db.DaoSession;
import com.vagrancys.learningassistant.db.GameSubjectItemDao;
import com.vagrancys.learningassistant.db.GameSubjectContentDao;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/12
 * version:1.0
 */
@Entity
public class GameSubjectContent {
    @Id
    @Unique
    private Long id;
    //知识项的id
    private long knowId;

    //知识的名称
    private String title;

    //上次答题时间
    private String last_time;

    //答对多少问题
    private int answer;

    //答错多少题
    private int error;

    //多少题问题
    private int problem;
    @ToMany(referencedJoinProperty = "subjectId")
    private List<GameSubjectItem> mItems;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1485093708)
    private transient GameSubjectContentDao myDao;

    @Generated(hash = 966085868)
    public GameSubjectContent(Long id, long knowId, String title, String last_time, int answer,
            int error, int problem) {
        this.id = id;
        this.knowId = knowId;
        this.title = title;
        this.last_time = last_time;
        this.answer = answer;
        this.error = error;
        this.problem = problem;
    }

    @Generated(hash = 1787853912)
    public GameSubjectContent() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getKnowId() {
        return this.knowId;
    }

    public void setKnowId(long knowId) {
        this.knowId = knowId;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1202187216)
    public List<GameSubjectItem> getMItems() {
        if (mItems == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            GameSubjectItemDao targetDao = daoSession.getGameSubjectItemDao();
            List<GameSubjectItem> mItemsNew = targetDao
                    ._queryGameSubjectContent_MItems(id);
            synchronized (this) {
                if (mItems == null) {
                    mItems = mItemsNew;
                }
            }
        }
        return mItems;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 888850779)
    public synchronized void resetMItems() {
        mItems = null;
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
    @Generated(hash = 1779131556)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getGameSubjectContentDao() : null;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLast_time() {
        return this.last_time;
    }

    public void setLast_time(String last_time) {
        this.last_time = last_time;
    }

    public int getAnswer() {
        return this.answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int getError() {
        return this.error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public int getProblem() {
        return this.problem;
    }

    public void setProblem(int problem) {
        this.problem = problem;
    }
}
