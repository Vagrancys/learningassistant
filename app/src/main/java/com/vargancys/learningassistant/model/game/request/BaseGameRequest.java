package com.vargancys.learningassistant.model.game.request;

import com.vagrancys.learningassistant.db.DaoSession;
import com.vagrancys.learningassistant.db.GameContentDao;
import com.vagrancys.learningassistant.db.OverViewListItemDao;
import com.vargancys.learningassistant.base.BaseApplication;
import com.vargancys.learningassistant.db.game.GameContent;
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
    private BaseGameRequest(){
        mDaoSession = BaseApplication.getInstance().getDaoSession();
        mGameContentDao = mDaoSession.getGameContentDao();
        mListItemDao = mDaoSession.getOverViewListItemDao();
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
}
