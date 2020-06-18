package com.vagrancys.learningassistant.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.vargancys.learningassistant.db.mine.KnowLedgeDataBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "KNOW_LEDGE_DATA_BEAN".
*/
public class KnowLedgeDataBeanDao extends AbstractDao<KnowLedgeDataBean, Long> {

    public static final String TABLENAME = "KNOW_LEDGE_DATA_BEAN";

    /**
     * Properties of entity KnowLedgeDataBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property MineId = new Property(1, long.class, "mineId", false, "MINE_ID");
        public final static Property Title = new Property(2, String.class, "title", false, "TITLE");
        public final static Property Count = new Property(3, String.class, "count", false, "COUNT");
        public final static Property Quality = new Property(4, int.class, "quality", false, "QUALITY");
        public final static Property Time = new Property(5, int.class, "time", false, "TIME");
        public final static Property People = new Property(6, int.class, "people", false, "PEOPLE");
        public final static Property Level = new Property(7, int.class, "level", false, "LEVEL");
        public final static Property Prize = new Property(8, int.class, "prize", false, "PRIZE");
        public final static Property Type = new Property(9, int.class, "type", false, "TYPE");
    }


    public KnowLedgeDataBeanDao(DaoConfig config) {
        super(config);
    }
    
    public KnowLedgeDataBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"KNOW_LEDGE_DATA_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY UNIQUE ," + // 0: id
                "\"MINE_ID\" INTEGER NOT NULL ," + // 1: mineId
                "\"TITLE\" TEXT," + // 2: title
                "\"COUNT\" TEXT," + // 3: count
                "\"QUALITY\" INTEGER NOT NULL ," + // 4: quality
                "\"TIME\" INTEGER NOT NULL ," + // 5: time
                "\"PEOPLE\" INTEGER NOT NULL ," + // 6: people
                "\"LEVEL\" INTEGER NOT NULL ," + // 7: level
                "\"PRIZE\" INTEGER NOT NULL ," + // 8: prize
                "\"TYPE\" INTEGER NOT NULL );"); // 9: type
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"KNOW_LEDGE_DATA_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, KnowLedgeDataBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getMineId());
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(3, title);
        }
 
        String count = entity.getCount();
        if (count != null) {
            stmt.bindString(4, count);
        }
        stmt.bindLong(5, entity.getQuality());
        stmt.bindLong(6, entity.getTime());
        stmt.bindLong(7, entity.getPeople());
        stmt.bindLong(8, entity.getLevel());
        stmt.bindLong(9, entity.getPrize());
        stmt.bindLong(10, entity.getType());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, KnowLedgeDataBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getMineId());
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(3, title);
        }
 
        String count = entity.getCount();
        if (count != null) {
            stmt.bindString(4, count);
        }
        stmt.bindLong(5, entity.getQuality());
        stmt.bindLong(6, entity.getTime());
        stmt.bindLong(7, entity.getPeople());
        stmt.bindLong(8, entity.getLevel());
        stmt.bindLong(9, entity.getPrize());
        stmt.bindLong(10, entity.getType());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public KnowLedgeDataBean readEntity(Cursor cursor, int offset) {
        KnowLedgeDataBean entity = new KnowLedgeDataBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getLong(offset + 1), // mineId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // title
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // count
            cursor.getInt(offset + 4), // quality
            cursor.getInt(offset + 5), // time
            cursor.getInt(offset + 6), // people
            cursor.getInt(offset + 7), // level
            cursor.getInt(offset + 8), // prize
            cursor.getInt(offset + 9) // type
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, KnowLedgeDataBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setMineId(cursor.getLong(offset + 1));
        entity.setTitle(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setCount(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setQuality(cursor.getInt(offset + 4));
        entity.setTime(cursor.getInt(offset + 5));
        entity.setPeople(cursor.getInt(offset + 6));
        entity.setLevel(cursor.getInt(offset + 7));
        entity.setPrize(cursor.getInt(offset + 8));
        entity.setType(cursor.getInt(offset + 9));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(KnowLedgeDataBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(KnowLedgeDataBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(KnowLedgeDataBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
