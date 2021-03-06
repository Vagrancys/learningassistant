package com.vagrancys.learningassistant.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.vargancys.learningassistant.bean.ladder.LadderRankDataBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "LADDER_RANK_DATA_BEAN".
*/
public class LadderRankDataBeanDao extends AbstractDao<LadderRankDataBean, Long> {

    public static final String TABLENAME = "LADDER_RANK_DATA_BEAN";

    /**
     * Properties of entity LadderRankDataBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Zone = new Property(1, int.class, "zone", false, "ZONE");
        public final static Property Unique = new Property(2, int.class, "unique", false, "UNIQUE");
        public final static Property Author = new Property(3, String.class, "author", false, "AUTHOR");
        public final static Property Avatar = new Property(4, String.class, "avatar", false, "AVATAR");
        public final static Property Level = new Property(5, String.class, "level", false, "LEVEL");
        public final static Property Total = new Property(6, int.class, "total", false, "TOTAL");
        public final static Property Floor = new Property(7, int.class, "floor", false, "FLOOR");
        public final static Property Time = new Property(8, String.class, "time", false, "TIME");
    }


    public LadderRankDataBeanDao(DaoConfig config) {
        super(config);
    }
    
    public LadderRankDataBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"LADDER_RANK_DATA_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY UNIQUE ," + // 0: id
                "\"ZONE\" INTEGER NOT NULL ," + // 1: zone
                "\"UNIQUE\" INTEGER NOT NULL ," + // 2: unique
                "\"AUTHOR\" TEXT," + // 3: author
                "\"AVATAR\" TEXT," + // 4: avatar
                "\"LEVEL\" TEXT," + // 5: level
                "\"TOTAL\" INTEGER NOT NULL ," + // 6: total
                "\"FLOOR\" INTEGER NOT NULL ," + // 7: floor
                "\"TIME\" TEXT);"); // 8: time
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"LADDER_RANK_DATA_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, LadderRankDataBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getZone());
        stmt.bindLong(3, entity.getUnique());
 
        String author = entity.getAuthor();
        if (author != null) {
            stmt.bindString(4, author);
        }
 
        String avatar = entity.getAvatar();
        if (avatar != null) {
            stmt.bindString(5, avatar);
        }
 
        String level = entity.getLevel();
        if (level != null) {
            stmt.bindString(6, level);
        }
        stmt.bindLong(7, entity.getTotal());
        stmt.bindLong(8, entity.getFloor());
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(9, time);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, LadderRankDataBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getZone());
        stmt.bindLong(3, entity.getUnique());
 
        String author = entity.getAuthor();
        if (author != null) {
            stmt.bindString(4, author);
        }
 
        String avatar = entity.getAvatar();
        if (avatar != null) {
            stmt.bindString(5, avatar);
        }
 
        String level = entity.getLevel();
        if (level != null) {
            stmt.bindString(6, level);
        }
        stmt.bindLong(7, entity.getTotal());
        stmt.bindLong(8, entity.getFloor());
 
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
    public LadderRankDataBean readEntity(Cursor cursor, int offset) {
        LadderRankDataBean entity = new LadderRankDataBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getInt(offset + 1), // zone
            cursor.getInt(offset + 2), // unique
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // author
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // avatar
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // level
            cursor.getInt(offset + 6), // total
            cursor.getInt(offset + 7), // floor
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8) // time
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, LadderRankDataBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setZone(cursor.getInt(offset + 1));
        entity.setUnique(cursor.getInt(offset + 2));
        entity.setAuthor(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setAvatar(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setLevel(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setTotal(cursor.getInt(offset + 6));
        entity.setFloor(cursor.getInt(offset + 7));
        entity.setTime(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(LadderRankDataBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(LadderRankDataBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(LadderRankDataBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
