package com.vargancys.learningassistant.persenter.common.help;

import com.vargancys.learningassistant.base.BaseRequest;
import com.vargancys.learningassistant.db.common.HelpCommendItem;
import com.vargancys.learningassistant.db.common.HelpContentItem;
import com.vargancys.learningassistant.model.common.request.HelpRequest;
import com.vargancys.learningassistant.module.common.view.HelpSummaryView;
import com.vargancys.learningassistant.module.common.view.HelpUpdateView;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/08
 * version:1.0
 * 帮助更新Presenter页
 */
public class HelpUpdatePresenter {
    private HelpUpdateView mView;
    private HelpRequest helpRequest;
    public HelpUpdatePresenter(HelpUpdateView view){
        this.mView = view;
        helpRequest = new HelpRequest();
    }

    public void getHelpData(int help_id){
        HelpContentItem helpContentItem = helpRequest.getBean(help_id);
        if(helpContentItem !=null){
            mView.getHelpData(helpContentItem);
        }else{
            mView.getHelpError(501,"数据库没有该数据!");
        }
    }

    public boolean equalsHelpData(String title,String summary){
        return mView.equalsHelpData(title,summary);
    }

    public void updateHelpData(int help_id,String title,String summary){
        boolean result = helpRequest.updateHelpData(help_id,title,summary);
        if(result){
            mView.updateFinish();
        }else{
            mView.updateError(502,"数据库保存失败!");
        }
    }
}
