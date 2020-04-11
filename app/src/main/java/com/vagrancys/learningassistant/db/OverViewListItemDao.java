package com.vagrancys.learningassistant.db;

import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import com.vargancys.learningassistant.db.overview.OverViewListItem;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "OVER_VIEW_LIST_ITEM".
*/
public class OverViewListItemDao extends AbstractDao<OverViewListItem, Long> {

    public static final String TABLENAME = "OVER_VIEW_LIST_ITEM";

    /**
     * Properties of entity OverViewListItem.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property ContentId = new Property(1, long.class, "contentId", false, "CONTENT_ID");
        public final static Property SortId = new Property(2, long.class, "sortId", false, "SORT_ID");
        public final static Property Create = new Property(3, long.class, "create", false, "CREATE");
        public final static Property ParentId = new Property(4, long.class, "parentId", false, "PARENT_ID");
        public final static Property Title = new Property(5, String.class, "title", false, "TITLE");
        public final static Property MasterLevel = new Property(6, int.class, "masterLevel", false, "MASTER_LEVEL");
        public final static Property Level = new Property(7, int.class, "level", false, "LEVEL");
        public final static Property Study = new Property(8, boolean.class, "study", false, "STUDY");
        public final static Property Score = new Property(9, int.class, "score", false, "SCORE");
        public final static Property Time = new Property(10, int.class, "time", false, "TIME");
    }

    private Query<OverViewListItem> overViewListContent_OverViewListItemQuery;

    public OverViewListItemDao(DaoConfig config) {
        super(config);
    }
    
    public OverViewListItemDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"OVER_VIEW_LIST_ITEM\" (" + //
                "\"_id\" INTEGER PRIMARY KEY UNIQUE ," + // 0: id
                "\"CONTENT_ID\" INTEGER NOT NULL ," + // 1: contentId
                "\"SORT_ID\" INTEGER NOT NULL ," + // 2: sortId
                "\"CREATE\" INTEGER NOT NULL ," + // 3: create
                "\"PARENT_ID\" INTEGER NOT NULL ," + // 4: parentId
                "\"TITLE\" TEXT," + // 5: title
                "\"MASTER_LEVEL\" INTEGER NOT NULL ," + // 6: masterLevel
                "\"LEVEL\" INTEGER NOT NULL ," + // 7: level
                "\"STUDY\" INTEGER NOT NULL ," + // 8: study
                "\"SCORE\" INTEGER NOT NULL ," + // 9: score
                "\"TIME\" INTEGER NOT NULL );"); // 10: time
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"OVER_VIEW_LIST_ITEM\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, OverViewListItem entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getContentId());
        stmt.bindLong(3, entity.getSortId());
        stmt.bindLong(4, entity.getCreate());
        stmt.bindLong(5, entity.getParentId());
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(6, title);
        }
        stmt.bindLong(7, entity.getMasterLevel());
        stmt.bindLong(8, entity.getLevel());
        stmt.bindLong(9, entity.getStudy() ? 1L: 0L);
        stmt.bindLong(10, entity.getScore());
        stmt.bindLong(11, entity.getTime());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, OverViewListItem entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getContentId());
        stmt.bindLong(3, entity.getSortId());
        stmt.bindLong(4, entity.getCreate());
        stmt.bindLong(5, entity.getParentId());
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(6, title);
        }
        stmt.bindLong(7, entity.getMasterLevel());
        stmt.bindLong(8, entity.getLevel());
        stmt.bindLong(9, entity.getStudy() ? 1L: 0L);
        stmt.bindLong(10, entity.getScore());
        stmt.bindLong(11, entity.getTime());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public OverViewListItem readEntity(Cursor cursor, int offset) {
        OverViewListItem entity = new OverViewListItem( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getLong(offset + 1), // contentId
            cursor.getLong(offset + 2), // sortId
            cursor.getLong(offset + 3), // create
            cursor.getLong(offset + 4), // parentId
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // title
            cursor.getInt(offset + 6), // masterLevel
            cursor.getInt(offset + 7), // level
            cursor.getShort(offset + 8) != 0, // study
            cursor.getInt(offset + 9), // score
            cursor.getInt(offset + 10) // time
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, OverViewListItem entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setContentId(cursor.getLong(offset + 1));
        entity.setSortId(cursor.getLong(offset + 2));
        entity.setCreate(cursor.getLong(offset + 3));
        entity.setParentId(cursor.getLong(offset + 4));
        entity.setTitle(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setMasterLevel(cursor.getInt(offset + 6));
        entity.setLevel(cursor.getInt(offset + 7));
        entity.setStudy(cursor.getShort(offset + 8) != 0);
        entity.setScore(cursor.getInt(offset + 9));
        entity.setTime(cursor.getInt(offset + 10));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(OverViewListItem entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(OverViewListItem entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(OverViewListItem entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "overViewListItem" to-many relationship of OverViewListContent. */
    public List<OverViewListItem> _queryOverViewListContent_OverViewListItem(long contentId) {
        synchronized (this) {
            if (overViewListContent_OverViewListItemQuery == null) {
                QueryBuilder<OverViewListItem> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.ContentId.eq(null));
                overViewListContent_OverViewListItemQuery = queryBuilder.build();
            }
        }
        Query<OverViewListItem> query = overViewListContent_OverViewListItemQuery.forCurrentThread();
        query.setParameter(0, contentId);
        return query.list();
    }

}
