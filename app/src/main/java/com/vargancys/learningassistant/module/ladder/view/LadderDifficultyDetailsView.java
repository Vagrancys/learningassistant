package com.vargancys.learningassistant.module.ladder.view;

import com.vargancys.learningassistant.bean.ladder.LadderDifficultyCommentBean;
import com.vargancys.learningassistant.bean.ladder.LadderDifficultyDataBean;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2020/5/11
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 难度区详情的视图层
 */
public interface LadderDifficultyDetailsView {
    void showDifficultyDetailsFinish(LadderDifficultyDataBean mBean);

    void showDifficultyDetailsError(int error, String message);

    void showDifficultyCommentFinish(List<LadderDifficultyCommentBean> mBean);

    void showDifficultyCommentError(int error, String message);
}
