package com.vagrancys.learningassistant.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

import org.greenrobot.greendao.AbstractDaoMaster;
import org.greenrobot.greendao.database.StandardDatabase;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseOpenHelper;
import org.greenrobot.greendao.identityscope.IdentityScopeType;


// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/**
 * Master of DAO (schema version 6): knows all DAOs.
 */
public class DaoMaster extends AbstractDaoMaster {
    public static final int SCHEMA_VERSION = 6;

    /** Creates underlying database table using DAOs. */
    public static void createAllTables(Database db, boolean ifNotExists) {
        HelpCommendItemDao.createTable(db, ifNotExists);
        HelpContentItemDao.createTable(db, ifNotExists);
        GameContentDao.createTable(db, ifNotExists);
        GameFillItemDao.createTable(db, ifNotExists);
        GameMultipleItemDao.createTable(db, ifNotExists);
        GameRadioItemDao.createTable(db, ifNotExists);
        GameSignContentDao.createTable(db, ifNotExists);
        GameSubjectContentDao.createTable(db, ifNotExists);
        GameSubjectItemDao.createTable(db, ifNotExists);
        GameSubjectiveItemDao.createTable(db, ifNotExists);
        HomeKnowCommendDao.createTable(db, ifNotExists);
        HomeKnowContentDao.createTable(db, ifNotExists);
        HomeKnowDataDao.createTable(db, ifNotExists);
        HomeKnowFunctionDao.createTable(db, ifNotExists);
        HomeKnowHistoryDao.createTable(db, ifNotExists);
        HomeKnowHistoryFunctionDao.createTable(db, ifNotExists);
        HomeKnowItemDao.createTable(db, ifNotExists);
        LadderCommentBeanDao.createTable(db, ifNotExists);
        LadderCommentReplyBeanDao.createTable(db, ifNotExists);
        LadderDataBeanDao.createTable(db, ifNotExists);
        LadderDifficultyCommentBeanDao.createTable(db, ifNotExists);
        LadderDifficultyDataBeanDao.createTable(db, ifNotExists);
        LadderHelpBeanDao.createTable(db, ifNotExists);
        LadderTopicBeanDao.createTable(db, ifNotExists);
        OverViewListContentDao.createTable(db, ifNotExists);
        OverViewListItemDao.createTable(db, ifNotExists);
        LadderRankDataBeanDao.createTable(db, ifNotExists);
        LadderRankSettingBeanDao.createTable(db, ifNotExists);
        LadderResultBeanDao.createTable(db, ifNotExists);
        MineDataBeanDao.createTable(db, ifNotExists);
        MineLevelPrivilegeBeanDao.createTable(db, ifNotExists);
        MineFeedbackBeanDao.createTable(db, ifNotExists);
    }

    /** Drops underlying database table using DAOs. */
    public static void dropAllTables(Database db, boolean ifExists) {
        HelpCommendItemDao.dropTable(db, ifExists);
        HelpContentItemDao.dropTable(db, ifExists);
        GameContentDao.dropTable(db, ifExists);
        GameFillItemDao.dropTable(db, ifExists);
        GameMultipleItemDao.dropTable(db, ifExists);
        GameRadioItemDao.dropTable(db, ifExists);
        GameSignContentDao.dropTable(db, ifExists);
        GameSubjectContentDao.dropTable(db, ifExists);
        GameSubjectItemDao.dropTable(db, ifExists);
        GameSubjectiveItemDao.dropTable(db, ifExists);
        HomeKnowCommendDao.dropTable(db, ifExists);
        HomeKnowContentDao.dropTable(db, ifExists);
        HomeKnowDataDao.dropTable(db, ifExists);
        HomeKnowFunctionDao.dropTable(db, ifExists);
        HomeKnowHistoryDao.dropTable(db, ifExists);
        HomeKnowHistoryFunctionDao.dropTable(db, ifExists);
        HomeKnowItemDao.dropTable(db, ifExists);
        LadderCommentBeanDao.dropTable(db, ifExists);
        LadderCommentReplyBeanDao.dropTable(db, ifExists);
        LadderDataBeanDao.dropTable(db, ifExists);
        LadderDifficultyCommentBeanDao.dropTable(db, ifExists);
        LadderDifficultyDataBeanDao.dropTable(db, ifExists);
        LadderHelpBeanDao.dropTable(db, ifExists);
        LadderTopicBeanDao.dropTable(db, ifExists);
        OverViewListContentDao.dropTable(db, ifExists);
        OverViewListItemDao.dropTable(db, ifExists);
        LadderRankDataBeanDao.dropTable(db, ifExists);
        LadderRankSettingBeanDao.dropTable(db, ifExists);
        LadderResultBeanDao.dropTable(db, ifExists);
        MineDataBeanDao.dropTable(db, ifExists);
        MineLevelPrivilegeBeanDao.dropTable(db, ifExists);
        MineFeedbackBeanDao.dropTable(db, ifExists);
    }

