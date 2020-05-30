package com.vargancys.learningassistant.module.mine.view;

import com.vargancys.learningassistant.db.mine.MineDataBean;
import com.vargancys.learningassistant.model.mine.bean.LevelTypeDataBean;

/**
 * @author Vagrancy
 * @date 2020/5/30
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 个人中心等级模块视图层
 */
public interface LevelView {
    void loadLevelDataError(int error,String message);
    void loadLevelDataFinish(MineDataBean mBean);
    void loadLevelTypeDataError(int error,String message);
    void loadLevelTypeDataFinish(LevelTypeDataBean mBean);
}
