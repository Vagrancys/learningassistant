package com.vargancys.learningassistant.model.home.request;

import android.media.AsyncPlayer;

import com.google.gson.Gson;
import com.vagrancys.learningassistant.db.TemporaryArticleDbDao;
import com.vargancys.learningassistant.bean.home.ArticleBean;
import com.vargancys.learningassistant.bean.home.ArticleDataBean;
import com.vargancys.learningassistant.bean.home.BookBean;
import com.vargancys.learningassistant.bean.home.BookDataBean;
import com.vargancys.learningassistant.db.TemporaryArticleDb;
import com.vargancys.learningassistant.http.ApiClient;
import com.vargancys.learningassistant.http.BaseBean;
import com.vargancys.learningassistant.http.CommonHttpListener;
import com.vargancys.learningassistant.http.MySubscriber;
import com.vargancys.learningassistant.model.common.bean.NoDataBean;
import com.vargancys.learningassistant.presenter.BaseCallBackListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.internal.ListenerClass;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/9/16
 * version:1.0
 * 模块名: 书籍型知识请求体
 */
public class BookRequest {
    private static BookRequest instance;
    private BookRequest(){

    }
    public static BookRequest getInstance(){
        if(instance == null){
            instance = new BookRequest();
        }
        return instance;
    }

    /**
     * 添加书籍型知识
     * @param object 文章实体类
     * @param listener 监听器
     */
    public void addBook(BookBean object, CommonHttpListener listener) {
        ApiClient.getInstance().addBook(initBook(object),new MySubscriber<BaseBean<NoDataBean>>() {
            @Override
            protected void onSuccess(BaseBean<NoDataBean> baseBean) {
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

    private RequestBody initBook(BookBean object) {
        return RequestBody.create(MediaType.parse("application/json"),new Gson().toJson(object));
    }

    /**
     * 删除特定的书籍型知识
     * @param id 要删除的书籍型id
     * @param listener
     */
    public void deleteBook(int[] id, CommonHttpListener listener) {
        ApiClient.getInstance().deleteBook(id,new MySubscriber<BaseBean<NoDataBean>>(){
            @Override
            protected void onSuccess(BaseBean<NoDataBean> baseBean) {
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
     * 删除多个书籍型知识
     * @param ids 要删除的书籍型数组
     * @param listener
     */
    public void deleteAllBook(int[] ids, CommonHttpListener listener) {
        ApiClient.getInstance().deleteAllBook(ids,new MySubscriber<BaseBean<NoDataBean>>(){
            @Override
            protected void onSuccess(BaseBean<NoDataBean> baseBean) {
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
     * 查询书籍型知识
     * @param id 要查询的书籍型id
     * @param listener
     */
    public void queryBook(int id, CommonHttpListener listener) {
        ApiClient.getInstance().queryBook(id,new MySubscriber<BaseBean<BookBean>>(){
            @Override
            protected void onSuccess(BaseBean<BookBean> baseBean) {
                listener.onSuccess(baseBean.getCode(),baseBean.getData());
            }

            @Override
            protected void onFinish() {
                super.onFinish();
                listener.onFinish();
            }

            @Override
            public void onError(Throwable t) {
                super.onError(t);
                listener.onError(t);
            }
        });
    }

    /**
     * 查询多个书籍型知识
     * @param ids 要查询的书籍型数组
     * @param listener
     */
    public void queryAllBook(int[] ids, CommonHttpListener listener) {
        ApiClient.getInstance().queryAllBook(ids,new MySubscriber<BaseBean<List<BookBean>>>(){
            @Override
            protected void onSuccess(BaseBean<List<BookBean>> baseBean) {
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
     * 要更新的书籍型知识
     * @param object 书籍型数据实体类
     * @param listener
     */
    public void updateBook(BookBean object, CommonHttpListener listener) {
        ApiClient.getInstance().updateBook(initBook(object),new MySubscriber<BaseBean<NoDataBean>>(){
            @Override
            protected void onSuccess(BaseBean<NoDataBean> baseBean) {
                listener.onSuccess(baseBean.getCode(),baseBean.getMsg());
            }

            @Override
            protected void onFinish() {
                super.onFinish();
                listener.onFinish();
            }

            @Override
            public void onError(Throwable t) {
                super.onError(t);
                listener.onError(t);
            }
        });
    }


    /**
     * 查询文章的数据情况
     * @param article_id
     * @param listener
     */
    public void queryBookData(int article_id, CommonHttpListener listener) {
        ApiClient.getInstance().queryBookData(article_id,new MySubscriber<BaseBean<BookDataBean>>() {
            @Override
            protected void onSuccess(BaseBean<BookDataBean> baseBean) {
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
