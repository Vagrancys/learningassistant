package com.vargancys.learningassistant.module.ladder.view;

/**
 * @author Vagrancy
 * @date 2020/5/8
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 交流中心的视图层
 */
public interface LadderCommunicationView {
    void sendCommentFinish();
    void sendCommentError(int error,String message);
    void refreshCommentLayout();
}
