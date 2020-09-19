package com.vargancys.learningassistant.module.ladder.view;

import com.vargancys.learningassistant.bean.ladder.LadderHelpBean;

/**
 * @author Vagrancy
 * @date 2020/5/12
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 帮助详情页面视图层
 */
public interface LadderHelpDetailsView {
    void showHelpDetailsFinish(LadderHelpBean mBean);
    void showHelpDetailsError(int error,String message);
}
