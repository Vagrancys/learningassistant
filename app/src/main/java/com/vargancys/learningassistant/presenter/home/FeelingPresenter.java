package com.vargancys.learningassistant.presenter.home;

import com.vargancys.learningassistant.db.knowledge.TemporaryArticleDb;
import com.vargancys.learningassistant.db.knowledge.TemporaryFeelingDb;
import com.vargancys.learningassistant.http.CommonHttpListener;
import com.vargancys.learningassistant.model.common.bean.NoDataBean;
import com.vargancys.learningassistant.model.home.bean.FeelingBean;
import com.vargancys.learningassistant.model.home.request.ArticleRequest;
import com.vargancys.learningassistant.model.home.request.FeelingRequest;
import com.vargancys.learningassistant.module.home.view.BaseKnowLedgeUpdateView;
import com.vargancys.learningassistant.module.home.view.DataKnowledgeView;
import com.vargancys.learningassistant.module.home.view.InsertArticleView;
import com.vargancys.learningassistant.module.home.view.InsertFeelingView;
import com.vargancys.learningassistant.presenter.BaseCallBackListener;
import com.vargancys.learningassistant.presenter.IBasePresenter;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/09
 * version:1.0
 * 模块名: 函数型管理器 增加获取查询删除修改
 */
public class FeelingPresenter implements IBasePresenter<FeelingBean> {
    private BaseCallBackListener mView;
    public FeelingPresenter(BaseCallBackListener view){
        mView = view;
    }

    /**
     * 判断是否为空
     */
    public void isDataEmpty() {
        InsertFeelingView view = (InsertFeelingView) mView;
        if(view.isEmpty()){
            view.isEmptyFail();
        }else{
            view.isEmptySuccess();
        }
    }

    @Override
    public void add(FeelingBean object) {
        FeelingRequest.getInstance().addFeeling(object,getIdListener());
    }

    @Override
    public void delete(int id) {
        FeelingRequest.getInstance().deleteFeeling(id,getDeleteDataListener());
    }

    @Override
    public void delete(int[] ids) {
        FeelingRequest.getInstance().deleteAllFeeling(ids,getNoDataListener());
    }

    @Override
    public void query(int id) {
        FeelingRequest.getInstance().queryFeeling(id,getDataListener());
    }

    @Override
    public void query(int[] ids) {
        FeelingRequest.getInstance().queryAllFeeling(ids,getArrayListener());
    }

    public void queryData(int id) {
        FeelingRequest.getInstance().queryFeelingData(id,getDataListener());
    }

    /**
     * 编辑过但是没有完成会保存在本地数据库里
     * @param id
     */
    @Override
    public void nativeQuery(int id) {
        InsertArticleView view = ((InsertArticleView) mView);
        TemporaryArticleDb articleBean = ArticleRequest.getInstance().nativeQuery(id,mView);
        if(articleBean != null){
            view.nativeQueryFinish(articleBean);
        }
    }


    @Override
    public void update(FeelingBean object) {
        FeelingRequest.getInstance().updateFeeling(object,getUpdateListener());
    }

    public CommonHttpListener getDeleteDataListener(){
        DataKnowledgeView view = (DataKnowledgeView) mView;
        return new CommonHttpListener() {
            @Override
            public void onSuccess(int code, Object data) {
                NoDataBean mBean = (NoDataBean) data;
                if(mBean.getCode()){
                    view.deleteSuccess();
                }else{
                    view.deleteFail();
                }
            }

            @Override
            public void onError(Throwable t) {
                view.deleteError(t.getMessage());
            }

            @Override
            public void onFinish() {
                mView.onFinish();
            }
        };
    }

    public CommonHttpListener getUpdateListener(){
        return new CommonHttpListener() {
            @Override
            public void onSuccess(int code, Object data) {
                NoDataBean mBean = (NoDataBean) data;
                BaseKnowLedgeUpdateView view = (BaseKnowLedgeUpdateView) mView;
                if(mBean.getCode()){
                    view.onUpdateSuccess();
                }else{
                    view.onUpdateFail();
                }
            }

            @Override
            public void onError(Throwable t) {
                mView.onError(t.getMessage());
            }

            @Override
            public void onFinish() {
                mView.onFinish();
            }
        };
    }

    public CommonHttpListener getNoDataListener(){
        return new CommonHttpListener() {
            @Override
            public void onSuccess(int code, Object data) {
                NoDataBean mBean = (NoDataBean) data;
                if(mBean.getCode()){
                    mView.onSuccess();
                }else{
                    mView.onFail();
                }
            }

            @Override
            public void onError(Throwable t) {
                mView.onError(t.getMessage());
            }

            @Override
            public void onFinish() {
                mView.onFinish();
            }
        };
    }

    public CommonHttpListener getIdListener(){
        return new CommonHttpListener() {
            @Override
            public void onSuccess(int code, Object data) {
                NoDataBean mBean = (NoDataBean) data;
                if(mBean != null){
                    mView.onSuccess(mBean.getMessage());
                }else{
                    mView.onFail();
                }
            }

            @Override
            public void onError(Throwable t) {
                mView.onError(t.getMessage());
            }

            @Override
            public void onFinish() {
                mView.onFinish();
            }
        };
    }

    public CommonHttpListener getDataListener(){
        return new CommonHttpListener() {
            @Override
            public void onSuccess(int code, Object data) {
                FeelingBean mBean = (FeelingBean) data;
                if(mBean != null){
                    mView.onSuccess(mBean);
                }else{
                    mView.onFail();
                }
            }

            @Override
            public void onError(Throwable t) {
                mView.onError(t.getMessage());
            }

            @Override
            public void onFinish() {
                mView.onFinish();
            }
        };
    }

    public CommonHttpListener getArrayListener(){
        return new CommonHttpListener() {
            @Override
            public void onSuccess(int code, Object data) {
                List<FeelingBean> mBean = (List<FeelingBean>) data;
                if(mBean != null && mBean.size() > 0){
                    mView.onSuccess(mBean);
                }else{
                    mView.onFail();
                }
            }

            @Override
            public void onError(Throwable t) {
                mView.onError(t.getMessage());
            }

            @Override
            public void onFinish() {
                mView.onFinish();
            }
        };
    }

    public void nativeDelete(long knowLedge_id) {
        FeelingRequest.getInstance().nativeDelete(knowLedge_id);
    }

    public void nativeAdd(TemporaryFeelingDb mDB) {
        FeelingRequest.getInstance().nativeAdd(mDB);
    }

    /**
     * 验证是否为空和是否相等
     */
    public void isUpdateEmpty() {
        BaseKnowLedgeUpdateView view = (BaseKnowLedgeUpdateView) mView;
        boolean result = view.isPass();
        if(result){
            view.isPassSuccess();
        }else{
            view.isPassFail();
        }
    }
}
