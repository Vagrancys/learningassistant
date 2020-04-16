package com.vargancys.learningassistant.module.game.view;

import com.vargancys.learningassistant.db.game.GameStartContent;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/16
 * version:1.0
 */
public interface StartGameView extends BaseGameView{
    void showTidyAllDataFinish(List<GameStartContent> contents);
    void showTidyAllDataError(int error,String message);
}
