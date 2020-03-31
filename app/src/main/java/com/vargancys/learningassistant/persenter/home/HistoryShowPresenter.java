package com.vargancys.learningassistant.persenter.home;

import com.vargancys.learningassistant.db.home.HomeKnowContent;
import com.vargancys.learningassistant.db.home.HomeKnowHistory;
import com.vargancys.learningassistant.model.home.request.KnowShowRequest;
import com.vargancys.learningassistant.module.home.view.HistoryShowView;
import com.vargancys.learningassistant.module.home.view.KnowShowView;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/17
 * version:1.0
 */
public class HistoryShowPresenter {
    private KnowShowRequest mRequest;
    private HistoryShowView mView;
    public HistoryShowPresenter(HistoryShowView view){
        mView = view;
        mRequest = new KnowShowRequest();
    }

    public void getDefaultShowData(long history_id){
        HomeKnowHistory homeKnowHistory = mRequest.getDefaultHistoryShowData(history_id);
        if(homeKnowHistory !=null){
            mView.showContentFinish(homeKnowHistory);
        }else{
            mView.showContentError(404,"没有该数据!");
        }
    }
}
