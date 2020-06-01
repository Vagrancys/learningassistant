package com.vargancys.learningassistant.module.mine.view;

import com.vargancys.learningassistant.db.mine.MineDataBean;
import com.vargancys.learningassistant.model.mine.bean.ProblemTypeDataBean;

/**
 * @author Vagrancy
 * @date 2020/6/1
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 个人中心问题视图层
 */
public interface ProblemView {
    void loadProblemDataFinish(MineDataBean mine);
    void loadProblemDataError(int error,String message);
    void loadProblemTypeDataFinish(ProblemTypeDataBean mBean);
    void loadProblemTypeDataError(int error,String message);
}
