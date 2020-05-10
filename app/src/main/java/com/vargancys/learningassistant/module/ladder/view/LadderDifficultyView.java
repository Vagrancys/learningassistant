package com.vargancys.learningassistant.module.ladder.view;

/**
 * @author Vagrancy
 * @date 2020/5/10
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 天梯难度区视图层
 */
public interface LadderDifficultyView {

    void showDifficultySendError(int error, String message);
    void showDifficultySendFinish();

    void showSelectType();
}
