package com.vargancys.learningassistant.model.mine.bean;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2020/5/26
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 天梯类型数据实体类
 */
public class ChallengeTypeDataBean {
    private List<ChallengeItemBean> itemBeans;

    public List<ChallengeItemBean> getItemBeans() {
        return itemBeans;
    }

    public void setItemBeans(List<ChallengeItemBean> itemBeans) {
        this.itemBeans = itemBeans;
    }
}
