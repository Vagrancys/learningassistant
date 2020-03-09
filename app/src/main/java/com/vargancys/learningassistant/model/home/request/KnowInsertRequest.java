package com.vargancys.learningassistant.model.home.request;

import com.vargancys.learningassistant.db.home.HomeKnowContent;
import com.vargancys.learningassistant.db.home.HomeKnowFunction;
import com.vargancys.learningassistant.db.home.HomeKnowItem;

import org.litepal.LitePal;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/09
 * version:1.0
 */
public class KnowInsertRequest {
    public void KnowINsertRequest(){

    }

    //判断默认级知识是否存在
    public boolean isEqualsDefaultItem(String title){
        return LitePal.isExist(HomeKnowContent.class,title);
    }

    //保存默认级知识
    public boolean saveKnowDefaultItem(int know_id,String title,String summary,String show,
                                       String explain,String heed,String experience){
        HomeKnowContent homeKnowContent = new HomeKnowContent();
        homeKnowContent.setTitle(title);
        homeKnowContent.setSummary(summary);
        homeKnowContent.setShow(show);
        homeKnowContent.setExplain(explain);
        homeKnowContent.setHeed(heed);
        homeKnowContent.setExperience(experience);
        boolean result = homeKnowContent.save();
        HomeKnowItem homeKnowItem = new HomeKnowItem();
        homeKnowItem.setHomeKnowContent(homeKnowContent);
        homeKnowItem.update(know_id);
        return result;
    }

    //判断入门级知识是否存在
    public boolean isEqualsFirstItem(String title){
        return LitePal.isExist(HomeKnowContent.class,title);
    }

    //保存入门级知识
    public boolean saveKnowFirstItem(int know_id,String title,String summary,String show,
                                       String explain,String heed,String experience){
        HomeKnowContent homeKnowContent = new HomeKnowContent();
        homeKnowContent.setTitle(title);
        homeKnowContent.setSummary(summary);
        homeKnowContent.setShow(show);
        homeKnowContent.setExplain(explain);
        homeKnowContent.setHeed(heed);
        homeKnowContent.setExperience(experience);
        boolean result = homeKnowContent.save();
        HomeKnowItem homeKnowItem = new HomeKnowItem();
        homeKnowItem.setHomeKnowContent(homeKnowContent);
        homeKnowItem.update(know_id);
        return result;
    }

    //保存熟悉级知识
    public boolean saveKnowSecondItem(int know_id, String title, String summary,
                                      List<HomeKnowFunction> homeKnowFunctions, String heed,
                                      String experience) {
        for (HomeKnowFunction function:homeKnowFunctions){
            function.save();
        }
        HomeKnowContent homeKnowContent = new HomeKnowContent();
        homeKnowContent.setTitle(title);
        homeKnowContent.setSummary(summary);
        homeKnowContent.setHomeKnowFunctions(homeKnowFunctions);
        homeKnowContent.setHeed(heed);
        homeKnowContent.setExperience(experience);
        boolean result = homeKnowContent.save();
        HomeKnowItem homeKnowItem = new HomeKnowItem();
        homeKnowItem.setHomeKnowContent(homeKnowContent);
        homeKnowItem.update(know_id);
        return result;
    }

    //判断熟悉级知识是否存在
    public boolean isEqualsSecondItem(String title) {
        return LitePal.isExist(HomeKnowContent.class,title);
    }
}
