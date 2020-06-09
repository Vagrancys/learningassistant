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
 * Description: 个人中心挑战实体类
 */
@Entity
public class ChallengeDataBean {
    @Unique
    @Id
    private Long id;

    //用户id
    private long mineId;

    //挑战类型
    private int type;

    //挑战的标题
    private String title;

    //挑战次数
    private int count;

    //挑战成功次数
    private int success;

    //挑战失败次数
    private int fail;

    //挑战最高层
    private int highest;

    //最近挑战
    private int time;

    //挑战id
    private int number;

    //挑战难度
    private int difficulty;

    @Generated(hash = 1717653776)
    public ChallengeDataBean(Long id, long mineId, int type, String title,
            int count, int success, int fail, int highest, int time, int number,
            int difficulty) {
        this.id = id;
        this.mineId = mineId;
        this.type = type;
        this.title = title;
        this.count = count;
        this.success = success;
        this.fail = fail;
        this.highest = highest;
        this.time = time;
        this.number = number;
        this.difficulty = difficulty;
    }

    @Generated(hash = 2011436006)
    public ChallengeDataBean() {
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

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getSuccess() {
        return this.success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getFail() {
        return this.fail;
    }

    public void setFail(int fail) {
        this.fail = fail;
    }

    public int getHighest() {
        return this.highest;
    }

    public void setHighest(int highest) {
        this.highest = highest;
    }

    public int getTime() {
        return this.time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getDifficulty() {
        return this.difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
