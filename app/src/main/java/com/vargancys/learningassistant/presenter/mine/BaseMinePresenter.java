package com.vargancys.learningassistant.presenter.mine;

import android.content.Context;

import com.vargancys.learningassistant.db.home.HomeKnowItem;
import com.vargancys.learningassistant.db.ladder.LadderDataBean;
import com.vargancys.learningassistant.db.mine.MineDataBean;
import com.vargancys.learningassistant.db.mine.MineLevelPrivilegeBean;
import com.vargancys.learningassistant.db.overview.OverViewListContent;
import com.vargancys.learningassistant.model.mine.bean.ChallengeTypeDataBean;
import com.vargancys.learningassistant.model.mine.bean.KnowLedgeItemBean;
import com.vargancys.learningassistant.model.mine.bean.KnowLedgeTypeDataBean;
import com.vargancys.learningassistant.model.mine.bean.LevelItemBean;
import com.vargancys.learningassistant.model.mine.bean.LevelTypeDataBean;
import com.vargancys.learningassistant.model.mine.bean.ProblemDetailsBean;
import com.vargancys.learningassistant.model.mine.bean.ProblemTypeDataBean;
import com.vargancys.learningassistant.model.mine.request.MineRequest;
import com.vargancys.learningassistant.module.mine.view.BaseMineView;
import com.vargancys.learningassistant.module.mine.view.ChallengeDetailsView;
import com.vargancys.learningassistant.module.mine.view.ChallengeItemView;
import com.vargancys.learningassistant.module.mine.view.ChallengeView;
import com.vargancys.learningassistant.module.mine.view.KnowLedgeItemView;
import com.vargancys.learningassistant.module.mine.view.KnowLedgeView;
import com.vargancys.learningassistant.module.mine.view.LevelDetailsView;
import com.vargancys.learningassistant.module.mine.view.LevelView;
import com.vargancys.learningassistant.module.mine.view.ProblemDetailsView;
import com.vargancys.learningassistant.module.mine.view.ProblemItemView;
import com.vargancys.learningassistant.module.mine.view.ProblemView;
import com.vargancys.learningassistant.module.mine.view.SettingFeedbackView;
import com.vargancys.learningassistant.module.mine.view.SystemView;

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
    public void getKnowLedgeTypeData(Context context,long mineId) {
        KnowLedgeTypeDataBean mBean = mRequest.getKnowLedgeTypeData(context,mineId);
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
    public void getChallengeTypeData(Context context,long mineId) {
        ChallengeTypeDataBean mBean = mRequest.getChallengeTypeData(context,mineId);
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

    //得到个人中心知识体系数据
    public void getSystemData(long mineId) {
        MineDataBean mBean = mRequest.getMineData(mineId);
        if(mBean != null){
            ((SystemView) mView).loadSystemDataFinish(mBean);
        }else{
            ((SystemView) mView).loadSystemDataError(404,"没有找到数据!");
        }
    }

    //得到个人中心等级体系数据
    public void getLevelData(long mineId) {
        MineDataBean mBean = mRequest.getMineData(mineId);
        if(mBean != null){
            ((LevelView) mView).loadLevelDataFinish(mBean);
        }else{
            ((LevelView) mView).loadLevelDataError(404,"没有找到数据!");
        }
    }

    //得到个人中心天梯类型数据
    public void getSystemTypeData(long mineId) {
        List<OverViewListContent> mBean = mRequest.getSystemTypeData(mineId);
        if(mBean != null){
            ((SystemView) mView).loadSystemTypeDataFinish(mBean);
        }else{
            ((SystemView) mView).loadSystemTypeDataError(404,"没有找到数据!");
        }
    }

    //得到个人中心等级详情数据
    public void getLevelDetailsData(long levelId) {
        LevelItemBean.LevelItem mItem = mRequest.getLevelDetailsData(levelId);
        if(mItem != null){
            ((LevelDetailsView) mView).loadLevelDetailsDataFinish(mItem);
        }else{
            ((LevelDetailsView) mView).loadLevelDetailsDataError(404,"没有找到数据!");
        }
    }

    //得到个人中心等级权力数据
    public void getLevelPrivilegeData() {
        List<MineLevelPrivilegeBean> mBean = mRequest.getLevelPrivilegeData();
        if(mBean!=null && mBean.size() > 0){
            ((LevelDetailsView) mView).loadLevelPrivilegeDataFinish(mBean);
        }else{
            ((LevelDetailsView) mView).loadLevelPrivilegeDataError(404,"没有找到数据!");
        }
    }

    //得到个人中心问题数据
    public void getProblemData(long mineId) {
        MineDataBean mBean = mRequest.getMineData(mineId);
        if(mBean != null){
            ((ProblemView) mView).loadProblemDataFinish(mBean);
        }else{
            ((ProblemView) mView).loadProblemDataError(404,"没有找到数据!");
        }
    }

    //得到个人中心问题数据
    public void getProblemTypeData(Context context,long mineId) {
        ProblemTypeDataBean mBean = mRequest.getProblemTypeData(context,mineId);
        if(mBean != null){
            ((ProblemView) mView).loadProblemTypeDataFinish(mBean);
        }else{
            ((ProblemView) mView).loadProblemTypeDataError(404,"没有找到数据!");
        }
    }

    //得到个人中心问题详情数据
    public void getProblemDetailsData(long detailsId) {
        ProblemDetailsBean mBean = mRequest.getProblemDetailsData(detailsId);
        if(mBean != null){
            ((ProblemDetailsView) mView).loadDetailsDataFinish(mBean);
        }else{
            ((ProblemDetailsView) mView).loadDetailsDataError(404,"多是啊啊");
        }
    }

    //保存反馈意见的数据
    public void saveFeedbackData(String edit) {
        boolean result = mRequest.saveFeedbackData(edit);
        if(result){
            ((SettingFeedbackView) mView).saveFeedbackFinish();
        }else{
            ((SettingFeedbackView) mView).saveFeedbackError(404,"都市里的");
        }
    }

    //得到问题的各项知识
    public void getProblemItemData(long problemId,int type) {
        List<KnowLedgeItemBean.KnowLedgeItem> mItem = mRequest.getProblemItemData(problemId,type);
        if(mItem != null && mItem.size() > 0){
            ((ProblemItemView) mView).loadProblemData(mItem);
        }else{
            ((ProblemItemView) mView).loadProblemDataError(404,"没有找到数据!");
        }
    }


    //得到个人中心天梯类型数据
    public void getLevelTypeData(Context context,long mineId) {
        LevelTypeDataBean mBean = mRequest.getLevelTypeData(context,mineId);
        if(mBean != null){
            ((LevelView) mView).loadLevelTypeDataFinish(mBean);
        }else{
            ((LevelView) mView).loadLevelTypeDataError(404,"没有找到数据!");
        }
    }
}
