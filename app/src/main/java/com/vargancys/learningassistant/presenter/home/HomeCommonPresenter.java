package com.vargancys.learningassistant.presenter.home;

import com.vargancys.learningassistant.db.home.HomeKnowItem;
import com.vargancys.learningassistant.model.home.request.HomeCommonRequest;
import com.vargancys.learningassistant.module.home.view.HomeCommonView;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/01
 * version:1.0
 */
public class HomeCommonPresenter {
    private HomeCommonRequest homeCommonRequest;
    private HomeCommonView mView;
    public HomeCommonPresenter(HomeCommonView view){
        mView = view;
        homeCommonRequest = HomeCommonRequest.getRequest();
    }

    public void getSearchAllData(String title) {
        List<HomeKnowItem> homeKnowItems = homeCommonRequest.getSearchAllData(title);
        if(homeKnowItems.size() >0){
            mView.showAllDataFinish(homeKnowItems);
        }else{
            mView.showAllDataError(404,"没有找到知识项!");
        }
    }
}
