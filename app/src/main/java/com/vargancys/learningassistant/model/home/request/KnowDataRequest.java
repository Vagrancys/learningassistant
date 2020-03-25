package com.vargancys.learningassistant.model.home.request;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/25
 * version:1.0
 */
public class KnowDataRequest {
    private static KnowDataRequest mRequest;
    private KnowDataRequest(){

    }

    public static KnowDataRequest getRequest() {
        if(mRequest ==null){
            synchronized (KnowDataRequest.class){
                if(mRequest == null){
                    mRequest = new KnowDataRequest();
                }
            }
        }
        return mRequest;
    }
}
