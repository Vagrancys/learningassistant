package com.vagrancys.learningassistant.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.vargancys.learningassistant.db.common.HelpCommendItem;
import com.vargancys.learningassistant.db.common.HelpContentItem;
import com.vargancys.learningassistant.db.game.GameContent;
import com.vargancys.learningassistant.db.game.GameFillItem;
import com.vargancys.learningassistant.db.game.GameMultipleItem;
import com.vargancys.learningassistant.db.game.GameRadioItem;
import com.vargancys.learningassistant.db.game.GameSignContent;
import com.vargancys.learningassistant.db.game.GameSubjectContent;
import com.vargancys.learningassistant.db.game.GameSubjectItem;
import com.vargancys.learningassistant.db.game.GameSubjectiveItem;
import com.vargancys.learningassistant.db.home.HomeKnowCommend;
import com.vargancys.learningassistant.db.home.HomeKnowContent;
import com.vargancys.learningassistant.db.home.HomeKnowData;
import com.vargancys.learningassistant.db.home.HomeKnowFunction;
import com.vargancys.learningassistant.db.home.HomeKnowHistory;
import com.vargancys.learningassistant.db.home.HomeKnowHistoryFunction;
import com.vargancys.learningassistant.db.home.HomeKnowItem;
import com.vargancys.learningassistant.db.ladder.LadderCommentBean;
import com.vargancys.learningassistant.db.ladder.LadderCommentReplyBean;
import com.vargancys.learningassistant.db.ladder.LadderDataBean;
import com.vargancys.learningassistant.db.ladder.LadderDifficultyCommentBean;
import com.vargancys.learningassistant.db.ladder.LadderDifficultyDataBean;
import com.vargancys.learningassistant.db.ladder.LadderHelpBean;
import com.vargancys.learningassistant.db.ladder.LadderTopicBean;
import com.vargancys.learningassistant.db.overview.OverViewListContent;
import com.vargancys.learningassistant.db.overview.OverViewListItem;
import com.vargancys.learningassistant.db.ladder.LadderRankDataBean;
import com.vargancys.learningassistant.db.ladder.LadderRankSettingBean;
import com.vargancys.learningassistant.db.ladder.LadderResultBean;
import com.vargancys.learningassistant.db.mine.MineDataBean;
import com.vargancys.learningassistant.db.mine.MineLevelPrivilegeBean;
import com.vargancys.learningassistant.db.mine.MineFeedbackBean;
import com.vargancys.learningassistant.db.mine.KnowLedgeDataBean;
import com.vargancys.learningassistant.db.mine.ChallengeDataBean;
import com.vargancys.learningassistant.db.mine.ChallengePartBean;
import com.vargancys.learningassistant.db.mine.LevelDataBean;
import com.vargancys.learningassistant.db.mine.LevelPartBean;

