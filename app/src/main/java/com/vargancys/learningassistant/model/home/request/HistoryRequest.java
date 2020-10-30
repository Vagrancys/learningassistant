package com.vargancys.learningassistant.model.home.request;

import com.vagrancys.learningassistant.db.DaoSession;
import com.vagrancys.learningassistant.db.HomeKnowHistoryDao;
import com.vargancys.learningassistant.base.BaseApplication;
import com.vargancys.learningassistant.bean.home.HomeKnowHistory;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/28
 * version:1.0
 * 知识历史请求类
 */
public class HistoryRequest {
    private static HistoryRequest instance;
    private HistoryRequest(){
    }
    public static HistoryRequest getInstance(){
        if(instance == null){
            instance = new HistoryRequest();
        }
        return instance;
    }
}
