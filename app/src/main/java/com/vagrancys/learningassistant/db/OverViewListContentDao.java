package com.vagrancys.learningassistant.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.vargancys.learningassistant.bean.overview.OverViewListContent;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "OVER_VIEW_LIST_CONTENT".
*/
public class OverViewListContentDao extends AbstractDao<OverViewListContent, Long> {

    public static final String TABLENAME = "OVER_VIEW_LIST_CONTENT";

    /**
     * Properties of entity OverViewListContent.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Title = new Property(1, String.class, "title", false, "TITLE");
        public final static Property AuthorId = new Property(2, long.class, "authorId", false, "AUTHOR_ID");
        public final static Property Author = new Property(3, String.class, "author", false, "AUTHOR");
        public final static Property Recommend = new Property(4, boolean.class, "recommend", false, "RECOMMEND");
        public final static Property Level = new Property(5, int.class, "level", false, "LEVEL");
        public final static Property Number = new Property(6, int.class, "number", false, "NUMBER");
        public final static Property Grade = new Property(7, int.class, "grade", false, "GRADE");
        public final static Property Summary = new Property(8, String.class, "summary", false, "SUMMARY");
        public final static Property Time = new Property(9, String.class, "time", false, "TIME");
        public final static Property People = new Property(10, int.class, "people", false, "PEOPLE");
        public final static Property Layer = new Property(11, int.class, "layer", false, "LAYER");
    }

    private DaoSession daoSession;


    public OverViewListContentDao(DaoConfig config) {
        super(config);
    }
    
    public OverViewListContentDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"OVER_VIEW_LIST_CONTENT\" (" + //
                "\"_id\" INTEGER PRIMARY KEY UNIQUE ," + // 0: id
                "\"TITLE\" TEXT," + // 1: title
                "\"AUTHOR_ID\" INTEGER NOT NULL ," + // 2: authorId
                "\"AUTHOR\" TEXT," + // 3: author
                "\"RECOMMEND\" INTEGER NOT NULL ," + // 4: recommend
                "\"LEVEL\" INTEGER NOT NULL ," + // 5: level
                "\"NUMBER\" INTEGER NOT NULL ," + // 6: number
                "\"GRADE\" INTEGER NOT NULL ," + // 7: grade
                "\"SUMMARY\" TEXT," + // 8: summary
                "\"TIME\" TEXT," + // 9: time
                "\"PEOPLE\" INTEGER NOT NULL ," + // 10: people
                "\"LAYER\" INTEGER NOT NULL );"); // 11: layer
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"OVER_VIEW_LIST_CONTENT\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, OverViewListContent entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(2, title);
        }
        stmt.bindLong(3, entity.getAuthorId());
 
        String author = entity.getAuthor();
        if (author != null) {
            stmt.bindString(4, author);
        }
        stmt.bindLong(5, entity.getRecommend() ? 1L: 0L);
        stmt.bindLong(6, entity.getLevel());
        stmt.bindLong(7, entity.getNumber());
        stmt.bindLong(8, entity.getGrade());
 
        String summary = entity.getSummary();
        if (summary != null) {
            stmt.bindString(9, summary);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(10, time);
        }
        stmt.bindLong(11, entity.getPeople());
        stmt.bindLong(12, entity.getLayer());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, OverViewListContent entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(2, title);
        }
        stmt.bindLong(3, entity.getAuthorId());
 
        String author = entity.getAuthor();
        if (author != null) {
            stmt.bindString(4, author);
        }
        stmt.bindLong(5, entity.getRecommend() ? 1L: 0L);
        stmt.bindLong(6, entity.getLevel());
        stmt.bindLong(7, entity.getNumber());
        stmt.bindLong(8, entity.getGrade());
 
        String summary = entity.getSummary();
        if (summary != null) {
            stmt.bindString(9, summary);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(10, time);
        }
        stmt.bindLong(11, entity.getPeople());
        stmt.bindLong(12, entity.getLayer());
    }

    @Override
    protected final void attachEntity(OverViewListContent entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public OverViewListContent readEntity(Cursor cursor, int offset) {
        OverViewListContent entity = new OverViewListContent( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // title
            cursor.getLong(offset + 2), // authorId
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // author
            cursor.getShort(offset + 4) != 0, // recommend
            cursor.getInt(offset + 5), // level
            cursor.getInt(offset + 6), // number
            cursor.getInt(offset + 7), // grade
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // summary
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // time
            cursor.getInt(offset + 10), // people
            cursor.getInt(offset + 11) // layer
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, OverViewListContent entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setTitle(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setAuthorId(cursor.getLong(offset + 2));
        entity.setAuthor(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setRecommend(cursor.getShort(offset + 4) != 0);
        entity.setLevel(cursor.getInt(offset + 5));
        entity.setNumber(cursor.getInt(offset + 6));
        entity.setGrade(cursor.getInt(offset + 7));
        entity.setSummary(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setTime(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setPeople(cursor.getInt(offset + 10));
        entity.setLayer(cursor.getInt(offset + 11));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(OverViewListContent entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(OverViewListContent entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(OverViewListContent entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
