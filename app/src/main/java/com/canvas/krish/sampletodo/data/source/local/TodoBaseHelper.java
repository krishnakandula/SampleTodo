package com.canvas.krish.sampletodo.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
