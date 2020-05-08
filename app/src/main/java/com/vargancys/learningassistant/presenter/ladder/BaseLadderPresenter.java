package com.vargancys.learningassistant.presenter.ladder;

import com.vargancys.learningassistant.db.ladder.LadderCommentBean;
import com.vargancys.learningassistant.db.ladder.LadderDataBean;
import com.vargancys.learningassistant.db.ladder.LadderTopicBean;
import com.vargancys.learningassistant.model.ladder.LadderRequest;
import com.vargancys.learningassistant.module.ladder.view.LadderCommentView;
import com.vargancys.learningassistant.module.ladder.view.LadderCommunicationView;
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

    public void saveLadderData(long ladderId) {
        mRequest.saveLadderData(ladderId);
    }

    public void saveCommentData(int current,String comment) {
        boolean result = mRequest.saveCommentData(current,comment);
        if(result){
            ((LadderCommunicationView) mView).sendCommentFinish();
        }else{
            ((LadderCommunicationView) mView).sendCommentError(404,"评论发布失败!");
        }
    }

    public void refreshCommentLayout() {
        ((LadderCommunicationView) mView).refreshCommentLayout();
    }

    public void refreshCommentData(int mCurrent) {
        List<LadderCommentBean> mBean = mRequest.refreshCommentData(mCurrent);
        if(mBean!=null && mBean.size()>0){
            ((LadderCommentView) mView).showCommentDataFinish(mBean);
        }else{
            ((LadderCommentView) mView).showCommentDataError(404,"没有查找到数据!");
        }
    }
}
