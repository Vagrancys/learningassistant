package com.vargancys.learningassistant.model.home.request;

import com.vargancys.learningassistant.db.home.HomeKnowItem;
import com.vargancys.learningassistant.http.ApiClient;
import com.vargancys.learningassistant.http.BaseBean;
import com.vargancys.learningassistant.http.CommonHttpListener;
import com.vargancys.learningassistant.http.MySubscriber;

import java.util.List;

public class KnowledgeRequest {
    public void getKnowLedge(CommonHttpListener listener){
        ApiClient.getInstance().getKnowledge(new MySubscriber<BaseBean<HomeKnowItem>>() {
            @Override
            protected void onSuccess(BaseBean<HomeKnowItem> baseBean) {
                listener.onSuccess(baseBean.getCode(),baseBean.getData());
            }

            @Override
            public void onError(Throwable t) {
                super.onError(t);
                listener.onError(t);
            }

            @Override
            protected void onFinish() {
                super.onFinish();
                listener.onFinish();
            }
        });
    }
}
