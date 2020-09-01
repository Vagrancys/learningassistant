package com.vargancys.learningassistant.model.home.request;

import android.util.Log;

import com.vargancys.learningassistant.db.home.KnowLedgeBean;
import com.vargancys.learningassistant.http.ApiClient;
import com.vargancys.learningassistant.http.BaseBean;
import com.vargancys.learningassistant.http.CommonHttpListener;
import com.vargancys.learningassistant.http.MySubscriber;
import com.vargancys.learningassistant.model.common.bean.NoDataBean;

import java.util.List;
import java.util.Map;

public class KnowledgeRequest {
    private static KnowledgeRequest mKnowLedge;
    private KnowledgeRequest(){

    }
    public static KnowledgeRequest getInstance(){
        if(mKnowLedge == null){
            mKnowLedge = new KnowledgeRequest();
        }
        return mKnowLedge;
    }

    /**
     * 获取服务器知识数据
     * @param page 页数
     * @param limit 限制
     * @param listener 网络请求回调
     */
    public void getKnowLedge(int page,int limit,CommonHttpListener listener){
        ApiClient.getInstance().getKnowledge(page,limit,new MySubscriber<BaseBean<List<KnowLedgeBean>>>() {
            @Override
            protected void onSuccess(BaseBean<List<KnowLedgeBean>> baseBean) {
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

    /**
     * 判断知识数据是否重复
     * @param title 校验的标题
     * @param listener
     */
    public void queryKnowLedgeRepeat(String title,CommonHttpListener listener){
        ApiClient.getInstance().queryKnowLedgeRepeat(title, new MySubscriber<BaseBean<NoDataBean>>() {
            @Override
            protected void onSuccess(BaseBean<NoDataBean> baseBean) {
                listener.onSuccess(baseBean.getCode(),baseBean.getData());
            }

            @Override
            public void onError(Throwable t) {
                super.onError(t);
                listener.onError(t);
            }
        });
    }

    /**
     * 保存知识数据
     * @param knowLedgeBean map集合数据
     * @param listener
     */
    public void saveKnowLedge(Map<String,Object> knowLedgeBean, CommonHttpListener listener) {
        knowLedgeBean.put(KnowLedgeBean.MAX,1);
        knowLedgeBean.put(KnowLedgeBean.COUNT,0);
        knowLedgeBean.put(KnowLedgeBean.CREATECLASS,false);
        knowLedgeBean.put(KnowLedgeBean.PROGRESS,0);
        knowLedgeBean.put(KnowLedgeBean.MASTERLEVEL,0);
        ApiClient.getInstance().saveKnowLedge(knowLedgeBean, new MySubscriber<BaseBean<NoDataBean>>() {
            @Override
            protected void onSuccess(BaseBean<NoDataBean> baseBean) {
                NoDataBean mBean = baseBean.getData();
                listener.onSuccess(baseBean.getCode(),mBean);
            }

            @Override
            public void onError(Throwable t) {
                super.onError(t);
                listener.onError(t);
            }
        });
    }
}
