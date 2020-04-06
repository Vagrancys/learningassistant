package com.vargancys.learningassistant.module.overview.view;

import com.vargancys.learningassistant.db.overview.OverViewListContent;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/06
 * version:1.0
 */
public interface OverViewInformationView extends BaseOverView{
    void getContentData(OverViewListContent mContent);

    void getContentDataError(int error,String message);
}
