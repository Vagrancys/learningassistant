package com.vargancys.learningassistant.model.home.request;

import com.vagrancys.learningassistant.db.DaoSession;
import com.vargancys.learningassistant.base.BaseApplication;
import com.vargancys.learningassistant.db.home.KnowLedgeBean;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/01
 * version:1.0
 */
public class HomeCommonRequest {
    private static String TAG = "KnowDataRequest";
    private static HomeCommonRequest mRequest;
    private DaoSession mDaoSession;

    public static HomeCommonRequest getRequest() {
        if(mRequest ==null){
            synchronized (KnowDataRequest.class){
                if(mRequest == null){
                    mRequest = new HomeCommonRequest();
                }
            }
        }
        return mRequest;
    }

    private HomeCommonRequest(){
        mDaoSession = BaseApplication.getInstance().getDaoSession();
    }
    public List<KnowLedgeBean> getSearchAllData(String title) {
        /*QueryBuilder<KnowLedgeBean> queryBuilder =mKnowItemDao.queryBuilder();
        queryBuilder.and(HomeKnowItemDao.Properties.Title.like(title),HomeKnowItemDao.Properties.Summary.like(title));
        return queryBuilder.list();*/
        return null;
    }
}
