package com.vargancys.learningassistant.module.overview.view;

import com.vargancys.learningassistant.db.common.KnowListBean;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2020/7/2
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识体系更新视图层
 */
public interface OverViewUpdateView {
    void getOverViewDataSuccess(List<KnowListBean> mBean);
    void getOverViewDataFail(int error,String message);
    void updateOverViewDataSuccess();
    void updateOverViewDataFail(int error,String message);
}
