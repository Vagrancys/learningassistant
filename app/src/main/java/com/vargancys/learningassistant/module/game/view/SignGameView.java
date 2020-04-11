package com.vargancys.learningassistant.module.game.view;

import com.vargancys.learningassistant.db.game.GameSignContent;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/11
 * version:1.0
 */
public interface SignGameView extends BaseGameView{
    void showGameSignAllData(List<GameSignContent> mSign);
    void showGameSignAllError(int error,String message);
}
