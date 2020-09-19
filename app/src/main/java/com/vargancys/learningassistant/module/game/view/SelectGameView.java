package com.vargancys.learningassistant.module.game.view;

import com.vargancys.learningassistant.bean.overview.OverViewListContent;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/10
 * version:1.0
 */
public interface SelectGameView extends BaseGameView{
    void showGameSelectAllData(List<OverViewListContent> contents);
    void showGameSelectAllError(int error,String message);
    void tidyGameContent(OverViewListContent mContent);

    void saveSelectGameFinish(long gameId);
    void saveSelectGameError(int error,String message);

    void isGameContentEmptyFinish(long gameId);
}
