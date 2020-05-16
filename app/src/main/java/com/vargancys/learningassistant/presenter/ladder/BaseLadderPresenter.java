package com.vargancys.learningassistant.presenter.ladder;

import android.util.Log;

import com.vargancys.learningassistant.db.ladder.LadderCommentBean;
import com.vargancys.learningassistant.db.ladder.LadderCommentReplyBean;
import com.vargancys.learningassistant.db.ladder.LadderDataBean;
import com.vargancys.learningassistant.db.ladder.LadderDifficultyCommentBean;
import com.vargancys.learningassistant.db.ladder.LadderDifficultyDataBean;
import com.vargancys.learningassistant.db.ladder.LadderHelpBean;
import com.vargancys.learningassistant.db.ladder.LadderRankDataBean;
import com.vargancys.learningassistant.db.ladder.LadderTopicBean;
import com.vargancys.learningassistant.model.ladder.request.LadderRequest;
import com.vargancys.learningassistant.module.ladder.view.LadderHelpView;
import com.vargancys.learningassistant.module.ladder.view.LadderCommentReplyView;
import com.vargancys.learningassistant.module.ladder.view.LadderCommentView;
import com.vargancys.learningassistant.module.ladder.view.LadderCommunicationView;
import com.vargancys.learningassistant.module.ladder.view.LadderDifficultyDetailsView;
import com.vargancys.learningassistant.module.ladder.view.LadderDifficultyView;
import com.vargancys.learningassistant.module.ladder.view.LadderHelpDetailsView;
import com.vargancys.learningassistant.module.ladder.view.LadderView;
import com.vargancys.learningassistant.module.ladder.view.LadderZoneRankView;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2020/5/5
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description:
 */
public class BaseLadderPresenter {
    private Object mView;
    private LadderRequest mRequest;

    public BaseLadderPresenter(Object view){
        mView = view;
        mRequest = LadderRequest.getInstance();
    }

    public void getLadderAllTopicItem(int highest) {
        List<LadderTopicBean> mTopics = mRequest.getLadderAllTopicItem(highest);
        ((LadderView) mView).loadingFinish();
        if(mTopics != null && mTopics.size() > 0){
            ((LadderView) mView).getLadderTopicFinish(mTopics);
        }else{
            ((LadderView) mView).getLadderTopicError(404,"没有该数据!");
        }

    }

    public void getLadderData(long ladderId) {
        ((LadderView) mView).loadingLayout();
        LadderDataBean ladder = mRequest.getLadderData(ladderId);
        if(ladder != null){
            ((LadderView) mView).getLadderData(ladder);
        }else{
            long result = mRequest.insertLadderData();
            Log.e("测试","对是测试");
            ((LadderView) mView).loadingFinish();
            ((LadderView) mView).getLadderDataError(result);
        }
    }

    public void showPrepareLayout() {
        ((LadderView) mView).showPrepareLayout();
    }

    public void showLadderLayout() {
        ((LadderView) mView).showLadderLayout();
    }

    public void TrailAnswer() {
        ((LadderView) mView).TrailAnswer();
    }

    public boolean isAnswerEmpty() {
        return ((LadderView) mView).isAnswerEmpty();
    }

    public void showFailLayout() {
        ((LadderView) mView).showFailLayout();
    }

    public void showWinLayout() {
        ((LadderView) mView).showWinLayout();
    }

    //保持天梯数据
    public void saveLadderData(long ladderId) {
        mRequest.saveLadderData(ladderId);
    }

    //发布评论
    public void saveCommentData(int current,String comment) {
        boolean result = mRequest.saveCommentData(current,comment);
        if(result){
            ((LadderCommunicationView) mView).sendCommentFinish();
        }else{
            ((LadderCommunicationView) mView).sendCommentError(404,"评论发布失败!");
        }
    }

    //刷新评论页面
    public void refreshCommentLayout() {
        ((LadderCommunicationView) mView).refreshCommentLayout();
    }

    //刷新评论数据
    public void refreshCommentData(int mCurrent) {
        List<LadderCommentBean> mBean = mRequest.refreshCommentData(mCurrent);
        if(mBean!=null && mBean.size()>0){
            ((LadderCommentView) mView).showCommentDataFinish(mBean);
        }else{
            ((LadderCommentView) mView).showCommentDataError(404,"没有查找到数据!");
        }
    }

