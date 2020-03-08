package com.vargancys.learningassistant.model.home.request;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.ColorSpace;

import com.vargancys.learningassistant.base.BaseRequest;
import com.vargancys.learningassistant.base.BaseRequestNetWork;
import com.vargancys.learningassistant.db.home.HomeKnowItem;
import com.vargancys.learningassistant.model.home.bean.HomeContentBean;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/05
 * version:1.0
 */
public class HomeRequest implements BaseRequest{
    //等到所有的知识项
    @Override
    public void getBean(GetBeanCallback callback) {
        List<?> mContentBean = LitePal.findAll(HomeKnowItem.class);
        //baseRequestNetWork = new BaseRequestNetWork("ss",new Object());
        //HomeContentBean homeContentBean = (HomeContentBean) baseRequestNetWork.getBean();
        //if(homeContentBean.getError() == 200){
        //    callback.onFinish(homeContentBean.getContentBeans());
        //}else{
        //    callback.onError(homeContentBean.getError(),homeContentBean.getMsg());
        //}
        if(mContentBean.size() > 0){
            callback.onFinish(mContentBean);
        }else{
            callback.onError(404,"数据库没有数据!");
        }
    }

    //等到固定的知识项
    @Override
    public void getBean(int item,GetBeanCallback callback) {

    }

    public void updateCount(int position){
        HomeKnowItem homeKnowItem = LitePal.find(HomeKnowItem.class,position);
        homeKnowItem.setMax(homeKnowItem.getMax()+1);
        homeKnowItem.setCount(homeKnowItem.getCount()+1);
        homeKnowItem.save();
    }

    public boolean queryKnowRepeat(String title){
        List<HomeKnowItem> items = LitePal.where("title = ",title).find(HomeKnowItem.class);
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
