package com.vargancys.learningassistant.module.overview.view;

import com.vargancys.learningassistant.db.overview.OverViewListItem;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2020/4/3
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识体系中心视图层
 */
public interface OverViewContentView {
    void showOverViewDataFinish(List<OverViewListItem> overViewListItemList);

    void showOverViewDataError(int error, String message);
}
