package com.canvas.krish.sampletodo.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.canvas.krish.sampletodo.data.source.local.TodoDbSchema.*;

/**
 * Created by Krishna Chaitanya Kandula on 4/1/17.
 */

public class TodoBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 4;
    private static final String DB_NAME = "todo.db";

    public TodoBaseHelper(Context context){
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createStatement = "CREATE TABLE " + TodoTable.NAME + "(" +
                                    TodoTable.Cols.UUID +   " primary key    NOT NULL, " +
                                    TodoTable.Cols.TEXT  +   " NOT NULL, " +
                                    TodoTable.Cols.COMPLETED + "  NOT NULL, " +
                                    TodoTable.Cols.CREATED_ON + "  DEFAULT CURRENT_TIMESTAMP NOT NULL, " +
                                    TodoTable.Cols.COMPLETED_ON + " )";
        db.execSQL(createStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO: 4/2/17 Change drop table to some kind of alter statement
        String dropStatement = "DROP TABLE IF EXISTS " + TodoTable.NAME;
        db.execSQL(dropStatement);
        //Recreate table
        onCreate(db);
    }
}
