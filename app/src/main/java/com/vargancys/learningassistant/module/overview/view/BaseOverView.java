package com.vargancys.learningassistant.module.overview.view;

import com.vargancys.learningassistant.db.overview.OverViewListContent;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2020/4/3
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识体系基础视图层
 */
public interface BaseOverView {
    void getAllData(List<OverViewListContent> objects);
    void getAllDataError(int error,String message);
}
