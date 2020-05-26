package com.vargancys.learningassistant.module.mine.view;

import com.vargancys.learningassistant.db.mine.MineDataBean;
import com.vargancys.learningassistant.model.mine.bean.ChallengeTypeDataBean;

/**
 * @author Vagrancy
 * @date 2020/5/26
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 个人中心挑战视图层
 */
public interface ChallengeView {
    void loadChallengeDataFinish(MineDataBean mBean);
    void loadChallengeDataError(int error,String message);
    void loadChallengeTypeDataFinish(ChallengeTypeDataBean mBean);
    void loadChallengeTypeDataError(int error,String message);
}
