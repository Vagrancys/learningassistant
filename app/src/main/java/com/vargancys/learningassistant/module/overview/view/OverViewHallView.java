package com.vargancys.learningassistant.module.overview.view;

import com.vargancys.learningassistant.model.overview.bean.OverViewHallBean;

/**
 * @author Vagrancy
 * @date 2020/6/27
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识体系大厅视图层
 */
public interface OverViewHallView {
    void getOverViewHallDataSuccess(OverViewHallBean mBean);
    void getOverViewHallDataFail(int error, String message);
    void selectHallDataSuccess();
    void selectHallDataFail(int error,String message);
}
