package com.vargancys.learningassistant.presenter.overview;

import android.os.Handler;
import android.util.Log;

import com.vargancys.learningassistant.db.common.KnowListBean;
import com.vargancys.learningassistant.db.overview.OverViewListContent;
import com.vargancys.learningassistant.db.overview.OverViewListItem;
import com.vargancys.learningassistant.model.overview.bean.OverViewHallBean;
import com.vargancys.learningassistant.model.overview.bean.OverViewHallRankBean;
import com.vargancys.learningassistant.model.overview.request.OverViewRequest;
import com.vargancys.learningassistant.module.overview.view.BaseHallView;
import com.vargancys.learningassistant.module.overview.view.BaseOverView;
import com.vargancys.learningassistant.module.overview.view.OverViewAddView;
import com.vargancys.learningassistant.module.overview.view.OverViewCreateView;
import com.vargancys.learningassistant.module.overview.view.OverViewHallView;
import com.vargancys.learningassistant.module.overview.view.OverViewInformationView;
import com.vargancys.learningassistant.module.overview.view.OverViewQueryView;
import com.vargancys.learningassistant.module.overview.view.OverViewUpdateView;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/03
 * version:1.0
 */
public class BaseOverViewPresenter<T> {
    private static String TAG = "BaseOverViewPresenter";
    private T mView;
    private OverViewRequest mRequest;

    public BaseOverViewPresenter(T view){
        this.mView = view;
        mRequest = OverViewRequest.getInstance();
    }


    public void getAllContentData() {
        List<OverViewListContent> mObject = mRequest.getAllContentData();
        if(mObject !=null && mObject.size()>0){
            ((BaseOverView)mView).getAllData(mObject);
        }else{
            ((BaseOverView)mView).getAllDataError(404,"没有找到数据!");
        }
    }

    public void TidyAllData() {
        ((OverViewAddView)mView).TidyAllData();
    }

    public void saveOverViewAllData(Handler handler, OverViewListContent mContent, List<OverViewListItem> mItems) {
        long parentId = mRequest.saveOverViewContent(mContent);
        boolean result = mRequest.saveOverViewItem(parentId,mItems);
        if(result){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    ((OverViewAddView)mView).saveDataFinish();
                }
            });
        }else{
            handler.post(new Runnable() {
                @Override
                public void run() {
                    ((OverViewAddView)mView).saveDataError(200,"没有保存成功");
                }
            });
        }
    }

    //获得知识集的详细信息
    public void getContentData(long selectId) {
        OverViewListContent mContent = mRequest.getContentData(selectId);
        if(mContent !=null){
            Log.e(TAG,"断点2");
            ((OverViewInformationView)mView).getContentData(mContent);
        }else{
            Log.e(TAG,"断点3");
            ((OverViewInformationView)mView).getContentDataError(404,"没有该数据!");
        }

    }

    //判断知识体系查询是否为空
    public void isQueryDataEmpty(String query) {
        boolean result = ((OverViewQueryView) mView).isDataEmpty(query);
        if(!result){
            List<OverViewListContent> mBean = mRequest.queryOverViewData(query);
            if(mBean != null){
                ((OverViewQueryView) mView).queryDataFinish(mBean);
            }else{
                ((OverViewQueryView) mView).queryDataError(404,"没有数据!");
            }
        }else{
            ((OverViewQueryView) mView).queryDataEmpty();
        }
    }

    //得到知识体系大厅的数据
    public void getOverViewHallData() {
        OverViewHallBean mBean = mRequest.getOverViewHallData();
        if(mBean != null){
            ((OverViewHallView) mView).getOverViewHallDataSuccess(mBean);
        }else{
            ((OverViewHallView) mView).getOverViewHallDataFail(404,"没有查询成功!");
        }
    }

    //选择知识体系大厅数据
    public void selectHallData(long hallId) {
        boolean result = mRequest.selectHallData(hallId);
        if(result){
            ((OverViewHallView) mView).selectHallDataSuccess();
        }else{
            ((OverViewHallView) mView).selectHallDataFail(404,"没有选择该知识体系!");
        }
    }

    //根据质量获取知识体系数据
    public void getHallQualityData() {
        BaseHallData(mRequest.getHallQualityData());
    }

    //公共知识体系大厅获取数据
    private void BaseHallData(List<OverViewHallRankBean> mBean){
        if(mBean != null){
            ((BaseHallView) mView).getHallDataSuccess(mBean);
        }else{
            ((BaseHallView) mView).getHallDataFail(404,"沒有找到数据!");
        }
    }

    //添加知识体系数据
    public void insertOverViewData(long overviewId) {
        boolean result = mRequest.insertOverViewData(overviewId);
        if(result){
            ((BaseHallView) mView).insertHallDataSuccess();
        }else{
            ((BaseHallView) mView).insertHallDataFail(404,"关注失败!");
        }
    }

    //添加个人知识体系数据
    public void insertOverViewCreateData(long overviewId) {
        boolean result = mRequest.insertOverViewCreateData(overviewId);
        if(result){
            ((OverViewCreateView) mView).insertCreateDataSuccess();
        }else{
            ((OverViewCreateView) mView).insertCreateDataFail(404,"关注失败!");
        }
    }

    //根据热度获取知识体系数据
    public void getHallHotData() {
        BaseHallData(mRequest.getHallHotData());
    }

    //根据系统获取知识体系数据
    public void getHallSystemData() {
        BaseHallData(mRequest.getHallSystemData());
    }

    //得到个人知识体系数据
    public void getOverViewData(long createId) {
        List<OverViewListItem> mItems = mRequest.getOverViewData(createId);
        if(mItems != null && mItems.size() > 0){
            ((OverViewCreateView) mView).getOverViewCreateDataSuccess(mItems);
        }else{
            ((OverViewCreateView) mView).getOverViewCreateDataFail(404,"没有找到数据!");
        }

    }

    //删除知识体系
    public void deleteOverViewCreateData(Long id) {
        boolean result = mRequest.deleteOverViewCreateData(id);
        if(result){
            ((OverViewCreateView) mView).deleteCreateDataSuccess();
        }else{
            ((OverViewCreateView) mView).deleteCreateDataFail(404,"删除失败了!");
        }
    }

    //获取知识体系数据
    public void getOverViewUpdateData(long overViewId) {
        List<KnowListBean> mBean = mRequest.getOverViewUpdateData(overViewId);
        if(mBean !=null && mBean.size() >0){
            ((OverViewUpdateView)mView).getOverViewDataSuccess(mBean);
        }else{
            ((OverViewUpdateView)mView).getOverViewDataFail(403,"获取数据失败!");
        }
    }

    //更新知识体系的数据
    public void updateOverViewData(long id, String message) {
        boolean result = mRequest.updateOverViewData(id,message);
        if(result){
            ((OverViewUpdateView) mView).updateOverViewDataSuccess();
        }else{
            ((OverViewUpdateView)mView).updateOverViewDataFail(403,"获取数据失败!");
        }
    }
}
