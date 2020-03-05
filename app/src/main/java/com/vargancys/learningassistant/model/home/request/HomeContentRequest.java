package com.vargancys.learningassistant.model.home.request;

import com.vargancys.learningassistant.base.BaseRequest;
import com.vargancys.learningassistant.base.BaseRequestNetWork;
import com.vargancys.learningassistant.model.home.bean.HomeContentBean;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/05
 * version:1.0
 */
public class HomeContentRequest implements BaseRequest {
    private BaseRequestNetWork baseRequestNetWork;
    @Override
    public void getBean(String task,GetBeanCallback callback) {
        baseRequestNetWork = new BaseRequestNetWork("ss",new Object());

        HomeContentBean homeContentBean = (HomeContentBean) baseRequestNetWork.getBean();
        if(homeContentBean.getError() == 200){
            callback.onFinish(homeContentBean.getContentBeans());
        }else{
            callback.onError(homeContentBean.getError(),homeContentBean.getMsg());
        }
    }

    @Override
    public void getBean(String item, String task, GetBeanCallback callback) {

    }
}
