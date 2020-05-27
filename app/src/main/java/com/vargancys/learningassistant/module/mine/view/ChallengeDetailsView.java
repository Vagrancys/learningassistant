package com.vargancys.learningassistant.module.mine.view;

import com.vargancys.learningassistant.db.ladder.LadderDataBean;

/**
 * @author Vagrancy
 * @date 2020/5/27
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 天梯详情页面视图层
 */
public interface ChallengeDetailsView {
    void loadChallengeDetailsData(LadderDataBean mBean);
    void loadChallengeDetailsDataError(int error,String message);
}
