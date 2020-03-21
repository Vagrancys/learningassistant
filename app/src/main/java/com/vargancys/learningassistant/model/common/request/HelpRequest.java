package com.vargancys.learningassistant.model.common.request;

import android.util.Log;

import com.vagrancys.learningassistant.db.DaoSession;
import com.vagrancys.learningassistant.db.HelpCommendItemDao;
import com.vagrancys.learningassistant.db.HelpContentItemDao;
import com.vargancys.learningassistant.base.BaseApplication;
import com.vargancys.learningassistant.db.common.HelpCommendItem;
import com.vargancys.learningassistant.db.common.HelpContentItem;
import com.vargancys.learningassistant.utils.TimeUtils;


import java.util.ArrayList;
import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/07
 * version:1.0
 */
public class HelpRequest{
    private HelpContentItemDao mContentDao;
    private HelpCommendItemDao mCommendDao;
    private DaoSession daoSession;
    public HelpRequest(){
        daoSession = BaseApplication.getInstance().getDaoSession();
        mContentDao = daoSession.getHelpContentItemDao();
        mCommendDao = daoSession.getHelpCommendItemDao();
    }

    public HelpContentItem getItemBean(long id){
        HelpContentItem helpContentItem = mContentDao.load(id);
        if(helpContentItem !=null){
            return helpContentItem;
        }
        return null;
    }

    public List<HelpContentItem> getAllBean() {
        List<HelpContentItem> helpContentItems =  mContentDao.loadAll();
        if(helpContentItems !=null){
            return helpContentItems;
        }else{
            return null;
        }
    }

    public HelpContentItem getContentBean(long item) {
        return mContentDao.load(item);
    }

    public List<HelpCommendItem> getCommendBean(int item){
        return mCommendDao._queryHelpContentItem_CommendItems(item);
        //Log.e("helpcommend","size"+helpContentItem.getCommendItems().size());
    }

    public boolean saveHelpData(String title,String summary){
        if(title.length() == 0 && summary.length() == 0){
            return false;
        }
        HelpContentItem helpContentItem = new HelpContentItem();
        //helpContentItem.setNumber(LitePal.findAll(HelpContentItem.class).size());
        helpContentItem.setPoor(0);
        helpContentItem.setPraise(0);
        helpContentItem.setTitle(title);
        helpContentItem.setSummary(summary);
        helpContentItem.setNumber(0);
        helpContentItem.setTime(TimeUtils.getTime());
        long result = mContentDao.insert(helpContentItem);
        if(result == 0){
            return false;
        }else{
            return true;
        }
        //helpContentItem.save();
    }

    public boolean updateHelpData(int id,String title,String summary){
        HelpContentItem helpContentItem = new HelpContentItem();
        helpContentItem.setContentId(id);
        helpContentItem.setTitle(title);
        helpContentItem.setSummary(summary);
        helpContentItem.setTime(TimeUtils.getTime());
        mContentDao.update(helpContentItem);
        //int error = helpContentItem.update(id);
        return true;
    }

    public void deleteHelpData(ArrayList<Integer> count){
        for (Integer number :count){
            mContentDao.deleteByKey((long)number);
            //LitePal.delete(HelpContentItem.class,number);
        }
    }

    public int deleteHelpData(long id){
        mContentDao.deleteByKey(id);
        return 1;//LitePal.delete(HelpContentItem.class,id);
    }

    public int addPraiseOrPoor(int state,long id){
        HelpContentItem helpContentItem = mContentDao.load(id);
        int number;
        if(state == 0){
            number = helpContentItem.getPraise()+1;
            helpContentItem.setPraise(number);
            mContentDao.update(helpContentItem);
        }else{
            number = helpContentItem.getPoor()+1;
            helpContentItem.setPoor(number);
            mContentDao.update(helpContentItem);
        }
        return number;
    }

    public int subPraiseOrPoor(int state,long id){
        HelpContentItem helpContentItem = mContentDao.load(id);
        int number;
        if(state == 0){
            number = helpContentItem.getPraise()+1;
            helpContentItem.setPraise(number);
            mContentDao.update(helpContentItem);
        }else{
            number = helpContentItem.getPoor()+1;
            helpContentItem.setPoor(number);
            mContentDao.update(helpContentItem);
        }
        return number;
    }

    public HelpCommendItem saveCommendData(int id,String title){
        HelpCommendItem helpCommendItem = new HelpCommendItem();
        helpCommendItem.setSummary(title);
        helpCommendItem.setTime(TimeUtils.getTime());
        helpCommendItem.setCommendId(id);
        long result = mCommendDao.insert(helpCommendItem);
        if(result != 0){
            return helpCommendItem;
        }else{
            return null;
        }
    }
}
