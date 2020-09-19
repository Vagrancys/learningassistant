package com.vargancys.learningassistant.module.mine.view;

import com.vargancys.learningassistant.bean.ladder.LadderDataBean;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2020/5/28
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 个人中心天梯各项数据
 */
public interface ChallengeItemView {
    void loadItemDataFinish(List<LadderDataBean> mBean);
    void loadItemDataError(int error,String message);
}
