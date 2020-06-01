package com.vargancys.learningassistant.model.mine.bean;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2020/6/1
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 个人中心问题各项实体类
 */
public class ProblemTypeDataBean {
    private List<ProblemItemBean> itemBeans;

    public List<ProblemItemBean> getItemBeans() {
        return itemBeans;
    }

    public void setItemBeans(List<ProblemItemBean> itemBeans) {
        this.itemBeans = itemBeans;
    }
}
