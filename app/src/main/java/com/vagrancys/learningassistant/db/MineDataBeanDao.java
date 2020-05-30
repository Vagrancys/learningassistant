package com.vagrancys.learningassistant.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.vargancys.learningassistant.db.mine.MineDataBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "MINE_DATA_BEAN".
*/
public class MineDataBeanDao extends AbstractDao<MineDataBean, Long> {

    public static final String TABLENAME = "MINE_DATA_BEAN";

    /**
     * Properties of entity MineDataBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property Level = new Property(2, int.class, "level", false, "LEVEL");
        public final static Property Level_name = new Property(3, String.class, "level_name", false, "LEVEL_NAME");
        public final static Property Area = new Property(4, String.class, "area", false, "AREA");
        public final static Property Highest = new Property(5, int.class, "highest", false, "HIGHEST");
        public final static Property Ladder_day = new Property(6, int.class, "ladder_day", false, "LADDER_DAY");
        public final static Property Level_total = new Property(7, int.class, "level_total", false, "LEVEL_TOTAL");
        public final static Property Level_current = new Property(8, int.class, "level_current", false, "LEVEL_CURRENT");
        public final static Property Real_level = new Property(9, int.class, "real_level", false, "REAL_LEVEL");
        public final static Property Knowledge = new Property(10, int.class, "knowledge", false, "KNOWLEDGE");
        public final static Property Influence = new Property(11, int.class, "influence", false, "INFLUENCE");
        public final static Property Money = new Property(12, int.class, "money", false, "MONEY");
        public final static Property Day = new Property(13, int.class, "day", false, "DAY");
        public final static Property Quality = new Property(14, int.class, "quality", false, "QUALITY");
        public final static Property Result = new Property(15, int.class, "result", false, "RESULT");
        public final static Property System_count = new Property(16, int.class, "system_count", false, "SYSTEM_COUNT");
        public final static Property Knowledge_total = new Property(17, int.class, "knowledge_total", false, "KNOWLEDGE_TOTAL");
        public final static Property People_count = new Property(18, int.class, "people_count", false, "PEOPLE_COUNT");
        public final static Property Speciality_total = new Property(19, int.class, "speciality_total", false, "SPECIALITY_TOTAL");
        public final static Property Level_highest = new Property(20, int.class, "level_highest", false, "LEVEL_HIGHEST");
        public final static Property Level_minimum = new Property(21, int.class, "level_minimum", false, "LEVEL_MINIMUM");
        public final static Property Level_rank = new Property(22, int.class, "level_rank", false, "LEVEL_RANK");
        public final static Property Level_count = new Property(23, int.class, "level_count", false, "LEVEL_COUNT");
    }


    public MineDataBeanDao(DaoConfig config) {
        super(config);
    }
    
    public MineDataBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"MINE_DATA_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY UNIQUE ," + // 0: id
                "\"NAME\" TEXT," + // 1: name
                "\"LEVEL\" INTEGER NOT NULL ," + // 2: level
                "\"LEVEL_NAME\" TEXT," + // 3: level_name
                "\"AREA\" TEXT," + // 4: area
                "\"HIGHEST\" INTEGER NOT NULL ," + // 5: highest
                "\"LADDER_DAY\" INTEGER NOT NULL ," + // 6: ladder_day
                "\"LEVEL_TOTAL\" INTEGER NOT NULL ," + // 7: level_total
                "\"LEVEL_CURRENT\" INTEGER NOT NULL ," + // 8: level_current
                "\"REAL_LEVEL\" INTEGER NOT NULL ," + // 9: real_level
                "\"KNOWLEDGE\" INTEGER NOT NULL ," + // 10: knowledge
                "\"INFLUENCE\" INTEGER NOT NULL ," + // 11: influence
                "\"MONEY\" INTEGER NOT NULL ," + // 12: money
                "\"DAY\" INTEGER NOT NULL ," + // 13: day
                "\"QUALITY\" INTEGER NOT NULL ," + // 14: quality
                "\"RESULT\" INTEGER NOT NULL ," + // 15: result
                "\"SYSTEM_COUNT\" INTEGER NOT NULL ," + // 16: system_count
                "\"KNOWLEDGE_TOTAL\" INTEGER NOT NULL ," + // 17: knowledge_total
                "\"PEOPLE_COUNT\" INTEGER NOT NULL ," + // 18: people_count
                "\"SPECIALITY_TOTAL\" INTEGER NOT NULL ," + // 19: speciality_total
                "\"LEVEL_HIGHEST\" INTEGER NOT NULL ," + // 20: level_highest
                "\"LEVEL_MINIMUM\" INTEGER NOT NULL ," + // 21: level_minimum
                "\"LEVEL_RANK\" INTEGER NOT NULL ," + // 22: level_rank
                "\"LEVEL_COUNT\" INTEGER NOT NULL );"); // 23: level_count
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"MINE_DATA_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, MineDataBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
        stmt.bindLong(3, entity.getLevel());
 
        String level_name = entity.getLevel_name();
        if (level_name != null) {
            stmt.bindString(4, level_name);
        }
 
        String area = entity.getArea();
        if (area != null) {
            stmt.bindString(5, area);
        }
        stmt.bindLong(6, entity.getHighest());
        stmt.bindLong(7, entity.getLadder_day());
        stmt.bindLong(8, entity.getLevel_total());
        stmt.bindLong(9, entity.getLevel_current());
        stmt.bindLong(10, entity.getReal_level());
        stmt.bindLong(11, entity.getKnowledge());
        stmt.bindLong(12, entity.getInfluence());
        stmt.bindLong(13, entity.getMoney());
        stmt.bindLong(14, entity.getDay());
        stmt.bindLong(15, entity.getQuality());
        stmt.bindLong(16, entity.getResult());
        stmt.bindLong(17, entity.getSystem_count());
        stmt.bindLong(18, entity.getKnowledge_total());
        stmt.bindLong(19, entity.getPeople_count());
        stmt.bindLong(20, entity.getSpeciality_total());
        stmt.bindLong(21, entity.getLevel_highest());
        stmt.bindLong(22, entity.getLevel_minimum());
        stmt.bindLong(23, entity.getLevel_rank());
        stmt.bindLong(24, entity.getLevel_count());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, MineDataBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
        stmt.bindLong(3, entity.getLevel());
 
        String level_name = entity.getLevel_name();
        if (level_name != null) {
            stmt.bindString(4, level_name);
        }
 
        String area = entity.getArea();
        if (area != null) {
            stmt.bindString(5, area);
        }
        stmt.bindLong(6, entity.getHighest());
        stmt.bindLong(7, entity.getLadder_day());
        stmt.bindLong(8, entity.getLevel_total());
        stmt.bindLong(9, entity.getLevel_current());
        stmt.bindLong(10, entity.getReal_level());
        stmt.bindLong(11, entity.getKnowledge());
        stmt.bindLong(12, entity.getInfluence());
        stmt.bindLong(13, entity.getMoney());
        stmt.bindLong(14, entity.getDay());
        stmt.bindLong(15, entity.getQuality());
        stmt.bindLong(16, entity.getResult());
        stmt.bindLong(17, entity.getSystem_count());
        stmt.bindLong(18, entity.getKnowledge_total());
        stmt.bindLong(19, entity.getPeople_count());
        stmt.bindLong(20, entity.getSpeciality_total());
        stmt.bindLong(21, entity.getLevel_highest());
        stmt.bindLong(22, entity.getLevel_minimum());
        stmt.bindLong(23, entity.getLevel_rank());
        stmt.bindLong(24, entity.getLevel_count());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public MineDataBean readEntity(Cursor cursor, int offset) {
        MineDataBean entity = new MineDataBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.getInt(offset + 2), // level
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // level_name
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // area
            cursor.getInt(offset + 5), // highest
            cursor.getInt(offset + 6), // ladder_day
            cursor.getInt(offset + 7), // level_total
            cursor.getInt(offset + 8), // level_current
            cursor.getInt(offset + 9), // real_level
            cursor.getInt(offset + 10), // knowledge
            cursor.getInt(offset + 11), // influence
            cursor.getInt(offset + 12), // money
            cursor.getInt(offset + 13), // day
            cursor.getInt(offset + 14), // quality
            cursor.getInt(offset + 15), // result
            cursor.getInt(offset + 16), // system_count
            cursor.getInt(offset + 17), // knowledge_total
            cursor.getInt(offset + 18), // people_count
            cursor.getInt(offset + 19), // speciality_total
            cursor.getInt(offset + 20), // level_highest
            cursor.getInt(offset + 21), // level_minimum
            cursor.getInt(offset + 22), // level_rank
            cursor.getInt(offset + 23) // level_count
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, MineDataBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setLevel(cursor.getInt(offset + 2));
        entity.setLevel_name(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setArea(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setHighest(cursor.getInt(offset + 5));
        entity.setLadder_day(cursor.getInt(offset + 6));
        entity.setLevel_total(cursor.getInt(offset + 7));
        entity.setLevel_current(cursor.getInt(offset + 8));
        entity.setReal_level(cursor.getInt(offset + 9));
        entity.setKnowledge(cursor.getInt(offset + 10));
        entity.setInfluence(cursor.getInt(offset + 11));
        entity.setMoney(cursor.getInt(offset + 12));
        entity.setDay(cursor.getInt(offset + 13));
        entity.setQuality(cursor.getInt(offset + 14));
        entity.setResult(cursor.getInt(offset + 15));
        entity.setSystem_count(cursor.getInt(offset + 16));
        entity.setKnowledge_total(cursor.getInt(offset + 17));
        entity.setPeople_count(cursor.getInt(offset + 18));
        entity.setSpeciality_total(cursor.getInt(offset + 19));
        entity.setLevel_highest(cursor.getInt(offset + 20));
        entity.setLevel_minimum(cursor.getInt(offset + 21));
        entity.setLevel_rank(cursor.getInt(offset + 22));
        entity.setLevel_count(cursor.getInt(offset + 23));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(MineDataBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(MineDataBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(MineDataBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
