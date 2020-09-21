package com.vargancys.learningassistant.presenter.home;

import com.vargancys.learningassistant.bean.home.ArticleBean;
import com.vargancys.learningassistant.db.TemporaryArticleDb;
import com.vargancys.learningassistant.http.CommonHttpListener;
import com.vargancys.learningassistant.model.common.bean.NoDataBean;
import com.vargancys.learningassistant.model.home.request.ArticleRequest;
import com.vargancys.learningassistant.module.home.view.DataArticleView;
import com.vargancys.learningassistant.module.home.view.InsertArticleView;
import com.vargancys.learningassistant.presenter.BaseCallBackListener;
import com.vargancys.learningassistant.presenter.BasePresenter;

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
    public ArticlePresenter(BaseCallBackListener view){
        this.mView = view;
    }

    @Override
    public void add(ArticleBean object) {
        ArticleRequest.getInstance().addArticle(object,getIdListener());
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
        ArticleRequest.getInstance().queryArticle(id,getDataListener());
    }

    @Override
    public void query(int[] ids) {
        ArticleRequest.getInstance().queryAllArticle(ids,getArrayListener());
    }

    public void queryData(int id) {
        ArticleRequest.getInstance().queryArticleData(id,getDataListener());
    }

    /**
     * 编辑过但是没有完成会保存在本地数据库里
     * @param id
     */
    @Override
    public void nativeQuery(int id) {
        InsertArticleView view = ((InsertArticleView) mView);
        TemporaryArticleDb articleBean = ArticleRequest.getInstance().nativeQuery(id,mView);
        if(articleBean != null){
            view.nativeQueryFinish(articleBean);
        }
    }


    @Override
    public void update(ArticleBean object) {
        ArticleRequest.getInstance().updateArticle(object,getNoDataListener());
    }

    public void isEmpty(){
        InsertArticleView view = ((InsertArticleView) mView);
        boolean result = view.isEmpty();
        if(result){
            view.isEmptyFinish();
        }else{
            view.isEmptyError(200);
        }
    }
    public CommonHttpListener getDeleteDataListener(){
        return new CommonHttpListener() {
            @Override
            public void onSuccess(int code, Object data) {
                NoDataBean mBean = (NoDataBean) data;
                if(mBean.getCode()){
                    ((DataArticleView) mView).deleteArticleSuccess();
                }else{
                    ((DataArticleView) mView).deleteArticleFail();
                }
            }

            @Override
            public void onError(Throwable t) {
                ((DataArticleView) mView).deleteArticleError(t.getMessage());
            }

            @Override
            public void onFinish() {
                mView.onFinish();
            }
        };
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

    public CommonHttpListener getIdListener(){
        return new CommonHttpListener() {
            @Override
            public void onSuccess(int code, Object data) {
                NoDataBean mBean = (NoDataBean) data;
                if(mBean != null){
                    mView.onSuccess(mBean.getMessage());
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

    public void nativeDelete(long knowLedge_id) {
        ArticleRequest.getInstance().nativeDelete(knowLedge_id);
    }

    public void addArticle() {
        ((InsertArticleView) mView).addArticle();
    }


    public void nativeAdd(TemporaryArticleDb mDB) {
        ArticleRequest.getInstance().nativeAdd(mDB);
    }
}
