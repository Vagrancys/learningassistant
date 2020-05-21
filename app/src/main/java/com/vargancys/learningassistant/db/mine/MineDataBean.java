package com.vargancys.learningassistant.db.mine;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author Vagrancy
 * @date 2020/5/21
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 个人中心数据实体类
 */
@Entity
public class MineDataBean {
    @Unique
    @Id
    private Long id;

    //个人姓名
    private String name;

    //个人等级
    private int level;

    //个人等级升级总数
    private int level_total;
    //个人等级升级当前数
    private int level_current;

    //个人真实等级
    private int real_level;

    //个人知识总数
    private int knowledge;

    //个人影响力
    private int influence;

    //个人积累
    private int money;

    @Generated(hash = 748354572)
    public MineDataBean(Long id, String name, int level, int level_total,
            int level_current, int real_level, int knowledge, int influence,
            int money) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.level_total = level_total;
        this.level_current = level_current;
        this.real_level = real_level;
        this.knowledge = knowledge;
        this.influence = influence;
        this.money = money;
    }

    @Generated(hash = 193054536)
    public MineDataBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel_total() {
        return this.level_total;
    }

    public void setLevel_total(int level_total) {
        this.level_total = level_total;
    }

    public int getLevel_current() {
        return this.level_current;
    }

    public void setLevel_current(int level_current) {
        this.level_current = level_current;
    }

    public int getReal_level() {
        return this.real_level;
    }

    public void setReal_level(int real_level) {
        this.real_level = real_level;
    }

    public int getKnowledge() {
        return this.knowledge;
    }

    public void setKnowledge(int knowledge) {
        this.knowledge = knowledge;
    }

    public int getInfluence() {
        return this.influence;
    }

    public void setInfluence(int influence) {
        this.influence = influence;
    }

    public int getMoney() {
        return this.money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
