package com.vargancys.learningassistant.module.overview.view;

import com.vargancys.learningassistant.db.common.KnowListBean;
import com.vargancys.learningassistant.db.overview.OverViewListItem;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2020/7/2
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识体系添加视图层
 */
public interface OverViewInsertView {
    void getOverViewDataSuccess(List<OverViewListItem> mBean);
    void getOverViewDataFail(int error,String message);
    void insertOverViewDataSuccess();
    void insertOverViewDataFail(int error,String message);
}
