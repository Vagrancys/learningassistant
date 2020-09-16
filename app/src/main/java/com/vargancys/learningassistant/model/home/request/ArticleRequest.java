package com.vargancys.learningassistant.model.home.request;

import com.vargancys.learningassistant.db.home.ArticleBean;
import com.vargancys.learningassistant.db.home.KnowLedgeBean;
import com.vargancys.learningassistant.http.ApiClient;
import com.vargancys.learningassistant.http.BaseBean;
import com.vargancys.learningassistant.http.CommonHttpListener;
import com.vargancys.learningassistant.http.MySubscriber;
import com.vargancys.learningassistant.model.common.bean.NoDataBean;

import java.util.HashMap;
import java.util.List;
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
    private ArticleRequest(){

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
}
