package com.vargancys.learningassistant.module.ladder.view;

import com.vargancys.learningassistant.bean.ladder.LadderHelpBean;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2020/5/11
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 评论详情视图层
 */
public interface LadderHelpView {

    void showAllDataFinish(List<LadderHelpBean> mBean);

    void showAllDataError(int error, String message);
}
