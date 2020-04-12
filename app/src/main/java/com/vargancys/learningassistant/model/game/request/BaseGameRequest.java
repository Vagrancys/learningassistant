package com.vargancys.learningassistant.model.game.request;

import com.vagrancys.learningassistant.db.DaoSession;
import com.vagrancys.learningassistant.db.GameContentDao;
import com.vagrancys.learningassistant.db.GameSignContentDao;
import com.vagrancys.learningassistant.db.OverViewListContentDao;
import com.vagrancys.learningassistant.db.OverViewListItemDao;
import com.vargancys.learningassistant.base.BaseApplication;
import com.vargancys.learningassistant.db.game.GameContent;
import com.vargancys.learningassistant.db.game.GameSignContent;
import com.vargancys.learningassistant.db.overview.OverViewListContent;
import com.vargancys.learningassistant.db.overview.OverViewListItem;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/10
 * version:1.0
 */
public class BaseGameRequest {
    private static BaseGameRequest mRequest;
    private DaoSession mDaoSession;
    private GameContentDao mGameContentDao;
    private OverViewListItemDao mListItemDao;
    private OverViewListContentDao mListContentDao;
    private GameSignContentDao mSignContentDao;
    private BaseGameRequest(){
        mDaoSession = BaseApplication.getInstance().getDaoSession();
        mGameContentDao = mDaoSession.getGameContentDao();
        mListItemDao = mDaoSession.getOverViewListItemDao();
        mListContentDao = mDaoSession.getOverViewListContentDao();
        mSignContentDao = mDaoSession.getGameSignContentDao();
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
}
