package com.vargancys.learningassistant.module.game.view;

import com.vargancys.learningassistant.bean.game.GameContent;
import com.vargancys.learningassistant.bean.overview.OverViewListItem;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/10
 * version:1.0
 */
public interface GameView extends BaseGameView{
    void showGameContentFinish(GameContent gameContent);

    void showGameBeanFinish(List<OverViewListItem> gameListBean);

    void showGameBeanError(int error, String message);

    void showGameContentError(int error, String message);
}
