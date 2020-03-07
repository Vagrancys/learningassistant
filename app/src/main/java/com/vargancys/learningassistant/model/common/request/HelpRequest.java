package com.vargancys.learningassistant.model.common.request;

import android.content.ContentValues;
import android.content.Intent;

import com.vargancys.learningassistant.base.BaseRequest;
import com.vargancys.learningassistant.db.common.HelpContentItem;
import com.vargancys.learningassistant.utils.TimeUtils;

import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/07
 * version:1.0
 */
public class HelpRequest implements BaseRequest {

    @Override
    public void getBean(GetBeanCallback callback) {
        List<?> helpContentBean =  LitePal.findAll(HelpContentItem.class);
        if(helpContentBean.size() > 0){
            callback.onFinish(helpContentBean);
        }else{
            callback.onError(501,"没有帮助数据!");
        }
    }

    @Override
    public void getBean(int item, GetBeanCallback callback) {
        HelpContentItem helpContentItem = LitePal.find(HelpContentItem.class,item);
        if(helpContentItem != null){
            callback.onFinish(helpContentItem);
        }else{
            callback.onError(501,"没有该帮助数据!");
        }
    }

    public void saveHelpData(String title,String summary){
        HelpContentItem helpContentItem = new HelpContentItem();
        helpContentItem.setNumber(LitePal.findAll(HelpContentItem.class).size());
        helpContentItem.setPoor(0);
        helpContentItem.setPraise(0);
        helpContentItem.setTitle(title);
        helpContentItem.setSummary(summary);
        helpContentItem.setTime(TimeUtils.getTime());
        helpContentItem.save();
    }

    public void updateHelpData(int id,String title,String summary){
        HelpContentItem helpContentItem = new HelpContentItem();
        helpContentItem.setTitle(title);
        helpContentItem.setSummary(summary);
        helpContentItem.setTime(TimeUtils.getTime());
        helpContentItem.update(id);
    }

    public void deleteHelpData(ArrayList<Integer> count){
        for (Integer number :count){
            LitePal.delete(HelpContentItem.class,number);
        }
    }
}
