package com.vargancys.learningassistant.persenter.common.help;

import com.vargancys.learningassistant.base.BaseRequest;
import com.vargancys.learningassistant.model.common.request.HelpRequest;
import com.vargancys.learningassistant.module.common.view.HelpContentView;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/07
 * version:1.0
 */
public class HelpContentPresenter implements BaseRequest.GetBeanCallback {
    private HelpContentView helpContentView;
    private HelpRequest helpRequest;
    public HelpContentPresenter(HelpContentView view){
        this.helpContentView = view;
        helpRequest = new HelpRequest();
    }

    public void getAllBean(){
        helpRequest.getBean(this);
    }

    @Override
    public void onFinish(List<?> bean) {
        if(bean.size() > 0){
            helpContentView.hideEmpty();
            helpContentView.showContentBean(bean);
        }else{
            helpContentView.showEmpty();
        }
    }

    public void deleteHelpData(int id){
        int result = helpRequest.deleteHelpData(id);
        if(result != 0){
            helpContentView.deleteFinish(id);
        }else{
            helpContentView.deleteError();
        }
    }

    @Override
    public void onFinish(Object object) {

    }

    @Override
    public void onError(int error, String msg) {
        helpContentView.showError(error,msg);
    }
}
