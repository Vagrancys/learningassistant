package com.vargancys.learningassistant.model.home.request;

import com.vagrancys.learningassistant.db.DaoSession;
import com.vagrancys.learningassistant.db.HomeKnowContentDao;
import com.vagrancys.learningassistant.db.HomeKnowDataDao;
import com.vagrancys.learningassistant.db.HomeKnowFunctionDao;
import com.vagrancys.learningassistant.db.HomeKnowHistoryDao;
import com.vagrancys.learningassistant.db.HomeKnowHistoryFunctionDao;
import com.vargancys.learningassistant.base.BaseApplication;
import com.vargancys.learningassistant.bean.home.HomeKnowContent;
import com.vargancys.learningassistant.bean.home.HomeKnowData;
import com.vargancys.learningassistant.bean.home.HomeKnowFunction;
import com.vargancys.learningassistant.bean.home.HomeKnowHistory;
import com.vargancys.learningassistant.bean.home.HomeKnowHistoryFunction;
import com.vargancys.learningassistant.utils.TimeUtils;

import java.util.List;

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
    private HomeKnowHistoryFunctionDao mHistoryFunctionDao;
    private HomeKnowFunctionDao mFunctionDao;
    private static String TAG = "KnowUpdateRequest";
    private KnowUpdateRequest(){
        mDaoSession = BaseApplication.getInstance().getDaoSession();
        mContentDao = mDaoSession.getHomeKnowContentDao();
        mHistoryDao = mDaoSession.getHomeKnowHistoryDao();
        mDataDao = mDaoSession.getHomeKnowDataDao();
        mHistoryFunctionDao = mDaoSession.getHomeKnowHistoryFunctionDao();
        mFunctionDao = mDaoSession.getHomeKnowFunctionDao();
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

    //返回要修改的数据
    public HomeKnowContent getKnowDefaultContent(long content_id) {
        return mContentDao.load(content_id);
    }

    //更新默认级的数据
    public boolean saveKnowUpdateDefault(HomeKnowHistory mOldHistory, HomeKnowContent mNewContent) {
        mOldHistory.setTime(TimeUtils.getTime());
        updateData(mOldHistory.getDataId(),mNewContent.getTitle(),mOldHistory.getTime());
        mHistoryDao.insert(mOldHistory);
        mContentDao.update(mNewContent);
        return true;
    }

    //更新入门级的数据
    public boolean saveKnowUpdateFirst(HomeKnowHistory mOldHistory, HomeKnowContent mNewContent) {
        mOldHistory.setTime(TimeUtils.getTime());
        updateData(mOldHistory.getDataId(),mNewContent.getTitle(),mOldHistory.getTime());
        mHistoryDao.save(mOldHistory);
        mContentDao.update(mNewContent);
        return true;
    }

    //公有更新数据
    private void updateData(long Id,String title,String time){
        HomeKnowData homeKnowData = mDataDao.load(Id);
        homeKnowData.setHistorycount(homeKnowData.getHistorycount()+1);
        homeKnowData.setHistorytime(time);
        homeKnowData.setTitle(title);
        homeKnowData.update();
    }

    //更新熟悉级的数据
    public boolean saveKnowUpdateSecond(HomeKnowHistory mOldHistory, List<HomeKnowHistoryFunction> mOldHistoryFunction, HomeKnowContent mNewContent, List<HomeKnowFunction> homeKnowFunctions) {
        mOldHistory.setTime(TimeUtils.getTime());
        updateData(mOldHistory.getDataId(),mNewContent.getTitle(),mOldHistory.getTime());
        long result = mHistoryDao.insert(mOldHistory);
        for (HomeKnowHistoryFunction mFunction :mOldHistoryFunction){
            mFunction.setFunctionId(result);
            mHistoryFunctionDao.save(mFunction);
        }
        for (HomeKnowFunction mFunctions :homeKnowFunctions){
            mFunctionDao.save(mFunctions);
        }
        mContentDao.update(mNewContent);
        return true;
    }

    //更新精通级的数据
    public boolean saveKnowUpdateFourth(HomeKnowHistory mOldHistory, List<HomeKnowHistoryFunction> mOldHistoryFunction, HomeKnowContent mNewContent, List<HomeKnowFunction> homeKnowFunctions) {
        mOldHistory.setTime(TimeUtils.getTime());
        updateData(mOldHistory.getDataId(),mNewContent.getTitle(),mOldHistory.getTime());
        long result = mHistoryDao.insert(mOldHistory);
        for (HomeKnowHistoryFunction mFunction :mOldHistoryFunction){
            mFunction.setFunctionId(result);
            mHistoryFunctionDao.save(mFunction);
        }
        for (HomeKnowFunction mFunctions :homeKnowFunctions){
            mFunctionDao.save(mFunctions);
        }
        mContentDao.update(mNewContent);
        return true;
    }

    //更新熟练级的数据
    public boolean saveKnowUpdateThird(HomeKnowHistory mOldHistory, HomeKnowContent mNewContent) {
        mOldHistory.setTime(TimeUtils.getTime());
        updateData(mOldHistory.getDataId(),mNewContent.getTitle(),mOldHistory.getTime());
        mHistoryDao.save(mOldHistory);
        mContentDao.update(mNewContent);
        return true;
    }

    //更新创造级的数据
    public boolean saveKnowUpdateFifth(HomeKnowHistory mOldHistory, HomeKnowContent mNewContent) {
        mOldHistory.setTime(TimeUtils.getTime());
        updateData(mOldHistory.getDataId(),mNewContent.getTitle(),mOldHistory.getTime());
        mHistoryDao.save(mOldHistory);
        mContentDao.update(mNewContent);
        return true;
    }
}
