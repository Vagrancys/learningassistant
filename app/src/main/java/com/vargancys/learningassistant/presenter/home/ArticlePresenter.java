package com.vargancys.learningassistant.presenter.home;

import com.vargancys.learningassistant.db.home.ArticleBean;
import com.vargancys.learningassistant.http.CommonHttpListener;
import com.vargancys.learningassistant.model.common.bean.NoDataBean;
import com.vargancys.learningassistant.model.home.request.ArticleRequest;
import com.vargancys.learningassistant.presenter.BaseCallBackListener;
import com.vargancys.learningassistant.presenter.BasePresenter;

import java.util.Arrays;
import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/9/16
 * version:1.0
 * 模块名: 文章型管理器 增加获取查询删除修改
 */
public class ArticlePresenter implements BasePresenter<ArticleBean> {
    private BaseCallBackListener mView;
    private ArticlePresenter(BaseCallBackListener view){
        this.mView = view;
    }

    @Override
    public void add(ArticleBean object) {
        ArticleRequest.getInstance().addArticle(object,getNoDataListener());
    }

    @Override
    public void delete(int id) {
        ArticleRequest.getInstance().deleteArticle(id,getNoDataListener());
    }

    @Override
    public void delete(int[] ids) {
        ArticleRequest.getInstance().deleteAllArticle(ids,getNoDataListener());
    }

    @Override
    public void query(int id) {
        ArticleRequest.getInstance().queryArticle(id,getNoDataListener());
    }

    @Override
    public void query(int[] ids) {
        ArticleRequest.getInstance().queryAllArticle(ids,getNoDataListener());
    }

    @Override
    public void update(ArticleBean object) {
        ArticleRequest.getInstance().updateArticle(object,getNoDataListener());
    }

    public CommonHttpListener getNoDataListener(){
       return new CommonHttpListener() {
            @Override
            public void onSuccess(int code, Object data) {
                NoDataBean mBean = (NoDataBean) data;
                if(mBean.getCode()){
                    mView.onSuccess();
                }else{
                    mView.onFail();
                }
            }

            @Override
            public void onError(Throwable t) {
                mView.onError(t.getMessage());
            }

            @Override
            public void onFinish() {
                mView.onFinish();
            }
        };
    }

    public CommonHttpListener getDataListener(){
        return new CommonHttpListener() {
            @Override
            public void onSuccess(int code, Object data) {
                ArticleBean mBean = (ArticleBean) data;
                if(mBean != null){
                    mView.onSuccess(mBean);
                }else{
                    mView.onFail();
                }
            }

            @Override
            public void onError(Throwable t) {
                mView.onError(t.getMessage());
            }

            @Override
            public void onFinish() {
                mView.onFinish();
            }
        };
    }

    public CommonHttpListener getArrayListener(){
        return new CommonHttpListener() {
            @Override
            public void onSuccess(int code, Object data) {
                List<ArticleBean> mBean = (List<ArticleBean>) data;
                if(mBean != null && mBean.size() > 0){
                    mView.onSuccess(mBean);
                }else{
                    mView.onFail();
                }
            }

            @Override
            public void onError(Throwable t) {
                mView.onError(t.getMessage());
            }

            @Override
            public void onFinish() {
                mView.onFinish();
            }
        };
    }
}
