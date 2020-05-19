package com.vargancys.learningassistant.module.ladder.view;

import com.vargancys.learningassistant.db.ladder.LadderRankSettingBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vagrancy
 * @date 2020/5/18
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 天梯排行配置视图层
 */
public interface LadderRankSettingView {
    void showRankSettingFinish(List<LadderRankSettingBean> bean);
    void showRankSettingError();
    void saveRankSettingFinish();
    void saveRankSettingError(int error,String message);
}
