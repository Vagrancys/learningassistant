package com.vargancys.learningassistant.presenter.home;

import com.vargancys.learningassistant.bean.home.HomeKnowContent;

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

    public void getDefaultShowData(long id){
        HomeKnowContent homeKnowContent = mRequest.getDefaultShowData(id);
        if(homeKnowContent !=null){
            //((ShowCommonView) mView).showContentFinish(homeKnowContent);
        }else{
            //((ShowCommonView) mView).showContentError(404,"没有该数据!");
        }
    }

    public void getRefreshDefaultShowData(long id) {
        HomeKnowContent homeKnowContent = mRequest.getRefreshDefaultShowData(id);
        if(homeKnowContent !=null){
            //((ShowCommonView) mView).showContentFinish(homeKnowContent);
        }else{
            //((ShowCommonView) mView).showContentError(404,"没有该数据!");
        }
    }
}
