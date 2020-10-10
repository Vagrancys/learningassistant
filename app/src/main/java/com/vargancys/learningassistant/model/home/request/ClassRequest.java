package com.vargancys.learningassistant.model.home.request;

import com.google.gson.Gson;
import com.vagrancys.learningassistant.db.TemporaryClassDbDao;
import com.vargancys.learningassistant.base.BaseApplication;
import com.vargancys.learningassistant.db.knowledge.TemporaryClassDb;
import com.vargancys.learningassistant.http.ApiClient;
import com.vargancys.learningassistant.http.BaseBean;
import com.vargancys.learningassistant.http.CommonHttpListener;
import com.vargancys.learningassistant.http.MySubscriber;
import com.vargancys.learningassistant.model.common.bean.NoDataBean;
import com.vargancys.learningassistant.model.home.bean.BookBean;
import com.vargancys.learningassistant.model.home.bean.BookDataBean;
import com.vargancys.learningassistant.model.home.bean.ClassBean;
import com.vargancys.learningassistant.model.home.bean.ClassDataBean;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/9/16
 * version:1.0
 * 模块名: 函数型知识请求体
 */
public class ClassRequest {
    private static ClassRequest instance;
    private TemporaryClassDbDao classDao;
    private ClassRequest(){
        classDao = BaseApplication.getInstance().getDaoSession().getTemporaryClassDbDao();
    }
    public static ClassRequest getInstance(){
        if(instance == null){
            instance = new ClassRequest();
        }
        return instance;
    }

    /**
     * 添加函数型知识
     * @param object 函数实体类
     * @param listener 监听器
     */
    public void addClass(ClassBean object, CommonHttpListener listener) {
        ApiClient.getInstance().addClass(initClass(object),new MySubscriber<BaseBean<NoDataBean>>() {
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

    private RequestBody initClass(ClassBean object) {
        return RequestBody.create(MediaType.parse("application/json"),new Gson().toJson(object));
    }

    /**
     * 删除特定的函数型知识
     * @param id 要删除的函数型id
     * @param listener
     */
    public void deleteClass(int id, CommonHttpListener listener) {
        ApiClient.getInstance().deleteClass(id,new MySubscriber<BaseBean<NoDataBean>>(){
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
     * 删除多个函数型知识
     * @param ids 要删除的函数型数组
     * @param listener
     */
    public void deleteAllClass(int[] ids, CommonHttpListener listener) {
        ApiClient.getInstance().deleteAllClass(ids,new MySubscriber<BaseBean<NoDataBean>>(){
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
     * 查询函数型知识
     * @param id 要查询的函数型id
     * @param listener
     */
    public void queryClass(int id, CommonHttpListener listener) {
        ApiClient.getInstance().queryClass(id,new MySubscriber<BaseBean<ClassBean>>(){
            @Override
            protected void onSuccess(BaseBean<ClassBean> baseBean) {
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
     * 查询多个函数型知识
     * @param ids 要查询的函数型数组
     * @param listener
     */
    public void queryAllClass(int[] ids, CommonHttpListener listener) {
        ApiClient.getInstance().queryAllClass(ids,new MySubscriber<BaseBean<List<ClassBean>>>(){
            @Override
            protected void onSuccess(BaseBean<List<ClassBean>> baseBean) {
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
     * 要更新的函数型知识
     * @param object 函数型数据实体类
     * @param listener
     */
    public void updateClass(ClassBean object, CommonHttpListener listener) {
        ApiClient.getInstance().updateClass(initClass(object),new MySubscriber<BaseBean<NoDataBean>>(){
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
     * 查询函数的数据情况
     * @param article_id
     * @param listener
     */
    public void queryClassData(int article_id, CommonHttpListener listener) {
        ApiClient.getInstance().queryClassData(article_id,new MySubscriber<BaseBean<ClassDataBean>>() {
            @Override
            protected void onSuccess(BaseBean<ClassDataBean> baseBean) {
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
     * 添加本地文章数据
     * @param mDB
     */
    public void nativeAdd(TemporaryClassDb mDB) {
        classDao.insert(mDB);
    }

    /**
     * 删除本地文章数据
     * @param native_id
     */
    public void nativeDelete(long native_id) {
        classDao.deleteByKey(native_id);
    }
}
