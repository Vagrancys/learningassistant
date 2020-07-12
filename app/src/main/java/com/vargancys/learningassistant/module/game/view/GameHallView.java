package com.vargancys.learningassistant.module.game.view;

import com.vargancys.learningassistant.model.game.bean.GameHallBean;
import com.vargancys.learningassistant.model.overview.bean.OverViewHallBean;

/**
 * @author Vagrancy
 * @date 2020/7/6
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 关卡大厅视图层
 */
public interface GameHallView {
    void getGameHallDataSuccess(GameHallBean mBean);
    void getGameHallDataFail(int error, String message);
    void selectHallDataSuccess();
    void selectHallDataFail(int error,String message);
}
