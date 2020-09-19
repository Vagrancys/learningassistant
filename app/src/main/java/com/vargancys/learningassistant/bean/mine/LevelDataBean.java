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
 * Description: 个人中心等级实体类
 */
@Entity
public class LevelDataBean {
    @Unique
    @Id
    private Long id;

    //用户id
    private int mineId;

    //等级类型
    private int type;

    //等级标题
    private String title;

    //等级最新
    private String news;

    //下一级等级
    private String next;

    //当前等级经验
    private String experience;

    //下一级等级经验
    private String next_experience;

    //等级排行
    private String rank;

    @Generated(hash = 1009944434)
    public LevelDataBean(Long id, int mineId, int type, String title, String news,
            String next, String experience, String next_experience, String rank) {
        this.id = id;
        this.mineId = mineId;
        this.type = type;
        this.title = title;
        this.news = news;
        this.next = next;
        this.experience = experience;
        this.next_experience = next_experience;
        this.rank = rank;
    }

    @Generated(hash = 1865516181)
    public LevelDataBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMineId() {
        return this.mineId;
    }

    public void setMineId(int mineId) {
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

    public String getNews() {
        return this.news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public String getNext() {
        return this.next;
    }

    public void setNext(String next) {
        this.next = next;
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
}
