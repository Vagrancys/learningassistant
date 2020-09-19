package com.vargancys.learningassistant.bean.mine;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author Vagrancy
 * @date 2020/5/31
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 个人中心等级权力实体类
 */
@Entity
public class MineLevelPrivilegeBean {
    @Unique
    @Id
    private Long id;

    //等级权力标题
    private String title;

    //等级权力序号
    private String level;

    //升级条件
    private String condition;

    //等级权力时间
    private String time;

    //等级权力简介
    private String summary;

    @Generated(hash = 2116153505)
    public MineLevelPrivilegeBean(Long id, String title, String level,
            String condition, String time, String summary) {
        this.id = id;
        this.title = title;
        this.level = level;
        this.condition = condition;
        this.time = time;
        this.summary = summary;
    }

    @Generated(hash = 1405060670)
    public MineLevelPrivilegeBean() {
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

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCondition() {
        return this.condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
