package com.vargancys.learningassistant.base;

import com.vargancys.learningassistant.model.home.bean.HomeContentBean;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/05
 * version:1.0
 */
public interface BaseRequest {

    interface GetBeanCallback{
        void onFinish(List<?> bean);
        void onError(int error,String msg);
        void onFinish(Object object);
    }

    void getBean(GetBeanCallback callback);

    void getBean(int item,GetBeanCallback callback);
}
