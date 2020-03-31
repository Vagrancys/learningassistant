package com.vagrancys.learningassistant.db;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.SqlUtils;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.vargancys.learningassistant.db.home.HomeKnowContent;
import com.vargancys.learningassistant.db.home.HomeKnowData;

import com.vargancys.learningassistant.db.home.HomeKnowItem;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "HOME_KNOW_ITEM".
*/
public class HomeKnowItemDao extends AbstractDao<HomeKnowItem, Long> {

    public static final String TABLENAME = "HOME_KNOW_ITEM";

    /**
     * Properties of entity HomeKnowItem.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property ContentId = new Property(1, long.class, "contentId", false, "CONTENT_ID");
        public final static Property DataId = new Property(2, long.class, "dataId", false, "DATA_ID");
        public final static Property Official = new Property(3, boolean.class, "official", false, "OFFICIAL");
        public final static Property Activity = new Property(4, String.class, "activity", false, "ACTIVITY");
        public final static Property Have = new Property(5, boolean.class, "have", false, "HAVE");
        public final static Property Title = new Property(6, String.class, "title", false, "TITLE");
        public final static Property Level = new Property(7, int.class, "level", false, "LEVEL");
        public final static Property Summary = new Property(8, String.class, "summary", false, "SUMMARY");
        public final static Property Progress = new Property(9, int.class, "progress", false, "PROGRESS");
        public final static Property Count = new Property(10, int.class, "count", false, "COUNT");
        public final static Property Max = new Property(11, int.class, "max", false, "MAX");
        public final static Property MasterLevel = new Property(12, int.class, "masterLevel", false, "MASTER_LEVEL");
        public final static Property StudyTitle = new Property(13, String.class, "studyTitle", false, "STUDY_TITLE");
        public final static Property CreateClass = new Property(14, boolean.class, "createClass", false, "CREATE_CLASS");
        public final static Property Language = new Property(15, int.class, "language", false, "LANGUAGE");
    }

    private DaoSession daoSession;


    public HomeKnowItemDao(DaoConfig config) {
        super(config);
    }
    
    public HomeKnowItemDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"HOME_KNOW_ITEM\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE ," + // 0: id
                "\"CONTENT_ID\" INTEGER NOT NULL ," + // 1: contentId
                "\"DATA_ID\" INTEGER NOT NULL ," + // 2: dataId
                "\"OFFICIAL\" INTEGER NOT NULL ," + // 3: official
                "\"ACTIVITY\" TEXT," + // 4: activity
                "\"HAVE\" INTEGER NOT NULL ," + // 5: have
                "\"TITLE\" TEXT," + // 6: title
                "\"LEVEL\" INTEGER NOT NULL ," + // 7: level
                "\"SUMMARY\" TEXT," + // 8: summary
                "\"PROGRESS\" INTEGER NOT NULL ," + // 9: progress
                "\"COUNT\" INTEGER NOT NULL ," + // 10: count
                "\"MAX\" INTEGER NOT NULL ," + // 11: max
                "\"MASTER_LEVEL\" INTEGER NOT NULL ," + // 12: masterLevel
                "\"STUDY_TITLE\" TEXT," + // 13: studyTitle
                "\"CREATE_CLASS\" INTEGER NOT NULL ," + // 14: createClass
                "\"LANGUAGE\" INTEGER NOT NULL );"); // 15: language
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"HOME_KNOW_ITEM\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, HomeKnowItem entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getContentId());
        stmt.bindLong(3, entity.getDataId());
        stmt.bindLong(4, entity.getOfficial() ? 1L: 0L);
 
        String activity = entity.getActivity();
        if (activity != null) {
            stmt.bindString(5, activity);
        }
        stmt.bindLong(6, entity.getHave() ? 1L: 0L);
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(7, title);
        }
        stmt.bindLong(8, entity.getLevel());
 
        String summary = entity.getSummary();
        if (summary != null) {
            stmt.bindString(9, summary);
        }
        stmt.bindLong(10, entity.getProgress());
        stmt.bindLong(11, entity.getCount());
        stmt.bindLong(12, entity.getMax());
        stmt.bindLong(13, entity.getMasterLevel());
 
        String studyTitle = entity.getStudyTitle();
        if (studyTitle != null) {
            stmt.bindString(14, studyTitle);
        }
        stmt.bindLong(15, entity.getCreateClass() ? 1L: 0L);
        stmt.bindLong(16, entity.getLanguage());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, HomeKnowItem entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getContentId());
        stmt.bindLong(3, entity.getDataId());
        stmt.bindLong(4, entity.getOfficial() ? 1L: 0L);
 
        String activity = entity.getActivity();
        if (activity != null) {
            stmt.bindString(5, activity);
        }
        stmt.bindLong(6, entity.getHave() ? 1L: 0L);
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(7, title);
        }
        stmt.bindLong(8, entity.getLevel());
 
        String summary = entity.getSummary();
        if (summary != null) {
            stmt.bindString(9, summary);
        }
        stmt.bindLong(10, entity.getProgress());
        stmt.bindLong(11, entity.getCount());
        stmt.bindLong(12, entity.getMax());
        stmt.bindLong(13, entity.getMasterLevel());
 
        String studyTitle = entity.getStudyTitle();
        if (studyTitle != null) {
            stmt.bindString(14, studyTitle);
        }
        stmt.bindLong(15, entity.getCreateClass() ? 1L: 0L);
        stmt.bindLong(16, entity.getLanguage());
    }

    @Override
    protected final void attachEntity(HomeKnowItem entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public HomeKnowItem readEntity(Cursor cursor, int offset) {
        HomeKnowItem entity = new HomeKnowItem( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getLong(offset + 1), // contentId
            cursor.getLong(offset + 2), // dataId
            cursor.getShort(offset + 3) != 0, // official
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // activity
            cursor.getShort(offset + 5) != 0, // have
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // title
            cursor.getInt(offset + 7), // level
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // summary
            cursor.getInt(offset + 9), // progress
            cursor.getInt(offset + 10), // count
            cursor.getInt(offset + 11), // max
            cursor.getInt(offset + 12), // masterLevel
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // studyTitle
            cursor.getShort(offset + 14) != 0, // createClass
            cursor.getInt(offset + 15) // language
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, HomeKnowItem entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setContentId(cursor.getLong(offset + 1));
        entity.setDataId(cursor.getLong(offset + 2));
        entity.setOfficial(cursor.getShort(offset + 3) != 0);
        entity.setActivity(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setHave(cursor.getShort(offset + 5) != 0);
        entity.setTitle(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setLevel(cursor.getInt(offset + 7));
        entity.setSummary(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setProgress(cursor.getInt(offset + 9));
        entity.setCount(cursor.getInt(offset + 10));
        entity.setMax(cursor.getInt(offset + 11));
        entity.setMasterLevel(cursor.getInt(offset + 12));
        entity.setStudyTitle(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setCreateClass(cursor.getShort(offset + 14) != 0);
        entity.setLanguage(cursor.getInt(offset + 15));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(HomeKnowItem entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(HomeKnowItem entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(HomeKnowItem entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getHomeKnowContentDao().getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T1", daoSession.getHomeKnowDataDao().getAllColumns());
            builder.append(" FROM HOME_KNOW_ITEM T");
            builder.append(" LEFT JOIN HOME_KNOW_CONTENT T0 ON T.\"CONTENT_ID\"=T0.\"_id\"");
            builder.append(" LEFT JOIN HOME_KNOW_DATA T1 ON T.\"DATA_ID\"=T1.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected HomeKnowItem loadCurrentDeep(Cursor cursor, boolean lock) {
        HomeKnowItem entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        HomeKnowContent homeKnowContent = loadCurrentOther(daoSession.getHomeKnowContentDao(), cursor, offset);
         if(homeKnowContent != null) {
            entity.setHomeKnowContent(homeKnowContent);
        }
        offset += daoSession.getHomeKnowContentDao().getAllColumns().length;

        HomeKnowData homeKnowData = loadCurrentOther(daoSession.getHomeKnowDataDao(), cursor, offset);
         if(homeKnowData != null) {
            entity.setHomeKnowData(homeKnowData);
        }

        return entity;    
    }

    public HomeKnowItem loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<HomeKnowItem> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<HomeKnowItem> list = new ArrayList<HomeKnowItem>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<HomeKnowItem> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<HomeKnowItem> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
