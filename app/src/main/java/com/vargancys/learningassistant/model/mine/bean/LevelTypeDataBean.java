package com.vargancys.learningassistant.model.mine.bean;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2020/5/30
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 个人中心等级实体类
 */
public class LevelTypeDataBean {
    private List<LevelItemBean> itemBeans;

    public List<LevelItemBean> getItemBeans() {
        return itemBeans;
    }

    public void setItemBeans(List<LevelItemBean> itemBeans) {
        this.itemBeans = itemBeans;
    }
}
