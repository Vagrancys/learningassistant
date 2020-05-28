package com.vargancys.learningassistant.presenter.mine;

import com.vargancys.learningassistant.db.home.HomeKnowItem;
import com.vargancys.learningassistant.db.ladder.LadderDataBean;
import com.vargancys.learningassistant.db.mine.MineDataBean;
import com.vargancys.learningassistant.model.mine.bean.ChallengeTypeDataBean;
import com.vargancys.learningassistant.model.mine.bean.KnowLedgeTypeDataBean;
import com.vargancys.learningassistant.model.mine.request.MineRequest;
import com.vargancys.learningassistant.module.mine.view.BaseMineView;
import com.vargancys.learningassistant.module.mine.view.ChallengeDetailsView;
import com.vargancys.learningassistant.module.mine.view.ChallengeItemView;
import com.vargancys.learningassistant.module.mine.view.ChallengeView;
import com.vargancys.learningassistant.module.mine.view.KnowLedgeItemView;
import com.vargancys.learningassistant.module.mine.view.KnowLedgeView;

import java.util.List;

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

    //得到个人发布的所有知识
    public void getHomeKnowData(int type) {
        List<HomeKnowItem> mItem = mRequest.getHomeKnowData(type);
        if(mItem != null && mItem.size() > 0){
            ((KnowLedgeItemView) mView).loadKnowLedge(mItem);
        }else{
            ((KnowLedgeItemView) mView).loadKnowLedgeError(404,"没有找到数据!");
        }
    }

    //得到个人中心知识用户数据
    public void getChallengeData(long mineId) {
        MineDataBean mBean = mRequest.getKnowLedgeData(mineId);
        if(mBean != null){
            ((ChallengeView) mView).loadChallengeDataFinish(mBean);
        }else{
            ((ChallengeView) mView).loadChallengeDataError(404,"没有找到数据!");
        }
    }

    //得到个人中心天梯类型数据
    public void getChallengeTypeData(long mineId) {
        ChallengeTypeDataBean mBean = mRequest.getChallengeTypeData(mineId);
        if(mBean != null){
            ((ChallengeView) mView).loadChallengeTypeDataFinish(mBean);
        }else{
            ((ChallengeView) mView).loadChallengeTypeDataError(404,"没有找到数据!");
        }
    }

    //得到个人中心天梯详情数据
    public void getChallengeDetailsData(long challengeId) {
        LadderDataBean mBean = mRequest.getChallengeDetailsData(challengeId);
        if(mBean != null){
            ((ChallengeDetailsView) mView).loadChallengeDetailsData(mBean);
        }else{
            ((ChallengeDetailsView) mView).loadChallengeDetailsDataError(404,"没有找到数据!");
        }
    }

    //得到个人中心天梯各项数据
    public void getChallengeItemData(int type) {
        List<LadderDataBean> mBean = mRequest.getChallengeItemData(type);
        if(mBean != null && mBean.size() > 0){
            ((ChallengeItemView) mView).loadItemDataFinish(mBean);
        }else{
            ((ChallengeItemView) mView).loadItemDataError(404,"数据没有找到!");
        }
    }
}
