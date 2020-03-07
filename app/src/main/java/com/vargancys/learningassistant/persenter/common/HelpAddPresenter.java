package com.vargancys.learningassistant.persenter.common;

import com.vargancys.learningassistant.model.common.request.HelpRequest;
import com.vargancys.learningassistant.module.common.view.HelpAddView;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/07
 * version:1.0
 */
public class HelpAddPresenter{
    private HelpAddView mView;
    private HelpRequest helpRequest;
    public HelpAddPresenter(HelpAddView view){
        this.mView = view;
        helpRequest = new HelpRequest();
    }

    public void saveHelpData(String title,String summary){
        helpRequest.saveHelpData(title,summary);
        mView.saveFinish();
    }
}
