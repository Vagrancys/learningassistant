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

import com.vargancys.learningassistant.bean.ladder.LadderDifficultyCommentBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "LADDER_DIFFICULTY_COMMENT_BEAN".
*/
public class LadderDifficultyCommentBeanDao extends AbstractDao<LadderDifficultyCommentBean, Long> {

    public static final String TABLENAME = "LADDER_DIFFICULTY_COMMENT_BEAN";

    /**
     * Properties of entity LadderDifficultyCommentBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property DataId = new Property(1, long.class, "dataId", false, "DATA_ID");
        public final static Property Author = new Property(2, int.class, "author", false, "AUTHOR");
        public final static Property Author_title = new Property(3, String.class, "author_title", false, "AUTHOR_TITLE");
        public final static Property Comment = new Property(4, String.class, "comment", false, "COMMENT");
        public final static Property Time = new Property(5, String.class, "time", false, "TIME");
        public final static Property Type = new Property(6, int.class, "type", false, "TYPE");
        public final static Property Level = new Property(7, String.class, "level", false, "LEVEL");
        public final static Property Floor = new Property(8, int.class, "floor", false, "FLOOR");
    }

    private Query<LadderDifficultyCommentBean> ladderDifficultyDataBean_MBeanQuery;

    public LadderDifficultyCommentBeanDao(DaoConfig config) {
        super(config);
    }
    
    public LadderDifficultyCommentBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"LADDER_DIFFICULTY_COMMENT_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY UNIQUE ," + // 0: id
                "\"DATA_ID\" INTEGER NOT NULL ," + // 1: dataId
                "\"AUTHOR\" INTEGER NOT NULL ," + // 2: author
                "\"AUTHOR_TITLE\" TEXT," + // 3: author_title
                "\"COMMENT\" TEXT," + // 4: comment
                "\"TIME\" TEXT," + // 5: time
                "\"TYPE\" INTEGER NOT NULL ," + // 6: type
                "\"LEVEL\" TEXT," + // 7: level
                "\"FLOOR\" INTEGER NOT NULL );"); // 8: floor
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"LADDER_DIFFICULTY_COMMENT_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, LadderDifficultyCommentBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getDataId());
        stmt.bindLong(3, entity.getAuthor());
 
        String author_title = entity.getAuthor_title();
        if (author_title != null) {
            stmt.bindString(4, author_title);
        }
 
        String comment = entity.getComment();
        if (comment != null) {
            stmt.bindString(5, comment);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(6, time);
        }
        stmt.bindLong(7, entity.getType());
 
        String level = entity.getLevel();
        if (level != null) {
            stmt.bindString(8, level);
        }
        stmt.bindLong(9, entity.getFloor());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, LadderDifficultyCommentBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getDataId());
        stmt.bindLong(3, entity.getAuthor());
 
        String author_title = entity.getAuthor_title();
        if (author_title != null) {
            stmt.bindString(4, author_title);
        }
 
        String comment = entity.getComment();
        if (comment != null) {
            stmt.bindString(5, comment);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(6, time);
        }
        stmt.bindLong(7, entity.getType());
 
        String level = entity.getLevel();
        if (level != null) {
            stmt.bindString(8, level);
        }
        stmt.bindLong(9, entity.getFloor());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public LadderDifficultyCommentBean readEntity(Cursor cursor, int offset) {
        LadderDifficultyCommentBean entity = new LadderDifficultyCommentBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getLong(offset + 1), // dataId
            cursor.getInt(offset + 2), // author
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // author_title
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // comment
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // time
            cursor.getInt(offset + 6), // type
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // level
            cursor.getInt(offset + 8) // floor
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, LadderDifficultyCommentBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setDataId(cursor.getLong(offset + 1));
        entity.setAuthor(cursor.getInt(offset + 2));
        entity.setAuthor_title(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setComment(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setTime(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setType(cursor.getInt(offset + 6));
        entity.setLevel(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setFloor(cursor.getInt(offset + 8));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(LadderDifficultyCommentBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(LadderDifficultyCommentBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(LadderDifficultyCommentBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "mBean" to-many relationship of LadderDifficultyDataBean. */
    public List<LadderDifficultyCommentBean> _queryLadderDifficultyDataBean_MBean(long dataId) {
        synchronized (this) {
            if (ladderDifficultyDataBean_MBeanQuery == null) {
                QueryBuilder<LadderDifficultyCommentBean> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.DataId.eq(null));
                ladderDifficultyDataBean_MBeanQuery = queryBuilder.build();
            }
        }
        Query<LadderDifficultyCommentBean> query = ladderDifficultyDataBean_MBeanQuery.forCurrentThread();
        query.setParameter(0, dataId);
        return query.list();
    }

}
