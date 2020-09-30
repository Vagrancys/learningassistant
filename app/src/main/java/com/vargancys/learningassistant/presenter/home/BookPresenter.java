package com.vargancys.learningassistant.presenter.home;

import android.content.Intent;

import com.vargancys.learningassistant.bean.home.ArticleBean;
import com.vargancys.learningassistant.bean.home.BookBean;
import com.vargancys.learningassistant.http.CommonHttpListener;
import com.vargancys.learningassistant.model.common.bean.NoDataBean;
import com.vargancys.learningassistant.model.home.request.BookRequest;
import com.vargancys.learningassistant.module.home.view.BaseKnowLedgeUpdateView;
import com.vargancys.learningassistant.module.home.view.DataArticleView;
import com.vargancys.learningassistant.module.home.view.InsertBookView;
import com.vargancys.learningassistant.presenter.BaseCallBackListener;
import com.vargancys.learningassistant.presenter.IBasePresenter;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/9/24
 * version:1.0
 * 模块名: 书籍类型管理器
 */
public class BookPresenter implements IBasePresenter<BookBean> {
    private BaseCallBackListener mView;
    public BookPresenter(BaseCallBackListener view){
        this.mView = view;
    }

    @Override
    public void add(BookBean object) {
        BookRequest.getInstance().addBook(object,getIdListener());
    }

    @Override
    public void delete(int id) {
        BookRequest.getInstance().deleteBook(id,getDeleteDataListener());
    }

    @Override
    public void delete(int[] ids) {
        BookRequest.getInstance().deleteAllBook(ids,getNoDataListener());
    }

    @Override
    public void query(int id) {
        BookRequest.getInstance().queryBook(id,getDataListener());
    }

    @Override
    public void query(int[] ids) {
        BookRequest.getInstance().queryAllBook(ids,getArrayListener());
    }

    @Override
    public void nativeQuery(int id) {

    }

    @Override
    public void update(BookBean object) {
        BookRequest.getInstance().updateBook(object,getUpdateListener());
    }

    public void queryData(int id) {
        BookRequest.getInstance().queryBookData(id,getDataListener());
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

    private CommonHttpListener getIdListener(){
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

    private CommonHttpListener getArrayListener(){
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

    private CommonHttpListener getDataListener(){
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

    /**
     * 判断是否为空
     */
    public boolean isAddEmpty() {
        return ((InsertBookView) mView).isBookEmpty();
    }

    /**
     * 判断更新是否为空
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
