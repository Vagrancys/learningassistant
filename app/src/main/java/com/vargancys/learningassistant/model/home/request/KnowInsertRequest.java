package com.vargancys.learningassistant.model.home.request;

import android.util.Log;

import com.vagrancys.learningassistant.db.DaoSession;
import com.vagrancys.learningassistant.db.HomeKnowContentDao;
import com.vagrancys.learningassistant.db.HomeKnowDataDao;
import com.vagrancys.learningassistant.db.HomeKnowFunctionDao;
import com.vagrancys.learningassistant.db.HomeKnowItemDao;
import com.vargancys.learningassistant.base.BaseApplication;
import com.vargancys.learningassistant.db.home.HomeKnowContent;
import com.vargancys.learningassistant.db.home.HomeKnowData;
import com.vargancys.learningassistant.db.home.HomeKnowFunction;
import com.vargancys.learningassistant.db.home.KnowLedgeBean;
import com.vargancys.learningassistant.utils.TimeUtils;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/09
 * version:1.0
 */
public class KnowInsertRequest {
    private static String TAG = "KnowInsertRequest";
    private HomeKnowContentDao mContentDao;
    private HomeKnowItemDao mItemDao;
    private HomeKnowFunctionDao mFunctionDao;
    private DaoSession daoSession;
    private HomeKnowDataDao mDataDao;
    public KnowInsertRequest(){
        daoSession = BaseApplication.getInstance().getDaoSession();
        mContentDao = daoSession.getHomeKnowContentDao();
        mItemDao = daoSession.getHomeKnowItemDao();
        mFunctionDao = daoSession.getHomeKnowFunctionDao();
        mDataDao = daoSession.getHomeKnowDataDao();
    }

    //判断默认级知识是否存在
    public boolean isEqualsDefaultItem(String title){
        int number = mContentDao.queryRaw("title",title).size();
        if(number == 0){
            return false;
        }
        return true;
    }

    private long addHomeKnowData(String title,int level){

        HomeKnowData homeKnowData = new HomeKnowData();
        homeKnowData.setTitle(title);
        homeKnowData.setTime(TimeUtils.getTime());
        homeKnowData.setCommendcount(0);
        homeKnowData.setCount(0);
        homeKnowData.setHistorycount(0);
        homeKnowData.setHistorytime(TimeUtils.getTime());
        homeKnowData.setLevel(level);
        homeKnowData.setMaster("入门");
        return mDataDao.insert(homeKnowData);
    }

    //保存默认级知识
    public boolean saveKnowDefaultItem(long know_id,String title,String summary,String show,
                                       String explain,String heed,String experience){
        HomeKnowContent homeKnowContent = new HomeKnowContent();
        homeKnowContent.setTitle(title);
        homeKnowContent.setSummary(summary);
        homeKnowContent.setShow(show);
        homeKnowContent.setExplain(explain);
        homeKnowContent.setHeed(heed);
        homeKnowContent.setExperience(experience);
        Log.e(TAG,"id = "+know_id);
        long dataId = addHomeKnowData(title,0);
        KnowLedgeBean homeKnowItem = mItemDao.load(know_id);
        homeKnowItem.setDataId(dataId);
        homeKnowItem.update();
        long result = mContentDao.insert(homeKnowContent);
        if(result == 0){
            return false;
        }
        return true;
    }

    //判断入门级知识是否存在
    public boolean isEqualsFirstItem(String title){
        return isEqualsTitle(title);
    }

    //保存入门级知识
    public boolean saveKnowFirstItem(long know_id,String title,String summary,String show,
                                       String explain,String heed,String experience){
        HomeKnowContent homeKnowContent = new HomeKnowContent();
        homeKnowContent.setTitle(title);
        homeKnowContent.setSummary(summary);
        homeKnowContent.setShow(show);
        homeKnowContent.setExplain(explain);
        homeKnowContent.setHeed(heed);
        homeKnowContent.setExperience(experience);
        long result = mContentDao.insert(homeKnowContent);
        long dataId = addHomeKnowData(title,1);
        KnowLedgeBean homeKnowItem = mItemDao.load(know_id);
        homeKnowItem.setContentId(result);
        homeKnowItem.setCreateClass(true);
        homeKnowItem.setDataId(dataId);
        mItemDao.update(homeKnowItem);
        //Log.e("save","is=" +is+"know=" +know_id);
        return insertHomeKnowContent(result);
    }

