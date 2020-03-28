package com.vargancys.learningassistant.persenter.home;

import com.vargancys.learningassistant.db.home.HomeKnowContent;
import com.vargancys.learningassistant.db.home.HomeKnowHistory;
import com.vargancys.learningassistant.model.home.request.KnowUpdateRequest;
import com.vargancys.learningassistant.module.home.view.BaseKnowUpdateView;
import com.vargancys.learningassistant.module.home.view.KnowUpdateDefaultView;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/28
 * version:1.0
 */
public class KnowUpdatePresenter {
    private KnowUpdateRequest mRequest;
    private BaseKnowUpdateView mView;
    public KnowUpdatePresenter(BaseKnowUpdateView view){
        mView = view;
        mRequest = KnowUpdateRequest.getInstance();
    };

    public void getKnowDefaultContent(long know_id){
        HomeKnowContent homeKnowContent = mRequest.getKnowDefaultContent(know_id);
        if(homeKnowContent != null){
             mView.showKnowDataFinish(homeKnowContent);
        }else{
            mView.showKNowDatError(404,"没有该数据!");
        }
    }

    public void isKnowUpdateDefaultEmpty(){
        boolean result = mView.isKnowUpdateDefaultEmpty();
        if(result){
            mView.showKnowEmptyError(404,"该数据没有填写完成!");
        }else{
            boolean result2 = mView.isKnowUpdateDefaultEquals();
            if(result2){
                mView.saveKnowUpdateContent();
            }else{
                mView.showKnowEqualsError(404,"您没有修改!");
            }
        }
    }


    public void saveKnowUpdateDefault(HomeKnowHistory mOldHistory, HomeKnowContent mNewContent) {
        boolean result = mRequest.saveKnowUpdateDefault(mOldHistory,mNewContent);
        if(result){
            mView.showKnowSaveFinish();
        }else{
            mView.showKnowSaveError(404,"保存失败了!");
        }
    }
}
