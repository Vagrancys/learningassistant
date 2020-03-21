package com.vargancys.learningassistant.persenter.common.help;

import android.util.Log;

import com.vagrancys.learningassistant.db.DaoSession;
import com.vargancys.learningassistant.db.common.HelpCommendItem;
import com.vargancys.learningassistant.db.common.HelpContentItem;
import com.vargancys.learningassistant.model.common.request.HelpRequest;
import com.vargancys.learningassistant.module.common.view.HelpSummaryView;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/08
 * version:1.0
 */
public class HelpSummaryPresenter {
    private static String TAG = "helpSummaryPresenter";
    private HelpSummaryView mView;
    private HelpRequest helpRequest;
    public HelpSummaryPresenter(HelpSummaryView view){
        this.mView = view;
        helpRequest = new HelpRequest();
    }

    public void getHelpData(int help_id){

        HelpContentItem helpContentItem = helpRequest.getContentBean(help_id);

        if(helpContentItem != null){
            Log.e(TAG,"data = "+helpContentItem.toString());
            mView.findFinish(helpContentItem);
        }else{
            mView.findError(404,"数据库没有数据!");
        }
    }

    public void getCommendData(int help_id){
        List<HelpCommendItem> helpCommendItems = helpRequest.getCommendBean(help_id);
        if(helpCommendItems !=null&&helpCommendItems.size() > 0){
            mView.findCommend(helpCommendItems);
        }else{
            mView.findEmpty();
        }
    }

    public void reFreshSummary(int help_id){
        HelpContentItem helpContentItem = helpRequest.getItemBean(help_id);
        mView.reFreshSummary(helpContentItem);
    }

    public void addPraiseOrPoor(int state,int id){
        int number = helpRequest.addPraiseOrPoor(state,id);
        if(state == 0){
            mView.PraiseOrPoor(0,number,"赞赏");
        }else{
            mView.PraiseOrPoor(1,number,"失望");
        }
    }

    public void subPraiseOrPoor(int state,int id){
        int number = helpRequest.subPraiseOrPoor(state,id);
        if(state == 0){
            mView.PraiseOrPoor(0,number,"赞赏取消");
        }else{
            mView.PraiseOrPoor(1,number,"失望取消");
        }
    }

    public void saveCommendData(int id,String title){
        HelpCommendItem item = helpRequest.saveCommendData(id,title);
        if(item !=null){
            mView.saveCommendFinish(item);
        }else{
            mView.saveCommendError(503,"保存失败了!");
        }
    }
}
