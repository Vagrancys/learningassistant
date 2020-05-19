package com.vargancys.learningassistant.db.ladder;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author Vagrancy
 * @date 2020/5/19
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 排行配置实体类
 */
@Entity
public class LadderRankSettingBean {
    @Unique
    @Id
    private Long id;

    private int type;

    @Generated(hash = 1550115457)
    public LadderRankSettingBean(Long id, int type) {
        this.id = id;
        this.type = type;
    }

    @Generated(hash = 1315474350)
    public LadderRankSettingBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
