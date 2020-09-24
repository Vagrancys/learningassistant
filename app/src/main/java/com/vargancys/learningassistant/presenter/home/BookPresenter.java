package com.vargancys.learningassistant.presenter.home;

import com.vargancys.learningassistant.bean.home.ArticleBean;
import com.vargancys.learningassistant.bean.home.BookBean;
import com.vargancys.learningassistant.bean.home.BookContentBean;
import com.vargancys.learningassistant.http.CommonHttpListener;
import com.vargancys.learningassistant.model.common.bean.NoDataBean;
import com.vargancys.learningassistant.model.home.request.ArticleRequest;
import com.vargancys.learningassistant.presenter.BaseCallBackListener;
import com.vargancys.learningassistant.presenter.BasePresenter;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/9/24
 * version:1.0
 * 模块名: 书籍类型管理器
 */
public class BookPresenter<T> implements BasePresenter<BookBean> {
    private T mView;
    public BookPresenter(T view){
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

    }

    public void queryData(int id) {
        BookRequest.getInstance().queryBookData(id,getDataListener());
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
}
