package com.vargancys.learningassistant.model.home.model;

import com.vargancys.learningassistant.base.BaseModel;
import com.vargancys.learningassistant.http.CommonHttpListener;
import com.vargancys.learningassistant.model.common.bean.NoDataBean;
import com.vargancys.learningassistant.model.home.bean.ArticleBean;
import com.vargancys.learningassistant.model.home.request.ArticleRequest;
import com.vargancys.learningassistant.module.home.contract.KnowledgeArticleContract;
import com.vargancys.learningassistant.presenter.home.ArticlePresenter;
import com.vargancys.learningassistant.presenter.home.InsertArticlePresenter;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/10/31
 * version:1.0
 * 模块名:
 */
public class KnowledgeInsertArticleModel extends BaseModel<InsertArticlePresenter, KnowledgeArticleContract.Model> {
    public KnowledgeInsertArticleModel(InsertArticlePresenter insertArticlePresenter) {
        super(insertArticlePresenter);
    }

    @Override
    public KnowledgeArticleContract.Model getContract() {
        return new KnowledgeArticleContract.Model() {
            @Override
            public void addArticle(ArticleBean articleBean){
                ArticleRequest.getInstance().addArticle(articleBean,getNoDataListener());
            }
        };
    }

    public CommonHttpListener getNoDataListener(){
        return new CommonHttpListener() {
            @Override
            public void onSuccess(int code, Object data) {
                NoDataBean mBean = (NoDataBean) data;
                if(mBean.getCode()==1){
                    p.getContract().onSuccess(mBean);
                }else{
                    p.getContract().onFail();
                }
            }

            @Override
            public void onError(Throwable t) {
                p.getContract().onError(t.getMessage());
            }

            @Override
            public void onFinish() {
                p.getContract().onFinish();
            }
        };
    }
}
