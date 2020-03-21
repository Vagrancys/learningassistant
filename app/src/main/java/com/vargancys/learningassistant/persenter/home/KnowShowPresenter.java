package com.vargancys.learningassistant.persenter.home;

import com.vagrancys.learningassistant.db.DaoSession;
import com.vargancys.learningassistant.db.home.HomeKnowContent;
import com.vargancys.learningassistant.model.home.request.KnowShowRequest;
import com.vargancys.learningassistant.module.home.view.KnowShowView;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/17
 * version:1.0
 */
public class KnowShowPresenter {
    private KnowShowRequest mRequest;
    private Object mView;
    public KnowShowPresenter(Object view){
        mView = view;
        mRequest = new KnowShowRequest();
    }

    public void getDefaultShowData(int id){
        HomeKnowContent homeKnowContent = mRequest.getDefaultShowData(id);
        if(homeKnowContent !=null){
            ((KnowShowView) mView).showContentFinish(homeKnowContent);
        }else{
            ((KnowShowView) mView).showContentError(404,"没有该数据!");
        }
    }

}
