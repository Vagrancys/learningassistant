package com.vargancys.learningassistant.presenter.game;

import android.os.Handler;

import com.vargancys.learningassistant.db.common.KnowListBean;
import com.vargancys.learningassistant.db.game.GameAnswerSheetBean;
import com.vargancys.learningassistant.db.game.GameContent;
import com.vargancys.learningassistant.db.game.GameFillItem;
import com.vargancys.learningassistant.db.game.GameMultipleItem;
import com.vargancys.learningassistant.db.game.GameRadioItem;
import com.vargancys.learningassistant.db.game.GameSignContent;
import com.vargancys.learningassistant.db.game.GameStartContent;
import com.vargancys.learningassistant.db.game.GameSubjectContent;
import com.vargancys.learningassistant.db.game.GameSubjectItem;
import com.vargancys.learningassistant.db.game.GameSubjectiveItem;
import com.vargancys.learningassistant.db.overview.OverViewListContent;
import com.vargancys.learningassistant.db.overview.OverViewListItem;
import com.vargancys.learningassistant.model.game.request.BaseGameRequest;
import com.vargancys.learningassistant.module.game.view.AddGameView;
import com.vargancys.learningassistant.module.game.view.AnswerSheetView;
import com.vargancys.learningassistant.module.game.view.BaseGameView;
import com.vargancys.learningassistant.module.game.view.GameView;
import com.vargancys.learningassistant.module.game.view.SelectGameView;
import com.vargancys.learningassistant.module.game.view.SignAddView;
import com.vargancys.learningassistant.module.game.view.SignGameView;
import com.vargancys.learningassistant.module.game.view.StartGameView;
import com.vargancys.learningassistant.module.game.view.SubjectShowView;

import java.util.ArrayList;
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
        if(!result){
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

    public void getSubjectData(long knowId) {
        GameSubjectContent mContent = mRequest.getSubjectContentData(knowId);
        if(mContent != null){
            ((SubjectShowView) mView).showSubjectContentFinish(mContent);
            List<GameSubjectItem> mItems = mRequest.getSubjectItemData(mContent.getId());
            if(mItems != null &&mItems.size() > 0){
                ((SubjectShowView) mView).showSubjectItemFinish(mItems);
            }else{
                ((SubjectShowView) mView).showSubjectItemError(404,"没有找到问题!");
            }
        }else{
            ((SubjectShowView) mView).showSubjectContentError(404,"没有该数据项啊");
        }
    }

    public void isSubjectDataEmpty() {
        boolean result = ((AddGameView) mView).isSubjectDataEmpty();
        if(!result){
            ((AddGameView) mView).tidySubjectData();
        }else{
            ((AddGameView) mView).isSubjectDataError(404,"请填写完成在保存!");
        }
    }

    public void saveSubjectItemData(GameSubjectItem mItem) {
        long result = mRequest.saveSubjectItemData(mItem);
        showView(result);
    }

    public long saveGameRadioItemData(GameRadioItem mRadio,long subjectId,long gameId) {
        return mRequest.saveGameRadioItemData(mRadio,subjectId,gameId);
    }

    public long saveGameMultipleItemData(GameMultipleItem mMultiple,long subjectId,long gameId) {
        return mRequest.saveGameMultipleItemData(mMultiple,subjectId,gameId);
    }

    public long saveGameFillItemData(GameFillItem mFill,long subjectId,long gameId) {
        return mRequest.saveGameFillItemData(mFill,subjectId,gameId);
    }


    public long saveGameSubjectiveItemData(GameSubjectiveItem mSubjective,long subjectId,long gameId) {
        return mRequest.saveGameSubjectiveItemData(mSubjective,subjectId,gameId);
    }

    private void showView(long result){
        if(result != 0){
            ((AddGameView) mView).showAddDataFinish();
        }else{
            ((AddGameView) mView).showAddDataError(404,"不错的关系啊!");
        }
    }

    public void updateAnswerSheetData(ArrayList<GameAnswerSheetBean> mBean) {
        boolean result = mRequest.updateAnswerSheetData(mBean);
        if(result){
            ((AnswerSheetView) mView).updateDataFinish();
        }else{
            ((AnswerSheetView) mView).updateDataError(401,"更新失败!");
        }
    }

    public interface TidyAllData{
        void showFinish(List<GameStartContent> mContent);
        void showError(int error,String message);
    }

    public void getGameStartAllData(final Handler handler,long gameId) {
        ((StartGameView) mView).showRefreshLayout();
        TidyAllData tidyAllData = new TidyAllData() {
            @Override
            public void showFinish(final List<GameStartContent> mContent) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        ((StartGameView) mView).showTidyAllDataFinish(mContent);
                    }
                });

            }

            @Override
            public void showError(int error, String message) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        ((StartGameView) mView).showTidyAllDataError(404,"没有处理好!");
                    }
                });
            }
        };
        mRequest.getGameStartAllData(gameId,tidyAllData);
    }
}
