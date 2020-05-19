package com.vargancys.learningassistant.db.ladder;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author Vagrancy
 * @date 2020/5/19
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 天梯成就实体类
 */
@Entity
public class LadderResultBean {
    @Unique
    @Id
    private Long id;

    //挑战开始时间
    private long start_time;

    //挑战天数
    private int day;

    //挑战总层数
    private int total;

    //挑战失败次数
    private int fail_count;

    //挑战成功次数
    private int win_count;

    //挑战最长时间
    private long long_time;

    //挑战最短时间
    private long short_time;
    //挑战最高层
    private int highest_count;
    //挑战分数
    private int score;
    @Generated(hash = 1088012713)
    public LadderResultBean(Long id, long start_time, int day, int total,
            int fail_count, int win_count, long long_time, long short_time,
            int highest_count, int score) {
        this.id = id;
        this.start_time = start_time;
        this.day = day;
        this.total = total;
        this.fail_count = fail_count;
        this.win_count = win_count;
        this.long_time = long_time;
        this.short_time = short_time;
        this.highest_count = highest_count;
        this.score = score;
    }
    @Generated(hash = 1384833359)
    public LadderResultBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public long getStart_time() {
        return this.start_time;
    }
    public void setStart_time(long start_time) {
        this.start_time = start_time;
    }
    public int getDay() {
        return this.day;
    }
    public void setDay(int day) {
        this.day = day;
    }
    public int getTotal() {
        return this.total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public int getFail_count() {
        return this.fail_count;
    }
    public void setFail_count(int fail_count) {
        this.fail_count = fail_count;
    }
    public int getWin_count() {
        return this.win_count;
    }
    public void setWin_count(int win_count) {
        this.win_count = win_count;
    }
    public long getLong_time() {
        return this.long_time;
    }
    public void setLong_time(long long_time) {
        this.long_time = long_time;
    }
    public long getShort_time() {
        return this.short_time;
    }
    public void setShort_time(long short_time) {
        this.short_time = short_time;
    }
    public int getHighest_count() {
        return this.highest_count;
    }
    public void setHighest_count(int highest_count) {
        this.highest_count = highest_count;
    }
    public int getScore() {
        return this.score;
    }
    public void setScore(int score) {
        this.score = score;
    }
}
