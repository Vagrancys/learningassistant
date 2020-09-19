package com.vargancys.learningassistant.module.overview.view;

import com.vargancys.learningassistant.bean.overview.OverViewListContent;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2020/6/23
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识体系查询视图层
 */
public interface OverViewQueryView {
    boolean isDataEmpty(String query);
    void queryDataEmpty();
    void queryDataFinish(List<OverViewListContent> mBean);
    void queryDataError(int error,String message);
}
