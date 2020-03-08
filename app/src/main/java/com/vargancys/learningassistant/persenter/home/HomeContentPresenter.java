package com.vargancys.learningassistant.persenter.home;

import com.vargancys.learningassistant.base.BaseRequest;
import com.vargancys.learningassistant.model.home.request.HomeRequest;
import com.vargancys.learningassistant.module.home.view.HomeContentView;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/05
 * version:1.0
 */
public class HomeContentPresenter implements BaseRequest.GetBeanCallback {
    private HomeRequest homeContentRequest;
    private HomeContentView mView;
    public HomeContentPresenter(HomeContentView view){
        mView = view;
        homeContentRequest = new HomeRequest();
    }

    //增加知识项的浏览记录
    public void updateCount(int position){
        homeContentRequest.updateCount(position);
    }

    public void getData(){
        homeContentRequest.getBean(this);
    }

    @Override
    public void onFinish(Object object) {

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

    public void deleteKnowData(int item_id) {
        boolean result = homeContentRequest.deleteKnowData(item_id);
        if(result){
            mView.deleteFinish(item_id);
        }else{
            mView.deleteError(501,"该知识项没有被删除!请重试!");
        }
    }
}
