package com.vargancys.learningassistant.presenter.home;

import com.vargancys.learningassistant.db.home.KnowLedgeBean;
import com.vargancys.learningassistant.http.CommonHttpListener;
import com.vargancys.learningassistant.model.common.bean.NoDataBean;
import com.vargancys.learningassistant.model.home.request.HomeRequest;
import com.vargancys.learningassistant.model.home.request.KnowledgeRequest;
import com.vargancys.learningassistant.module.home.view.HomeAddView;

import java.util.Map;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/08
 * version:1.0
 */
public class HomeKnowPresenter {
    private HomeRequest homeRequest;
    private KnowledgeRequest mKnowLedge;
    private HomeAddView mView;
    public HomeKnowPresenter(HomeAddView view){
        mView = view;
        homeRequest = new HomeRequest();
        mKnowLedge = KnowledgeRequest.getInstance();
    }

    public void queryKnowRepeat(String title){
        CommonHttpListener listener = new CommonHttpListener() {
            @Override
            public void onSuccess(int code, Object data) {
                NoDataBean mBean = (NoDataBean) data;
                if(mBean.getCode()){
                    mView.queryRepeatFinish();
                }else{
                    mView.queryRepeatError();
                }
            }

            @Override
            public void onError(Throwable t) {
                mView.queryRepeatError();
            }

            @Override
            public void onFinish() {

            }
        };
        mKnowLedge.queryKnowLedgeRepeat(title,listener);
    }

    public void saveKnowData(Map<String,Object> knowLedgeBean){
        CommonHttpListener listener = new CommonHttpListener() {
            @Override
            public void onSuccess(int code, Object data) {
                NoDataBean mBean = (NoDataBean) data;
                if(mBean.getCode()){
                    mView.saveFinish();
                }else{
                    mView.saveError(501,"保存知识失败!");
                }
            }

            @Override
            public void onError(Throwable t) {
                mView.saveError(501,"网络出现故障了!");
            }

            @Override
            public void onFinish() {

            }
        };
        mKnowLedge.saveKnowLedge(knowLedgeBean,listener);
    }
}
