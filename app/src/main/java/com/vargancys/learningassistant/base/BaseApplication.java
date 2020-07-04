package com.vargancys.learningassistant.base;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.vagrancys.learningassistant.db.DaoMaster;
import com.vagrancys.learningassistant.db.DaoSession;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/07
 * version:1.0
 */
public class BaseApplication extends Application {
    private static BaseApplication instance;
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        setDatabase();
    }

    private void setDatabase() {
        mHelper = new DaoMaster.DevOpenHelper(this,"study-db",null);
        db = mHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }

    public static BaseApplication getInstance() {
        return instance;
    }
}
