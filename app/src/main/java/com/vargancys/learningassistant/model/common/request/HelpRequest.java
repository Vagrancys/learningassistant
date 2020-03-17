package com.vargancys.learningassistant.model.common.request;

import android.util.Log;

import com.vargancys.learningassistant.db.common.HelpCommendItem;
import com.vargancys.learningassistant.db.common.HelpContentItem;
import com.vargancys.learningassistant.utils.TimeUtils;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/07
 * version:1.0
 */
public class HelpRequest{

    public HelpContentItem getItemBean(int id){
        HelpContentItem helpContentItem = LitePal.find(HelpContentItem.class,id);
        if(helpContentItem !=null){
            return helpContentItem;
        }
        return null;
    }

    public List<HelpContentItem> getAllBean() {
        List<HelpContentItem> helpContentItems =  LitePal.findAll(HelpContentItem.class);
        if(helpContentItems !=null){
            return helpContentItems;
        }else{
            return null;
        }
    }

    public HelpContentItem getContentBean(int item) {
        return LitePal.find(HelpContentItem.class,item);
    }

    public List<HelpCommendItem> getCommendBean(int item){
        HelpContentItem helpContentItem = LitePal.find(HelpContentItem.class,item,true);
        Log.e("helpcommend","size"+helpContentItem.getCommendItems().size());
        return helpContentItem.getCommendItems();
    }

    public boolean saveHelpData(String title,String summary){
        if(title.length() == 0 && summary.length() == 0){
            return false;
        }
        HelpContentItem helpContentItem = new HelpContentItem();
        helpContentItem.setNumber(LitePal.findAll(HelpContentItem.class).size());
        helpContentItem.setPoor(0);
        helpContentItem.setPraise(0);
        helpContentItem.setTitle(title);
        helpContentItem.setSummary(summary);
        helpContentItem.setTime(TimeUtils.getTime());
        return helpContentItem.save();
    }

    public boolean updateHelpData(int id,String title,String summary){
        HelpContentItem helpContentItem = new HelpContentItem();
        helpContentItem.setTitle(title);
        helpContentItem.setSummary(summary);
        helpContentItem.setTime(TimeUtils.getTime());
        int error = helpContentItem.update(id);
        if(error != 0){
            return true;
        }else{
            return false;
        }
    }

    public void deleteHelpData(ArrayList<Integer> count){
        for (Integer number :count){
            LitePal.delete(HelpContentItem.class,number);
        }
    }

    public int deleteHelpData(int id){
        return LitePal.delete(HelpContentItem.class,id);
    }

    public int addPraiseOrPoor(int state,int id){
        HelpContentItem helpContentItem = LitePal.find(HelpContentItem.class,id);
        int number;
        if(state == 0){
            number = helpContentItem.getPraise()+1;
            helpContentItem.setPraise(number);
            helpContentItem.update(id);
        }else{
            number = helpContentItem.getPoor()+1;
            helpContentItem.setPoor(number);
            helpContentItem.update(id);
        }
        return number;
    }

    public int subPraiseOrPoor(int state,int id){
        HelpContentItem helpContentItem = LitePal.find(HelpContentItem.class,id);
        int number;
        if(state == 0){
            number = helpContentItem.getPraise()+1;
            helpContentItem.setPraise(number);
            helpContentItem.update(id);
        }else{
            number = helpContentItem.getPoor()+1;
            helpContentItem.setPoor(number);
            helpContentItem.update(id);
        }
        return number;
    }

    public HelpCommendItem saveCommendData(int id,String title){
        HelpCommendItem helpCommendItem = new HelpCommendItem();
        HelpContentItem helpContentItem = LitePal.find(HelpContentItem.class,id);
        helpCommendItem.setSummary(title);
        helpCommendItem.setTime(TimeUtils.getTime());
        helpCommendItem.setHelpContentItem(helpContentItem);
        boolean result = helpCommendItem.save();
        if(result){
            return helpCommendItem;
        }else{
            return null;
        }
    }
}
