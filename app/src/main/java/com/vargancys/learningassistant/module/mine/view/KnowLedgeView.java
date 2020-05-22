package com.vargancys.learningassistant.module.mine.view;

import com.vargancys.learningassistant.db.mine.MineDataBean;
import com.vargancys.learningassistant.model.mine.bean.KnowLedgeTypeDataBean;

/**
 * @author Vagrancy
 * @date 2020/5/22
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description:个人中心知识模块视图层
 */
public interface KnowLedgeView {
    void showKnowLedgeDataFinish(MineDataBean mine);
    void showKnowLedgeDataError(int error,String message);
    void showKnowLedgeTypeDataFinish(KnowLedgeTypeDataBean mBean);
    void showKnowLedgeTypeDataError(int error,String message);
}
