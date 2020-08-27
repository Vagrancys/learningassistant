package com.vargancys.learningassistant.presenter.home;

import android.util.Log;

import com.vargancys.learningassistant.db.home.HomeKnowItem;
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
        mKnowLedge = new KnowledgeRequest();
    }

    //增加知识项的浏览记录
    public void updateCount(long position){
        homeContentRequest.updateCount(position);
    }

    public void getKnowledge(){
        CommonHttpListener listener = new CommonHttpListener() {
            @Override
            public void onSuccess(int code, Object data) {
                if(data !=null){
                    List<HomeKnowItem> mBean = (List<HomeKnowItem>) data;
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

            }

            @Override
            public void onFinish() {

            }
        };
        mKnowLedge.getKnowLedge(listener);
    }

    public void getData(){
        List<HomeKnowItem> mBean = homeContentRequest.getBean();
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
        List<HomeKnowItem> mBean = homeContentRequest.getSelectBean(language,level,show,master);
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
