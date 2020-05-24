package com.vargancys.learningassistant.model.mine.bean;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2020/5/22
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 个人中心知识类型数据总实体类
 */
public class KnowLedgeTypeDataBean {
    private List<KnowLedgeItemBean> itemBeans;

    public List<KnowLedgeItemBean> getItemBeans() {
        return itemBeans;
    }

    public void setItemBeans(List<KnowLedgeItemBean> itemBeans) {
        this.itemBeans = itemBeans;
    }
}