    //保存熟悉级知识
    public boolean saveKnowSecondItem(long know_id, String title, String summary,
                                      List<HomeKnowFunction> homeKnowFunctions, String heed,
                                      String experience) {

        HomeKnowContent homeKnowContent = new HomeKnowContent();
        homeKnowContent.setTitle(title);
        homeKnowContent.setSummary(summary);
        homeKnowContent.setHeed(heed);
        homeKnowContent.setExperience(experience);
        long result = mContentDao.insert(homeKnowContent);

        for (HomeKnowFunction function:homeKnowFunctions){
            function.setFunctionId((int)result);
            mFunctionDao.insert(function);
        }
        long dataId = addHomeKnowData(title,2);
        KnowLedgeBean homeKnowItem = mItemDao.load(know_id);
        homeKnowItem.setCreateClass(true);
        homeKnowItem.setContentId(result);
        homeKnowItem.setDataId(dataId);
        mItemDao.save(homeKnowItem);
        //Log.e("save","is=" +is);
        if(result == 0){
            return false;
        }
        return true;
    }

    //判断熟悉级知识是否存在
    public boolean isEqualsSecondItem(String title) {
        return isEqualsTitle(title);
    }

    //保存熟练级知识
    public boolean saveKnowThirdItem(long know_id, String title, String summary,
                                      String show,String explain, String heed,
                                      String experience) {
        HomeKnowContent homeKnowContent = new HomeKnowContent();
        homeKnowContent.setTitle(title);
        homeKnowContent.setSummary(summary);
        homeKnowContent.setHeed(heed);
        homeKnowContent.setExplain(explain);
        homeKnowContent.setShow(show);
        homeKnowContent.setExperience(experience);
        long result = mContentDao.insert(homeKnowContent);
        KnowLedgeBean homeKnowItem = mItemDao.load(know_id);
        homeKnowItem.setCreateClass(true);
        homeKnowItem.setContentId(result);
        long dataId = addHomeKnowData(title,3);
        homeKnowItem.setDataId(dataId);
        mItemDao.save(homeKnowItem);
        //Log.e("save","is=" +is);
        return insertHomeKnowContent(result);
    }

    //判断熟悉级知识是否存在
    public boolean isEqualsThirdItem(String title) {
        return isEqualsTitle(title);
    }

    //保存精通级知识
    public boolean saveKnowFourthItem(long know_id, String title, String summary,
                                      List<HomeKnowFunction> homeKnowFunctions, String heed,
                                      String experience) {

        HomeKnowContent homeKnowContent = new HomeKnowContent();
        homeKnowContent.setTitle(title);
        homeKnowContent.setSummary(summary);
        homeKnowContent.setHeed(heed);
        homeKnowContent.setExperience(experience);
        long result = mContentDao.insert(homeKnowContent);
        KnowLedgeBean homeKnowItem = mItemDao.load(know_id);
        homeKnowItem.setCreateClass(true);
        homeKnowItem.setContentId(result);
        long dataId = addHomeKnowData(title,4);
        homeKnowItem.setDataId(dataId);
        mItemDao.save(homeKnowItem);
        for (HomeKnowFunction function:homeKnowFunctions){
            function.setFunctionId((int) result);
            mFunctionDao.insert(function);
        }
        //Log.e("save","is=" +is);
        if(result == 0){
            return false;
        }
        return true;
    }

    //判断精通级知识是否存在
    public boolean isEqualsFourthItem(String title) {
        return isEqualsTitle(title);
    }

    //保存专家级知识
    public boolean saveKnowFifthItem(long know_id, String title, String summary,
                                     String show,String explain, String heed,
                                     String experience) {
        HomeKnowContent homeKnowContent = new HomeKnowContent();
        homeKnowContent.setTitle(title);
        homeKnowContent.setSummary(summary);
        homeKnowContent.setHeed(heed);
        homeKnowContent.setExplain(explain);
        homeKnowContent.setShow(show);
        homeKnowContent.setExperience(experience);
        long result = mContentDao.insert(homeKnowContent);
        KnowLedgeBean homeKnowItem = mItemDao.load(know_id);
        homeKnowItem.setCreateClass(true);
        homeKnowItem.setContentId(result);
        long dataId = addHomeKnowData(title,5);
        homeKnowItem.setDataId(dataId);
        mItemDao.save(homeKnowItem);
        if(result == 0){
            return false;
        }
        //Log.e("save","is=" +is);
        return true;
    }

    //判断专家级知识是否存在
    public boolean isEqualsFifthItem(String title) {
        return isEqualsTitle(title);
    }

    private boolean isEqualsTitle(String title){
        long number = mContentDao.queryBuilder().where(HomeKnowContentDao.Properties.Title.eq(title)).count();
        if(number == 0){
            return true;
        }
        return false;
    }

    private boolean insertHomeKnowContent(long result){
        if(result == 0){
            return false;
        }
        return true;
    }
}
