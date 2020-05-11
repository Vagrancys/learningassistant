package com.vargancys.learningassistant.db.ladder;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.Unique;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.vagrancys.learningassistant.db.DaoSession;
import com.vagrancys.learningassistant.db.LadderDifficultyCommentBeanDao;
import com.vagrancys.learningassistant.db.LadderDifficultyDataBeanDao;

/**
 * @author Vagrancy
 * @date 2020/5/11
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 难度数据的实体类
 */
@Entity
public class LadderDifficultyDataBean{
    @Id
    @Unique
    private Long id;

    //难度区类型
    private int type;

    //难度区名称
    private String title;

    //难度区的内容
    private String content;

    //难度区的成功率
    private String successful;

    //难度区的使用人数
    private int people;

    //难度区的创建时间
    private String time;

    @ToMany(referencedJoinProperty = "dataId")
    private List<LadderDifficultyCommentBean> mBean;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 782343824)
    private transient LadderDifficultyDataBeanDao myDao;

    @Generated(hash = 28556759)
    public LadderDifficultyDataBean(Long id, int type, String title, String content,
            String successful, int people, String time) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.content = content;
        this.successful = successful;
        this.people = people;
        this.time = time;
    }

    @Generated(hash = 1190499585)
    public LadderDifficultyDataBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSuccessful() {
        return this.successful;
    }

    public void setSuccessful(String successful) {
        this.successful = successful;
    }

    public int getPeople() {
        return this.people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1000338068)
    public List<LadderDifficultyCommentBean> getMBean() {
        if (mBean == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            LadderDifficultyCommentBeanDao targetDao = daoSession
                    .getLadderDifficultyCommentBeanDao();
            List<LadderDifficultyCommentBean> mBeanNew = targetDao
                    ._queryLadderDifficultyDataBean_MBean(id);
            synchronized (this) {
                if (mBean == null) {
                    mBean = mBeanNew;
                }
            }
        }
        return mBean;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 971872784)
    public synchronized void resetMBean() {
        mBean = null;
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
    @Generated(hash = 379003794)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getLadderDifficultyDataBeanDao()
                : null;
    }
}
