package com.vargancys.learningassistant.module.ladder.view;

import com.vargancys.learningassistant.db.ladder.LadderDataBean;
import com.vargancys.learningassistant.db.ladder.LadderTopicBean;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2020/5/5
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 天梯的视图层
 */
public interface LadderView {
    void loadingLayout();
    void loadingFinish();
    void getLadderData(LadderDataBean ladder);
    void getLadderTopicFinish(List<LadderTopicBean> topic);
    void getLadderTopicError(int error,String message);
    void showPrepareLayout();
    void showLadderLayout();
    void TrailAnswer();
    boolean isAnswerEmpty();
    void showFailLayout();
    void showWinLayout();
    void getLadderDataError(long result);
}
