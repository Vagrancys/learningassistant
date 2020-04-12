package com.vargancys.learningassistant.presenter.game;

import com.vargancys.learningassistant.db.common.KnowListBean;
import com.vargancys.learningassistant.db.game.GameContent;
import com.vargancys.learningassistant.db.game.GameSignContent;
import com.vargancys.learningassistant.db.overview.OverViewListContent;
import com.vargancys.learningassistant.db.overview.OverViewListItem;
import com.vargancys.learningassistant.model.game.request.BaseGameRequest;
import com.vargancys.learningassistant.module.game.view.BaseGameView;
import com.vargancys.learningassistant.module.game.view.GameView;
import com.vargancys.learningassistant.module.game.view.SelectGameView;
import com.vargancys.learningassistant.module.game.view.SignAddView;
import com.vargancys.learningassistant.module.game.view.SignGameView;

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

    public void getGameSelectListData() {
        List<OverViewListContent> mContent = mRequest.getGameSelectListData();
        if(mContent != null && mContent.size()> 0){
            ((SelectGameView) mView).showGameSelectAllData(mContent);
        }else{
            ((SelectGameView) mView).showGameSelectAllError(404,"不错的消息!");
        }
    }

    public void saveGameSelectData(long overviewId) {
        long result = mRequest.isOverViewEmpty(overviewId);
        if(result != 0){
            ((SelectGameView) mView).isGameContentEmptyFinish(result);
        }else{
            OverViewListContent mContent = mRequest.getOverViewListContentData(overviewId);
            ((SelectGameView) mView).tidyGameContent(mContent);
        }

    }

    public void saveGameContentData(GameContent gameContent) {
        long result = mRequest.saveGameContentData(gameContent);
        if(result != 0){
            ((SelectGameView) mView).saveSelectGameFinish(result);
        }else{
            ((SelectGameView) mView).saveSelectGameError(501,"没有保存成功");
        }
    }

    public void getGameSignAllData() {
        List<GameSignContent> mSigns = mRequest.getGameSignAllData();
        if(mSigns != null && mSigns.size() > 0){
            ((SignGameView) mView).showGameSignAllData(mSigns);
        }else{
            ((SignGameView) mView).showGameSignAllError(404,"不错的打卡");
        }
    }

    public void getGameContentData(long gameId) {
        GameContent mContent = mRequest.getGameContentData(gameId);
        if(mContent != null){
            ((SignAddView) mView).initSignAddDataFinish(mContent);
        }else{
            ((SignAddView) mView).initSignAddDataError(404,"不需要打卡!");
        }
    }

    public void isSignDataEmpty() {
        boolean result = ((SignAddView) mView).isSignDataEmpty();
        if(result){
            ((SignAddView) mView).addSignData();
        }else{
            ((SignAddView) mView).isSignDataEmptyError(404,"错误的事情!");
        }
    }

    public void saveSignData(GameSignContent content) {
        boolean result = mRequest.saveSignData(content);
        if(result){
            ((SignAddView) mView).saveSignDataFinish();
        }else{
            ((SignAddView) mView).saveSignDataError(404,"没有保存成功!");
        }
    }
}
