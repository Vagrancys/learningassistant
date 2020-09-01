package com.vargancys.learningassistant.module.mine.view;

import com.vargancys.learningassistant.db.home.KnowLedgeBean;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2020/5/26
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 各语言知识展示视图层
 */
public interface KnowLedgeItemView {
    void loadKnowLedge(List<KnowLedgeBean> mItem);
    void loadKnowLedgeError(int error,String message);
}
