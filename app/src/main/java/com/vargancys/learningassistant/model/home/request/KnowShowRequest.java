package com.vargancys.learningassistant.model.home.request;

import com.vagrancys.learningassistant.db.DaoSession;
import com.vagrancys.learningassistant.db.HomeKnowContentDao;
import com.vagrancys.learningassistant.db.HomeKnowHistoryDao;
import com.vargancys.learningassistant.base.BaseApplication;
import com.vargancys.learningassistant.bean.home.HomeKnowContent;
import com.vargancys.learningassistant.bean.home.HomeKnowHistory;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/17
 * version:1.0
 */
public class KnowShowRequest {
    private static String TAG = "KnowShowRequest11";
    //private HomeKnowItemDao mItemDao;
    private HomeKnowContentDao mContentDao;
    private DaoSession daoSession;
    private HomeKnowHistoryDao mHistoryDao;
    public KnowShowRequest(){
        daoSession = BaseApplication.getInstance().getDaoSession();
        mContentDao = daoSession.getHomeKnowContentDao();
        mHistoryDao = daoSession.getHomeKnowHistoryDao();
    }

    public HomeKnowContent getDefaultShowData(long item_id){
        //HomeKnowContent homeKnowContent = mContentDao.queryBuilder().where(HomeKnowContentDao.Properties.ContentId.eq(id)).unique();
        //Log.e(TAG,"id ="+id+"content ="+homeKnowContent.getTitle());
        //Log.e(TAG,"Title ="+homeKnowItem.getHomeKnowContent().getTitle());
        //return homeKnowItem.getHomeKnowContent();
        return null;
    }

    public HomeKnowHistory getDefaultHistoryShowData(long history_id) {
        return mHistoryDao.load(history_id);
    }

    public HomeKnowContent getRefreshDefaultShowData(long item_id) {
        return null;
    }
}
