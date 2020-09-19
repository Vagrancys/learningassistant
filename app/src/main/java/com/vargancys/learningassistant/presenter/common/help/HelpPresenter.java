package com.vargancys.learningassistant.presenter.common.help;

import android.util.Log;

import com.vargancys.learningassistant.bean.common.HelpCommendItem;
import com.vargancys.learningassistant.bean.common.HelpContentBean;
import com.vargancys.learningassistant.http.CommonHttpListener;
import com.vargancys.learningassistant.model.common.bean.NoDataBean;
import com.vargancys.learningassistant.model.common.request.HelpRequest;
import com.vargancys.learningassistant.module.common.view.HelpAddView;
import com.vargancys.learningassistant.module.common.view.HelpContentView;
import com.vargancys.learningassistant.module.common.view.HelpSummaryView;
import com.vargancys.learningassistant.module.common.view.HelpUpdateView;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/09/03
 * version:1.0
 */
public class HelpPresenter<T> {
    private T mView;
    private HelpRequest helpRequest;
    public HelpPresenter(T view){
        this.mView = view;
        helpRequest = new HelpRequest();
    }

    /**
     * 得到所有数据
     */
    public void getAllData(){
        HelpContentView mHelp = (HelpContentView) mView;
        mHelp.showRefreshView();
        CommonHttpListener listener = new CommonHttpListener() {
            @Override
            public void onSuccess(int code, Object data) {
                List<HelpContentBean> mItems = (List<HelpContentBean>) data;
                if(mItems != null){
                    if(mItems.size() > 0){
                        Log.e("helpPresenter","hideEmpty");
                        mHelp.hideEmpty();
                        mHelp.showContentBean(mItems);
                    }else{
                        Log.e("helpPresenter","showEmpty");
                        mHelp.showEmpty();
                    }
                }else{
                    mHelp.showError(501,"没有帮助数据!");
                }
            }

            @Override
            public void onError(Throwable t) {
                mHelp.showEmpty();
            }

            @Override
            public void onFinish() {

            }
        };
        helpRequest.getAllBean(listener);
    }

