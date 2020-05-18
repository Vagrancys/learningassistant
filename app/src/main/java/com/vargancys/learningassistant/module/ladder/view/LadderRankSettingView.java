package com.vargancys.learningassistant.module.ladder.view;

import java.util.ArrayList;

/**
 * @author Vagrancy
 * @date 2020/5/18
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 天梯排行配置视图层
 */
public interface LadderRankSettingView {
    void showRankSettingFinish(ArrayList<Integer> bean);
    void showRankSettingError();
}
