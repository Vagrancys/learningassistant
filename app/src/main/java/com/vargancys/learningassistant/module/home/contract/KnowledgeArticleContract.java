package com.vargancys.learningassistant.module.home.contract;

import com.vargancys.learningassistant.base.BaseEntity;
import com.vargancys.learningassistant.db.knowledge.TemporaryArticleDb;
import com.vargancys.learningassistant.model.home.bean.ArticleBean;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/10/31
 * version:1.0
 * 模块名:将model层,view层,presenter层协商的共同业务,封装成接口
 */
public interface KnowledgeArticleContract {
    interface Model{
        void addArticle(ArticleBean articleBean) throws Exception;
    }

    interface View<T extends BaseEntity>{
        void onSuccess(T t);
        void onFail();
        void onError(String message);
        void onFinish();
    }

    interface Presenter<T extends BaseEntity>{
        void onSuccess(T t);
        void onFail();
        void onError(String message);
        void onFinish();
        void isEmpty();
        void addArticle(ArticleBean mArticle) throws Exception;
        void nativeDelete(long nativeArticle_id);
        void nativeQuery(int knowLedge_id);
        void nativeAdd(TemporaryArticleDb mDB);
    }
}













