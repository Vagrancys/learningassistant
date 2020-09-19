package com.vargancys.learningassistant.bean.mine;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author Vagrancy
 * @date 2020/6/10
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 个人中心问题各项实体类
 */
@Entity
public class ProblemDataBean {
    @Unique
    @Id
    private Long id;

    private long mineId;

    private int type;

    //标题
    private String title;
    //等级
    private String level;
    //使用人数
    private String people;
    //使用次数
    private String use;
    //数量
    private String count;
    //发布时间
    private String time;
    @Generated(hash = 420374642)
    public ProblemDataBean(Long id, long mineId, int type, String title,
            String level, String people, String use, String count, String time) {
        this.id = id;
        this.mineId = mineId;
        this.type = type;
        this.title = title;
        this.level = level;
        this.people = people;
        this.use = use;
        this.count = count;
        this.time = time;
    }
    @Generated(hash = 299465490)
    public ProblemDataBean() {
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
    public String getPeople() {
        return this.people;
    }
    public void setPeople(String people) {
        this.people = people;
    }
    public String getUse() {
        return this.use;
    }
    public void setUse(String use) {
        this.use = use;
    }
    public String getCount() {
        return this.count;
    }
    public void setCount(String count) {
        this.count = count;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
}
