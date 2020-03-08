package com.vargancys.learningassistant.persenter.common.help;

import com.vargancys.learningassistant.base.BaseRequest;
import com.vargancys.learningassistant.db.common.HelpCommendItem;
import com.vargancys.learningassistant.db.common.HelpContentItem;
import com.vargancys.learningassistant.model.common.request.HelpRequest;
import com.vargancys.learningassistant.module.common.view.HelpSummaryView;

import org.litepal.LitePal;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/08
 * version:1.0
 */
public class HelpSummaryPresenter {
    private HelpSummaryView mView;
    private HelpRequest helpRequest;
    public HelpSummaryPresenter(HelpSummaryView view){
        this.mView = view;
        helpRequest = new HelpRequest();
    }

    public void getHelpData(int help_id){
        helpRequest.getBean(help_id, new BaseRequest.GetBeanCallback() {
            @Override
            public void onFinish(List<?> bean) {

            }

            @Override
            public void onError(int error, String msg) {
                mView.findError(error,msg);
            }

            @Override
            public void onFinish(Object object) {
                mView.findFinish(object);
            }
        });
    }

    public void getCommendData(int help_id){
        helpRequest.getBean(help_id, new BaseRequest.GetBeanCallback() {
            @Override
            public void onFinish(List<?> bean) {

            }

            @Override
            public void onError(int error, String msg) {
                mView.findCommendError(error,msg);
            }

            @Override
            public void onFinish(Object object) {
                List<HelpCommendItem> helpCommendItems = ((HelpContentItem) object).getCommendItems();
                if(helpCommendItems.size() > 0){
                    mView.findCommend(helpCommendItems);
                } else{
                    mView.findEmpty();
                }
            }
        });
    }

    public void reFreshSummary(int help_id){
        HelpContentItem helpContentItem = helpRequest.getBean(help_id);
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
        boolean result = helpRequest.saveCommendData(id,title);
        if(result){
            mView.saveCommendFinish();
        }else{
            mView.saveCommendError(503,"保存失败了!");
        }
    }
}
