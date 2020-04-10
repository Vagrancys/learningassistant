package com.vargancys.learningassistant.presenter.game;

import com.vargancys.learningassistant.db.common.KnowListBean;
import com.vargancys.learningassistant.db.game.GameContent;
import com.vargancys.learningassistant.db.overview.OverViewListItem;
import com.vargancys.learningassistant.model.game.request.BaseGameRequest;
import com.vargancys.learningassistant.module.game.view.BaseGameView;
import com.vargancys.learningassistant.module.game.view.GameView;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/06
 * version:1.0
 */
public class BaseGamePresenter {
    private BaseGameView mView;
    private BaseGameRequest mRequest;

    public BaseGamePresenter(BaseGameView view){
        mView = view;
        mRequest = BaseGameRequest.getInstance();
    }


    public void getGameListData(long gameId) {
        GameContent gameContent = mRequest.getGameListData(gameId);
        if(gameContent !=null){
            ((GameView) mView).showGameContentFinish(gameContent);
            List<OverViewListItem> gameListBean = mRequest.getGameListBean(gameContent.getOverviewId());
            if(gameListBean !=null && gameListBean.size()>0){
                ((GameView) mView).showGameBeanFinish(gameListBean);
            }else{
                ((GameView) mView).showGameBeanError(404,"没有找到详细的关卡数据");
            }
        }else{
            ((GameView) mView).showGameContentError(404,"没有找到关卡数据");
        }
    }
}
