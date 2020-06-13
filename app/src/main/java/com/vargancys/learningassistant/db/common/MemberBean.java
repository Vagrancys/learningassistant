package com.vargancys.learningassistant.db.common;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author Vagrancy
 * @date 2020/6/13
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 用户信息实体类
 */
@Entity
public class MemberBean {
    @Id
    @Unique
    private Long id;

    //用户名
    private String title;

    //密码
    private String password;

    @Generated(hash = 765827798)
    public MemberBean(Long id, String title, String password) {
        this.id = id;
        this.title = title;
        this.password = password;
    }

    @Generated(hash = 1592035565)
    public MemberBean() {
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

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
