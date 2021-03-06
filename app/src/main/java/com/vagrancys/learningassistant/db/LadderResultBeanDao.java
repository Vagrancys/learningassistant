package com.vagrancys.learningassistant.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.vargancys.learningassistant.bean.ladder.LadderResultBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "LADDER_RESULT_BEAN".
*/
public class LadderResultBeanDao extends AbstractDao<LadderResultBean, Long> {

    public static final String TABLENAME = "LADDER_RESULT_BEAN";

    /**
     * Properties of entity LadderResultBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Start_time = new Property(1, long.class, "start_time", false, "START_TIME");
        public final static Property Day = new Property(2, int.class, "day", false, "DAY");
        public final static Property Total = new Property(3, int.class, "total", false, "TOTAL");
        public final static Property Fail_count = new Property(4, int.class, "fail_count", false, "FAIL_COUNT");
        public final static Property Win_count = new Property(5, int.class, "win_count", false, "WIN_COUNT");
        public final static Property Long_time = new Property(6, long.class, "long_time", false, "LONG_TIME");
        public final static Property Short_time = new Property(7, long.class, "short_time", false, "SHORT_TIME");
        public final static Property Highest_count = new Property(8, int.class, "highest_count", false, "HIGHEST_COUNT");
        public final static Property Score = new Property(9, int.class, "score", false, "SCORE");
    }


    public LadderResultBeanDao(DaoConfig config) {
        super(config);
    }
    
    public LadderResultBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"LADDER_RESULT_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY UNIQUE ," + // 0: id
                "\"START_TIME\" INTEGER NOT NULL ," + // 1: start_time
                "\"DAY\" INTEGER NOT NULL ," + // 2: day
                "\"TOTAL\" INTEGER NOT NULL ," + // 3: total
                "\"FAIL_COUNT\" INTEGER NOT NULL ," + // 4: fail_count
                "\"WIN_COUNT\" INTEGER NOT NULL ," + // 5: win_count
                "\"LONG_TIME\" INTEGER NOT NULL ," + // 6: long_time
                "\"SHORT_TIME\" INTEGER NOT NULL ," + // 7: short_time
                "\"HIGHEST_COUNT\" INTEGER NOT NULL ," + // 8: highest_count
                "\"SCORE\" INTEGER NOT NULL );"); // 9: score
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"LADDER_RESULT_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, LadderResultBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getStart_time());
        stmt.bindLong(3, entity.getDay());
        stmt.bindLong(4, entity.getTotal());
        stmt.bindLong(5, entity.getFail_count());
        stmt.bindLong(6, entity.getWin_count());
        stmt.bindLong(7, entity.getLong_time());
        stmt.bindLong(8, entity.getShort_time());
        stmt.bindLong(9, entity.getHighest_count());
        stmt.bindLong(10, entity.getScore());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, LadderResultBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getStart_time());
        stmt.bindLong(3, entity.getDay());
        stmt.bindLong(4, entity.getTotal());
        stmt.bindLong(5, entity.getFail_count());
        stmt.bindLong(6, entity.getWin_count());
        stmt.bindLong(7, entity.getLong_time());
        stmt.bindLong(8, entity.getShort_time());
        stmt.bindLong(9, entity.getHighest_count());
        stmt.bindLong(10, entity.getScore());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public LadderResultBean readEntity(Cursor cursor, int offset) {
        LadderResultBean entity = new LadderResultBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getLong(offset + 1), // start_time
            cursor.getInt(offset + 2), // day
            cursor.getInt(offset + 3), // total
            cursor.getInt(offset + 4), // fail_count
            cursor.getInt(offset + 5), // win_count
            cursor.getLong(offset + 6), // long_time
            cursor.getLong(offset + 7), // short_time
            cursor.getInt(offset + 8), // highest_count
            cursor.getInt(offset + 9) // score
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, LadderResultBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setStart_time(cursor.getLong(offset + 1));
        entity.setDay(cursor.getInt(offset + 2));
        entity.setTotal(cursor.getInt(offset + 3));
        entity.setFail_count(cursor.getInt(offset + 4));
        entity.setWin_count(cursor.getInt(offset + 5));
        entity.setLong_time(cursor.getLong(offset + 6));
        entity.setShort_time(cursor.getLong(offset + 7));
        entity.setHighest_count(cursor.getInt(offset + 8));
        entity.setScore(cursor.getInt(offset + 9));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(LadderResultBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(LadderResultBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(LadderResultBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
