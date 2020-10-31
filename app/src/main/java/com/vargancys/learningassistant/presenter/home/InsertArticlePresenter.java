package com.vargancys.learningassistant.presenter.home;

import com.vargancys.learningassistant.base.BaseEntity;
import com.vargancys.learningassistant.base.BasePresenter;
import com.vargancys.learningassistant.db.knowledge.TemporaryArticleDb;
import com.vargancys.learningassistant.model.common.bean.NoDataBean;
import com.vargancys.learningassistant.model.home.bean.ArticleBean;
import com.vargancys.learningassistant.model.home.model.KnowledgeInsertArticleModel;
import com.vargancys.learningassistant.module.home.activity.insert.InsertArticleActivity;
import com.vargancys.learningassistant.module.home.contract.KnowledgeArticleContract;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/10/31
 * version:1.0
 * 模块名:
 */
public class InsertArticlePresenter extends BasePresenter<InsertArticleActivity, KnowledgeInsertArticleModel, KnowledgeArticleContract.Presenter> {
    @Override
    public KnowledgeInsertArticleModel getModel() {
        return new KnowledgeInsertArticleModel(this);
    }

    @Override
    public KnowledgeArticleContract.Presenter getContract() {
        return new KnowledgeArticleContract.Presenter<NoDataBean>() {
            @Override
            public void onSuccess(NoDataBean noDataBean) {
                getView().getContract().onSuccess(noDataBean);
            }

            @Override
            public void onFail() {
                getView().getContract().onFail();
            }

            @Override
            public void onError(String message) {
                getView().getContract().onError(message);
            }

            @Override
            public void onFinish() {
                getView().getContract().onFinish();
            }

            @Override
            public void isEmpty() {
                getView().getContract().isEmpty();
            }

            @Override
            public void addArticle(ArticleBean mArticle) throws Exception {
                m.getContract().addArticle(mArticle);
            }

            @Override
            public void nativeDelete(long nativeArticle_id) {

            }

            @Override
            public void nativeQuery(int knowLedge_id) {

            }

            @Override
            public void nativeAdd(TemporaryArticleDb mDB) {

            }
        };
    }
}
















