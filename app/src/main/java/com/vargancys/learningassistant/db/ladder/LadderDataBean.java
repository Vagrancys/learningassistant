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

    //称号
    private String title;

    //升阶所需
    private int upgrade;

    //天梯难度
    private String difficulty;

    //最高阶层
    private int highest;

    //失败阶层
    private int fail;

    //登梯时间
    private String time;

    //总阶层
    private int total;

    //掌握数
    private int master;

    //登顶概率
    private String chance;

    @Generated(hash = 489515390)
    public LadderDataBean(Long id, String title, int upgrade, String difficulty,
            int highest, int fail, String time, int total, int master,
            String chance) {
        this.id = id;
        this.title = title;
        this.upgrade = upgrade;
        this.difficulty = difficulty;
        this.highest = highest;
        this.fail = fail;
        this.time = time;
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

    public int getUpgrade() {
        return this.upgrade;
    }

    public void setUpgrade(int upgrade) {
        this.upgrade = upgrade;
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

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
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
}