    /**
     * WARNING: Drops all table on Upgrade! Use only during development.
     * Convenience method using a {@link DevOpenHelper}.
     */
    public static DaoSession newDevSession(Context context, String name) {
        Database db = new DevOpenHelper(context, name).getWritableDb();
        DaoMaster daoMaster = new DaoMaster(db);
        return daoMaster.newSession();
    }

    public DaoMaster(SQLiteDatabase db) {
        this(new StandardDatabase(db));
    }

    public DaoMaster(Database db) {
        super(db, SCHEMA_VERSION);
        registerDaoClass(HelpCommendItemDao.class);
        registerDaoClass(HelpContentItemDao.class);
        registerDaoClass(GameContentDao.class);
        registerDaoClass(GameFillItemDao.class);
        registerDaoClass(GameMultipleItemDao.class);
        registerDaoClass(GameRadioItemDao.class);
        registerDaoClass(GameSignContentDao.class);
        registerDaoClass(GameSubjectContentDao.class);
        registerDaoClass(GameSubjectItemDao.class);
        registerDaoClass(GameSubjectiveItemDao.class);
        registerDaoClass(HomeKnowCommendDao.class);
        registerDaoClass(HomeKnowContentDao.class);
        registerDaoClass(HomeKnowDataDao.class);
        registerDaoClass(HomeKnowFunctionDao.class);
        registerDaoClass(HomeKnowHistoryDao.class);
        registerDaoClass(HomeKnowHistoryFunctionDao.class);
        registerDaoClass(HomeKnowItemDao.class);
        registerDaoClass(LadderCommentBeanDao.class);
        registerDaoClass(LadderCommentReplyBeanDao.class);
        registerDaoClass(LadderDataBeanDao.class);
        registerDaoClass(LadderDifficultyCommentBeanDao.class);
        registerDaoClass(LadderDifficultyDataBeanDao.class);
        registerDaoClass(LadderHelpBeanDao.class);
        registerDaoClass(LadderTopicBeanDao.class);
        registerDaoClass(OverViewListContentDao.class);
        registerDaoClass(OverViewListItemDao.class);
        registerDaoClass(LadderRankDataBeanDao.class);
        registerDaoClass(LadderRankSettingBeanDao.class);
        registerDaoClass(LadderResultBeanDao.class);
        registerDaoClass(MineDataBeanDao.class);
        registerDaoClass(MineLevelPrivilegeBeanDao.class);
        registerDaoClass(MineFeedbackBeanDao.class);
    }

    public DaoSession newSession() {
        return new DaoSession(db, IdentityScopeType.Session, daoConfigMap);
    }

    public DaoSession newSession(IdentityScopeType type) {
        return new DaoSession(db, type, daoConfigMap);
    }

    /**
     * Calls {@link #createAllTables(Database, boolean)} in {@link #onCreate(Database)} -
     */
    public static abstract class OpenHelper extends DatabaseOpenHelper {
        public OpenHelper(Context context, String name) {
            super(context, name, SCHEMA_VERSION);
        }

        public OpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory, SCHEMA_VERSION);
        }

        @Override
        public void onCreate(Database db) {
            Log.i("greenDAO", "Creating tables for schema version " + SCHEMA_VERSION);
            createAllTables(db, false);
        }
    }

    /** WARNING: Drops all table on Upgrade! Use only during development. */
    public static class DevOpenHelper extends OpenHelper {
        public DevOpenHelper(Context context, String name) {
            super(context, name);
        }

        public DevOpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory);
        }

        @Override
        public void onUpgrade(Database db, int oldVersion, int newVersion) {
            Log.i("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion + " by dropping all tables");
            dropAllTables(db, true);
            onCreate(db);
        }
    }

}