    //更新赞数据
    public void updatePraiseData(Long commentId, boolean state) {
        mRequest.updatePraiseData(commentId,state);
    }

    //更新踩数据
    public void updateStepData(Long commentId,boolean state){
        mRequest.updateStepData(commentId,state);
    }

    //获取评论回复的头部评论
    public void getLadderCommentData(long commentId) {
        LadderCommentBean mBean = mRequest.getLadderCommentData(commentId);
        if(mBean != null){
            ((LadderCommentReplyView) mView).showCommentDataFinish(mBean);
        }else{
            ((LadderCommentReplyView) mView).showCommentDataError(404,"没有该数据!");
        }
    }

    //保持回复评论
    public void saveCommentReplyData(long comment,String content) {
        boolean result = mRequest.saveCommentReplyData(comment,content);
        if(result){
            ((LadderCommentReplyView) mView).showSaveCommentDataFinish();
        }else{
            ((LadderCommentReplyView) mView).showSaveCommentDataError(404,"发表回复失败!");
        }
    }

    //得到该id下的所有回复
    public void getCommentReplyAllData(long commentId) {
        List<LadderCommentReplyBean> mBean = mRequest.getLadderCommentReplyAllData(commentId);
        if(mBean != null && mBean.size()>0){
            ((LadderCommentReplyView) mView).showCommentAllDataFinish(mBean);
        }else{
            ((LadderCommentReplyView) mView).showCommentAllDataError(404,"没有找到该数据!");
        }
    }

    //保存难度区评论数据
    public void saveDifficultData(int selectDifficulty, String data) {
        boolean result = mRequest.saveDifficultyData(selectDifficulty,data);
        if(result){
            ((LadderDifficultyView) mView).showDifficultySendFinish();
        }else{
            ((LadderDifficultyView) mView).showDifficultySendError(404,"没有保存成功!");
        }
    }


    public void showSelectType() {
        ((LadderDifficultyView) mView).showSelectType();
    }

    //得到难度区详情数据
    public void getLadderDetailsData(int difficultyType) {
        LadderDifficultyDataBean mBean = mRequest.getLadderDetailsData(difficultyType);
        if(mBean != null){
            ((LadderDifficultyDetailsView) mView).showDifficultyDetailsFinish(mBean);

        }else{
            ((LadderDifficultyDetailsView) mView).showDifficultyDetailsError(404,"没有该详情数据!");
        }
    }

    //得到难度区的所有评论
    public void getLadderCommentAllData(int difficultyType) {
        List<LadderDifficultyCommentBean> mBean = mRequest.getLadderCommentAllData(difficultyType);
        if(mBean != null && mBean.size() > 0){
            ((LadderDifficultyDetailsView) mView).showDifficultyCommentFinish(mBean);
        }else{
            ((LadderDifficultyDetailsView) mView).showDifficultyCommentError(404,"没有该数据!");
        }
    }

    //获取所有的难度区所有的数据
    public void getLadderHelpAllData() {
        List<LadderHelpBean> mBean = mRequest.getLadderHelpAllData();
        if(mBean != null && mBean.size() > 0){
            ((LadderHelpView) mView).showAllDataFinish(mBean);
        }else{
            ((LadderHelpView) mView).showAllDataError(404,"不错啊");
        }
    }

    //获取帮助详情页的数据
    public void getLadderHelpDetailsData(long helpId) {
        LadderHelpBean mBean = mRequest.getLadderHelpDetailsData(helpId);
        if(mBean != null){
            ((LadderHelpDetailsView) mView).showHelpDetailsFinish(mBean);
        }else{
            ((LadderHelpDetailsView) mView).showHelpDetailsError(404,"不能够执行这个行动");
        }

    }

    //获取各分区排行榜数据
    public void getLadderZoneRankData(int zoneId) {
        List<LadderRankDataBean> mBean = mRequest.getLadderZoneRankData(zoneId);
        if(mBean != null && mBean.size() > 0){
            ((LadderZoneRankView) mView).showZoneRankDataFinish(mBean);
        }else{
            ((LadderZoneRankView) mView).showZoneRankDataError(404,"没有找到数据!");
        }
    }
}
