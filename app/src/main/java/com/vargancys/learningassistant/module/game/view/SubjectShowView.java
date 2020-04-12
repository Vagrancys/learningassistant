package com.vargancys.learningassistant.module.game.view;

import com.vargancys.learningassistant.db.game.GameSubjectContent;
import com.vargancys.learningassistant.db.game.GameSubjectItem;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/12
 * version:1.0
 */
public interface SubjectShowView extends BaseGameView{
    void showSubjectContentFinish(GameSubjectContent mContent);
    void showSubjectContentError(int error,String message);
    void showSubjectItemFinish(List<GameSubjectItem> mItems);
    void showSubjectItemError(int error,String message);
}
