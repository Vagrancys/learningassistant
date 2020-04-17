package com.vargancys.learningassistant.model.game.request;

import android.util.Log;

import com.vagrancys.learningassistant.db.DaoSession;
import com.vagrancys.learningassistant.db.GameContentDao;
import com.vagrancys.learningassistant.db.GameFillItemDao;
import com.vagrancys.learningassistant.db.GameMultipleItemDao;
import com.vagrancys.learningassistant.db.GameRadioItemDao;
import com.vagrancys.learningassistant.db.GameSignContentDao;
import com.vagrancys.learningassistant.db.GameSubjectContentDao;
import com.vagrancys.learningassistant.db.GameSubjectItemDao;
import com.vagrancys.learningassistant.db.GameSubjectiveItemDao;
import com.vagrancys.learningassistant.db.OverViewListContentDao;
import com.vagrancys.learningassistant.db.OverViewListItemDao;
import com.vargancys.learningassistant.base.BaseApplication;
import com.vargancys.learningassistant.db.game.GameConfigUtils;
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
import com.vargancys.learningassistant.presenter.game.BaseGamePresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionStage;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/10
 * version:1.0
 */
public class BaseGameRequest {
    private static String TAG = "BaseGameRequest";
    private static BaseGameRequest mRequest;
    private DaoSession mDaoSession;
    private GameContentDao mGameContentDao;
    private OverViewListItemDao mListItemDao;
    private OverViewListContentDao mListContentDao;
    private GameSignContentDao mSignContentDao;
    private GameSubjectContentDao mSubjectContentDao;
    private GameSubjectItemDao mSubjectItemDao;
    private GameRadioItemDao mRadioItemDao;
    private GameMultipleItemDao mMultipleItemDao;
    private GameFillItemDao mFillItemDao;
    private GameSubjectiveItemDao mSubjectiveItemDao;
    private BaseGameRequest(){
        mDaoSession = BaseApplication.getInstance().getDaoSession();
        mGameContentDao = mDaoSession.getGameContentDao();
        mListItemDao = mDaoSession.getOverViewListItemDao();
        mListContentDao = mDaoSession.getOverViewListContentDao();
        mSignContentDao = mDaoSession.getGameSignContentDao();
        mSubjectContentDao = mDaoSession.getGameSubjectContentDao();
        mSubjectItemDao = mDaoSession.getGameSubjectItemDao();
        mRadioItemDao = mDaoSession.getGameRadioItemDao();
        mMultipleItemDao = mDaoSession.getGameMultipleItemDao();
        mFillItemDao = mDaoSession.getGameFillItemDao();
        mSubjectiveItemDao = mDaoSession.getGameSubjectiveItemDao();
    }

    public static BaseGameRequest getInstance(){
        if(mRequest == null){
            synchronized (BaseGameRequest.class){
                if(mRequest == null){
                    mRequest = new BaseGameRequest();
                }
            }
        }
        return mRequest;
    }

    public GameContent getGameListData(long gameId) {
        return mGameContentDao.load(gameId);
    }

    public List<OverViewListItem> getGameListBean(long overviewId) {
        return mListItemDao.queryBuilder().where(OverViewListItemDao.Properties.ContentId.eq(overviewId)).list();
    }

    public List<OverViewListContent> getGameSelectListData() {
        return mListContentDao.loadAll();
    }

    public long saveGameContentData(GameContent gameContent) {
        return mGameContentDao.insert(gameContent);
    }

    //判断关卡是否已经创建了 有返回true 没有返回false
    public long isOverViewEmpty(long overviewId) {
        GameContent gameContent =mGameContentDao.queryBuilder().where(GameContentDao.Properties.OverviewId.eq(overviewId)).unique();
        if(gameContent !=null){
            return gameContent.getId();
        }else{
            return 0;
        }
    }

    public OverViewListContent getOverViewListContentData(long overviewId) {
        return mListContentDao.load(overviewId);
    }

    public List<GameSignContent> getGameSignAllData() {
        return mSignContentDao.loadAll();
    }

    public GameContent getGameContentData(long gameId) {
        return mGameContentDao.load(gameId);
    }

    public boolean saveSignData(GameSignContent content) {
        long id =mSignContentDao.insert(content);
        if(id != 0){
            return true;
        }else{
            return false;
        }
    }

    public GameSubjectContent getSubjectContentData(long knowId) {
        return mSubjectContentDao.queryBuilder().where(GameSubjectContentDao.Properties.KnowId.eq(knowId)).unique();
    }


    public List<GameSubjectItem> getSubjectItemData(Long id) {
        return mSubjectItemDao.queryBuilder().where(GameSubjectItemDao.Properties.SubjectId.eq(id)).list();
    }

    public long saveSubjectItemData(GameSubjectItem mItem) {
        return mSubjectItemDao.insert(mItem);
    }

    public long saveGameRadioItemData(GameRadioItem mRadio,long subjectId) {

        updateContent(subjectId);
        return mRadioItemDao.insert(mRadio);
    }

    private void updateContent(long subjectId) {
        GameContent gameContent = mGameContentDao.load(subjectId);
        gameContent.setSubject(gameContent.getSubject()+1);
        mGameContentDao.update(gameContent);
        GameSubjectContent gameSubjectContent = mSubjectContentDao.load(subjectId);
        gameSubjectContent.setProblem(gameSubjectContent.getProblem()+1);
        mSubjectContentDao.update(gameSubjectContent);
    }

    public long saveGameMultipleItemData(GameMultipleItem mMultiple,long subjectId) {
        updateContent(subjectId);
        return mMultipleItemDao.insert(mMultiple);
    }

    public long saveGameFillItemData(GameFillItem mFill,long subjectId) {
        updateContent(subjectId);
        return mFillItemDao.insert(mFill);
    }

    public long saveGameSubjectiveItemData(GameSubjectiveItem mSubjective,long subjectId) {
        updateContent(subjectId);
        return mSubjectiveItemDao.insert(mSubjective);
    }

    public void getGameStartAllData(final long gameId, final BaseGamePresenter.TidyAllData tidyAllData) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //TODO 处理考察知识问题的处理和排序,要求1秒以下
                List<GameSubjectItem> mItems = mSubjectItemDao
                        .queryBuilder()
                        .where(GameSubjectItemDao.Properties.SubjectId.eq(gameId))
                        .limit(GameConfigUtils.CONFIG_NUMBER)
                        .list();
                Log.e(TAG,"mItems ="+mItems.size());
                List<GameStartContent> mStarts = new ArrayList<>();
                for(GameSubjectItem mItem :mItems){
                    GameStartContent mStart = new GameStartContent();
                    switch (mItem.getSelect()){
                        case 1:
                            GameRadioItem mRadio = mItem.getRadioItem();
                            //处理单选需要的数据
                            mStart.setType(1);
                            mStart.setRadio_title(mRadio.getTitle());
                            mStart.setRadio_yes(mRadio.getYes());
                            mStart.setRadio_first_title(mRadio.getFirst_title());
                            mStart.setRadio_second_title(mRadio.getSecond_title());
                            mStart.setRadio_third_title(mRadio.getThird_title());
                            mStart.setRadio_fourth_title(mRadio.getFourth_title());
                            break;
                        case 2:
                            mStart.setType(2);

                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                        case 5:
                            break;
                    }
                    mStarts.add(mStart);
                }
            }
        }).start();
    }
}
