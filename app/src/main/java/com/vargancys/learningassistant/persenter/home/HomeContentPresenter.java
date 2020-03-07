package com.vargancys.learningassistant.persenter.home;

import com.vargancys.learningassistant.base.BaseRequest;
import com.vargancys.learningassistant.model.home.request.HomeContentRequest;
import com.vargancys.learningassistant.module.home.view.HomeContentView;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/05
 * version:1.0
 */
public class HomeContentPresenter implements BaseRequest.GetBeanCallback {
    private HomeContentRequest homeContentRequest;
    private HomeContentView mView;
    public HomeContentPresenter(HomeContentView view){
        mView = view;
        homeContentRequest = new HomeContentRequest();
    }

    //增加知识项的浏览记录
    public void updateCount(int position){
        homeContentRequest.updateCount(position);
    }

    public void getData(){
        homeContentRequest.getBean(this);
    }

    @Override
    public void onFinish(List<?> bean) {
        if(bean.size() > 0){
            mView.hideEmpty();
            mView.showContentBean(bean);
        }else{
            mView.showEmpty();
        }

    }

    @Override
    public void onError(int error, String msg) {
        mView.showError(error,msg);
    }
}
