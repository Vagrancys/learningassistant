package com.vargancys.learningassistant.model.overview.request;

import com.vagrancys.learningassistant.db.DaoSession;
import com.vagrancys.learningassistant.db.OverViewListContentDao;
import com.vagrancys.learningassistant.db.OverViewListItemDao;
import com.vargancys.learningassistant.base.BaseApplication;
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
    private OverViewRequest(){
        mDaoSession = BaseApplication.getInstance().getDaoSession();
        mListItemDao = mDaoSession.getOverViewListItemDao();
        mListContentDao = mDaoSession.getOverViewListContentDao();
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
       return mListContentDao.insert(mContent);
    }

    public boolean saveOverViewItem(long parent,List<OverViewListItem> mItems) {
        for (OverViewListItem mItem :mItems){
            mItem.setContentId(parent);
            mListItemDao.insert(mItem);
        }
        return true;
    }

    public OverViewListContent getContentData(long selectId) {
        return mListContentDao.load(selectId);
    }
}
