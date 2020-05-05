package com.vargancys.learningassistant.db.ladder;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author Vagrancy
 * @date 2020/5/5
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 天梯问题项
 */
@Entity
public class LadderTopicBean {
    @Id
    @Unique
    private Long id;

    @Generated(hash = 1434289044)
    public LadderTopicBean(Long id) {
        this.id = id;
    }

    @Generated(hash = 183396880)
    public LadderTopicBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
