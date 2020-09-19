package com.vargancys.learningassistant.bean.mine;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author Vagrancy
 * @date 2020/6/9
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 个人中心头部数据实体类
 */
@Entity
public class KnowLedgeDataBean {
    @Id
    @Unique
    private Long id;

    //个人中心id
    private long mineId;

    //类型标题
    private String title;

    //知识数量
    private String count;

    //知识质量
    private int quality;

    //最后时间
    private int time;

    //使用人数
    private int people;

    //最高等级
    private int level;

    //官方奖励
    private int prize;

    //类型
    private int type;

    @Generated(hash = 617325080)
    public KnowLedgeDataBean(Long id, long mineId, String title, String count,
            int quality, int time, int people, int level, int prize, int type) {
        this.id = id;
        this.mineId = mineId;
        this.title = title;
        this.count = count;
        this.quality = quality;
        this.time = time;
        this.people = people;
        this.level = level;
        this.prize = prize;
        this.type = type;
    }

    @Generated(hash = 1999437857)
    public KnowLedgeDataBean() {
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

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCount() {
        return this.count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public int getQuality() {
        return this.quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public int getTime() {
        return this.time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getPeople() {
        return this.people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPrize() {
        return this.prize;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
