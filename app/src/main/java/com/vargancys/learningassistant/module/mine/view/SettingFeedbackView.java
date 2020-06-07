package com.vargancys.learningassistant.module.mine.view;

/**
 * @author Vagrancy
 * @date 2020/6/7
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description:意见反馈的视图层
 */
public interface SettingFeedbackView {
    void saveFeedbackFinish();
    void saveFeedbackError(int error,String message);
}
