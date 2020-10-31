package com.vargancys.learningassistant.module.home.view;

import com.vargancys.learningassistant.model.common.bean.NoDataBean;
import com.vargancys.learningassistant.module.home.contract.KnowledgeArticleContract;
import com.vargancys.learningassistant.presenter.BaseCallBackListener;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/09
 * version:1.0
 */
public interface InsertArticleView extends KnowledgeArticleContract.View<NoDataBean> {
    boolean isEmpty();
    void isEmptyFail(int error);
    void isEmptySuccess();
    void nativeQueryFinish(Object object);
}
