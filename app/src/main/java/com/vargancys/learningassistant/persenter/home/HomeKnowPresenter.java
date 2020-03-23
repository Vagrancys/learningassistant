package com.vargancys.learningassistant.persenter.home;

import com.vagrancys.learningassistant.db.DaoSession;
import com.vargancys.learningassistant.db.home.HomeKnowItem;
import com.vargancys.learningassistant.model.home.request.HomeRequest;
import com.vargancys.learningassistant.module.home.view.HomeAddView;

import java.security.PublicKey;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/08
 * version:1.0
 */
public class HomeKnowPresenter {
    private HomeRequest homeRequest;
    private HomeAddView mView;
    public HomeKnowPresenter(HomeAddView view){
        mView = view;
        homeRequest = new HomeRequest();
    }

    public void queryKnowRepeat(String title){
        boolean result = homeRequest.queryKnowRepeat(title);
        if(result){
            mView.queryRepeatFinish();
        }else{
            mView.queryRepeatError();
        }
    }

    public void saveKnowData(HomeKnowItem homeKnowItem){
        boolean result = homeRequest.saveKnowData(homeKnowItem);
        if(result){
            mView.saveFinish();
        }else{
            mView.saveError(501,"保存知识失败!");
        }
    }
}
