package com.vargancys.learningassistant.module.game.view;

import com.vargancys.learningassistant.model.game.bean.GameHallRankBean;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2020/7/13
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 基础关卡大厅视图层
 */
public interface BaseGameHallView {
    void getHallDataSuccess(List<GameHallRankBean> mBean);
    void getHallDataFail(int error,String message);
    void insertHallDataSuccess();
    void insertHallDataFail(int error,String message);
}
