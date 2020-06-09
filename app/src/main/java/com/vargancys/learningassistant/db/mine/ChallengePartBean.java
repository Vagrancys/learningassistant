package com.vargancys.learningassistant.db.mine;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author Vagrancy
 * @date 2020/6/9
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 个人中心挑战各项实体类
 */
@Entity
public class ChallengePartBean {
    @Unique
    @Id
    private Long id;

    //用户id
    private long mineId;

    //挑战类型
    private int type;

    //挑战标题
    private String title;

    //挑战次数
    private int serial;

    //挑战简介
    private String summary;

    //挑战最高层
    private int highest;

    //挑战时间
    private long time;

    //挑战状态
    private boolean situation;

    @Generated(hash = 404526562)
    public ChallengePartBean(Long id, long mineId, int type, String title,
            int serial, String summary, int highest, long time, boolean situation) {
        this.id = id;
        this.mineId = mineId;
        this.type = type;
        this.title = title;
        this.serial = serial;
        this.summary = summary;
        this.highest = highest;
        this.time = time;
        this.situation = situation;
    }

    @Generated(hash = 1096325784)
    public ChallengePartBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getMineId() {
        return this.mineId;
    }

    public void setMineId(long mineId) {
        this.mineId = mineId;
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

    public int getSerial() {
        return this.serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getHighest() {
        return this.highest;
    }

    public void setHighest(int highest) {
        this.highest = highest;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean getSituation() {
        return this.situation;
    }

    public void setSituation(boolean situation) {
        this.situation = situation;
    }

   
}
