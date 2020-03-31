package com.vargancys.learningassistant.model.home.request;

import com.vagrancys.learningassistant.db.DaoSession;
import com.vagrancys.learningassistant.db.HomeKnowDataDao;
import com.vagrancys.learningassistant.db.HomeKnowItemDao;
import com.vargancys.learningassistant.base.BaseApplication;
import com.vargancys.learningassistant.db.home.HomeKnowData;
import com.vargancys.learningassistant.db.home.HomeKnowItem;
import com.vargancys.learningassistant.utils.TimeUtils;


import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/05
 * version:1.0
 */
public class HomeRequest{
    private HomeKnowItemDao mItemDao;
    private DaoSession daoSession;

    public HomeRequest(){
        daoSession = BaseApplication.getInstance().getDaoSession();
        mItemDao = daoSession.getHomeKnowItemDao();


    }
    //等到所有的知识项
    public List<HomeKnowItem> getBean() {
        List<HomeKnowItem> mContentBean = mItemDao.loadAll();
        if(mContentBean !=null){
            return mContentBean;
        }else{
            return null;
        }
    }

    public void updateCount(long position){
        HomeKnowItem homeKnowItem = mItemDao.load(position);
        homeKnowItem.setMax(homeKnowItem.getMax()+1);
        homeKnowItem.setCount(homeKnowItem.getCount()+1);
        mItemDao.save(homeKnowItem);
    }

    public boolean queryKnowRepeat(String title){
        HomeKnowItem homeKnowItem = mItemDao.queryBuilder().where(HomeKnowItemDao.Properties.Title.eq(title)).unique();
        if(homeKnowItem == null){
            return true;
        }
        return false;
    }

    public boolean saveKnowData(HomeKnowItem item){
        item.setMax(1);
        item.setCount(0);
        item.setCreateClass(false);
        item.setProgress(0);
        item.setMasterLevel(0);

        long result = mItemDao.insert(item);
        if(result == 0){
            return false;
        }
        return true;
    }



    public boolean deleteKnowData(long item_id) {
        mItemDao.deleteByKey(item_id);
        return true;
    }

    public List<HomeKnowItem> getSelectBean(int language, int level, int show, int master) {
        
    }
}
