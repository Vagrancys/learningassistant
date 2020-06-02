package com.vargancys.learningassistant.module.mine.view;

import com.vargancys.learningassistant.model.mine.bean.ProblemDetailsBean;

/**
 * @author Vagrancy
 * @date 2020/6/2
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 个人中心问题详情视图层
 */
public interface ProblemDetailsView {
    void loadDetailsDataFinish(ProblemDetailsBean mBean);
    void loadDetailsDataError(int error,String message);
}
