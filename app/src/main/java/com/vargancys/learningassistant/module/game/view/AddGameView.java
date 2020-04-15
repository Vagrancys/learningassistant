package com.vargancys.learningassistant.module.game.view;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/13
 * version:1.0
 */
public interface AddGameView extends BaseGameView{
    boolean isSubjectDataEmpty();
    void tidySubjectData();
    void isSubjectDataError(int error,String message);
    void showAddDataFinish();
    void showAddDataError(int error,String message);
}
