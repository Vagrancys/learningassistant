package com.vargancys.learningassistant.module.overview.view;

import com.vargancys.learningassistant.db.overview.OverViewListItem;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/03
 * version:1.0
 */
public interface OverViewContentView {
    void showOverViewDataFinish(List<OverViewListItem> overViewListItemList);

    void showOverViewDataError(int error, String message);
}
