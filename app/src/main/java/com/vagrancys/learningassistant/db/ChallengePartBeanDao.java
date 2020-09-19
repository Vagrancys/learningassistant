package com.vagrancys.learningassistant.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.vargancys.learningassistant.bean.mine.ChallengePartBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CHALLENGE_PART_BEAN".
*/
public class ChallengePartBeanDao extends AbstractDao<ChallengePartBean, Long> {

    public static final String TABLENAME = "CHALLENGE_PART_BEAN";

    /**
     * Properties of entity ChallengePartBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property MineId = new Property(1, long.class, "mineId", false, "MINE_ID");
        public final static Property Type = new Property(2, int.class, "type", false, "TYPE");
        public final static Property Title = new Property(3, String.class, "title", false, "TITLE");
        public final static Property Serial = new Property(4, int.class, "serial", false, "SERIAL");
        public final static Property Summary = new Property(5, String.class, "summary", false, "SUMMARY");
        public final static Property Highest = new Property(6, int.class, "highest", false, "HIGHEST");
        public final static Property Time = new Property(7, long.class, "time", false, "TIME");
        public final static Property Situation = new Property(8, boolean.class, "situation", false, "SITUATION");
    }


    public ChallengePartBeanDao(DaoConfig config) {
        super(config);
    }
    
    public ChallengePartBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CHALLENGE_PART_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY UNIQUE ," + // 0: id
                "\"MINE_ID\" INTEGER NOT NULL ," + // 1: mineId
                "\"TYPE\" INTEGER NOT NULL ," + // 2: type
                "\"TITLE\" TEXT," + // 3: title
                "\"SERIAL\" INTEGER NOT NULL ," + // 4: serial
                "\"SUMMARY\" TEXT," + // 5: summary
                "\"HIGHEST\" INTEGER NOT NULL ," + // 6: highest
                "\"TIME\" INTEGER NOT NULL ," + // 7: time
                "\"SITUATION\" INTEGER NOT NULL );"); // 8: situation
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CHALLENGE_PART_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ChallengePartBean entity) {
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
        stmt.bindLong(5, entity.getSerial());
 
        String summary = entity.getSummary();
        if (summary != null) {
            stmt.bindString(6, summary);
        }
        stmt.bindLong(7, entity.getHighest());
        stmt.bindLong(8, entity.getTime());
        stmt.bindLong(9, entity.getSituation() ? 1L: 0L);
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ChallengePartBean entity) {
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
        stmt.bindLong(5, entity.getSerial());
 
        String summary = entity.getSummary();
        if (summary != null) {
            stmt.bindString(6, summary);
        }
        stmt.bindLong(7, entity.getHighest());
        stmt.bindLong(8, entity.getTime());
        stmt.bindLong(9, entity.getSituation() ? 1L: 0L);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public ChallengePartBean readEntity(Cursor cursor, int offset) {
        ChallengePartBean entity = new ChallengePartBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getLong(offset + 1), // mineId
            cursor.getInt(offset + 2), // type
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // title
            cursor.getInt(offset + 4), // serial
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // summary
            cursor.getInt(offset + 6), // highest
            cursor.getLong(offset + 7), // time
            cursor.getShort(offset + 8) != 0 // situation
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ChallengePartBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setMineId(cursor.getLong(offset + 1));
        entity.setType(cursor.getInt(offset + 2));
        entity.setTitle(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setSerial(cursor.getInt(offset + 4));
        entity.setSummary(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setHighest(cursor.getInt(offset + 6));
        entity.setTime(cursor.getLong(offset + 7));
        entity.setSituation(cursor.getShort(offset + 8) != 0);
     }
    
    @Override
    protected final Long updateKeyAfterInsert(ChallengePartBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(ChallengePartBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ChallengePartBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
