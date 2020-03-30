package com.vargancys.learningassistant.model.home.request;

import android.util.Log;

import com.vagrancys.learningassistant.db.DaoSession;
import com.vagrancys.learningassistant.db.HomeKnowCommendDao;
import com.vagrancys.learningassistant.db.HomeKnowDataDao;
import com.vagrancys.learningassistant.db.HomeKnowItemDao;
import com.vargancys.learningassistant.base.BaseApplication;
import com.vargancys.learningassistant.db.home.HomeKnowCommend;
import com.vargancys.learningassistant.db.home.HomeKnowData;
import com.vargancys.learningassistant.db.home.HomeKnowHistory;
import com.vargancys.learningassistant.utils.TimeUtils;

import java.util.List;


/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/25
 * version:1.0
 */
public class KnowDataRequest {
    private static String TAG = "KnowDataRequest";
    private static KnowDataRequest mRequest;
    private HomeKnowCommendDao mKnowCommendDao;
    private HomeKnowDataDao mKnowDataDao;
    private HomeKnowItemDao mKnowItemDao;
    private DaoSession mDaoSession;
    private KnowDataRequest(){
        mDaoSession = BaseApplication.getInstance().getDaoSession();
        mKnowDataDao = mDaoSession.getHomeKnowDataDao();
        mKnowItemDao = mDaoSession.getHomeKnowItemDao();
        mKnowCommendDao = mDaoSession.getHomeKnowCommendDao();
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

    public HomeKnowData getShowData(long know_id){
        return mKnowDataDao.load(know_id);
    }

    public HomeKnowCommend saveCommend(long know_id,String commend) {
        HomeKnowCommend homeKnowCommend = new HomeKnowCommend();
        homeKnowCommend.setCommendId(know_id);
        Log.e(TAG,"know_id"+know_id);
        homeKnowCommend.setTime(TimeUtils.getTime());
        homeKnowCommend.setTitle(commend);
        HomeKnowData homeKnowData = mKnowDataDao.load(know_id);
        homeKnowData.setCommendcount(homeKnowData.getCommendcount()+1);
        homeKnowData.update();
        mKnowCommendDao.insert(homeKnowCommend);
        return homeKnowCommend;
    }

    public int deleteDataItem(long know_id) {
        mKnowItemDao.deleteByKey(know_id);
        return 200;
    }

    //刷新请求最新的历史数据
    public List<HomeKnowHistory> getHistoryRefreshData(long know_id) {
        return mKnowDataDao.load(know_id).getHomeKnowHistorys();
    }
}