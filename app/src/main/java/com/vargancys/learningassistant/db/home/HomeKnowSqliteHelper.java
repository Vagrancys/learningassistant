package com.vargancys.learningassistant.db.home;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/03/07
 * version:1.0
 */
public class HomeKnowSqliteHelper extends SQLiteOpenHelper {
    public static final String CREATE_HOME_KNOW = "create table homeknow ("+"id integer primary key autoincrement, "
            +"item_id integer,activity text,have boolean, "
            +"title text,level integer,summary text, "
            +"progress integer,count integer,max integer,"
            +"masterLevel integer,studyTitle text)";

    public HomeKnowSqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_HOME_KNOW);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion){
            case 1:
                break;
            case 2:
                //添加指定的列字段
                //db.execSQL("alter table comment add column publishdate integer");
                break;
            default:
                break;
        }
    }
}
