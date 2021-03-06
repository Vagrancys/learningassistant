package com.vagrancys.learningassistant.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.vargancys.learningassistant.bean.mine.ChallengeDataBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CHALLENGE_DATA_BEAN".
*/
public class ChallengeDataBeanDao extends AbstractDao<ChallengeDataBean, Long> {

    public static final String TABLENAME = "CHALLENGE_DATA_BEAN";

    /**
     * Properties of entity ChallengeDataBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property MineId = new Property(1, long.class, "mineId", false, "MINE_ID");
        public final static Property Type = new Property(2, int.class, "type", false, "TYPE");
        public final static Property Title = new Property(3, String.class, "title", false, "TITLE");
        public final static Property Count = new Property(4, int.class, "count", false, "COUNT");
        public final static Property Success = new Property(5, int.class, "success", false, "SUCCESS");
        public final static Property Fail = new Property(6, int.class, "fail", false, "FAIL");
        public final static Property Highest = new Property(7, int.class, "highest", false, "HIGHEST");
        public final static Property Time = new Property(8, int.class, "time", false, "TIME");
        public final static Property Number = new Property(9, int.class, "number", false, "NUMBER");
        public final static Property Difficulty = new Property(10, int.class, "difficulty", false, "DIFFICULTY");
    }


    public ChallengeDataBeanDao(DaoConfig config) {
        super(config);
    }
    
    public ChallengeDataBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CHALLENGE_DATA_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY UNIQUE ," + // 0: id
                "\"MINE_ID\" INTEGER NOT NULL ," + // 1: mineId
                "\"TYPE\" INTEGER NOT NULL ," + // 2: type
                "\"TITLE\" TEXT," + // 3: title
                "\"COUNT\" INTEGER NOT NULL ," + // 4: count
                "\"SUCCESS\" INTEGER NOT NULL ," + // 5: success
                "\"FAIL\" INTEGER NOT NULL ," + // 6: fail
                "\"HIGHEST\" INTEGER NOT NULL ," + // 7: highest
                "\"TIME\" INTEGER NOT NULL ," + // 8: time
                "\"NUMBER\" INTEGER NOT NULL ," + // 9: number
                "\"DIFFICULTY\" INTEGER NOT NULL );"); // 10: difficulty
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CHALLENGE_DATA_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ChallengeDataBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getMineId());
        stmt.bindLong(3, entity.getType());
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(4, title);
        }
        stmt.bindLong(5, entity.getCount());
        stmt.bindLong(6, entity.getSuccess());
        stmt.bindLong(7, entity.getFail());
        stmt.bindLong(8, entity.getHighest());
        stmt.bindLong(9, entity.getTime());
        stmt.bindLong(10, entity.getNumber());
        stmt.bindLong(11, entity.getDifficulty());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ChallengeDataBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getMineId());
        stmt.bindLong(3, entity.getType());
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(4, title);
        }
        stmt.bindLong(5, entity.getCount());
        stmt.bindLong(6, entity.getSuccess());
        stmt.bindLong(7, entity.getFail());
        stmt.bindLong(8, entity.getHighest());
        stmt.bindLong(9, entity.getTime());
        stmt.bindLong(10, entity.getNumber());
        stmt.bindLong(11, entity.getDifficulty());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public ChallengeDataBean readEntity(Cursor cursor, int offset) {
        ChallengeDataBean entity = new ChallengeDataBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getLong(offset + 1), // mineId
            cursor.getInt(offset + 2), // type
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // title
            cursor.getInt(offset + 4), // count
            cursor.getInt(offset + 5), // success
            cursor.getInt(offset + 6), // fail
            cursor.getInt(offset + 7), // highest
            cursor.getInt(offset + 8), // time
            cursor.getInt(offset + 9), // number
            cursor.getInt(offset + 10) // difficulty
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ChallengeDataBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setMineId(cursor.getLong(offset + 1));
        entity.setType(cursor.getInt(offset + 2));
        entity.setTitle(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setCount(cursor.getInt(offset + 4));
        entity.setSuccess(cursor.getInt(offset + 5));
        entity.setFail(cursor.getInt(offset + 6));
        entity.setHighest(cursor.getInt(offset + 7));
        entity.setTime(cursor.getInt(offset + 8));
        entity.setNumber(cursor.getInt(offset + 9));
        entity.setDifficulty(cursor.getInt(offset + 10));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(ChallengeDataBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(ChallengeDataBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ChallengeDataBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