    /**
     * 删除帮助数据
     * @param position 位置
     * @param id 要删除的id
     */
    public void deleteData(int position,int id){
        HelpContentView mHelp = (HelpContentView) mView;
        CommonHttpListener listener = new CommonHttpListener() {
            @Override
            public void onSuccess(int code, Object data) {
                NoDataBean mBean = (NoDataBean) data;
                if(mBean.getCode()){
                    mHelp.deleteFinish(position);
                }else{
                    mHelp.deleteError();
                }
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onFinish() {

            }
        };
        helpRequest.deleteHelpData(id,listener);
    }

    /**
     * 得到单条帮助数据
     * @param help_id
     */
    public void getSingleHelpData(int help_id){
        HelpUpdateView mUpdate = (HelpUpdateView) mView;
        CommonHttpListener listener = new CommonHttpListener() {
            @Override
            public void onSuccess(int code, Object data) {
                HelpContentBean mBean = (HelpContentBean) data;
                if(mBean !=null&&code != 200){
                    mUpdate.getHelpData(mBean);
                }else{
                    mUpdate.getHelpError(501,"数据库没有该数据!");
                }
                mUpdate.getHelpData(mBean);
            }

            @Override
            public void onError(Throwable t) {
                mUpdate.getHelpError(501,"网络错误!");
            }

            @Override
            public void onFinish() {

            }
        };
        helpRequest.getSingleHelpData(help_id,listener);
    }

    /**
     * 判断是否重复了
     * @param title
     * @param summary
     * @return
     */
    public boolean equalSingleHelpData(String title,String summary){
        HelpUpdateView mUpdate = (HelpUpdateView) mView;
        return mUpdate.equalSingleHelpData(title,summary);
    }

    /**
     * 添加帮助数据
     * @param title
     * @param summary
     */
    public void saveHelpData(String title,String summary){
        HelpAddView mAdd = (HelpAddView) mView;
        CommonHttpListener listener = new CommonHttpListener() {
            @Override
            public void onSuccess(int code, Object data) {
                NoDataBean mBean = (NoDataBean) data;
                if(code == 200&&mBean.getCode()){
                    mAdd.addFinish();
                }else{
                    mAdd.addError(501,"添加帮助失败了!");
                }
            }

            @Override
            public void onError(Throwable t) {
                mAdd.addError(404,"网络错误!");
            }

            @Override
            public void onFinish() {

            }
        };
        helpRequest.addHelpData(title,summary,listener);
    }

    /**
     * 更新帮助数据
     * @param help_id 帮助的id
     * @param title 帮助的标题
     * @param summary 帮助的简介
     */
    public void updateHelpData(int help_id,String title,String summary){
        HelpUpdateView mUpdate = (HelpUpdateView) mView;
        CommonHttpListener listener = new CommonHttpListener() {
            @Override
            public void onSuccess(int code, Object data) {
                NoDataBean mBean = (NoDataBean) data;
                if(code == 200&& mBean.getCode()){
                    ((HelpUpdateView) mView).updateFinish();
                }else{
                    mUpdate.updateError(502,"帮助保存失败!");
                }
            }

            @Override
            public void onError(Throwable t) {
                mUpdate.updateError(404,"网络错误!");
            }

            @Override
            public void onFinish() {

            }
        };
        helpRequest.updateHelpData(help_id,title,summary,listener);
    }

    /**
     * 得到该id下的所有评论
     * @param help_id 帮助的id
     */
    public void getAllCommendData(int help_id){
        HelpSummaryView mCommend = (HelpSummaryView) mView;
        CommonHttpListener listener = new CommonHttpListener() {
            @Override
            public void onSuccess(int code, Object data) {
                List<HelpCommendItem> mBean = (List<HelpCommendItem>) data;
                if(code == 200 && mBean !=null && mBean.size() > 0){
                    mCommend.findCommend(mBean);
                }else{
                    mCommend.findEmpty();
                }
            }

            @Override
            public void onError(Throwable t) {
                mCommend.findError(501,"网络错误!");
            }

            @Override
            public void onFinish() {
            }
        };
        helpRequest.getAllCommendData(help_id,listener);
    }

    /**
     * 刷新帮助数据
     * @param help_id 帮助的id
     */
    public void reFreshSummary(int help_id){
        HelpSummaryView mSummary = (HelpSummaryView) mView;
        CommonHttpListener listener = new CommonHttpListener() {
            @Override
            public void onSuccess(int code, Object data) {
                HelpContentBean mBean = (HelpContentBean) data;
                if(code == 200 && mBean != null){
                    mSummary.reFreshSummary(mBean);
                }
            }

            @Override
            public void onError(Throwable t) {
            }

            @Override
            public void onFinish() {

            }
        };
        helpRequest.getSingleHelpData(help_id,listener);
    }

    public void addPraiseOrPoor(int state,int id){
        HelpSummaryView mSummary = (HelpSummaryView) mView;
        CommonHttpListener listener = new CommonHttpListener() {
            @Override
            public void onSuccess(int code, Object data) {
                NoDataBean mBean = (NoDataBean) data;
                if(state == 0 && code == 200 && mBean != null){
                    mSummary.PraiseOrPoor(0,Integer.parseInt(mBean.getMessage()),"赞赏");
                }else{
                    mSummary.PraiseOrPoor(1,Integer.parseInt(mBean.getMessage()),"失望");
                }
            }

            @Override
            public void onError(Throwable t) {
            }

            @Override
            public void onFinish() {

            }
        };
        helpRequest.addPraiseOrPoor(state,id,listener);
    }

    public void subPraiseOrPoor(int state,int id){
        HelpSummaryView mSummary = (HelpSummaryView) mView;
        CommonHttpListener listener = new CommonHttpListener() {
            @Override
            public void onSuccess(int code, Object data) {
                NoDataBean mBean = (NoDataBean) data;
                if(state == 0 && code == 200 && mBean != null){
                    mSummary.PraiseOrPoor(0,Integer.parseInt(mBean.getMessage()),"赞赏取消");
                }else{
                    mSummary.PraiseOrPoor(1,Integer.parseInt(mBean.getMessage()),"失望取消");
                }
            }

            @Override
            public void onError(Throwable t) {
            }

            @Override
            public void onFinish() {

            }
        };
        helpRequest.subPraiseOrPoor(state,id,listener);
    }

    public void saveCommendData(int id,String title){
        HelpSummaryView mSummary = (HelpSummaryView) mView;
        CommonHttpListener listener = new CommonHttpListener() {
            @Override
            public void onSuccess(int code, Object data) {
                HelpCommendItem mBean = (HelpCommendItem) data;
                if(code == 200 && mBean != null){
                    mSummary.saveCommendFinish(mBean);
                }else{
                    mSummary.saveCommendError(503,"保存失败了!");
                }
            }

            @Override
            public void onError(Throwable t) {
                mSummary.saveCommendError(404,"网络错误!");
            }

            @Override
            public void onFinish() {

            }
        };
        helpRequest.saveCommendData(id,title,listener);
    }
}
