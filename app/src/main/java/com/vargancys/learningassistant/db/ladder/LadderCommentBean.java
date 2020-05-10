package com.vargancys.learningassistant.db.ladder;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

import java.util.List;
import org.greenrobot.greendao.DaoException;
import com.vagrancys.learningassistant.db.DaoSession;
import com.vagrancys.learningassistant.db.LadderCommentReplyBeanDao;
import com.vagrancys.learningassistant.db.LadderCommentBeanDao;

/**
 * @author Vagrancy
 * @date 2020/5/8
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 天梯交流评论中心数据
 */
@Entity
public class LadderCommentBean {
    @Id
    @Unique
    private Long id;
    //交流分区id
    private int current;

    //评论作者
    private long author;

    //评论作者名称
    private String author_title;

    //作者等级
    private String level;

    //评论作者头像
    private String avatar;

    //赞数
    private int praise;

    //踩数
    private int step;

    //评论时间
    private String time;

    //评论内容
    private String comment;

    //回复数
    private int reply_count;

    //评论数量
    private int floor;

    @ToMany(referencedJoinProperty = "commentId")
    private List<LadderCommentReplyBean> mBean;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 881217379)
    private transient LadderCommentBeanDao myDao;

    @Generated(hash = 48203243)
    public LadderCommentBean(Long id, int current, long author, String author_title,
            String level, String avatar, int praise, int step, String time,
            String comment, int reply_count, int floor) {
        this.id = id;
        this.current = current;
        this.author = author;
        this.author_title = author_title;
        this.level = level;
        this.avatar = avatar;
        this.praise = praise;
        this.step = step;
        this.time = time;
        this.comment = comment;
        this.reply_count = reply_count;
        this.floor = floor;
    }

    @Generated(hash = 1363383988)
    public LadderCommentBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCurrent() {
        return this.current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public long getAuthor() {
        return this.author;
    }

    public void setAuthor(long author) {
        this.author = author;
    }

    public String getAuthor_title() {
        return this.author_title;
    }

    public void setAuthor_title(String author_title) {
        this.author_title = author_title;
    }

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getPraise() {
        return this.praise;
    }

    public void setPraise(int praise) {
        this.praise = praise;
    }

    public int getStep() {
        return this.step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getReply_count() {
        return this.reply_count;
    }

    public void setReply_count(int reply_count) {
        this.reply_count = reply_count;
    }

    public int getFloor() {
        return this.floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1944519342)
    public List<LadderCommentReplyBean> getMBean() {
        if (mBean == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            LadderCommentReplyBeanDao targetDao = daoSession
                    .getLadderCommentReplyBeanDao();
            List<LadderCommentReplyBean> mBeanNew = targetDao
                    ._queryLadderCommentBean_MBean(id);
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
    @Generated(hash = 419526131)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getLadderCommentBeanDao() : null;
    }
}
