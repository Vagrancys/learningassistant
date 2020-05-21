package com.vargancys.learningassistant.module.mine.view;

import com.vargancys.learningassistant.db.mine.MineDataBean;

/**
 * @author Vagrancy
 * @date 2020/5/20
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 基础我的中心视图层
 */
public interface BaseMineView {
    void showMineDataFinish(MineDataBean bean);
    void showMineDataError(int error,String message);
}
