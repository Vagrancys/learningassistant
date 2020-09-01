package com.vargancys.learningassistant.model.home.request;

import com.vagrancys.learningassistant.db.DaoSession;
import com.vagrancys.learningassistant.db.HomeKnowItemDao;
import com.vargancys.learningassistant.base.BaseApplication;
import com.vargancys.learningassistant.db.home.KnowLedgeBean;


import org.greenrobot.greendao.query.QueryBuilder;

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
    public List<KnowLedgeBean> getBean() {
        List<KnowLedgeBean> mContentBean = mItemDao.loadAll();
        if(mContentBean !=null){
            return mContentBean;
        }else{
            return null;
        }
    }

    public void updateCount(long position){
        KnowLedgeBean homeKnowItem = mItemDao.load(position);
        homeKnowItem.setMax(homeKnowItem.getMax()+1);
        homeKnowItem.setCount(homeKnowItem.getCount()+1);
        mItemDao.save(homeKnowItem);
    }

    public boolean queryKnowRepeat(String title){
        KnowLedgeBean homeKnowItem = mItemDao.queryBuilder().where(HomeKnowItemDao.Properties.Title.eq(title)).unique();
        if(homeKnowItem == null){
            return true;
        }
        return false;
    }

    public boolean saveKnowData(KnowLedgeBean item){
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

    public List<KnowLedgeBean> getSelectBean(int language, int level, int show, int master) {
        QueryBuilder<KnowLedgeBean> queryBuilder = mItemDao.queryBuilder();
        if(language != 0){
            queryBuilder.where(HomeKnowItemDao.Properties.Language.eq(language));
        }
        if(level != 0){
            queryBuilder.where(HomeKnowItemDao.Properties.Level.eq(level));
        }
        if(show != 0){
            boolean result;
            if(show ==1){
                result = true;
            }else{
                result = false;
            }
            queryBuilder.where(HomeKnowItemDao.Properties.CreateClass.eq(result));
        }
        if(master != 0){
            queryBuilder.where(HomeKnowItemDao.Properties.MasterLevel.eq(master));
        }
        return queryBuilder.list();
    }

}
