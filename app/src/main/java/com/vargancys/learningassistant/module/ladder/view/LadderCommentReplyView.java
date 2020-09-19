package com.vargancys.learningassistant.module.ladder.view;

import com.vargancys.learningassistant.bean.ladder.LadderCommentBean;
import com.vargancys.learningassistant.bean.ladder.LadderCommentReplyBean;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2020/5/9
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 评论回复的视图层
 */
public interface LadderCommentReplyView {
    void showCommentDataFinish(LadderCommentBean mBean);
    void showCommentDataError(int error,String message);
    void showCommentAllDataFinish(List<LadderCommentReplyBean> mBeans);
    void showCommentAllDataError(int error,String message);
    void showSaveCommentDataFinish();
    void showSaveCommentDataError(int error,String message);
}
