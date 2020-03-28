package com.vargancys.learningassistant.model.home.request;

import com.vagrancys.learningassistant.db.DaoSession;
import com.vagrancys.learningassistant.db.HomeKnowContentDao;
import com.vagrancys.learningassistant.db.HomeKnowDataDao;
import com.vagrancys.learningassistant.db.HomeKnowHistoryDao;
import com.vargancys.learningassistant.base.BaseApplication;
import com.vargancys.learningassistant.db.home.HomeKnowContent;
import com.vargancys.learningassistant.db.home.HomeKnowData;
import com.vargancys.learningassistant.db.home.HomeKnowHistory;
import com.vargancys.learningassistant.utils.TimeUtils;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/28
 * version:1.0
 */
public class KnowUpdateRequest {
    private static KnowUpdateRequest mRequest;
    private HomeKnowContentDao mContentDao;
    private HomeKnowHistoryDao mHistoryDao;
    private DaoSession mDaoSession;
    private HomeKnowDataDao mDataDao;
    private KnowUpdateRequest(){
        mDaoSession = BaseApplication.getInstance().getDaoSession();
        mContentDao = mDaoSession.getHomeKnowContentDao();
        mHistoryDao = mDaoSession.getHomeKnowHistoryDao();
        mDataDao = mDaoSession.getHomeKnowDataDao();
    }

    public static KnowUpdateRequest getInstance(){
        if(mRequest == null){
            synchronized (KnowUpdateRequest.class){
                if(mRequest == null){
                    mRequest = new KnowUpdateRequest();
                }
            }
        }
        return mRequest;
    }

    //返回默认级要修改的数据
    public HomeKnowContent getKnowDefaultContent(long know_id) {
        return mContentDao.load(know_id);
    }

    public boolean saveKnowUpdateDefault(HomeKnowHistory mOldHistory, HomeKnowContent mNewContent) {
        mOldHistory.setTime(TimeUtils.getTime());
        mHistoryDao.save(mOldHistory);
        mContentDao.update(mNewContent);
        return true;
    }
}
