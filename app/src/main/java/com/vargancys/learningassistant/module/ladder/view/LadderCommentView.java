package com.vargancys.learningassistant.module.ladder.view;

import com.vargancys.learningassistant.bean.ladder.LadderCommentBean;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2020/5/8
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 交流中心各区的视图层
 */
public interface LadderCommentView {
    void showCommentDataFinish(List<LadderCommentBean> bean);

    void showCommentDataError(int error, String message);
}
