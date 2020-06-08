package com.vargancys.learningassistant.module.mine.view;

import com.vargancys.learningassistant.model.mine.bean.KnowLedgeItemBean;

import java.util.List;

/**
 * @author Vagrancy
 * @date 2020/6/8
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 个人中心问题视图层
 */
public interface ProblemItemView {
    void loadProblemData(List<KnowLedgeItemBean.KnowLedgeItem> mItem);
    void loadProblemDataError(int error,String message);
}
