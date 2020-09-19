package com.vargancys.learningassistant.presenter.home;

import com.vargancys.learningassistant.bean.home.HomeKnowHistory;
import com.vargancys.learningassistant.model.home.request.KnowHistoryRequest;
import com.vargancys.learningassistant.module.home.view.BaseHistoryView;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/28
 * version:1.0
 */
public class KnowHistoryPresenter {
    private BaseHistoryView mView;
    private KnowHistoryRequest mRequest;
    public KnowHistoryPresenter(BaseHistoryView view){
        mView = view;
        mRequest = KnowHistoryRequest.getInstance();
    }
    public void getAllHistoryData(long know_id) {
        List<HomeKnowHistory> histories = mRequest.getAllHistoryData(know_id);
        if(histories.size() > 0){
            mView.showHistoryDataFinish(histories);
        }else{
            mView.showHistoryDataError(404,"没有历史数据");
        }
    }
}
