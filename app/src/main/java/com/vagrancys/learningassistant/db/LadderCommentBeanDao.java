package com.vagrancys.learningassistant.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.vargancys.learningassistant.db.ladder.LadderCommentBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "LADDER_COMMENT_BEAN".
*/
public class LadderCommentBeanDao extends AbstractDao<LadderCommentBean, Long> {

    public static final String TABLENAME = "LADDER_COMMENT_BEAN";

    /**
     * Properties of entity LadderCommentBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Current = new Property(1, int.class, "current", false, "CURRENT");
        public final static Property Author = new Property(2, long.class, "author", false, "AUTHOR");
        public final static Property Author_title = new Property(3, String.class, "author_title", false, "AUTHOR_TITLE");
        public final static Property Level = new Property(4, String.class, "level", false, "LEVEL");
        public final static Property Avatar = new Property(5, String.class, "avatar", false, "AVATAR");
        public final static Property Praise = new Property(6, int.class, "praise", false, "PRAISE");
        public final static Property Step = new Property(7, int.class, "step", false, "STEP");
        public final static Property Time = new Property(8, String.class, "time", false, "TIME");
        public final static Property Comment = new Property(9, String.class, "comment", false, "COMMENT");
        public final static Property Reply_count = new Property(10, int.class, "reply_count", false, "REPLY_COUNT");
        public final static Property Floor = new Property(11, int.class, "floor", false, "FLOOR");
    }


    public LadderCommentBeanDao(DaoConfig config) {
        super(config);
    }
    
    public LadderCommentBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"LADDER_COMMENT_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY UNIQUE ," + // 0: id
                "\"CURRENT\" INTEGER NOT NULL ," + // 1: current
                "\"AUTHOR\" INTEGER NOT NULL ," + // 2: author
                "\"AUTHOR_TITLE\" TEXT," + // 3: author_title
                "\"LEVEL\" TEXT," + // 4: level
                "\"AVATAR\" TEXT," + // 5: avatar
                "\"PRAISE\" INTEGER NOT NULL ," + // 6: praise
                "\"STEP\" INTEGER NOT NULL ," + // 7: step
                "\"TIME\" TEXT," + // 8: time
                "\"COMMENT\" TEXT," + // 9: comment
                "\"REPLY_COUNT\" INTEGER NOT NULL ," + // 10: reply_count
                "\"FLOOR\" INTEGER NOT NULL );"); // 11: floor
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"LADDER_COMMENT_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, LadderCommentBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getCurrent());
        stmt.bindLong(3, entity.getAuthor());
 
        String author_title = entity.getAuthor_title();
        if (author_title != null) {
            stmt.bindString(4, author_title);
        }
 
        String level = entity.getLevel();
        if (level != null) {
            stmt.bindString(5, level);
        }
 
        String avatar = entity.getAvatar();
        if (avatar != null) {
            stmt.bindString(6, avatar);
        }
        stmt.bindLong(7, entity.getPraise());
        stmt.bindLong(8, entity.getStep());
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(9, time);
        }
 
        String comment = entity.getComment();
        if (comment != null) {
            stmt.bindString(10, comment);
        }
        stmt.bindLong(11, entity.getReply_count());
        stmt.bindLong(12, entity.getFloor());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, LadderCommentBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getCurrent());
        stmt.bindLong(3, entity.getAuthor());
 
        String author_title = entity.getAuthor_title();
        if (author_title != null) {
            stmt.bindString(4, author_title);
        }
 
        String level = entity.getLevel();
        if (level != null) {
            stmt.bindString(5, level);
        }
 
        String avatar = entity.getAvatar();
        if (avatar != null) {
            stmt.bindString(6, avatar);
        }
        stmt.bindLong(7, entity.getPraise());
        stmt.bindLong(8, entity.getStep());
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(9, time);
        }
 
        String comment = entity.getComment();
        if (comment != null) {
            stmt.bindString(10, comment);
        }
        stmt.bindLong(11, entity.getReply_count());
        stmt.bindLong(12, entity.getFloor());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public LadderCommentBean readEntity(Cursor cursor, int offset) {
        LadderCommentBean entity = new LadderCommentBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getInt(offset + 1), // current
            cursor.getLong(offset + 2), // author
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // author_title
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // level
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // avatar
            cursor.getInt(offset + 6), // praise
            cursor.getInt(offset + 7), // step
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // time
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // comment
            cursor.getInt(offset + 10), // reply_count
            cursor.getInt(offset + 11) // floor
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, LadderCommentBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setCurrent(cursor.getInt(offset + 1));
        entity.setAuthor(cursor.getLong(offset + 2));
        entity.setAuthor_title(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setLevel(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setAvatar(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setPraise(cursor.getInt(offset + 6));
        entity.setStep(cursor.getInt(offset + 7));
        entity.setTime(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setComment(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setReply_count(cursor.getInt(offset + 10));
        entity.setFloor(cursor.getInt(offset + 11));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(LadderCommentBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(LadderCommentBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(LadderCommentBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
