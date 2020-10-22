package com.vargancys.learningassistant.model.home.request;

import com.google.gson.Gson;
import com.vagrancys.learningassistant.db.TemporaryClassDbDao;
import com.vagrancys.learningassistant.db.TemporaryFeelingDbDao;
import com.vargancys.learningassistant.base.BaseApplication;
import com.vargancys.learningassistant.db.knowledge.TemporaryClassDb;
import com.vargancys.learningassistant.db.knowledge.TemporaryFeelingDb;
import com.vargancys.learningassistant.http.ApiClient;
import com.vargancys.learningassistant.http.BaseBean;
import com.vargancys.learningassistant.http.CommonHttpListener;
import com.vargancys.learningassistant.http.MySubscriber;
import com.vargancys.learningassistant.model.common.bean.NoDataBean;
import com.vargancys.learningassistant.model.home.bean.ClassBean;
import com.vargancys.learningassistant.model.home.bean.ClassDataBean;
import com.vargancys.learningassistant.model.home.bean.FeelingBean;
import com.vargancys.learningassistant.model.home.bean.FeelingDataBean;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/9/16
 * version:1.0
 * 模块名: 感悟型知识请求体
 */
public class FeelingRequest {
    private static FeelingRequest instance;
    private TemporaryFeelingDbDao feelingDao;
    private FeelingRequest(){
        feelingDao = BaseApplication.getInstance().getDaoSession().getTemporaryFeelingDbDao();
    }
    public static FeelingRequest getInstance(){
        if(instance == null){
            instance = new FeelingRequest();
        }
        return instance;
    }

    /**
     * 添加感悟型知识
     * @param object 感悟实体类
     * @param listener 监听器
     */
    public void addFeeling(FeelingBean object, CommonHttpListener listener) {
        ApiClient.getInstance().addFeeling(initFeeling(object),new MySubscriber<BaseBean<NoDataBean>>() {
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

    private RequestBody initFeeling(FeelingBean object) {
        return RequestBody.create(MediaType.parse("application/json"),new Gson().toJson(object));
    }

    /**
     * 删除特定的感悟型知识
     * @param id 要删除的感悟型id
     * @param listener
     */
    public void deleteFeeling(int id, CommonHttpListener listener) {
        ApiClient.getInstance().deleteFeeling(id,new MySubscriber<BaseBean<NoDataBean>>(){
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
     * 删除多个感悟型知识
     * @param ids 要删除的感悟型数组
     * @param listener
     */
    public void deleteAllFeeling(int[] ids, CommonHttpListener listener) {
        ApiClient.getInstance().deleteAllFeeling(ids,new MySubscriber<BaseBean<NoDataBean>>(){
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
     * 查询感悟型知识
     * @param id 要查询的感悟型id
     * @param listener
     */
    public void queryFeeling(int id, CommonHttpListener listener) {
        ApiClient.getInstance().queryFeeling(id,new MySubscriber<BaseBean<FeelingBean>>(){
            @Override
            protected void onSuccess(BaseBean<FeelingBean> baseBean) {
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
     * 查询多个感悟型知识
     * @param ids 要查询的感悟型数组
     * @param listener
     */
    public void queryAllFeeling(int[] ids, CommonHttpListener listener) {
        ApiClient.getInstance().queryAllFeeling(ids,new MySubscriber<BaseBean<List<FeelingBean>>>(){
            @Override
            protected void onSuccess(BaseBean<List<FeelingBean>> baseBean) {
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
     * 要更新的感悟型知识
     * @param object 感悟型数据实体类
     * @param listener
     */
    public void updateFeeling(FeelingBean object, CommonHttpListener listener) {
        ApiClient.getInstance().updateFeeling(initFeeling(object),new MySubscriber<BaseBean<NoDataBean>>(){
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
     * 查询感悟的数据情况
     * @param article_id
     * @param listener
     */
    public void queryFeelingData(int article_id, CommonHttpListener listener) {
        ApiClient.getInstance().queryFeelingData(article_id,new MySubscriber<BaseBean<FeelingDataBean>>() {
            @Override
            protected void onSuccess(BaseBean<FeelingDataBean> baseBean) {
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
    public void nativeAdd(TemporaryFeelingDb mDB) {
        feelingDao.insert(mDB);
    }

    /**
     * 删除本地文章数据
     * @param native_id
     */
    public void nativeDelete(long native_id) {
        feelingDao.deleteByKey(native_id);
    }
}
