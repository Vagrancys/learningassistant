package com.vagrancys.learningassistant.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.vargancys.learningassistant.db.game.GameContent;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "GAME_CONTENT".
*/
public class GameContentDao extends AbstractDao<GameContent, Long> {

    public static final String TABLENAME = "GAME_CONTENT";

    /**
     * Properties of entity GameContent.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Title = new Property(1, String.class, "title", false, "TITLE");
        public final static Property Subject = new Property(2, int.class, "subject", false, "SUBJECT");
        public final static Property Error = new Property(3, int.class, "error", false, "ERROR");
        public final static Property Score = new Property(4, int.class, "score", false, "SCORE");
        public final static Property Difficulty = new Property(5, int.class, "difficulty", false, "DIFFICULTY");
        public final static Property OverviewId = new Property(6, long.class, "overviewId", false, "OVERVIEW_ID");
    }


    public GameContentDao(DaoConfig config) {
        super(config);
    }
    
    public GameContentDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"GAME_CONTENT\" (" + //
                "\"_id\" INTEGER PRIMARY KEY UNIQUE ," + // 0: id
                "\"TITLE\" TEXT," + // 1: title
                "\"SUBJECT\" INTEGER NOT NULL ," + // 2: subject
                "\"ERROR\" INTEGER NOT NULL ," + // 3: error
                "\"SCORE\" INTEGER NOT NULL ," + // 4: score
                "\"DIFFICULTY\" INTEGER NOT NULL ," + // 5: difficulty
                "\"OVERVIEW_ID\" INTEGER NOT NULL );"); // 6: overviewId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"GAME_CONTENT\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, GameContent entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(2, title);
        }
        stmt.bindLong(3, entity.getSubject());
        stmt.bindLong(4, entity.getError());
        stmt.bindLong(5, entity.getScore());
        stmt.bindLong(6, entity.getDifficulty());
        stmt.bindLong(7, entity.getOverviewId());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, GameContent entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(2, title);
        }
        stmt.bindLong(3, entity.getSubject());
        stmt.bindLong(4, entity.getError());
        stmt.bindLong(5, entity.getScore());
        stmt.bindLong(6, entity.getDifficulty());
        stmt.bindLong(7, entity.getOverviewId());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public GameContent readEntity(Cursor cursor, int offset) {
        GameContent entity = new GameContent( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // title
            cursor.getInt(offset + 2), // subject
            cursor.getInt(offset + 3), // error
            cursor.getInt(offset + 4), // score
            cursor.getInt(offset + 5), // difficulty
            cursor.getLong(offset + 6) // overviewId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, GameContent entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setTitle(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setSubject(cursor.getInt(offset + 2));
        entity.setError(cursor.getInt(offset + 3));
        entity.setScore(cursor.getInt(offset + 4));
        entity.setDifficulty(cursor.getInt(offset + 5));
        entity.setOverviewId(cursor.getLong(offset + 6));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(GameContent entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(GameContent entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(GameContent entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
