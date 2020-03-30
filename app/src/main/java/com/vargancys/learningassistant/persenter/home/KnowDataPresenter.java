package com.vargancys.learningassistant.persenter.home;

import com.vargancys.learningassistant.db.home.HomeKnowCommend;
import com.vargancys.learningassistant.db.home.HomeKnowContent;
import com.vargancys.learningassistant.db.home.HomeKnowData;
import com.vargancys.learningassistant.db.home.HomeKnowHistory;
import com.vargancys.learningassistant.model.home.request.KnowDataRequest;
import com.vargancys.learningassistant.module.home.view.KnowDataView;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/25
 * version:1.0
 */
public class KnowDataPresenter {
    private KnowDataView mView;
    private KnowDataRequest mRequest;
    public KnowDataPresenter(KnowDataView knowDataView){
        this.mView = knowDataView;
        mRequest = KnowDataRequest.getRequest();
    }

    public void getShowData(Long data_id) {
        HomeKnowData homeKnowData = mRequest.getShowData(data_id);
        if(homeKnowData !=null){
            mView.showDataFinish(homeKnowData);
        }else{
            mView.showDataError(501,"获取数据失败!");
        }
    }

    public void isCommendEmpty(String commend) {
        if(commend.isEmpty()){
            mView.showCommendEmptyError(501,"评论没有填写！");
        }else{
            mView.showSaveCommend();
        }
    }

    public void saveCommend(long know_id,String commend) {
        HomeKnowCommend homeKnowCommend = mRequest.saveCommend(know_id,commend);
        if(homeKnowCommend != null){
            mView.showCommendSaveFinish(homeKnowCommend);
        }else{
            mView.showCommendSaveError(404,"没有添加评论成功!");
        }
    }

    public void deleteDataItem(Long know_id) {
        int delete = mRequest.deleteDataItem(know_id);
        if(delete == 200){
            mView.deleteDataFinish();
        }else{
            mView.deleteDataError(404,"删除失败了!");
        }
    }

    public void getHistoryRefreshData(long data_id,long content_id) {
        List<HomeKnowHistory> homeKnowHistories = mRequest.getHistoryRefreshData(data_id);
        HomeKnowContent homeKnowContent = mRequest.getContentRefreshData(content_id);
        if(homeKnowHistories.size() > 0){
            mView.showRefreshHistoryFinish(homeKnowHistories,homeKnowContent);
        }else{
            mView.showRefreshHistoryError(501,"获取数据失败!");
        }
    }

    public long getContentId(long know_id) {
        return mRequest.getContentId(know_id);
    }
}
