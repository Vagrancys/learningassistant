package com.vargancys.learningassistant.persenter.home;

import com.vargancys.learningassistant.model.home.request.KnowDataRequest;
import com.vargancys.learningassistant.module.home.view.KnowDataView;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/25
 * version:1.0
 */
public class KnowDataPresenter {
    private KnowDataView mView;
    private KnowDataRequest mRequest;
    public KnowDataPresenter(KnowDataView knowDataView){
        this.mView = knowDataView;
        mRequest = KnowDataRequest.getRequest();
    }

    public void getShowData() {

    }
}
