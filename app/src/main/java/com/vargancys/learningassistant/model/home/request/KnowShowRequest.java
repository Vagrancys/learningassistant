package com.vargancys.learningassistant.model.home.request;

import android.util.Log;

import com.vagrancys.learningassistant.db.DaoSession;
import com.vagrancys.learningassistant.db.HomeKnowContentDao;
import com.vagrancys.learningassistant.db.HomeKnowHistoryDao;
import com.vagrancys.learningassistant.db.HomeKnowItemDao;
import com.vargancys.learningassistant.base.BaseApplication;
import com.vargancys.learningassistant.db.home.HomeKnowContent;
import com.vargancys.learningassistant.db.home.HomeKnowHistory;
import com.vargancys.learningassistant.db.home.HomeKnowItem;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/17
 * version:1.0
 */
public class KnowShowRequest {
    private static String TAG = "KnowShowRequest11";
    private HomeKnowItemDao mItemDao;
    private HomeKnowContentDao mContentDao;
    private DaoSession daoSession;
    private HomeKnowHistoryDao mHistoryDao;
    public KnowShowRequest(){
        daoSession = BaseApplication.getInstance().getDaoSession();
        mItemDao = daoSession.getHomeKnowItemDao();
        mContentDao = daoSession.getHomeKnowContentDao();
        mHistoryDao = daoSession.getHomeKnowHistoryDao();
    }

    public HomeKnowContent getDefaultShowData(long item_id){
        //HomeKnowContent homeKnowContent = mContentDao.queryBuilder().where(HomeKnowContentDao.Properties.ContentId.eq(id)).unique();
        return   mItemDao.load(item_id).getHomeKnowContent();
        //Log.e(TAG,"id ="+id+"content ="+homeKnowContent.getTitle());
        //Log.e(TAG,"Title ="+homeKnowItem.getHomeKnowContent().getTitle());
        //return homeKnowItem.getHomeKnowContent();
    }

    public HomeKnowHistory getDefaultHistoryShowData(long history_id) {
        return mHistoryDao.load(history_id);
    }

    public HomeKnowContent getRefreshDefaultShowData(long item_id) {
        return mItemDao.load(item_id).getHomeKnowContent();
    }
}
