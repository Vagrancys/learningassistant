package com.vargancys.learningassistant.presenter.mine;

import com.vargancys.learningassistant.db.mine.MineDataBean;
import com.vargancys.learningassistant.model.mine.bean.KnowLedgeTypeDataBean;
import com.vargancys.learningassistant.model.mine.request.MineRequest;
import com.vargancys.learningassistant.module.mine.view.BaseMineView;
import com.vargancys.learningassistant.module.mine.view.KnowLedgeView;

/**
 * @author Vagrancy
 * @date 2020/5/20
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 基础我的中心逻辑层
 */
public class BaseMinePresenter {
    private static String TAG = "BaseMinePresenter";
    private Object mView;
    private MineRequest mRequest;

    public BaseMinePresenter(Object view){
        mView = view;
        mRequest = MineRequest.getInstance();
    }

    //得到个人中心数据
    public void getMineData(long mineId) {
        MineDataBean mBean = mRequest.getMineData(mineId);
        if(mBean != null){
            ((BaseMineView) mView).showMineDataFinish(mBean);
        }else{
            ((BaseMineView) mView).showMineDataError(404,"没有找到该数据!");
        }
    }

    //得到个人中心知识用户数据
    public void getKnowLedgeData(long mineId) {
        MineDataBean mBean = mRequest.getKnowLedgeData(mineId);
        if(mBean != null){
            ((KnowLedgeView) mView).showKnowLedgeDataFinish(mBean);
        }else{
            ((KnowLedgeView) mView).showKnowLedgeDataError(404,"没有找到数据!");
        }
    }

    //得到个人中心知识类型数据
    public void getKnowLedgeTypeData(long mineId) {
        KnowLedgeTypeDataBean mBean = mRequest.getKnowLedgeTypeData(mineId);
        if(mBean != null){
            ((KnowLedgeView) mView).showKnowLedgeTypeDataFinish(mBean);
        }else{
            ((KnowLedgeView) mView).showKnowLedgeTypeDataError(404,"没有找到数据!");
        }
    }
}