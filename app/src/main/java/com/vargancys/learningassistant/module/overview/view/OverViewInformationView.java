package com.vargancys.learningassistant.module.overview.view;

import com.vargancys.learningassistant.db.overview.OverViewListContent;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2020/4/6
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识体系信息视图层
 */
public interface OverViewInformationView extends BaseOverView{
    void getContentData(OverViewListContent mContent);

    void getContentDataError(int error,String message);
}
