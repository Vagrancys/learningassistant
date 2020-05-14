package com.vargancys.learningassistant.module.ladder.view;

import com.vargancys.learningassistant.db.ladder.LadderRankDataBean;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2020/5/14
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 分区排行视图层
 */
public interface LadderZoneRankView {
    void showZoneRankDataFinish(List<LadderRankDataBean> mBean);
    void showZoneRankDataError(int error ,String message);
}
