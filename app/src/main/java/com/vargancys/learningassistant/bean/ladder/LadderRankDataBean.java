package com.vargancys.learningassistant.bean.ladder;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author Vagrancy
 * @date 2020/5/14
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 天梯排行数据实体类
 */
@Entity
public class LadderRankDataBean {
    @Id
    @Unique
    private Long id;

    //分区类型id
    private int zone;

    //特有类型id
    private int unique;

    //作者
    private String author;

    //头像
    private String avatar;

    //等级
    private String level;
    //总楼层
    private int total;

    //挑战楼层
    private int floor;

    //挑战时间
    private String time;
    
    @Generated(hash = 851575646)
    public LadderRankDataBean(Long id, int zone, int unique, String author,
            String avatar, String level, int total, int floor, String time) {
        this.id = id;
        this.zone = zone;
        this.unique = unique;
        this.author = author;
        this.avatar = avatar;
        this.level = level;
        this.total = total;
        this.floor = floor;
        this.time = time;
    }

    @Generated(hash = 745157437)
    public LadderRankDataBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getZone() {
        return this.zone;
    }

    public void setZone(int zone) {
        this.zone = zone;
    }

    public int getUnique() {
        return this.unique;
    }

    public void setUnique(int unique) {
        this.unique = unique;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getFloor() {
        return this.floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
