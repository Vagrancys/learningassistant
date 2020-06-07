package com.vargancys.learningassistant.db.mine;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author Vagrancy
 * @date 2020/6/7
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 个人中心意见反馈实体类
 */
@Entity
public class MineFeedbackBean {
    @Id
    @Unique
    private Long id;

    private long time;

    private String common;

    @Generated(hash = 815220131)
    public MineFeedbackBean(Long id, long time, String common) {
        this.id = id;
        this.time = time;
        this.common = common;
    }

    @Generated(hash = 67532207)
    public MineFeedbackBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getCommon() {
        return this.common;
    }

    public void setCommon(String common) {
        this.common = common;
    }
}
