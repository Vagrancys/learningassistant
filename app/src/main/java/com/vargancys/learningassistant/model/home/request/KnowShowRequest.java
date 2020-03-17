package com.vargancys.learningassistant.model.home.request;

import com.vargancys.learningassistant.db.home.HomeKnowContent;
import com.vargancys.learningassistant.db.home.HomeKnowItem;

import org.litepal.LitePal;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/17
 * version:1.0
 */
public class KnowShowRequest {
    public KnowShowRequest(){

    }

    public HomeKnowContent getDefaultShowData(int id){
        HomeKnowItem homeKnowItem = LitePal.find(HomeKnowItem.class,id,true);
        if(homeKnowItem.getHomeKnowContent() != null){
            return homeKnowItem.getHomeKnowContent();
        }else {
            return null;
        }
    }
}
