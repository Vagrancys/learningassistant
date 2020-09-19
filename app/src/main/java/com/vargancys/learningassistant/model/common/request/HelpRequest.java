package com.vargancys.learningassistant.model.common.request;

import com.vagrancys.learningassistant.db.DaoSession;
import com.vagrancys.learningassistant.db.HelpCommendItemDao;
import com.vargancys.learningassistant.base.BaseApplication;
import com.vargancys.learningassistant.bean.common.HelpCommendItem;
import com.vargancys.learningassistant.bean.common.HelpContentBean;
import com.vargancys.learningassistant.http.ApiClient;
import com.vargancys.learningassistant.http.BaseBean;
import com.vargancys.learningassistant.http.CommonHttpListener;
import com.vargancys.learningassistant.http.MySubscriber;
import com.vargancys.learningassistant.model.common.bean.NoDataBean;
import com.vargancys.learningassistant.utils.TimeUtils;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/07
 * version:1.0
 * Description: 帮助数据请求中心
 */
public class HelpRequest{
    private HelpCommendItemDao mCommendDao;
    private DaoSession daoSession;
    public HelpRequest(){
        daoSession = BaseApplication.getInstance().getDaoSession();
        mCommendDao = daoSession.getHelpCommendItemDao();
    }

    /**
     * 获取帮助的所有数据
     * @param listener
     */
    public void getAllBean(CommonHttpListener listener) {
        ApiClient.getInstance().getAllHelpData(new MySubscriber<BaseBean<List<HelpContentBean>>>() {
            @Override
            protected void onSuccess(BaseBean<List<HelpContentBean>> baseBean) {
                listener.onSuccess(baseBean.getCode(),baseBean.getData());
            }

            @Override
            public void onError(Throwable t) {
                super.onError(t);
                listener.onError(t);
            }

            @Override
            protected void onFinish() {
                super.onFinish();
                listener.onFinish();
            }
        });
    }

    /**
     * 得到单条帮助数据
     * @param id
     * @return
     */
    public void getSingleHelpData(int id,CommonHttpListener listener) {
        ApiClient.getInstance().getSingleHelpData(id, new MySubscriber<BaseBean<HelpContentBean>>() {
            @Override
            protected void onSuccess(BaseBean<HelpContentBean> baseBean) {
                listener.onSuccess(baseBean.getCode(),baseBean.getData());
            }

            @Override
            public void onError(Throwable t) {
                super.onError(t);
                listener.onError(t);
            }

            @Override
            protected void onFinish() {
                super.onFinish();
                listener.onFinish();
            }
        });
    }

    public void getAllCommendData(int item,CommonHttpListener listener){
        ApiClient.getInstance().getAllCommendData(item, new MySubscriber<BaseBean<List<HelpCommendItem>>>() {
            @Override
            protected void onSuccess(BaseBean<List<HelpCommendItem>> baseBean) {
                listener.onSuccess(baseBean.getCode(),baseBean.getData());
            }

            @Override
            public void onError(Throwable t) {
                super.onError(t);
                listener.onError(t);
            }

            @Override
            protected void onFinish() {
                super.onFinish();
                listener.onFinish();
            }
        });
    }

    /**
     * 添加帮助
     * @param title
     * @param summary
     * @param listener
     * @return
     */
    public void addHelpData(String title,String summary,CommonHttpListener listener){
        Map<String,Object> mMap = new HashMap<>();
        mMap.put(HelpContentBean.TITLE,title);
        mMap.put(HelpContentBean.NUMBER,0);
        mMap.put(HelpContentBean.SUMMARY,summary);
        mMap.put(HelpContentBean.PRAISE,0);
        mMap.put(HelpContentBean.POOR,0);
        mMap.put(HelpContentBean.TIME, TimeUtils.getLongTime());
        ApiClient.getInstance().addSingleHelpData(mMap, new MySubscriber<BaseBean<NoDataBean>>() {
            @Override
            protected void onSuccess(BaseBean<NoDataBean> baseBean) {
                listener.onSuccess(baseBean.getCode(),baseBean.getData());
            }

            @Override
            public void onError(Throwable t) {
                super.onError(t);
                listener.onError(t);
            }

            @Override
            protected void onFinish() {
                super.onFinish();
                listener.onFinish();
            }
        });
    }

