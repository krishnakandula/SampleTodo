package com.canvas.krish.sampletodo.data.source.local;

import android.database.Cursor;
import android.database.CursorWrapper;

/**
 * Created by Krishna Chaitanya Kandula on 4/2/2017.
 */

public class TodoCursorWrapper extends CursorWrapper {
    public TodoCursorWrapper(Cursor cursor) {
        super(cursor);
    }

//    public Todo getTodo(){
//        String uuid = getString(getColumnIndex(TodoTable.Cols.UUID));
//    }
}
