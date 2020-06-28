package com.vargancys.learningassistant.module.overview.view;

import com.vargancys.learningassistant.model.overview.bean.OverViewHallRankBean;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2020/6/28
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 知识体系大厅质量视图层
 */
public interface BaseHallView {
    void getHallDataSuccess(List<OverViewHallRankBean> mBean);
    void getHallDataFail(int error,String message);
}
