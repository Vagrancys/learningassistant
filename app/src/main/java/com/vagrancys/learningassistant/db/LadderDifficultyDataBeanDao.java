package com.vagrancys.learningassistant.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.vargancys.learningassistant.bean.ladder.LadderDifficultyDataBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "LADDER_DIFFICULTY_DATA_BEAN".
*/
public class LadderDifficultyDataBeanDao extends AbstractDao<LadderDifficultyDataBean, Long> {

    public static final String TABLENAME = "LADDER_DIFFICULTY_DATA_BEAN";

    /**
     * Properties of entity LadderDifficultyDataBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Type = new Property(1, int.class, "type", false, "TYPE");
        public final static Property Title = new Property(2, String.class, "title", false, "TITLE");
        public final static Property Content = new Property(3, String.class, "content", false, "CONTENT");
        public final static Property Successful = new Property(4, String.class, "successful", false, "SUCCESSFUL");
        public final static Property People = new Property(5, int.class, "people", false, "PEOPLE");
        public final static Property Time = new Property(6, String.class, "time", false, "TIME");
    }

    private DaoSession daoSession;


    public LadderDifficultyDataBeanDao(DaoConfig config) {
        super(config);
    }
    
    public LadderDifficultyDataBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"LADDER_DIFFICULTY_DATA_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY UNIQUE ," + // 0: id
                "\"TYPE\" INTEGER NOT NULL ," + // 1: type
                "\"TITLE\" TEXT," + // 2: title
                "\"CONTENT\" TEXT," + // 3: content
                "\"SUCCESSFUL\" TEXT," + // 4: successful
                "\"PEOPLE\" INTEGER NOT NULL ," + // 5: people
                "\"TIME\" TEXT);"); // 6: time
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"LADDER_DIFFICULTY_DATA_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, LadderDifficultyDataBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getType());
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(3, title);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(4, content);
        }
 
        String successful = entity.getSuccessful();
        if (successful != null) {
            stmt.bindString(5, successful);
        }
        stmt.bindLong(6, entity.getPeople());
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(7, time);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, LadderDifficultyDataBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getType());
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(3, title);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(4, content);
        }
 
        String successful = entity.getSuccessful();
        if (successful != null) {
            stmt.bindString(5, successful);
        }
        stmt.bindLong(6, entity.getPeople());
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(7, time);
        }
    }

    @Override
    protected final void attachEntity(LadderDifficultyDataBean entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public LadderDifficultyDataBean readEntity(Cursor cursor, int offset) {
        LadderDifficultyDataBean entity = new LadderDifficultyDataBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getInt(offset + 1), // type
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // title
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // content
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // successful
            cursor.getInt(offset + 5), // people
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6) // time
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, LadderDifficultyDataBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setType(cursor.getInt(offset + 1));
        entity.setTitle(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setContent(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setSuccessful(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setPeople(cursor.getInt(offset + 5));
        entity.setTime(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(LadderDifficultyDataBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(LadderDifficultyDataBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(LadderDifficultyDataBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
