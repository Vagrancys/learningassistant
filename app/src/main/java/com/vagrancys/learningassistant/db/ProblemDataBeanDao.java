package com.vagrancys.learningassistant.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.vargancys.learningassistant.bean.mine.ProblemDataBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PROBLEM_DATA_BEAN".
*/
public class ProblemDataBeanDao extends AbstractDao<ProblemDataBean, Long> {

    public static final String TABLENAME = "PROBLEM_DATA_BEAN";

    /**
     * Properties of entity ProblemDataBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property MineId = new Property(1, long.class, "mineId", false, "MINE_ID");
        public final static Property Type = new Property(2, int.class, "type", false, "TYPE");
        public final static Property Title = new Property(3, String.class, "title", false, "TITLE");
        public final static Property Level = new Property(4, String.class, "level", false, "LEVEL");
        public final static Property People = new Property(5, String.class, "people", false, "PEOPLE");
        public final static Property Use = new Property(6, String.class, "use", false, "USE");
        public final static Property Count = new Property(7, String.class, "count", false, "COUNT");
        public final static Property Time = new Property(8, String.class, "time", false, "TIME");
    }


    public ProblemDataBeanDao(DaoConfig config) {
        super(config);
    }
    
    public ProblemDataBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PROBLEM_DATA_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY UNIQUE ," + // 0: id
                "\"MINE_ID\" INTEGER NOT NULL ," + // 1: mineId
                "\"TYPE\" INTEGER NOT NULL ," + // 2: type
                "\"TITLE\" TEXT," + // 3: title
                "\"LEVEL\" TEXT," + // 4: level
                "\"PEOPLE\" TEXT," + // 5: people
                "\"USE\" TEXT," + // 6: use
                "\"COUNT\" TEXT," + // 7: count
                "\"TIME\" TEXT);"); // 8: time
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PROBLEM_DATA_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ProblemDataBean entity) {
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
 
        String level = entity.getLevel();
        if (level != null) {
            stmt.bindString(5, level);
        }
 
        String people = entity.getPeople();
        if (people != null) {
            stmt.bindString(6, people);
        }
 
        String use = entity.getUse();
        if (use != null) {
            stmt.bindString(7, use);
        }
 
        String count = entity.getCount();
        if (count != null) {
            stmt.bindString(8, count);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(9, time);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ProblemDataBean entity) {
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
 
        String level = entity.getLevel();
        if (level != null) {
            stmt.bindString(5, level);
        }
 
        String people = entity.getPeople();
        if (people != null) {
            stmt.bindString(6, people);
        }
 
        String use = entity.getUse();
        if (use != null) {
            stmt.bindString(7, use);
        }
 
        String count = entity.getCount();
        if (count != null) {
            stmt.bindString(8, count);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(9, time);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public ProblemDataBean readEntity(Cursor cursor, int offset) {
        ProblemDataBean entity = new ProblemDataBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getLong(offset + 1), // mineId
            cursor.getInt(offset + 2), // type
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // title
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // level
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // people
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // use
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // count
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8) // time
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ProblemDataBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setMineId(cursor.getLong(offset + 1));
        entity.setType(cursor.getInt(offset + 2));
        entity.setTitle(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setLevel(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setPeople(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setUse(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setCount(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setTime(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(ProblemDataBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(ProblemDataBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ProblemDataBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
