package com.vargancys.learningassistant.db.ladder;

import android.support.annotation.IdRes;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author Vagrancy
 * @date 2020/5/5
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 天梯个人数据中心
 */
@Entity
public class LadderDataBean {
    @Id
    @Unique
    private Long id;

    //天梯类型
    private int type;
    //挑战姓名
    private String name;

    //称号
    private String title;

    //称号等级
    private int title_level;

    //升阶所需
    private int upgrade;

    //升阶总数
    private int upgrade_total;

    //天梯难度
    private String difficulty;

    //最高阶层
    private int highest;

    //失败阶层
    private int fail;

    //登梯时间
    private long time;

    //总用时
    private long total_time;

    //总阶层
    private int total;

    //掌握数
    private int master;

    //登顶概率
    private String chance;

    @Generated(hash = 27198839)
    public LadderDataBean(Long id, int type, String name, String title,
            int title_level, int upgrade, int upgrade_total, String difficulty,
            int highest, int fail, long time, long total_time, int total,
            int master, String chance) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.title = title;
        this.title_level = title_level;
        this.upgrade = upgrade;
        this.upgrade_total = upgrade_total;
        this.difficulty = difficulty;
        this.highest = highest;
        this.fail = fail;
        this.time = time;
        this.total_time = total_time;
        this.total = total;
        this.master = master;
        this.chance = chance;
    }

    @Generated(hash = 2141663966)
    public LadderDataBean() {
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

    public int getTitle_level() {
        return this.title_level;
    }

    public void setTitle_level(int title_level) {
        this.title_level = title_level;
    }

    public int getUpgrade() {
        return this.upgrade;
    }

    public void setUpgrade(int upgrade) {
        this.upgrade = upgrade;
    }

    public int getUpgrade_total() {
        return this.upgrade_total;
    }

    public void setUpgrade_total(int upgrade_total) {
        this.upgrade_total = upgrade_total;
    }

    public String getDifficulty() {
        return this.difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getHighest() {
        return this.highest;
    }

    public void setHighest(int highest) {
        this.highest = highest;
    }

    public int getFail() {
        return this.fail;
    }

    public void setFail(int fail) {
        this.fail = fail;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTotal_time() {
        return this.total_time;
    }

    public void setTotal_time(long total_time) {
        this.total_time = total_time;
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getMaster() {
        return this.master;
    }

    public void setMaster(int master) {
        this.master = master;
    }

    public String getChance() {
        return this.chance;
    }

    public void setChance(String chance) {
        this.chance = chance;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