    /**
     * 更新帮助书库
     * @param id
     * @param title
     * @param summary
     * @param listener
     */
    public void updateHelpData(int id,String title,String summary,CommonHttpListener listener){
        ApiClient.getInstance().updateHelpData(id, title, summary,TimeUtils.getLongTime(), new MySubscriber<BaseBean<NoDataBean>>() {
            @Override
            protected void onSuccess(BaseBean<NoDataBean> baseBean) {
                listener.onSuccess(baseBean.getCode(),baseBean.getData());
            }

            @Override
            public void onError(Throwable t) {
                super.onError(t);
                listener.onError(t);
            }

            @Override
            protected void onFinish() {
                super.onFinish();
                listener.onFinish();
            }
        });
    }

    public void deleteHelpData(ArrayList<Integer> deletes,CommonHttpListener listener){
        ApiClient.getInstance().deleteHelpData(deletes, new MySubscriber<BaseBean<NoDataBean>>() {
            @Override
            protected void onSuccess(BaseBean<NoDataBean> baseBean) {
                listener.onSuccess(baseBean.getCode(),baseBean.getData());
            }

            @Override
            public void onError(Throwable t) {
                super.onError(t);
                listener.onError(t);
            }

            @Override
            protected void onFinish() {
                super.onFinish();
                listener.onFinish();
            }
        });
    }

    public void deleteHelpData(int delete,CommonHttpListener listener){
        List<Integer> deletes = new ArrayList<>();
        deletes.add(delete);
        ApiClient.getInstance().deleteHelpData(deletes, new MySubscriber<BaseBean<NoDataBean>>() {
            @Override
            protected void onSuccess(BaseBean<NoDataBean> baseBean) {
                listener.onSuccess(baseBean.getCode(),baseBean.getData());
            }

            @Override
            public void onError(Throwable t) {
                super.onError(t);
                listener.onError(t);
            }

            @Override
            protected void onFinish() {
                super.onFinish();
                listener.onFinish();
            }
        });
    }

    /**
     * 添加评论的数据
     * @param state 判断是哪一个
     * @param id 帮助id
     * @param listener
     */
    public void addPraiseOrPoor(int state,int id,CommonHttpListener listener){
        ApiClient.getInstance().addPraiseOrPoor(state, id, new MySubscriber<BaseBean<NoDataBean>>() {
            @Override
            protected void onSuccess(BaseBean<NoDataBean> baseBean) {
                listener.onSuccess(baseBean.getCode(),baseBean.getData());
            }

            @Override
            public void onError(Throwable t) {
                super.onError(t);
                listener.onError(t);
            }

            @Override
            protected void onFinish() {
                super.onFinish();
                listener.onFinish();
            }
        });
    }

    /**
     * 取消评论的数据
     * @param state 判断是哪一个
     * @param id 帮助id
     * @param listener
     */
    public void subPraiseOrPoor(int state,int id,CommonHttpListener listener){
        ApiClient.getInstance().subPraiseOrPoor(state, id, new MySubscriber<BaseBean<NoDataBean>>() {
            @Override
            protected void onSuccess(BaseBean<NoDataBean> baseBean) {
                listener.onSuccess(baseBean.getCode(),baseBean.getData());
            }

            @Override
            public void onError(Throwable t) {
                super.onError(t);
                listener.onError(t);
            }

            @Override
            protected void onFinish() {
                super.onFinish();
                listener.onFinish();
            }
        });
    }

    public void saveCommendData(int id,String title,CommonHttpListener listener){
        ApiClient.getInstance().addHelpCommendData(id, title, TimeUtils.getLongTime(), new MySubscriber<BaseBean<HelpCommendItem>>() {
            @Override
            protected void onSuccess(BaseBean<HelpCommendItem> baseBean) {
                listener.onSuccess(baseBean.getCode(),baseBean.getData());
            }

            @Override
            public void onError(Throwable t) {
                super.onError(t);
                listener.onError(t);
            }

            @Override
            protected void onFinish() {
                super.onFinish();
                listener.onFinish();
            }
        });
    }
}
