package com.vargancys.learningassistant.persenter.common.help;

import android.util.Log;

import com.vargancys.learningassistant.db.common.HelpContentItem;
import com.vargancys.learningassistant.model.common.request.HelpRequest;
import com.vargancys.learningassistant.module.common.view.HelpContentView;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/07
 * version:1.0
 */
public class HelpContentPresenter{
    private HelpContentView helpContentView;
    private HelpRequest helpRequest;
    public HelpContentPresenter(HelpContentView view){
        this.helpContentView = view;
        helpRequest = new HelpRequest();
    }

    public void getAllBean(){
        helpContentView.showRefreshView();
        List<HelpContentItem> mItems = helpRequest.getAllBean();
        if(mItems != null){
            if(mItems.size() > 0){
                Log.e("helpPresenter","hideEmpty");
                helpContentView.hideEmpty();
                helpContentView.showContentBean(mItems);
            }else{
                Log.e("helpPresenter","showEmpty");
                helpContentView.showEmpty();
            }
        }else{
            helpContentView.showError(501,"没有帮助数据!");
        }
    }

    public void deleteHelpData(int position,int id){
        int result = helpRequest.deleteHelpData(id);
        if(result != 0){
            helpContentView.deleteFinish(position);
        }else{
            helpContentView.deleteError();
        }
    }

}
