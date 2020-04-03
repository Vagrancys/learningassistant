package com.vargancys.learningassistant.model.overview.request;

import com.vagrancys.learningassistant.db.DaoSession;
import com.vagrancys.learningassistant.db.OverViewListItemDao;
import com.vargancys.learningassistant.base.BaseApplication;
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
    private OverViewRequest(){
        mDaoSession = BaseApplication.getInstance().getDaoSession();
        mListItemDao = mDaoSession.getOverViewListItemDao();
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
        return mListItemDao.queryBuilder().where(OverViewListItemDao.Properties.Create.eq(selectId)).list();
    }
}
