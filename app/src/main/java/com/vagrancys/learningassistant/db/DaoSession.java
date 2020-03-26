package com.vagrancys.learningassistant.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.vargancys.learningassistant.db.common.HelpCommendItem;
import com.vargancys.learningassistant.db.common.HelpContentItem;
import com.vargancys.learningassistant.db.home.HomeKnowContent;
import com.vargancys.learningassistant.db.home.HomeKnowItem;
import com.vargancys.learningassistant.db.home.HomeKnowData;
import com.vargancys.learningassistant.db.home.HomeKnowFunction;
import com.vargancys.learningassistant.db.home.HomeKnowCommend;
import com.vargancys.learningassistant.db.home.HomeKnowHistory;

import com.vagrancys.learningassistant.db.HelpCommendItemDao;
import com.vagrancys.learningassistant.db.HelpContentItemDao;
import com.vagrancys.learningassistant.db.HomeKnowContentDao;
import com.vagrancys.learningassistant.db.HomeKnowItemDao;
import com.vagrancys.learningassistant.db.HomeKnowDataDao;
import com.vagrancys.learningassistant.db.HomeKnowFunctionDao;
import com.vagrancys.learningassistant.db.HomeKnowCommendDao;
import com.vagrancys.learningassistant.db.HomeKnowHistoryDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig helpCommendItemDaoConfig;
    private final DaoConfig helpContentItemDaoConfig;
    private final DaoConfig homeKnowContentDaoConfig;
    private final DaoConfig homeKnowItemDaoConfig;
    private final DaoConfig homeKnowDataDaoConfig;
    private final DaoConfig homeKnowFunctionDaoConfig;
    private final DaoConfig homeKnowCommendDaoConfig;
    private final DaoConfig homeKnowHistoryDaoConfig;

    private final HelpCommendItemDao helpCommendItemDao;
    private final HelpContentItemDao helpContentItemDao;
    private final HomeKnowContentDao homeKnowContentDao;
    private final HomeKnowItemDao homeKnowItemDao;
    private final HomeKnowDataDao homeKnowDataDao;
    private final HomeKnowFunctionDao homeKnowFunctionDao;
    private final HomeKnowCommendDao homeKnowCommendDao;
    private final HomeKnowHistoryDao homeKnowHistoryDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        helpCommendItemDaoConfig = daoConfigMap.get(HelpCommendItemDao.class).clone();
        helpCommendItemDaoConfig.initIdentityScope(type);

        helpContentItemDaoConfig = daoConfigMap.get(HelpContentItemDao.class).clone();
        helpContentItemDaoConfig.initIdentityScope(type);

        homeKnowContentDaoConfig = daoConfigMap.get(HomeKnowContentDao.class).clone();
        homeKnowContentDaoConfig.initIdentityScope(type);

        homeKnowItemDaoConfig = daoConfigMap.get(HomeKnowItemDao.class).clone();
        homeKnowItemDaoConfig.initIdentityScope(type);

        homeKnowDataDaoConfig = daoConfigMap.get(HomeKnowDataDao.class).clone();
        homeKnowDataDaoConfig.initIdentityScope(type);

        homeKnowFunctionDaoConfig = daoConfigMap.get(HomeKnowFunctionDao.class).clone();
        homeKnowFunctionDaoConfig.initIdentityScope(type);

        homeKnowCommendDaoConfig = daoConfigMap.get(HomeKnowCommendDao.class).clone();
        homeKnowCommendDaoConfig.initIdentityScope(type);

        homeKnowHistoryDaoConfig = daoConfigMap.get(HomeKnowHistoryDao.class).clone();
        homeKnowHistoryDaoConfig.initIdentityScope(type);

        helpCommendItemDao = new HelpCommendItemDao(helpCommendItemDaoConfig, this);
        helpContentItemDao = new HelpContentItemDao(helpContentItemDaoConfig, this);
        homeKnowContentDao = new HomeKnowContentDao(homeKnowContentDaoConfig, this);
        homeKnowItemDao = new HomeKnowItemDao(homeKnowItemDaoConfig, this);
        homeKnowDataDao = new HomeKnowDataDao(homeKnowDataDaoConfig, this);
        homeKnowFunctionDao = new HomeKnowFunctionDao(homeKnowFunctionDaoConfig, this);
        homeKnowCommendDao = new HomeKnowCommendDao(homeKnowCommendDaoConfig, this);
        homeKnowHistoryDao = new HomeKnowHistoryDao(homeKnowHistoryDaoConfig, this);

        registerDao(HelpCommendItem.class, helpCommendItemDao);
        registerDao(HelpContentItem.class, helpContentItemDao);
        registerDao(HomeKnowContent.class, homeKnowContentDao);
        registerDao(HomeKnowItem.class, homeKnowItemDao);
        registerDao(HomeKnowData.class, homeKnowDataDao);
        registerDao(HomeKnowFunction.class, homeKnowFunctionDao);
        registerDao(HomeKnowCommend.class, homeKnowCommendDao);
        registerDao(HomeKnowHistory.class, homeKnowHistoryDao);
    }
    
    public void clear() {
        helpCommendItemDaoConfig.clearIdentityScope();
        helpContentItemDaoConfig.clearIdentityScope();
        homeKnowContentDaoConfig.clearIdentityScope();
        homeKnowItemDaoConfig.clearIdentityScope();
        homeKnowDataDaoConfig.clearIdentityScope();
        homeKnowFunctionDaoConfig.clearIdentityScope();
        homeKnowCommendDaoConfig.clearIdentityScope();
        homeKnowHistoryDaoConfig.clearIdentityScope();
    }

    public HelpCommendItemDao getHelpCommendItemDao() {
        return helpCommendItemDao;
    }

    public HelpContentItemDao getHelpContentItemDao() {
        return helpContentItemDao;
    }

    public HomeKnowContentDao getHomeKnowContentDao() {
        return homeKnowContentDao;
    }

    public HomeKnowItemDao getHomeKnowItemDao() {
        return homeKnowItemDao;
    }

    public HomeKnowDataDao getHomeKnowDataDao() {
        return homeKnowDataDao;
    }

    public HomeKnowFunctionDao getHomeKnowFunctionDao() {
        return homeKnowFunctionDao;
    }

    public HomeKnowCommendDao getHomeKnowCommendDao() {
        return homeKnowCommendDao;
    }

    public HomeKnowHistoryDao getHomeKnowHistoryDao() {
        return homeKnowHistoryDao;
    }

}
