package com.canvas.krish.sampletodo.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.canvas.krish.sampletodo.data.source.local.TodoDbSchema.*;

/**
 * Created by krishnakandula on 4/1/17.
 */

public class TodoBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DB_NAME = "todo.db";

    public TodoBaseHelper(Context context){
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createStatement = "CREATE TABLE " + TodoTable.NAME + "(" +
                                    TodoTable.Cols.UUID +   "INTEGER    primary key    NOT NULL, " +
                                    TodoTable.Cols.TEXT  +   "TEXT      NOT NULL, " +
                                    TodoTable.Cols.COMPLETED + "INTEGER, " +
                                    TodoTable.Cols.CREATED_ON + "DATETIME   NOT NULL, " +
                                    TodoTable.Cols.COMPLETED_ON + "DATETIME)";
        db.execSQL(createStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
