package com.vargancys.learningassistant.module.ladder.view;

import com.vargancys.learningassistant.db.ladder.LadderDataBean;
import com.vargancys.learningassistant.db.ladder.LadderResultBean;

/**
 * @author Vagrancy
 * @date 2020/5/20
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 天梯成就视图层
 */
public interface LadderResultView {
    void showResultDataFinish(LadderDataBean mBean);
    void showResultUseFinish(LadderResultBean mBean);
    void showResultDataError(int error,String message);
    void showResultUseError(int error,String message);
}
