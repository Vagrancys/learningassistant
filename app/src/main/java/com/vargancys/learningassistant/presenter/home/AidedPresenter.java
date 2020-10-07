package com.vargancys.learningassistant.presenter.home;

import android.util.Log;

import com.vargancys.learningassistant.bean.home.AidedBean;
import com.vargancys.learningassistant.bean.home.ArticleBean;
import com.vargancys.learningassistant.bean.home.HomeKnowFunction;
import com.vargancys.learningassistant.http.CommonHttpListener;
import com.vargancys.learningassistant.model.common.bean.NoDataBean;
import com.vargancys.learningassistant.model.home.request.AidedRequest;
import com.vargancys.learningassistant.module.home.view.KnowInsertDefaultView;
import com.vargancys.learningassistant.module.home.view.KnowInsertFifthView;
import com.vargancys.learningassistant.module.home.view.KnowInsertFourthView;
import com.vargancys.learningassistant.module.home.view.InsertAidedView;
import com.vargancys.learningassistant.presenter.BaseCallBackListener;
import com.vargancys.learningassistant.presenter.IBasePresenter;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/09
 * version:1.0
 */
public class AidedPresenter implements IBasePresenter<AidedBean> {
    private BaseCallBackListener mView;
    public AidedPresenter(BaseCallBackListener view){
        mView = view;
    }

    /**
     * 判断是否为空
     */
    public void isDataEmpty() {
        InsertAidedView view = (InsertAidedView) mView;
        if(view.isDataEmpty()){
            view.isDataEmptyFail();
        }else{
            view.isDataEmptySuccess();
        }
    }

    @Override
    public void add(AidedBean object) {
        AidedRequest.getInstance().addAided(object,getIdListener());
    }

    @Override
    public void delete(int id) {
        ArticleRequest.getInstance().deleteArticle(id,getDeleteDataListener());
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
    public void update(AidedBean object) {
        ArticleRequest.getInstance().updateArticle(object,getUpdateListener());
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

    public CommonHttpListener getUpdateListener(){
        return new CommonHttpListener() {
            @Override
            public void onSuccess(int code, Object data) {
                NoDataBean mBean = (NoDataBean) data;
                BaseKnowLedgeUpdateView view = (BaseKnowLedgeUpdateView) mView;
                if(mBean.getCode()){
                    view.onUpdateSuccess();
                }else{
                    view.onUpdateFail();
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

    /**
     * 验证是否为空和是否相等
     */
    public void isUpdateEmpty() {
        BaseKnowLedgeUpdateView view = (BaseKnowLedgeUpdateView) mView;
        boolean result = view.isPass();
        if(result){
            view.isPassSuccess();
        }else{
            view.isPassFail();
        }
    }
}
