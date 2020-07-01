package com.vargancys.learningassistant.module.overview.view;

import com.vargancys.learningassistant.db.overview.OverViewListItem;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2020/6/30
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 个人知识体系中心视图层
 */
public interface OverViewCreateView {
    void getOverViewCreateDataSuccess(List<OverViewListItem> mItem);
    void getOverViewCreateDataFail(int error,String message);
    void insertCreateDataSuccess();
    void insertCreateDataFail(int error,String message);
}
