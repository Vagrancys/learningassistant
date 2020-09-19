package com.vagrancys.learningassistant.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.vargancys.learningassistant.bean.ladder.LadderDataBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "LADDER_DATA_BEAN".
*/
public class LadderDataBeanDao extends AbstractDao<LadderDataBean, Long> {

    public static final String TABLENAME = "LADDER_DATA_BEAN";

    /**
     * Properties of entity LadderDataBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Type = new Property(1, int.class, "type", false, "TYPE");
        public final static Property Name = new Property(2, String.class, "name", false, "NAME");
        public final static Property Title = new Property(3, String.class, "title", false, "TITLE");
        public final static Property Title_level = new Property(4, int.class, "title_level", false, "TITLE_LEVEL");
        public final static Property Upgrade = new Property(5, int.class, "upgrade", false, "UPGRADE");
        public final static Property Upgrade_total = new Property(6, int.class, "upgrade_total", false, "UPGRADE_TOTAL");
        public final static Property Difficulty = new Property(7, String.class, "difficulty", false, "DIFFICULTY");
        public final static Property Highest = new Property(8, int.class, "highest", false, "HIGHEST");
        public final static Property Fail = new Property(9, int.class, "fail", false, "FAIL");
        public final static Property Time = new Property(10, long.class, "time", false, "TIME");
        public final static Property Total_time = new Property(11, long.class, "total_time", false, "TOTAL_TIME");
        public final static Property Total = new Property(12, int.class, "total", false, "TOTAL");
        public final static Property Master = new Property(13, int.class, "master", false, "MASTER");
        public final static Property Chance = new Property(14, String.class, "chance", false, "CHANCE");
    }


    public LadderDataBeanDao(DaoConfig config) {
        super(config);
    }
    
    public LadderDataBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"LADDER_DATA_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY UNIQUE ," + // 0: id
                "\"TYPE\" INTEGER NOT NULL ," + // 1: type
                "\"NAME\" TEXT," + // 2: name
                "\"TITLE\" TEXT," + // 3: title
                "\"TITLE_LEVEL\" INTEGER NOT NULL ," + // 4: title_level
                "\"UPGRADE\" INTEGER NOT NULL ," + // 5: upgrade
                "\"UPGRADE_TOTAL\" INTEGER NOT NULL ," + // 6: upgrade_total
                "\"DIFFICULTY\" TEXT," + // 7: difficulty
                "\"HIGHEST\" INTEGER NOT NULL ," + // 8: highest
                "\"FAIL\" INTEGER NOT NULL ," + // 9: fail
                "\"TIME\" INTEGER NOT NULL ," + // 10: time
                "\"TOTAL_TIME\" INTEGER NOT NULL ," + // 11: total_time
                "\"TOTAL\" INTEGER NOT NULL ," + // 12: total
                "\"MASTER\" INTEGER NOT NULL ," + // 13: master
                "\"CHANCE\" TEXT);"); // 14: chance
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"LADDER_DATA_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, LadderDataBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getType());
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(4, title);
        }
        stmt.bindLong(5, entity.getTitle_level());
        stmt.bindLong(6, entity.getUpgrade());
        stmt.bindLong(7, entity.getUpgrade_total());
 
        String difficulty = entity.getDifficulty();
        if (difficulty != null) {
            stmt.bindString(8, difficulty);
        }
        stmt.bindLong(9, entity.getHighest());
        stmt.bindLong(10, entity.getFail());
        stmt.bindLong(11, entity.getTime());
        stmt.bindLong(12, entity.getTotal_time());
        stmt.bindLong(13, entity.getTotal());
        stmt.bindLong(14, entity.getMaster());
 
        String chance = entity.getChance();
        if (chance != null) {
            stmt.bindString(15, chance);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, LadderDataBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getType());
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(4, title);
        }
        stmt.bindLong(5, entity.getTitle_level());
        stmt.bindLong(6, entity.getUpgrade());
        stmt.bindLong(7, entity.getUpgrade_total());
 
        String difficulty = entity.getDifficulty();
        if (difficulty != null) {
            stmt.bindString(8, difficulty);
        }
        stmt.bindLong(9, entity.getHighest());
        stmt.bindLong(10, entity.getFail());
        stmt.bindLong(11, entity.getTime());
        stmt.bindLong(12, entity.getTotal_time());
        stmt.bindLong(13, entity.getTotal());
        stmt.bindLong(14, entity.getMaster());
 
        String chance = entity.getChance();
        if (chance != null) {
            stmt.bindString(15, chance);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public LadderDataBean readEntity(Cursor cursor, int offset) {
        LadderDataBean entity = new LadderDataBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getInt(offset + 1), // type
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // name
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // title
            cursor.getInt(offset + 4), // title_level
            cursor.getInt(offset + 5), // upgrade
            cursor.getInt(offset + 6), // upgrade_total
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // difficulty
            cursor.getInt(offset + 8), // highest
            cursor.getInt(offset + 9), // fail
            cursor.getLong(offset + 10), // time
            cursor.getLong(offset + 11), // total_time
            cursor.getInt(offset + 12), // total
            cursor.getInt(offset + 13), // master
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14) // chance
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, LadderDataBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setType(cursor.getInt(offset + 1));
        entity.setName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setTitle(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setTitle_level(cursor.getInt(offset + 4));
        entity.setUpgrade(cursor.getInt(offset + 5));
        entity.setUpgrade_total(cursor.getInt(offset + 6));
        entity.setDifficulty(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setHighest(cursor.getInt(offset + 8));
        entity.setFail(cursor.getInt(offset + 9));
        entity.setTime(cursor.getLong(offset + 10));
        entity.setTotal_time(cursor.getLong(offset + 11));
        entity.setTotal(cursor.getInt(offset + 12));
        entity.setMaster(cursor.getInt(offset + 13));
        entity.setChance(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(LadderDataBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(LadderDataBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(LadderDataBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