import com.vagrancys.learningassistant.db.HelpCommendItemDao;
import com.vagrancys.learningassistant.db.HelpContentItemDao;
import com.vagrancys.learningassistant.db.GameContentDao;
import com.vagrancys.learningassistant.db.GameFillItemDao;
import com.vagrancys.learningassistant.db.GameMultipleItemDao;
import com.vagrancys.learningassistant.db.GameRadioItemDao;
import com.vagrancys.learningassistant.db.GameSignContentDao;
import com.vagrancys.learningassistant.db.GameSubjectContentDao;
import com.vagrancys.learningassistant.db.GameSubjectItemDao;
import com.vagrancys.learningassistant.db.GameSubjectiveItemDao;
import com.vagrancys.learningassistant.db.HomeKnowCommendDao;
import com.vagrancys.learningassistant.db.HomeKnowContentDao;
import com.vagrancys.learningassistant.db.HomeKnowDataDao;
import com.vagrancys.learningassistant.db.HomeKnowFunctionDao;
import com.vagrancys.learningassistant.db.HomeKnowHistoryDao;
import com.vagrancys.learningassistant.db.HomeKnowHistoryFunctionDao;
import com.vagrancys.learningassistant.db.HomeKnowItemDao;
import com.vagrancys.learningassistant.db.LadderCommentBeanDao;
import com.vagrancys.learningassistant.db.LadderCommentReplyBeanDao;
import com.vagrancys.learningassistant.db.LadderDataBeanDao;
import com.vagrancys.learningassistant.db.LadderDifficultyCommentBeanDao;
import com.vagrancys.learningassistant.db.LadderDifficultyDataBeanDao;
import com.vagrancys.learningassistant.db.LadderHelpBeanDao;
import com.vagrancys.learningassistant.db.LadderTopicBeanDao;
import com.vagrancys.learningassistant.db.OverViewListContentDao;
import com.vagrancys.learningassistant.db.OverViewListItemDao;
import com.vagrancys.learningassistant.db.LadderRankDataBeanDao;
import com.vagrancys.learningassistant.db.LadderRankSettingBeanDao;
import com.vagrancys.learningassistant.db.LadderResultBeanDao;
import com.vagrancys.learningassistant.db.MineDataBeanDao;
import com.vagrancys.learningassistant.db.MineLevelPrivilegeBeanDao;
import com.vagrancys.learningassistant.db.MineFeedbackBeanDao;
import com.vagrancys.learningassistant.db.KnowLedgeDataBeanDao;
import com.vagrancys.learningassistant.db.ChallengeDataBeanDao;
import com.vagrancys.learningassistant.db.ChallengePartBeanDao;
import com.vagrancys.learningassistant.db.LevelDataBeanDao;
import com.vagrancys.learningassistant.db.LevelPartBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig helpCommendItemDaoConfig;
    private final DaoConfig helpContentItemDaoConfig;
    private final DaoConfig gameContentDaoConfig;
    private final DaoConfig gameFillItemDaoConfig;
    private final DaoConfig gameMultipleItemDaoConfig;
    private final DaoConfig gameRadioItemDaoConfig;
    private final DaoConfig gameSignContentDaoConfig;
    private final DaoConfig gameSubjectContentDaoConfig;
    private final DaoConfig gameSubjectItemDaoConfig;
    private final DaoConfig gameSubjectiveItemDaoConfig;
    private final DaoConfig homeKnowCommendDaoConfig;
    private final DaoConfig homeKnowContentDaoConfig;
    private final DaoConfig homeKnowDataDaoConfig;
    private final DaoConfig homeKnowFunctionDaoConfig;
    private final DaoConfig homeKnowHistoryDaoConfig;
    private final DaoConfig homeKnowHistoryFunctionDaoConfig;
    private final DaoConfig homeKnowItemDaoConfig;
    private final DaoConfig ladderCommentBeanDaoConfig;
    private final DaoConfig ladderCommentReplyBeanDaoConfig;
    private final DaoConfig ladderDataBeanDaoConfig;
    private final DaoConfig ladderDifficultyCommentBeanDaoConfig;
    private final DaoConfig ladderDifficultyDataBeanDaoConfig;
    private final DaoConfig ladderHelpBeanDaoConfig;
    private final DaoConfig ladderTopicBeanDaoConfig;
    private final DaoConfig overViewListContentDaoConfig;
    private final DaoConfig overViewListItemDaoConfig;
    private final DaoConfig ladderRankDataBeanDaoConfig;
    private final DaoConfig ladderRankSettingBeanDaoConfig;
    private final DaoConfig ladderResultBeanDaoConfig;
    private final DaoConfig mineDataBeanDaoConfig;
    private final DaoConfig mineLevelPrivilegeBeanDaoConfig;
    private final DaoConfig mineFeedbackBeanDaoConfig;
    private final DaoConfig knowLedgeDataBeanDaoConfig;
    private final DaoConfig challengeDataBeanDaoConfig;
    private final DaoConfig challengePartBeanDaoConfig;
    private final DaoConfig levelDataBeanDaoConfig;
    private final DaoConfig levelPartBeanDaoConfig;

    private final HelpCommendItemDao helpCommendItemDao;
    private final HelpContentItemDao helpContentItemDao;
    private final GameContentDao gameContentDao;
    private final GameFillItemDao gameFillItemDao;
    private final GameMultipleItemDao gameMultipleItemDao;
    private final GameRadioItemDao gameRadioItemDao;
    private final GameSignContentDao gameSignContentDao;
    private final GameSubjectContentDao gameSubjectContentDao;
    private final GameSubjectItemDao gameSubjectItemDao;
    private final GameSubjectiveItemDao gameSubjectiveItemDao;
    private final HomeKnowCommendDao homeKnowCommendDao;
    private final HomeKnowContentDao homeKnowContentDao;
    private final HomeKnowDataDao homeKnowDataDao;
    private final HomeKnowFunctionDao homeKnowFunctionDao;
    private final HomeKnowHistoryDao homeKnowHistoryDao;
    private final HomeKnowHistoryFunctionDao homeKnowHistoryFunctionDao;
    private final HomeKnowItemDao homeKnowItemDao;
    private final LadderCommentBeanDao ladderCommentBeanDao;
    private final LadderCommentReplyBeanDao ladderCommentReplyBeanDao;
    private final LadderDataBeanDao ladderDataBeanDao;
    private final LadderDifficultyCommentBeanDao ladderDifficultyCommentBeanDao;
    private final LadderDifficultyDataBeanDao ladderDifficultyDataBeanDao;
    private final LadderHelpBeanDao ladderHelpBeanDao;
    private final LadderTopicBeanDao ladderTopicBeanDao;
    private final OverViewListContentDao overViewListContentDao;
    private final OverViewListItemDao overViewListItemDao;
    private final LadderRankDataBeanDao ladderRankDataBeanDao;
    private final LadderRankSettingBeanDao ladderRankSettingBeanDao;
    private final LadderResultBeanDao ladderResultBeanDao;
    private final MineDataBeanDao mineDataBeanDao;
    private final MineLevelPrivilegeBeanDao mineLevelPrivilegeBeanDao;
    private final MineFeedbackBeanDao mineFeedbackBeanDao;
    private final KnowLedgeDataBeanDao knowLedgeDataBeanDao;
    private final ChallengeDataBeanDao challengeDataBeanDao;
    private final ChallengePartBeanDao challengePartBeanDao;
    private final LevelDataBeanDao levelDataBeanDao;
    private final LevelPartBeanDao levelPartBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        helpCommendItemDaoConfig = daoConfigMap.get(HelpCommendItemDao.class).clone();
        helpCommendItemDaoConfig.initIdentityScope(type);

        helpContentItemDaoConfig = daoConfigMap.get(HelpContentItemDao.class).clone();
        helpContentItemDaoConfig.initIdentityScope(type);

        gameContentDaoConfig = daoConfigMap.get(GameContentDao.class).clone();
        gameContentDaoConfig.initIdentityScope(type);

        gameFillItemDaoConfig = daoConfigMap.get(GameFillItemDao.class).clone();
        gameFillItemDaoConfig.initIdentityScope(type);

        gameMultipleItemDaoConfig = daoConfigMap.get(GameMultipleItemDao.class).clone();
        gameMultipleItemDaoConfig.initIdentityScope(type);

        gameRadioItemDaoConfig = daoConfigMap.get(GameRadioItemDao.class).clone();
        gameRadioItemDaoConfig.initIdentityScope(type);

        gameSignContentDaoConfig = daoConfigMap.get(GameSignContentDao.class).clone();
        gameSignContentDaoConfig.initIdentityScope(type);

        gameSubjectContentDaoConfig = daoConfigMap.get(GameSubjectContentDao.class).clone();
        gameSubjectContentDaoConfig.initIdentityScope(type);

        gameSubjectItemDaoConfig = daoConfigMap.get(GameSubjectItemDao.class).clone();
        gameSubjectItemDaoConfig.initIdentityScope(type);

        gameSubjectiveItemDaoConfig = daoConfigMap.get(GameSubjectiveItemDao.class).clone();
        gameSubjectiveItemDaoConfig.initIdentityScope(type);

        homeKnowCommendDaoConfig = daoConfigMap.get(HomeKnowCommendDao.class).clone();
        homeKnowCommendDaoConfig.initIdentityScope(type);

        homeKnowContentDaoConfig = daoConfigMap.get(HomeKnowContentDao.class).clone();
        homeKnowContentDaoConfig.initIdentityScope(type);

        homeKnowDataDaoConfig = daoConfigMap.get(HomeKnowDataDao.class).clone();
        homeKnowDataDaoConfig.initIdentityScope(type);

        homeKnowFunctionDaoConfig = daoConfigMap.get(HomeKnowFunctionDao.class).clone();
        homeKnowFunctionDaoConfig.initIdentityScope(type);

        homeKnowHistoryDaoConfig = daoConfigMap.get(HomeKnowHistoryDao.class).clone();
        homeKnowHistoryDaoConfig.initIdentityScope(type);

        homeKnowHistoryFunctionDaoConfig = daoConfigMap.get(HomeKnowHistoryFunctionDao.class).clone();
        homeKnowHistoryFunctionDaoConfig.initIdentityScope(type);

        homeKnowItemDaoConfig = daoConfigMap.get(HomeKnowItemDao.class).clone();
        homeKnowItemDaoConfig.initIdentityScope(type);

        ladderCommentBeanDaoConfig = daoConfigMap.get(LadderCommentBeanDao.class).clone();
        ladderCommentBeanDaoConfig.initIdentityScope(type);

        ladderCommentReplyBeanDaoConfig = daoConfigMap.get(LadderCommentReplyBeanDao.class).clone();
        ladderCommentReplyBeanDaoConfig.initIdentityScope(type);

        ladderDataBeanDaoConfig = daoConfigMap.get(LadderDataBeanDao.class).clone();
        ladderDataBeanDaoConfig.initIdentityScope(type);

        ladderDifficultyCommentBeanDaoConfig = daoConfigMap.get(LadderDifficultyCommentBeanDao.class).clone();
        ladderDifficultyCommentBeanDaoConfig.initIdentityScope(type);

        ladderDifficultyDataBeanDaoConfig = daoConfigMap.get(LadderDifficultyDataBeanDao.class).clone();
        ladderDifficultyDataBeanDaoConfig.initIdentityScope(type);

        ladderHelpBeanDaoConfig = daoConfigMap.get(LadderHelpBeanDao.class).clone();
        ladderHelpBeanDaoConfig.initIdentityScope(type);

        ladderTopicBeanDaoConfig = daoConfigMap.get(LadderTopicBeanDao.class).clone();
        ladderTopicBeanDaoConfig.initIdentityScope(type);

        overViewListContentDaoConfig = daoConfigMap.get(OverViewListContentDao.class).clone();
        overViewListContentDaoConfig.initIdentityScope(type);

        overViewListItemDaoConfig = daoConfigMap.get(OverViewListItemDao.class).clone();
        overViewListItemDaoConfig.initIdentityScope(type);

        ladderRankDataBeanDaoConfig = daoConfigMap.get(LadderRankDataBeanDao.class).clone();
        ladderRankDataBeanDaoConfig.initIdentityScope(type);

        ladderRankSettingBeanDaoConfig = daoConfigMap.get(LadderRankSettingBeanDao.class).clone();
        ladderRankSettingBeanDaoConfig.initIdentityScope(type);

        ladderResultBeanDaoConfig = daoConfigMap.get(LadderResultBeanDao.class).clone();
        ladderResultBeanDaoConfig.initIdentityScope(type);

        mineDataBeanDaoConfig = daoConfigMap.get(MineDataBeanDao.class).clone();
        mineDataBeanDaoConfig.initIdentityScope(type);

        mineLevelPrivilegeBeanDaoConfig = daoConfigMap.get(MineLevelPrivilegeBeanDao.class).clone();
        mineLevelPrivilegeBeanDaoConfig.initIdentityScope(type);

        mineFeedbackBeanDaoConfig = daoConfigMap.get(MineFeedbackBeanDao.class).clone();
        mineFeedbackBeanDaoConfig.initIdentityScope(type);

        knowLedgeDataBeanDaoConfig = daoConfigMap.get(KnowLedgeDataBeanDao.class).clone();
        knowLedgeDataBeanDaoConfig.initIdentityScope(type);

        challengeDataBeanDaoConfig = daoConfigMap.get(ChallengeDataBeanDao.class).clone();
        challengeDataBeanDaoConfig.initIdentityScope(type);

        challengePartBeanDaoConfig = daoConfigMap.get(ChallengePartBeanDao.class).clone();
        challengePartBeanDaoConfig.initIdentityScope(type);

        levelDataBeanDaoConfig = daoConfigMap.get(LevelDataBeanDao.class).clone();
        levelDataBeanDaoConfig.initIdentityScope(type);

        levelPartBeanDaoConfig = daoConfigMap.get(LevelPartBeanDao.class).clone();
        levelPartBeanDaoConfig.initIdentityScope(type);

        helpCommendItemDao = new HelpCommendItemDao(helpCommendItemDaoConfig, this);
        helpContentItemDao = new HelpContentItemDao(helpContentItemDaoConfig, this);
        gameContentDao = new GameContentDao(gameContentDaoConfig, this);
        gameFillItemDao = new GameFillItemDao(gameFillItemDaoConfig, this);
        gameMultipleItemDao = new GameMultipleItemDao(gameMultipleItemDaoConfig, this);
        gameRadioItemDao = new GameRadioItemDao(gameRadioItemDaoConfig, this);
        gameSignContentDao = new GameSignContentDao(gameSignContentDaoConfig, this);
        gameSubjectContentDao = new GameSubjectContentDao(gameSubjectContentDaoConfig, this);
        gameSubjectItemDao = new GameSubjectItemDao(gameSubjectItemDaoConfig, this);
        gameSubjectiveItemDao = new GameSubjectiveItemDao(gameSubjectiveItemDaoConfig, this);
        homeKnowCommendDao = new HomeKnowCommendDao(homeKnowCommendDaoConfig, this);
        homeKnowContentDao = new HomeKnowContentDao(homeKnowContentDaoConfig, this);
        homeKnowDataDao = new HomeKnowDataDao(homeKnowDataDaoConfig, this);
        homeKnowFunctionDao = new HomeKnowFunctionDao(homeKnowFunctionDaoConfig, this);
        homeKnowHistoryDao = new HomeKnowHistoryDao(homeKnowHistoryDaoConfig, this);
        homeKnowHistoryFunctionDao = new HomeKnowHistoryFunctionDao(homeKnowHistoryFunctionDaoConfig, this);
        homeKnowItemDao = new HomeKnowItemDao(homeKnowItemDaoConfig, this);
        ladderCommentBeanDao = new LadderCommentBeanDao(ladderCommentBeanDaoConfig, this);
        ladderCommentReplyBeanDao = new LadderCommentReplyBeanDao(ladderCommentReplyBeanDaoConfig, this);
        ladderDataBeanDao = new LadderDataBeanDao(ladderDataBeanDaoConfig, this);
        ladderDifficultyCommentBeanDao = new LadderDifficultyCommentBeanDao(ladderDifficultyCommentBeanDaoConfig, this);
        ladderDifficultyDataBeanDao = new LadderDifficultyDataBeanDao(ladderDifficultyDataBeanDaoConfig, this);
        ladderHelpBeanDao = new LadderHelpBeanDao(ladderHelpBeanDaoConfig, this);
        ladderTopicBeanDao = new LadderTopicBeanDao(ladderTopicBeanDaoConfig, this);
        overViewListContentDao = new OverViewListContentDao(overViewListContentDaoConfig, this);
        overViewListItemDao = new OverViewListItemDao(overViewListItemDaoConfig, this);
        ladderRankDataBeanDao = new LadderRankDataBeanDao(ladderRankDataBeanDaoConfig, this);
        ladderRankSettingBeanDao = new LadderRankSettingBeanDao(ladderRankSettingBeanDaoConfig, this);
        ladderResultBeanDao = new LadderResultBeanDao(ladderResultBeanDaoConfig, this);
        mineDataBeanDao = new MineDataBeanDao(mineDataBeanDaoConfig, this);
        mineLevelPrivilegeBeanDao = new MineLevelPrivilegeBeanDao(mineLevelPrivilegeBeanDaoConfig, this);
        mineFeedbackBeanDao = new MineFeedbackBeanDao(mineFeedbackBeanDaoConfig, this);
        knowLedgeDataBeanDao = new KnowLedgeDataBeanDao(knowLedgeDataBeanDaoConfig, this);
        challengeDataBeanDao = new ChallengeDataBeanDao(challengeDataBeanDaoConfig, this);
        challengePartBeanDao = new ChallengePartBeanDao(challengePartBeanDaoConfig, this);
        levelDataBeanDao = new LevelDataBeanDao(levelDataBeanDaoConfig, this);
        levelPartBeanDao = new LevelPartBeanDao(levelPartBeanDaoConfig, this);

        registerDao(HelpCommendItem.class, helpCommendItemDao);
        registerDao(HelpContentItem.class, helpContentItemDao);
        registerDao(GameContent.class, gameContentDao);
        registerDao(GameFillItem.class, gameFillItemDao);
        registerDao(GameMultipleItem.class, gameMultipleItemDao);
        registerDao(GameRadioItem.class, gameRadioItemDao);
        registerDao(GameSignContent.class, gameSignContentDao);
        registerDao(GameSubjectContent.class, gameSubjectContentDao);
        registerDao(GameSubjectItem.class, gameSubjectItemDao);
        registerDao(GameSubjectiveItem.class, gameSubjectiveItemDao);
        registerDao(HomeKnowCommend.class, homeKnowCommendDao);
        registerDao(HomeKnowContent.class, homeKnowContentDao);
        registerDao(HomeKnowData.class, homeKnowDataDao);
        registerDao(HomeKnowFunction.class, homeKnowFunctionDao);
        registerDao(HomeKnowHistory.class, homeKnowHistoryDao);
        registerDao(HomeKnowHistoryFunction.class, homeKnowHistoryFunctionDao);
        registerDao(HomeKnowItem.class, homeKnowItemDao);
        registerDao(LadderCommentBean.class, ladderCommentBeanDao);
        registerDao(LadderCommentReplyBean.class, ladderCommentReplyBeanDao);
        registerDao(LadderDataBean.class, ladderDataBeanDao);
        registerDao(LadderDifficultyCommentBean.class, ladderDifficultyCommentBeanDao);
        registerDao(LadderDifficultyDataBean.class, ladderDifficultyDataBeanDao);
        registerDao(LadderHelpBean.class, ladderHelpBeanDao);
        registerDao(LadderTopicBean.class, ladderTopicBeanDao);
        registerDao(OverViewListContent.class, overViewListContentDao);
        registerDao(OverViewListItem.class, overViewListItemDao);
        registerDao(LadderRankDataBean.class, ladderRankDataBeanDao);
        registerDao(LadderRankSettingBean.class, ladderRankSettingBeanDao);
        registerDao(LadderResultBean.class, ladderResultBeanDao);
        registerDao(MineDataBean.class, mineDataBeanDao);
        registerDao(MineLevelPrivilegeBean.class, mineLevelPrivilegeBeanDao);
        registerDao(MineFeedbackBean.class, mineFeedbackBeanDao);
        registerDao(KnowLedgeDataBean.class, knowLedgeDataBeanDao);
        registerDao(ChallengeDataBean.class, challengeDataBeanDao);
        registerDao(ChallengePartBean.class, challengePartBeanDao);
        registerDao(LevelDataBean.class, levelDataBeanDao);
        registerDao(LevelPartBean.class, levelPartBeanDao);
    }
    
    public void clear() {
        helpCommendItemDaoConfig.clearIdentityScope();
        helpContentItemDaoConfig.clearIdentityScope();
        gameContentDaoConfig.clearIdentityScope();
        gameFillItemDaoConfig.clearIdentityScope();
        gameMultipleItemDaoConfig.clearIdentityScope();
        gameRadioItemDaoConfig.clearIdentityScope();
        gameSignContentDaoConfig.clearIdentityScope();
        gameSubjectContentDaoConfig.clearIdentityScope();
        gameSubjectItemDaoConfig.clearIdentityScope();
        gameSubjectiveItemDaoConfig.clearIdentityScope();
        homeKnowCommendDaoConfig.clearIdentityScope();
        homeKnowContentDaoConfig.clearIdentityScope();
        homeKnowDataDaoConfig.clearIdentityScope();
        homeKnowFunctionDaoConfig.clearIdentityScope();
        homeKnowHistoryDaoConfig.clearIdentityScope();
        homeKnowHistoryFunctionDaoConfig.clearIdentityScope();
        homeKnowItemDaoConfig.clearIdentityScope();
        ladderCommentBeanDaoConfig.clearIdentityScope();
        ladderCommentReplyBeanDaoConfig.clearIdentityScope();
        ladderDataBeanDaoConfig.clearIdentityScope();
        ladderDifficultyCommentBeanDaoConfig.clearIdentityScope();
        ladderDifficultyDataBeanDaoConfig.clearIdentityScope();
        ladderHelpBeanDaoConfig.clearIdentityScope();
        ladderTopicBeanDaoConfig.clearIdentityScope();
        overViewListContentDaoConfig.clearIdentityScope();
        overViewListItemDaoConfig.clearIdentityScope();
        ladderRankDataBeanDaoConfig.clearIdentityScope();
        ladderRankSettingBeanDaoConfig.clearIdentityScope();
        ladderResultBeanDaoConfig.clearIdentityScope();
        mineDataBeanDaoConfig.clearIdentityScope();
        mineLevelPrivilegeBeanDaoConfig.clearIdentityScope();
        mineFeedbackBeanDaoConfig.clearIdentityScope();
        knowLedgeDataBeanDaoConfig.clearIdentityScope();
        challengeDataBeanDaoConfig.clearIdentityScope();
        challengePartBeanDaoConfig.clearIdentityScope();
        levelDataBeanDaoConfig.clearIdentityScope();
        levelPartBeanDaoConfig.clearIdentityScope();
    }

    public HelpCommendItemDao getHelpCommendItemDao() {
        return helpCommendItemDao;
    }

    public HelpContentItemDao getHelpContentItemDao() {
        return helpContentItemDao;
    }

    public GameContentDao getGameContentDao() {
        return gameContentDao;
    }

    public GameFillItemDao getGameFillItemDao() {
        return gameFillItemDao;
    }

    public GameMultipleItemDao getGameMultipleItemDao() {
        return gameMultipleItemDao;
    }

    public GameRadioItemDao getGameRadioItemDao() {
        return gameRadioItemDao;
    }

    public GameSignContentDao getGameSignContentDao() {
        return gameSignContentDao;
    }

    public GameSubjectContentDao getGameSubjectContentDao() {
        return gameSubjectContentDao;
    }

    public GameSubjectItemDao getGameSubjectItemDao() {
        return gameSubjectItemDao;
    }

    public GameSubjectiveItemDao getGameSubjectiveItemDao() {
        return gameSubjectiveItemDao;
    }

    public HomeKnowCommendDao getHomeKnowCommendDao() {
        return homeKnowCommendDao;
    }

    public HomeKnowContentDao getHomeKnowContentDao() {
        return homeKnowContentDao;
    }

    public HomeKnowDataDao getHomeKnowDataDao() {
        return homeKnowDataDao;
    }

    public HomeKnowFunctionDao getHomeKnowFunctionDao() {
        return homeKnowFunctionDao;
    }

    public HomeKnowHistoryDao getHomeKnowHistoryDao() {
        return homeKnowHistoryDao;
    }

    public HomeKnowHistoryFunctionDao getHomeKnowHistoryFunctionDao() {
        return homeKnowHistoryFunctionDao;
    }

    public HomeKnowItemDao getHomeKnowItemDao() {
        return homeKnowItemDao;
    }

    public LadderCommentBeanDao getLadderCommentBeanDao() {
        return ladderCommentBeanDao;
    }

    public LadderCommentReplyBeanDao getLadderCommentReplyBeanDao() {
        return ladderCommentReplyBeanDao;
    }

    public LadderDataBeanDao getLadderDataBeanDao() {
        return ladderDataBeanDao;
    }

    public LadderDifficultyCommentBeanDao getLadderDifficultyCommentBeanDao() {
        return ladderDifficultyCommentBeanDao;
    }

    public LadderDifficultyDataBeanDao getLadderDifficultyDataBeanDao() {
        return ladderDifficultyDataBeanDao;
    }

    public LadderHelpBeanDao getLadderHelpBeanDao() {
        return ladderHelpBeanDao;
    }

    public LadderTopicBeanDao getLadderTopicBeanDao() {
        return ladderTopicBeanDao;
    }

    public OverViewListContentDao getOverViewListContentDao() {
        return overViewListContentDao;
    }

    public OverViewListItemDao getOverViewListItemDao() {
        return overViewListItemDao;
    }

    public LadderRankDataBeanDao getLadderRankDataBeanDao() {
        return ladderRankDataBeanDao;
    }

    public LadderRankSettingBeanDao getLadderRankSettingBeanDao() {
        return ladderRankSettingBeanDao;
    }

    public LadderResultBeanDao getLadderResultBeanDao() {
        return ladderResultBeanDao;
    }

    public MineDataBeanDao getMineDataBeanDao() {
        return mineDataBeanDao;
    }

    public MineLevelPrivilegeBeanDao getMineLevelPrivilegeBeanDao() {
        return mineLevelPrivilegeBeanDao;
    }

    public MineFeedbackBeanDao getMineFeedbackBeanDao() {
        return mineFeedbackBeanDao;
    }

    public KnowLedgeDataBeanDao getKnowLedgeDataBeanDao() {
        return knowLedgeDataBeanDao;
    }

    public ChallengeDataBeanDao getChallengeDataBeanDao() {
        return challengeDataBeanDao;
    }

    public ChallengePartBeanDao getChallengePartBeanDao() {
        return challengePartBeanDao;
    }

    public LevelDataBeanDao getLevelDataBeanDao() {
        return levelDataBeanDao;
    }

    public LevelPartBeanDao getLevelPartBeanDao() {
        return levelPartBeanDao;
    }

}
