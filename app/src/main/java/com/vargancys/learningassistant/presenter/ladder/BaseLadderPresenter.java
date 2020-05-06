package com.vargancys.learningassistant.presenter.ladder;

import com.vargancys.learningassistant.db.ladder.LadderDataBean;
import com.vargancys.learningassistant.db.ladder.LadderTopicBean;
import com.vargancys.learningassistant.model.ladder.LadderRequest;
import com.vargancys.learningassistant.module.ladder.view.LadderView;

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


    public void getLadderAllTopicItem() {
        List<LadderTopicBean> mTopics = mRequest.getLadderAllTopicItem();
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
}
