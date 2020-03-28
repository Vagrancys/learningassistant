package com.vargancys.learningassistant.model.home.request;

import com.vagrancys.learningassistant.db.DaoSession;
import com.vagrancys.learningassistant.db.HomeKnowHistoryDao;
import com.vargancys.learningassistant.base.BaseApplication;
import com.vargancys.learningassistant.db.home.HomeKnowHistory;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/28
 * version:1.0
 */
public class KnowHistoryRequest {
    private static KnowHistoryRequest mRequest;
    private DaoSession mDaoSession;
    private HomeKnowHistoryDao mHistoryDao;
    private KnowHistoryRequest(){
        mDaoSession = BaseApplication.getInstance().getDaoSession();
        mHistoryDao = mDaoSession.getHomeKnowHistoryDao();
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

    public List<HomeKnowHistory> getAllHistoryData(long know_id) {
        return mHistoryDao._queryHomeKnowData_HomeKnowHistorys(know_id);
    }
}
