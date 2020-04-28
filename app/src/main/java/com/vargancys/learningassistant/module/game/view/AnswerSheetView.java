package com.vargancys.learningassistant.module.game.view;

/**
 * @author Vagrancy
 * @date 2020/4/28
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description:
 */
public interface AnswerSheetView extends BaseGameView{
    void updateDataFinish();
    void updateDataError(int error,String message);
}
