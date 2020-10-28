package com.vargancys.learningassistant.presenter.home;

import com.vargancys.learningassistant.bean.home.HomeKnowHistory;
import com.vargancys.learningassistant.model.home.bean.HistoryBean;
import com.vargancys.learningassistant.model.home.request.KnowHistoryRequest;
import com.vargancys.learningassistant.module.home.view.BaseHistoryView;
import com.vargancys.learningassistant.presenter.BaseCallBackListener;
import com.vargancys.learningassistant.presenter.IBasePresenter;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/28
 * version:1.0
 */
public class KnowledgeHistoryPresenter implements IBasePresenter<HistoryBean> {
    private BaseCallBackListener mView;
    public KnowledgeHistoryPresenter(BaseCallBackListener view){
        mView = view;
    }

    @Override
    public void delete(int id) {
        ClassRequest.getInstance().deleteClass(id,getDeleteDataListener());
    }

    @Override
    public void delete(int[] ids) {
        ClassRequest.getInstance().deleteAllClass(ids,getNoDataListener());
    }

    @Override
    public void query(int id) {
        ClassRequest.getInstance().queryClass(id,getDataListener());
    }

    @Override
    public void query(int[] ids) {
        ClassRequest.getInstance().queryAllClass(ids,getArrayListener());
    }

    public void queryData(int id) {
        ClassRequest.getInstance().queryClassData(id,getDataListener());
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
    public void update(ClassBean object) {
        ClassRequest.getInstance().updateClass(object,getUpdateListener());
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
                ClassBean mBean = (ClassBean) data;
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
                List<ClassBean> mBean = (List<ClassBean>) data;
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
        ClassRequest.getInstance().nativeDelete(knowLedge_id);
    }

    public void nativeAdd(TemporaryClassDb mDB) {
        ClassRequest.getInstance().nativeAdd(mDB);
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
