package com.vargancys.learningassistant.presenter.home;

import android.util.Log;

import com.vargancys.learningassistant.bean.home.KnowLedgeBean;
import com.vargancys.learningassistant.http.CommonHttpListener;
import com.vargancys.learningassistant.model.home.request.HomeRequest;
import com.vargancys.learningassistant.model.home.request.KnowledgeRequest;
import com.vargancys.learningassistant.module.home.view.HomeContentView;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/05
 * version:1.0
 */
public class HomeContentPresenter{
    private HomeRequest homeContentRequest;
    private KnowledgeRequest mKnowLedge;
    private HomeContentView mView;
    public HomeContentPresenter(HomeContentView view){
        mView = view;
        homeContentRequest = new HomeRequest();
        mKnowLedge = KnowledgeRequest.getInstance();
    }

    //增加知识项的浏览记录
    public void updateCount(long position){
        homeContentRequest.updateCount(position);
    }

    public void getKnowledge(int page,int limit){
        CommonHttpListener listener = new CommonHttpListener() {
            @Override
            public void onSuccess(int code, Object data) {
                if(data !=null){
                    List<KnowLedgeBean> mBean = (List<KnowLedgeBean>) data;
                    if(mBean.size() > 0){
                        Log.e("HomePresenter","hideEmpty");
                        mView.hideEmpty();
                        mView.showContentBean(mBean);
                    }else{
                        Log.e("HomePresenter","showEmpty");
                        mView.showEmpty();
                    }
                }else{
                    mView.showError(404,"数据库没有数据!");
                }
            }

            @Override
            public void onError(Throwable t) {
                mView.showError(404,t.getMessage());
            }

            @Override
            public void onFinish() {
                mView.hideEmpty();
            }
        };
        mKnowLedge.getKnowLedge(page,limit,listener);
    }

    public void getData(){
        List<KnowLedgeBean> mBean = homeContentRequest.getBean();
        if(mBean !=null){
            if(mBean.size() > 0){
                Log.e("HomePresenter","hideEmpty");
                mView.hideEmpty();
                mView.showContentBean(mBean);
            }else{
                Log.e("HomePresenter","showEmpty");
                mView.showEmpty();
            }
        }else{
            mView.showError(404,"数据库没有数据!");
        }
    }

    public void deleteKnowData(long item_id) {
        boolean result = homeContentRequest.deleteKnowData(item_id);
        if(result){
            mView.deleteFinish(item_id);
        }else{
            mView.deleteError(501,"该知识项没有被删除!请重试!");
        }
    }

    public void getSelectContentData(int language, int level, int show, int master) {
        List<KnowLedgeBean> mBean = homeContentRequest.getSelectBean(language,level,show,master);
        if(mBean !=null){
            if(mBean.size() > 0){
                Log.e("HomePresenter","hideEmpty");
                mView.hideEmpty();
                mView.showRefreshContentBean(mBean);
            }else{
                Log.e("HomePresenter","showEmpty");
                mView.showEmpty();
            }
        }else{
            mView.showError(404,"数据库没有数据!");
        }
    }
}
