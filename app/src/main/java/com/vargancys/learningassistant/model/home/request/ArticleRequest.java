package com.vargancys.learningassistant.model.home.request;

import com.vagrancys.learningassistant.db.TemporaryArticleDbDao;
import com.vargancys.learningassistant.base.BaseApplication;
import com.vargancys.learningassistant.bean.home.ArticleBean;
import com.vargancys.learningassistant.bean.home.ArticleDataBean;
import com.vargancys.learningassistant.db.TemporaryArticleDb;
import com.vargancys.learningassistant.http.ApiClient;
import com.vargancys.learningassistant.http.BaseBean;
import com.vargancys.learningassistant.http.CommonHttpListener;
import com.vargancys.learningassistant.http.MySubscriber;
import com.vargancys.learningassistant.model.common.bean.NoDataBean;
import com.vargancys.learningassistant.module.home.view.InsertArticleView;
import com.vargancys.learningassistant.presenter.BaseCallBackListener;

import java.util.HashMap;
import java.util.Map;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/9/16
 * version:1.0
 * 模块名: 文章型知识请求体
 */
public class ArticleRequest {
    private static ArticleRequest instance;
    private TemporaryArticleDbDao articleDao;
    private ArticleRequest(){
        articleDao = BaseApplication.getInstance().getDaoSession().getTemporaryArticleDbDao();
    }
    public static ArticleRequest getInstance(){
        if(instance == null){
            instance = new ArticleRequest();
        }
        return instance;
    }

    /**
     * 添加文章型知识
     * @param object 文章实体类
     * @param listener 监听器
     */
    public void addArticle(ArticleBean object, CommonHttpListener listener) {
        ApiClient.getInstance().addArticle(initArticle(object),new MySubscriber<BaseBean<NoDataBean>>() {
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

    private Map<String, Object> initArticle(ArticleBean object) {
        Map<String,Object> articleMap = new HashMap<>();
        articleMap.put(ArticleBean.ARTICLE_ID,object.getId());
        return articleMap;
    }

    /**
     * 删除特定的文章型知识
     * @param id 要删除的文章型id
     * @param noDataListener
     */
    public void deleteArticle(int id, CommonHttpListener noDataListener) {

    }

    /**
     * 删除多个文章型知识
     * @param ids 要删除的文章型数组
     * @param noDataListener
     */
    public void deleteAllArticle(int[] ids, CommonHttpListener noDataListener) {
    }

    /**
     * 查询文章型知识
     * @param id 要查询的文章型id
     * @param noDataListener
     */
    public void queryArticle(int id, CommonHttpListener noDataListener) {
    }

    /**
     * 查询多个文章型知识
     * @param ids 要查询的文章型数组
     * @param noDataListener
     */
    public void queryAllArticle(int[] ids, CommonHttpListener noDataListener) {
    }

    /**
     * 要更新的文章型知识
     * @param object 文章型数据实体类
     * @param noDataListener
     */
    public void updateArticle(ArticleBean object, CommonHttpListener noDataListener) {
    }

    /**
     * 查询本地的临时文章数据
     * @param id
     * @param mView
     */
    public TemporaryArticleDb nativeQuery(int article, BaseCallBackListener mView) {
        return articleDao.queryBuilder().where(TemporaryArticleDbDao.Properties.Article_id.eq(article)).unique();
    }

    /**
     * 添加本地文章数据
     * @param mDB
     */
    public void nativeAdd(TemporaryArticleDb mDB) {
        articleDao.insert(mDB);
    }

    /**
     * 删除本地文章数据
     * @param knowLedge_id
     */
    public void nativeDelete(long native_id) {
        articleDao.deleteByKey(native_id);
    }

    /**
     * 查询文章的数据情况
     * @param id
     * @param dataListener
     */
    public void queryArticleData(int article_id, CommonHttpListener listener) {
        ApiClient.getInstance().queryArticleData(article_id,new MySubscriber<BaseBean<ArticleDataBean>>() {
            @Override
            protected void onSuccess(BaseBean<ArticleDataBean> baseBean) {
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