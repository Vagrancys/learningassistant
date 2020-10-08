package com.vargancys.learningassistant.model.home.request;

import com.vagrancys.learningassistant.db.TemporaryAidedDbDao;
import com.vargancys.learningassistant.base.BaseApplication;
import com.vargancys.learningassistant.model.home.bean.AidedBean;
import com.vargancys.learningassistant.model.home.bean.AidedDataBean;
import com.vargancys.learningassistant.model.home.bean.ArticleBean;
import com.vargancys.learningassistant.db.knowledge.TemporaryAidedDb;
import com.vargancys.learningassistant.http.ApiClient;
import com.vargancys.learningassistant.http.BaseBean;
import com.vargancys.learningassistant.http.CommonHttpListener;
import com.vargancys.learningassistant.http.MySubscriber;
import com.vargancys.learningassistant.model.common.bean.NoDataBean;
import com.vargancys.learningassistant.presenter.BaseCallBackListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/9/16
 * version:1.0
 * 模块名: 辅助型知识请求体
 */
public class AidedRequest {
    private static AidedRequest instance;
    private TemporaryAidedDbDao AidedDao;
    private AidedRequest(){
        AidedDao = BaseApplication.getInstance().getDaoSession().getTemporaryAidedDbDao();
    }
    public static AidedRequest getInstance(){
        if(instance == null){
            instance = new AidedRequest();
        }
        return instance;
    }

    /**
     * 添加文章型知识
     * @param object 文章实体类
     * @param listener 监听器
     */
    public void addAided(AidedBean object, CommonHttpListener listener) {
        ApiClient.getInstance().addAided(initAided(object),new MySubscriber<BaseBean<NoDataBean>>() {
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

    private Map<String, Object> initAided(AidedBean object) {
        Map<String,Object> articleMap = new HashMap<>();
        articleMap.put(ArticleBean.ARTICLE_ID,object.getId());
        return articleMap;
    }

    /**
     * 删除特定的文章型知识
     * @param id 要删除的文章型id
     * @param noDataListener
     */
    public void deleteAided(int id, CommonHttpListener noDataListener) {
        ApiClient.getInstance().deleteAided(id, new MySubscriber<BaseBean<NoDataBean>>() {
            @Override
            protected void onSuccess(BaseBean<NoDataBean> baseBean) {
                noDataListener.onSuccess(baseBean.getCode(),baseBean.getData());
            }

            @Override
            public void onError(Throwable t) {
                super.onError(t);
                noDataListener.onError(t);
            }

            @Override
            protected void onFinish() {
                super.onFinish();
                noDataListener.onFinish();
            }
        });
    }

    /**
     * 删除多个文章型知识
     * @param deletes 要删除的文章型数组
     * @param noDataListener
     */
    public void deleteAllAided(int[] deletes, CommonHttpListener noDataListener) {
        ApiClient.getInstance().deleteAllAided(deletes, new MySubscriber<BaseBean<NoDataBean>>() {
            @Override
            protected void onSuccess(BaseBean<NoDataBean> baseBean) {
                noDataListener.onSuccess(baseBean.getCode(),baseBean.getData());
            }

            @Override
            public void onError(Throwable t) {
                super.onError(t);
                noDataListener.onError(t);
            }

            @Override
            protected void onFinish() {
                super.onFinish();
                noDataListener.onFinish();
            }
        });
    }

    /**
     * 查询文章型知识
     * @param id 要查询的文章型id
     * @param listener
     */
    public void queryAided(int id, CommonHttpListener listener) {
        ApiClient.getInstance().queryAided(id,new MySubscriber<BaseBean<AidedBean>>(){
            @Override
            protected void onSuccess(BaseBean<AidedBean> baseBean) {
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
     * 查询多个文章型知识
     * @param deletes 要查询的文章型数组
     * @param listener
     */
    public void queryAllAided(int[] deletes, CommonHttpListener listener) {
        ApiClient.getInstance().queryAllAided(deletes,new MySubscriber<BaseBean<List<AidedBean>>>(){
            @Override
            protected void onSuccess(BaseBean<List<AidedBean>> baseBean) {
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
     * 要更新的文章型知识
     * @param object 文章型数据实体类
     * @param listener
     */
    public void updateAided(AidedBean object, CommonHttpListener listener) {
        ApiClient.getInstance().updateAided(initAided(object),new MySubscriber<BaseBean<NoDataBean>>(){
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
     * 查询本地的临时文章数据
     * @param aided
     * @param mView
     */
    public TemporaryAidedDb nativeQuery(int aided, BaseCallBackListener mView) {
        return AidedDao.queryBuilder().where(TemporaryAidedDbDao.Properties.Db_id.eq(aided)).unique();
    }

    /**
     * 添加本地文章数据
     * @param mDB
     */
    public void nativeAdd(TemporaryAidedDb mDB) {
        AidedDao.insert(mDB);
    }

    /**
     * 删除本地文章数据
     * @param native_id
     */
    public void nativeDelete(long native_id) {
        AidedDao.deleteByKey(native_id);
    }

    /**
     * 查询文章的数据情况
     * @param aided_id
     * @param listener
     */
    public void queryAidedData(int aided_id, CommonHttpListener listener) {
        ApiClient.getInstance().queryAidedData(aided_id,new MySubscriber<BaseBean<AidedDataBean>>() {
            @Override
            protected void onSuccess(BaseBean<AidedDataBean> baseBean) {
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
