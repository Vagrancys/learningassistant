package com.vargancys.learningassistant.presenter.ladder;

import com.vargancys.learningassistant.db.ladder.LadderCommentBean;
import com.vargancys.learningassistant.db.ladder.LadderCommentReplyBean;
import com.vargancys.learningassistant.db.ladder.LadderDataBean;
import com.vargancys.learningassistant.db.ladder.LadderTopicBean;
import com.vargancys.learningassistant.model.ladder.LadderRequest;
import com.vargancys.learningassistant.module.ladder.view.LadderCommentReplyView;
import com.vargancys.learningassistant.module.ladder.view.LadderCommentView;
import com.vargancys.learningassistant.module.ladder.view.LadderCommunicationView;
import com.vargancys.learningassistant.module.ladder.view.LadderDifficultyView;
import com.vargancys.learningassistant.module.ladder.view.LadderView;

import java.util.ArrayList;
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
}
