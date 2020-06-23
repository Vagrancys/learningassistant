package com.vargancys.learningassistant.model.overview.request;

import com.vagrancys.learningassistant.db.DaoSession;
import com.vagrancys.learningassistant.db.GameContentDao;
import com.vagrancys.learningassistant.db.GameSubjectContentDao;
import com.vagrancys.learningassistant.db.OverViewListContentDao;
import com.vagrancys.learningassistant.db.OverViewListItemDao;
import com.vargancys.learningassistant.base.BaseApplication;
import com.vargancys.learningassistant.db.game.GameContent;
import com.vargancys.learningassistant.db.game.GameSubjectContent;
import com.vargancys.learningassistant.db.overview.OverViewListContent;
import com.vargancys.learningassistant.db.overview.OverViewListItem;
import com.vargancys.learningassistant.model.home.request.KnowUpdateRequest;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/04/03
 * version:1.0
 */
public class OverViewRequest {
    private static OverViewRequest mRequest;
    private OverViewListItemDao mListItemDao;
    private DaoSession mDaoSession;
    private OverViewListContentDao mListContentDao;
    private GameSubjectContentDao mSubjectContentDao;
    private GameContentDao mGameContentDao;
    private OverViewRequest(){
        mDaoSession = BaseApplication.getInstance().getDaoSession();
        mListItemDao = mDaoSession.getOverViewListItemDao();
        mListContentDao = mDaoSession.getOverViewListContentDao();
        mSubjectContentDao = mDaoSession.getGameSubjectContentDao();
        mGameContentDao = mDaoSession.getGameContentDao();
    }

    public static OverViewRequest getInstance(){
        if(mRequest == null){
            synchronized (OverViewRequest.class){
                if(mRequest == null){
                    mRequest = new OverViewRequest();
                }
            }
        }
        return mRequest;
    }

    public List<OverViewListItem> getOverViewListData(long selectId) {
        return mListItemDao.queryBuilder().where(OverViewListItemDao.Properties.ContentId.eq(selectId)).list();
    }

    public List<OverViewListContent> getAllContentData() {
        return mListContentDao.loadAll();
    }

    public long saveOverViewContent(OverViewListContent mContent) {
        long result = mListContentDao.insert(mContent);
        GameContent gameContent = new GameContent();
        gameContent.setTitle(mContent.getTitle());
        gameContent.setSubject(0);
        gameContent.setSubject_current(0);
        gameContent.setGame_title(mContent.getTitle());
        gameContent.setScore(mContent.getGrade());
        gameContent.setOverviewId(result);
        gameContent.setError(0);
        gameContent.setDifficulty(mContent.getLevel());
        gameContent.setError_current(0);
        gameContent.setScore_current(0);
        mGameContentDao.insert(gameContent);
       return result;
    }

    public boolean saveOverViewItem(long parent,List<OverViewListItem> mItems) {
        for (OverViewListItem mItem :mItems){
            mItem.setContentId(parent);
            long result =mListItemDao.insert(mItem);
            GameSubjectContent mContent = new GameSubjectContent();
            mContent.setTitle(mItem.getTitle());
            mContent.setError(0);
            mContent.setKnowId(result);
            mContent.setLast_time("--");
            mContent.setAnswer(0);
            mContent.setProblem(0);
            mSubjectContentDao.insert(mContent);
        }
        return true;
    }

    public OverViewListContent getContentData(long selectId) {
        return mListContentDao.load(selectId);
    }

    //获取知识体系数据
    public List<OverViewListContent> queryOverViewData(String query) {
        return mListContentDao.queryBuilder().where(OverViewListContentDao.Properties.Title.like(query)).list();
    }
}
