package com.vargancys.learningassistant.model.home.request;

import com.vargancys.learningassistant.db.home.HomeKnowItem;
import org.litepal.LitePal;


import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/05
 * version:1.0
 */
public class HomeRequest{
    //等到所有的知识项
    public List<HomeKnowItem> getBean() {
        List<HomeKnowItem> mContentBean = LitePal.findAll(HomeKnowItem.class);
        if(mContentBean !=null){
            return mContentBean;
        }else{
            return null;
        }
    }

    public void updateCount(int position){
        HomeKnowItem homeKnowItem = LitePal.find(HomeKnowItem.class,position);
        homeKnowItem.setMax(homeKnowItem.getMax()+1);
        homeKnowItem.setCount(homeKnowItem.getCount()+1);
        homeKnowItem.save();
    }

    public boolean queryKnowRepeat(String title){
        List<HomeKnowItem> items = LitePal.where("title = ?",title).find(HomeKnowItem.class);
        if(items.size() > 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean saveKnowData(HomeKnowItem item){
        item.setMax(1);
        item.setCount(0);
        item.setCreateClass(false);
        item.setProgress(0);
        item.setMasterLevel(0);
        return item.save();
    }

    public boolean deleteKnowData(int item_id) {
        int result = LitePal.delete(HomeKnowItem.class,item_id);
        if(result != 0){
            return true;
        }else{
            return false;
        }
    }
}
