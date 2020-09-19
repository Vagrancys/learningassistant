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
 * Description: 个人中心等级各项实体类
 */
@Entity
public class LevelPartBean {
    @Id
    @Unique
    private Long id;

    //用户id
    private long mineId;

    //类型
    private int type;

    //等级标题
    private String title;

    //等级的等级
    private String level;

    //等级当前经验
    private String experience;

    //等级当前下一级经验
    private String next_experience;

    //等级排行
    private String rank;

    //等级时间
    private String time;

    //获得的荣誉
    private String privilege;

    @Generated(hash = 407036883)
    public LevelPartBean(Long id, long mineId, int type, String title, String level,
            String experience, String next_experience, String rank, String time,
            String privilege) {
        this.id = id;
        this.mineId = mineId;
        this.type = type;
        this.title = title;
        this.level = level;
        this.experience = experience;
        this.next_experience = next_experience;
        this.rank = rank;
        this.time = time;
        this.privilege = privilege;
    }

    @Generated(hash = 437438270)
    public LevelPartBean() {
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

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getExperience() {
        return this.experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getNext_experience() {
        return this.next_experience;
    }

    public void setNext_experience(String next_experience) {
        this.next_experience = next_experience;
    }

    public String getRank() {
        return this.rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPrivilege() {
        return this.privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }
}
