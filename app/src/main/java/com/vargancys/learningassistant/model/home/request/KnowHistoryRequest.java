package com.vargancys.learningassistant.model.home.request;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/28
 * version:1.0
 */
public class KnowHistoryRequest {
    private static KnowHistoryRequest mRequest;
    private KnowHistoryRequest(){

    }

    public static KnowHistoryRequest getInstance(){
        if(mRequest == null){
            synchronized (KnowHistoryRequest.class){
                if(mRequest == null){
                    mRequest = new KnowHistoryRequest();
                }
            }
        }
        return mRequest;
    }
}
